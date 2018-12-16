package com.example.ewallet.dataaccessrepository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.example.ewallet.models.Transaction;

public interface TransactionRepository extends CrudRepository<Transaction, Long>{

	@Query(nativeQuery = true, value = "select * from transaction where transaction_reference = ?")
    Optional<Transaction> getTransactionByRef(Long txnRef);

    @Query(nativeQuery = true, value = "select ifnull(sum(amount),0.00) from transaction where user_account_id = ?")
    BigDecimal getBalance(Long accountId);

    @Query(nativeQuery = true, value = "select * from transaction where user_account_id = ?")
    List<Transaction> getTransactionsForUser(Long accountId);
}
