package com.dr.domains;

public class test {

	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		UserInfo userInfo = new UserInfo();
		userInfo.setUserName("aaa");
		userInfo.setPassword("password");
		userInfo.save(userInfo);
	}

}
