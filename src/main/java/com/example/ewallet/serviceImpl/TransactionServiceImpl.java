package com.example.ewallet.serviceImpl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.ewallet.dataaccessrepository.TransactionRepository;
import com.example.ewallet.datatransferobject.TransactionDTO;
import com.example.ewallet.datatransferobject.mapper.TransactionMapper;
import com.example.ewallet.models.Transaction;
import com.example.ewallet.service.TransactionService;
import com.example.ewallet.service.UserAccountService;
import com.google.common.collect.Lists;

@Service
@Transactional
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;
    @Autowired
    private UserAccountService accountService;

    /**
     * retrieve transactions by their transaction reference this operations is
     * used to validate if a transaction ref has been used previously
     */
    @Override
    public Transaction transactionByRef(Long txnRef) throws Exception {
        return transactionRepository.getTransactionByRef(txnRef).
                orElseThrow(() -> new Exception(String.format("transaction with ref '%d' doesnt exist", txnRef)));
    }

    /**
     * this operations registers a transaction on behalf of user
     * debit/credits, it also validates if a user has insufficient funds if a
     * debit is to be made
     */
    @Override
    @Transactional(rollbackFor=RuntimeException.class)
    public Transaction createTransaction(Transaction transaction) throws Exception {

        BigDecimal balance = transactionRepository.getBalance(transaction.getUserAccount().getId());

        if (balance.add(transaction.getAmount()).compareTo(BigDecimal.ZERO) == 1
                | balance.add(transaction.getAmount()).compareTo(BigDecimal.ZERO) == 0) {
            return transactionRepository.save(transaction);
        }

        throw new Exception(String.format("user's balance is %.2f and cannot perform a transaction of %.2f ",
                balance.doubleValue(), transaction.getAmount().doubleValue()));

    }

    @Override
    public Transaction update(Transaction transaction, Long id) throws Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<Transaction> getList() {
        return Lists.newArrayList(transactionRepository.findAll());
    }

    @Override
    public List<Transaction> transactionsByUserAccountID(Long accountId) {
        return transactionRepository.getTransactionsForUser(accountId);
    }

    @Override
    public BigDecimal balanceByUserAccountID(Long accountId) {
        return transactionRepository.getBalance(accountId);
    }

    @Override
    public List<Transaction> transactions() {
        return Lists.newArrayList(transactionRepository.findAll());
    }

    @Override
    public Transaction save(Transaction transaction) throws Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    @Transactional(rollbackFor=RuntimeException.class)
    public List<Transaction> transfer(TransactionDTO walletDTO,Long toUserAccountId,Long fromUserAccountId) {
    	List<Transaction> transactions= new ArrayList<>();
    	
    	Transaction sourceUserTransaction;
		Transaction destinationUserTransaction;
		try {

			walletDTO.setUserAccountId(fromUserAccountId);
			walletDTO.setAmount(walletDTO.getAmount().negate());
			sourceUserTransaction = createTransaction(TransactionMapper.dtoToDO(walletDTO));
			transactions.add(sourceUserTransaction);
			
			walletDTO.setUserAccountId(toUserAccountId);
			walletDTO.setAmount(walletDTO.getAmount().negate());
			destinationUserTransaction = createTransaction(TransactionMapper.dtoToDO(walletDTO));
			transactions.add(destinationUserTransaction);
			
		} catch (Exception ex) {
			Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
			transactions= Collections.emptyList();
		} 
		return transactions;
    }

}