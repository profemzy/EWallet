package com.example.ewallet.datatransferobject.mapper;

import java.util.List;
import java.util.stream.Collectors;

import com.example.ewallet.datatransferobject.TransactionDTO;
import com.example.ewallet.models.Transaction;

public class TransactionMapper {
	public static Transaction dtoToDO(TransactionDTO w) {
        Transaction transaction = new Transaction.TransactionBuilder()
                .setUserAccount(w.getUserAccountId())
                .setAmount(w.getAmount())
                .setId(w.getId())
                .setDetails(w.getDetails())
                .setTransactionDate(w.getTransactionDate())
                .setTransactionReference(w.getTransactionReference()).build();
        return transaction;
    }

    public static TransactionDTO doToDTO(Transaction w) {
        TransactionDTO transactionDTO = new TransactionDTO.TransactionDTOBuilder()
                .setUserAccountId(w.getUserAccount().getId())
                .setAmount(w.getAmount())
                .setId(w.getId())
                .setDetails(w.getDetails())
                .setTransactionDate(w.getTransactionDate())
                .setTransactionReference(w.getTransactionReference()).build();
        return transactionDTO;
    }

    public static List<TransactionDTO> doToDTOList(List<Transaction> txns) {
        return txns.stream()
                .map(TransactionMapper::doToDTO)
                .collect(Collectors.toList());
    }
}