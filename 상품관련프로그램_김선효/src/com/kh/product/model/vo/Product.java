package com.kh.product.model.vo;

import java.io.Serializable;
import java.sql.Date;

/* VO Value Object : 데이터 베이스 테이블 Member 의 각 컬럼값 저장용 객체 : 한행의 정보 저장 
 * DTO Data Transfer Object
 * DO Domain Object
 * Entity
 * bean 
 * 
 * 
 * VO :
 * 1. 모든 필드는 반드시 private 이여야한다.
 * 2. 기본 생성자와 매개변수 있는 생성자 필요 
 * 3. 모든 필드에대해서 getter/setter 필요
 * 4. 직렬화 처리 (네트워크상 데이터 처리를 위함 )
 */
public class Product implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 859290449853475232L; // 시리얼 번호

	private String productId;
	private String productName;
	private int productPrice;
	private String pDescription;
	private int productStock;

	public Product(String productId, String productName, int productPrice, String pDescription) {
		super();
		this.productId = productId;
		this.productName = productName;
		this.productPrice = productPrice;
		this.pDescription = pDescription;
	}

	public Product() {

	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public int getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(int productPrice) {
		this.productPrice = productPrice;
	}

	public String getpDescription() {
		return pDescription;
	}

	public void setpDescription(String pDescription) {
		this.pDescription = pDescription;
	}

	public int getProductStock() {
		return productStock;
	}

	public void setProductStock(int productStock) {
		this.productStock = productStock;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return productId + ", " + productName + ", " + productPrice
				+ ", " + pDescription + ", " + productStock;
	}


	
	
	

}
