package com.kh.product.model.vo;

import java.io.Serializable;
import java.sql.Date;

public class ProductIO  implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4642190346142032251L;
	private int ioNum;
	private String productid;
	private String pName;
	private Date ioDate;
	private int amount;
	private String status;

	public ProductIO() {
		// TODO Auto-generated constructor stub
	}
	
	public ProductIO(int ioNum, String productid, String pName, Date ioDate, int amount, String status) {
		super();
		this.ioNum = ioNum;
		this.productid = productid;
		this.pName = pName;
		this.ioDate = ioDate;
		this.amount = amount;
		this.status = status;
	}

	public int getIoNum() {
		return ioNum;
	}

	public void setIoNum(int ioNum) {
		this.ioNum = ioNum;
	}

	public String getProductid() {
		return productid;
	}

	public void setProductid(String productid) {
		this.productid = productid;
	}

	public String getpName() {
		return pName;
	}

	public void setpName(String pName) {
		this.pName = pName;
	}

	public Date getIoDate() {
		return ioDate;
	}

	public void setIoDate(Date ioDate) {
		this.ioDate = ioDate;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return  ioNum + ", " + productid + ", " + pName + ", " + ioDate
				+ ", " + amount + ", " + status;
	}


	
	
}
