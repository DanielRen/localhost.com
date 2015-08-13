package com.dr.services;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.dr.domains.TransactionResult;
import com.dr.domains.UserInfo;

@Component
public class AccountServiceImpl implements IAccountService {

	public TransactionResult login(String userName, String password) {
		TransactionResult result = new TransactionResult();
		UserInfo newUser = new UserInfo();
		UserInfo user = newUser.findUser(userName);
		if(user != null){
			if(user.getPassword().equals(password)){
				result.withResult("success");
				result.withMessage("Login success");
			}else{
				result.withResult("fail");
				result.withMessage("Password is not correct.");
			}
		}else{
			result.withResult("fail");
			result.withMessage("UserName is not found");
		}
		return result;
	}

	public TransactionResult createAccount(String userName, String password) {
		TransactionResult result = new TransactionResult();
		UserInfo newUser = new UserInfo();
		newUser.setUserName(userName);
		newUser.setPassword(password);
		newUser.save(newUser);
		result.withResult("success");
		return result;
	}

}
