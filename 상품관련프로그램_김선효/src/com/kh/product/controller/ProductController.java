package com.kh.product.controller;

import java.util.ArrayList;
import java.util.List;

import com.kh.product.model.exeception.ProductException;
import com.kh.product.model.vo.Product;
import com.kh.product.model.vo.ProductIO;
import com.kh.product.service.ProductService;
import com.kh.product.view.ProductMenu;

public class ProductController {

	
	
	public void selectAll() {

		ProductMenu menu = new ProductMenu();
		ArrayList<Product> list;
		try {
			list = new ProductService().selectAll();

			if (!list.isEmpty()) { // null로 비교하지 않는다.
				menu.displayProductList(list);
			} else {
				menu.displayNoData();
			}
		} catch (ProductException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
			menu.displayError("상품 전체 조회 실패, 관리자에게 문의하시오.");
			System.out.println(e.getMessage());
		}

	}
	
	public void selectAllIO() {
		ProductMenu menu = new ProductMenu();
		ArrayList<ProductIO> list;
		try {
			list = new ProductService().selectAllIO();

			if (!list.isEmpty()) { // null로 비교하지 않는다.
				menu.displayProductIOList(list);
			} else {
				menu.displayNoData();
			}
		} catch (ProductException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
			menu.displayError("전체 입출고 내역 조회 실패, 관리자에게 문의하시오.");
			System.out.println(e.getMessage());
		}
	}
	
	public void selectIn() {
		ProductMenu menu = new ProductMenu();
		ArrayList<ProductIO> list;
		try {
			list = new ProductService().selectIn();

			if (!list.isEmpty()) { // null로 비교하지 않는다.
				menu.displayProductIOList(list);
			} else {
				menu.displayNoData();
			}
		} catch (ProductException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
			menu.displayError("전체 입출고 내역 조회 실패, 관리자에게 문의하시오.");
			System.out.println(e.getMessage());
		}		
	}
	
	public void selectOut() {
		ProductMenu menu = new ProductMenu();
		ArrayList<ProductIO> list;
		try {
			list = new ProductService().selectOut();

			if (!list.isEmpty()) { // null로 비교하지 않는다.
				menu.displayProductIOList(list);
			} else {
				menu.displayNoData();
			}
		} catch (ProductException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
			menu.displayError("전체 입출고 내역 조회 실패, 관리자에게 문의하시오.");
			System.out.println(e.getMessage());
		}				
	}

	public void selectName(String productName) {

		ProductMenu menu = new ProductMenu();
		Product p;
		
		try {
			p = new ProductService().selectName(productName);

			if (p != null) {
				menu.displayProduct(p);
			} else {
				menu.displayNoData();

			}
		} catch (ProductException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
			menu.displayError("상품 조회 실패, 관리자에게 문의하시오.");
			System.out.println(e.getMessage());
		}

	}

	public void insertProduct(Product p) {
		int result;
		try {
			result = new ProductService().insertProduct(p);

			if (result > 0) { // 성공 된 갯수를 반환
				new ProductMenu().displaySucces("상품 등록 성공");
			}
		} catch (ProductException e) {
			e.printStackTrace();
			new ProductMenu().displayError("상품 등록 실패, 관리자에게 문의하시오.");
			System.out.println(e.getMessage());

		}

	}



	public void updateProduct(Product p) {
		
		try {
			int result = new ProductService().updateProduct(p);

			if (result > 0) { // 성공 된 갯수를 반환
				new ProductMenu().displaySucces("상품 수정 성공");
			} 
		} catch (ProductException e) {
			e.printStackTrace();
			new ProductMenu().displayError("상품 수정 실패");
			System.out.println(e.getMessage());
		}

	}

	public void deleteProduct(String productId) {
		int result;
		try {
			result = new ProductService().deleteProduct(productId);

			if (result > 0) { // 성공 된 갯수를 반환
				new ProductMenu().displaySucces("상품 삭제 성공");
			}

		} catch (ProductException e) {
			e.printStackTrace();
			new ProductMenu().displayError("상품 삭제 실패, 관리자에게 문의하시오.");
			System.out.println(e.getMessage());
		}

	}

	public void exitProgram() {

		new ProductService().exitProgram();
	}

	public void productIn(ProductIO pIO) throws ProductException {
		int result;
		result = new ProductService().productIn(pIO);

		if (result > 0) { // 성공 된 갯수를 반환
			new ProductMenu().displaySucces("입고 등록 성공");
		}		
	}

	








}
