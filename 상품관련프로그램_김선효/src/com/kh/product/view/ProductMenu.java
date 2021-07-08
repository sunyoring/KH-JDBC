package com.kh.product.view;

import java.util.List;
import java.util.Scanner;

import com.kh.product.controller.ProductController;
import com.kh.product.model.vo.Product;
import com.kh.product.model.vo.ProductIO;

public class ProductMenu {

	private static Scanner sc = new Scanner(System.in);
	private ProductController pController = new ProductController();

	public void mainMenu() {

		int choice;

		do {
			System.out.println("\n*************상품관리프로그램*************");
			System.out.println("1. 전체 조회"); // SELECT
			System.out.println("2. 상품 추가"); // INSERT
			System.out.println("3. 상품 수정"); // UPDATE
			System.out.println("4. 상품 삭제"); // DLEETE
			System.out.println("5. 상품 검색");// SELECT P_NAME
			System.out.println("6. 상품 입출고 메뉴");
			System.out.println("9. 프로그램 끝내기");
			System.out.println("번호선택 : ");

			choice = sc.nextInt();
			switch (choice) {
			case 1:
				pController.selectAll();
				break;
			case 2:
				pController.insertProduct(inputProduct());

				break;
			case 3:
				pController.updateProduct(updateProduct());
				
				break;
			case 4:
				pController.deleteProduct(inputProductID());
				break;
			case 5:
		
				pController.selectName(inputProductName());

				break;
			case 6:
				
				ioMenu();
				
				break;
		
			case 9:
				System.out.println("정말로 끝내시겠습니까 ? (y/n)");
				if ('y' == sc.next().toLowerCase().charAt(0)) {
					pController.exitProgram();
					return;
				}
				break;
			default:
				// 잘못입력했다 라고 띄워줌 break 안함
				System.out.println("번호를 잘못 입력하셨습니다.");
			}

		} while (true);
	}




	private void ioMenu() {
		
		System.out.println("======입출고 메뉴======");
		System.out.println("1. 전체 입출고 내역 조회");
		System.out.println("2. 입고 내역 조회");
		System.out.println("3. 출고 내역 조회");
		System.out.println("4. 상품 입고");
		System.out.println("5. 상품 출고");
		System.out.println("9. 메인메뉴로 돌아가기");
		System.out.print("번호 선택 : ");
		
		switch(sc.nextInt()) {
		case 1 :
			pController.selectAllIO();
			break;
		case 2 :
			pController.selectIn();
			break;
		case 3:
			pController.selectOut();

			break;
		case 4 :
//			pController.productIn();
			break;
		case 5 :
//			pController.productOut();
			break;
		case 9 :
			return;
			
		}
		
	}




	private Product updateProduct() {
	
		Product p = new Product();

		System.out.println("변경할 상품정보를 입력하세요 >> ");
		System.out.print("상품아이디 : ");
		p.setProductId(sc.next());
		System.out.print("상품명 : ");
		p.setProductName(sc.next());
		System.out.print("가격 : ");
		p.setProductPrice(sc.nextInt());
		sc.nextLine();
		System.out.print("운영체제 : ");
		p.setpDescription(sc.nextLine());
		
		return p;
	}




	private String inputProductName2() {
		System.out.println("조회할 회원 이름 입력 : ");
		return sc.next();
	}




	private Product inputProduct() {
		Product p = new Product();
		System.out.println("새로운 상품정보를 입력하세요 >> ");
		System.out.print("상품아이디 : ");
		p.setProductId(sc.next());
		System.out.print("상품명 : ");
		p.setProductName(sc.next());
		System.out.print("가격 : ");
		p.setProductPrice(sc.nextInt());
		sc.nextLine();
		System.out.print("운영체제 : ");
		p.setpDescription(sc.nextLine());

		return p;
	}

	private String inputProductID() {
		System.out.println("아이디 입력 : ");
		
		return sc.next();
	}
	
	private String inputProductName() {
		System.out.println("상품 이름 입력 : ");
		return sc.next();
	}

	public void displayProductList(List<Product> list) {
		System.out.println("\n 조회 된 전체 상품 정보는 다음과 같습니다.");
		System.out.println("\n상품아이디\t상품명\t가격\t운영체제\t재고");
		System.out.println("-----------------------------------------------");	
		
		for(Product p : list) {
			System.out.println(p);
		}
		
	}
	
	public void displayProductIOList(List<ProductIO> list) {
		System.out.println("\n 조회 된 전체 입출고 정보는 다음과 같습니다.");
		System.out.println("\n상품번호\t상품아이디\t날짜\t수량\t구분");
		System.out.println("-------------------------------------");
		
		for(ProductIO mIO : list) {
			System.out.println(mIO);
		}
	}	

	public void displayError(String message) {
		System.out.println("서비스 요청 처리 실패  : " + message);
		
	}

	public void displayProduct(Product p) {
		System.out.println("\n 조회 된 전체 상품 정보는 다음과 같습니다.");
		System.out.println("\n상품아이디\t상품명\t가격\t운영체제\t재고");
		System.out.println("-----------------------------------------------");	
		System.out.println(p.toString());
	}

	public void displaySucces(String message) {
		System.out.println("서비스 요청 결과 : " + message);
	}




	public void displayNoData() {

		System.out.println("조회 된 상품 데이터가 없습니다.");
	}

}
