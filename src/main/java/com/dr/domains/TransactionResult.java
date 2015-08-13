package com.dr.domains;

public class TransactionResult {
	String result;
	String message;
	
	public TransactionResult withResult(String result){
		this.result = result;
		return this;
	}
	public String getResult(){
		return this.result;
	}
	
	public TransactionResult withMessage(String message){
		this.message = message;
		return this;
	}
	public String getMessage(){
		return this.message;
	}
	
	@Override
	public String toString(){
		StringBuilder result = new StringBuilder();
		result.append("TransactionResult:\n{\n");
		result.append("\t result:").append(this.result).append("\n");
		result.append("\t message:").append(this.message).append("\n");
		result.append("}\n");
		return result.toString();
	}
	
}
