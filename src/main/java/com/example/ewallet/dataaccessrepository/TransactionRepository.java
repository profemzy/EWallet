package com.example.ewallet.dataaccessrepository;

import org.springframework.data.repository.CrudRepository;

import com.example.ewallet.models.Transaction;

public interface TransactionRepository extends CrudRepository<Transaction, Long>{

}
