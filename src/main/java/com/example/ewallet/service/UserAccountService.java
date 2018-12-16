package com.example.ewallet.service;

import java.util.List;

import com.example.ewallet.models.UserAccount;

public interface UserAccountService {
	UserAccount save(UserAccount t) throws Exception;
	UserAccount update(UserAccount t,Long id) throws Exception ;
	List<UserAccount> getList();
	UserAccount userAccountByPK(Long accountId) throws Exception;
}