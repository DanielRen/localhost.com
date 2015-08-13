package com.dr.services;

import com.dr.domains.TransactionResult;

public interface IAccountService {
	public TransactionResult login(String userName,String password);
	public TransactionResult createAccount(String userName,String password);
	

}
