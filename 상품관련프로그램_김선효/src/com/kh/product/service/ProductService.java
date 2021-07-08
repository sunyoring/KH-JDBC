package com.kh.product.service;

import static com.kh.common.JDBCTemplate.close;
import static com.kh.common.JDBCTemplate.commit;
import static com.kh.common.JDBCTemplate.getConnection;
import static com.kh.common.JDBCTemplate.rollback;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import com.kh.product.model.dao.ProductDAO;
import com.kh.product.model.exeception.ProductException;
import com.kh.product.model.vo.Product;
import com.kh.product.model.vo.ProductIO;

/* Service 클래스에서 메소드 작성 방법
 * 1) Controller로 부터 인자를 전달받음
 * 2) Connection 객체 생성
 * 3) Dao 객체 생성
 * 4) Dao로 생성한 Connection 객체와 인자를 전달
 * 5) Dao 수행 결과를 가지고 비즈니스 로직 및 트랜잭션 관리를 함 */

public class ProductService {

	public ArrayList<Product> selectAll() throws ProductException {
		Connection conn = getConnection();
		ArrayList<Product> list = new ProductDAO().selectAll(conn);
		return list;
	}


	public ArrayList<ProductIO> selectAllIO() throws ProductException {
		Connection conn = getConnection();
		ArrayList<ProductIO> list = new ProductDAO().selectAllIO(conn);		
		return list;
	}
	public ArrayList<ProductIO> selectIn() throws ProductException {
		Connection conn = getConnection();
		ArrayList<ProductIO> list = new ProductDAO().selectIn(conn);		
		return list;
	}
	public ArrayList<ProductIO> selectOut() throws ProductException {
		Connection conn = getConnection();
		ArrayList<ProductIO> list = new ProductDAO().selectOut(conn);		
		return list;
	}


	public int insertProduct(Product p) throws ProductException {
		Connection conn = getConnection();
		int result = new ProductDAO().insertProduct(conn,p);
		if (result > 0) { // 성공 된 갯수를 반환
			commit(conn);
		} else {
			rollback(conn);
		}
		return result;
	}

	public Product selectName(String ProductName) throws ProductException {
		Connection conn = getConnection();
		Product p = new ProductDAO().selectName(conn, ProductName);

		return p;
	}

	public int deleteProduct(String productId) throws ProductException {
		Connection conn = getConnection();

		int result = new ProductDAO().deleteProduct(conn, productId);

		if (result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		
		
		return result;
	}

	public int updateProduct(Product p) throws ProductException {
		Connection conn = getConnection();

		int result = new ProductDAO().updateProduct(conn,p);
		
		if (result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		
		return result;
	}


	public void exitProgram() {
		close(getConnection());

	}


	public int productIn(ProductIO pIO) throws ProductException {
		Connection conn = getConnection();
		int result = new ProductDAO().productIn(conn,pIO);
		if (result > 0) { // 성공 된 갯수를 반환
			commit(conn);
		} else {
			rollback(conn);
		}
		return result;
	}


	



}
