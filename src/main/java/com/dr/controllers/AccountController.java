package com.dr.controllers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import com.dr.domains.Student;
import com.dr.domains.TransactionResult;
import com.dr.domains.UserInfo;
import com.dr.services.IAccountService;

@Controller
public class AccountController{

	@Autowired
	IAccountService accountService;
	
	@RequestMapping(value = "/createUser", method = RequestMethod.GET)
	public String getWelcomePages(ModelMap model) {
		System.out.println("In login page");
		model.put("message", "Please input your userName and password.");
		model.put("userInfo", new UserInfo());
		return "createUser";
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView getPages() {
		ModelAndView model = new ModelAndView("index", "userInfo", new UserInfo());
		model.addObject("message", "Please sign in.");
		return model;
	}
	
	@RequestMapping(value = "/login2/{userName}/{password}", method = RequestMethod.GET)
	@ResponseBody
	public TransactionResult login2(@PathVariable String userName,@PathVariable String password) {
		TransactionResult result = accountService.login(userName, password);
		return result;
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ModelAndView login(@ModelAttribute("userInfo") UserInfo userInfo) {
		System.out.println("in login:"+userInfo.toString());
		//ModelAndView model = null;
		TransactionResult transactionResult = accountService.login(userInfo.getUserName(), userInfo.getPassword());
		System.out.println(transactionResult.toString());
		if(transactionResult != null && transactionResult.getResult().equals("success")){
			ModelAndView model = new ModelAndView("MainPage");
			return model;
		}
		ModelAndView model = new ModelAndView("index", "userInfo", new UserInfo());
		model.addObject("message", transactionResult.getMessage());
		return model;
	}
	
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public ModelAndView save(@ModelAttribute("userInfo") UserInfo userInfo) {
		ModelAndView model = new ModelAndView("createUser", "userInfo", new UserInfo());
		if(userInfo.findUser(userInfo.getUserName())==null){
			TransactionResult transactionResult = accountService.createAccount(userInfo.getUserName(), userInfo.getPassword());
			System.out.println(transactionResult.toString());
			if(transactionResult != null && transactionResult.getResult().equals("success")){
				model = new ModelAndView("MainPage");
				return model;
			}
			model.addObject("message", transactionResult.getMessage());
		}else{
			model.addObject("message", "UserName is already used.");
		}
		
		return model;
	}
}
