package com.example.ewallet.dataaccessobject;

import org.springframework.data.repository.CrudRepository;

import com.example.ewallet.models.UserAccount;

public interface UserAccountRepository extends CrudRepository<UserAccount, Long>{

}
