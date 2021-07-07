package com.kh.memeber.model.exeception;
//임의로 Exception 만들어 처리
public class MemberException extends Exception { //Exception 상속

	public MemberException() {}
	
	public MemberException(String message) {
		super(message);
	}
}
