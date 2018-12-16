package com.example.ewallet.dataaccessrepository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.example.ewallet.models.UserAccount;

public interface UserAccountRepository extends CrudRepository<UserAccount, Long>{

	Optional<UserAccount> getByUserName(String name);
}
