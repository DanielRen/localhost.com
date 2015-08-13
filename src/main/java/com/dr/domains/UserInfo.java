package com.dr.domains;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Serializable;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.Resource;

public class UserInfo{

	String userName;
	String password;
	
	public void setUserName(String userName){
		this.userName = userName;
	}
	public String getUserName(){
		return this.userName;
	}

	public void setPassword(String password){
		this.password = password;
	}
	public String getPassword(){
		return this.password;
	}
	
	public void save(UserInfo user){
		ApplicationContext appContext = new ClassPathXmlApplicationContext();
    	Resource resource =appContext.getResource("file:c:\\daniel_users.txt");
    	try{
    	  InputStream is = resource.getInputStream();
          BufferedReader br = new BufferedReader(new InputStreamReader(is));
          String line;
          StringBuilder oldUsers = new StringBuilder();
          while ((line = br.readLine()) != null) {
            	System.out.println("append:"+line);
            	oldUsers.append(line+"\n");
          }
           br.close();
           System.out.println(oldUsers.toString());
           OutputStream os = new FileOutputStream(resource.getFile());
           OutputStreamWriter osw = new OutputStreamWriter(os);
           osw.write(oldUsers.toString());
           osw.write(user.getUserName()+";"+user.getPassword()+"\n");
	   	   osw.close();
	   	   os.close();
    	}catch(IOException e){
    		e.printStackTrace();
    	}
	}
	public UserInfo findUser(String userName){
		UserInfo user = null;
		ApplicationContext appContext = new ClassPathXmlApplicationContext();
    	Resource resource =appContext.getResource("file:c:\\daniel_users.txt");
    	try{
    		InputStream is = resource.getInputStream();
        	BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String line;
            while ((line = br.readLine()) != null) {
                if(line.startsWith(userName+";")){
                	user = new UserInfo();
                	user.setUserName(userName);
                	user.setPassword(line.split(";")[1]);
                	break;
                }
          	  } 
             br.close();
    	}catch(IOException e){
    		e.printStackTrace();
    	}
    	return user;
	}
	
	@Override
	public String toString(){
		StringBuilder result = new StringBuilder();
		result.append("UserInfo:\n{\n");
		result.append("\t userName:").append(this.userName).append("\n");
		result.append("\t password:").append(this.password).append("\n");
		result.append("}\n");
		return result.toString();
	}	

}
