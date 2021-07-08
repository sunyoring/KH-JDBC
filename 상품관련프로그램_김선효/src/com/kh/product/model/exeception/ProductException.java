package com.kh.product.model.exeception;
//임의로 Exception 만들어 처리
public class ProductException extends Exception { //Exception 상속

	public ProductException() {}
	
	public ProductException(String message) {
		super(message);
	}
}
