package com.example.ewallet.service;

import java.math.BigDecimal;
import java.util.List;

import com.example.ewallet.datatransferobject.TransactionDTO;
import com.example.ewallet.models.Transaction;

public interface TransactionService {
	Transaction save(Transaction t) throws Exception;
	Transaction update(Transaction t,Long id) throws Exception ;
	List<Transaction> getList();
	List<Transaction> transactionsByUserAccountID(Long accountId)  throws Exception;
	Transaction transactionByRef(Long txnRef)  throws Exception;
	BigDecimal balanceByUserAccountID(Long accountId)  throws Exception;
	List<Transaction> transactions();
	Transaction createTransaction(Transaction txn) throws Exception;
	List<Transaction> transfer(TransactionDTO walletDTO,Long toUserAccountId,Long fromUserAccountId);
}