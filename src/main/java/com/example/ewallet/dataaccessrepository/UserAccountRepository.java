package com.example.ewallet.dataaccessrepository;

import org.springframework.data.repository.CrudRepository;

import com.example.ewallet.models.UserAccount;

public interface UserAccountRepository extends CrudRepository<UserAccount, Long>{

}
