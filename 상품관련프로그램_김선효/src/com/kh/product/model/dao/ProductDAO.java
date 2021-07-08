package com.kh.product.model.dao;

//커넥션을 가져오기 위한 임포트
import static com.kh.common.JDBCTemplate.close;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.kh.product.model.exeception.ProductException;
import com.kh.product.model.vo.Product;
import com.kh.product.model.vo.ProductIO;

/* 이전 프로젝트에서 Dao가 맡은 업무
1) JDBC드라이버 등록
2) DB 연결 Connection 객체생성
3) SQL 실행
4) 처리결과에 따른 트랜잭션처리(commit, rollback)
5) 자원 반환

이 때, 실제로 dao가 처리해야 업무는 SQL문을 DB로 전달하여 실행하고 반환값을 받아오는
3번의 역할만을 진행해야 함. 
 --> 3번 이외에 1,2,4,5 역할을 분리해야 함.
 
+ 1,2,4,5의 업무는 어디서든 공통적으로 이루어지는 공통 업무
 --> 한번에 묶어서 처리해주자
 --> 공통업무를 처리하기 위한
 com.kh.common.JDBCTemplate 클래스 생성.
*/
public class ProductDAO {

	private Properties prop = null;

	public ProductDAO() {

		try {

			prop = new Properties();
			prop.load(new FileReader("resources/query.properties"));

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public ArrayList<Product> selectAll(Connection conn) throws ProductException {
		ArrayList<Product> list = null;

		// 전체 조회 같은 경우 자동커밋을 하지 앟아도 된다.
		Statement stmt = null; // sql 패키지의 Statement
		ResultSet rset = null; // Select 결과를 담아 올 객체

		// String sql = "SELECT * FROM ProductDAO"; // 실행이 자동으로 되기때문에 쿼리 뒤에 세미콜론은 붙이지 않는다.
		String sql = prop.getProperty("selectALL");
		try {

			// 3. 쿼리문을 실행할 statement 객체 생성
			stmt = conn.createStatement();

			rset = stmt.executeQuery(sql);

			list = new ArrayList<Product>();

			while (rset.next()) {

				Product p = new Product();
				p.setProductId(rset.getString("PRODUCT_ID"));
				p.setProductName(rset.getString("P_NAME"));
				p.setProductPrice(rset.getInt("PRICE"));
				p.setpDescription(rset.getString("DESCRIPTION"));
				p.setProductStock(rset.getInt("STOCK"));
				

				list.add(p); // 반복문을 통해 한행씩 받아와서 리스트에 객체로 추가해준다.

			}

		} catch (Exception e) {
			e.printStackTrace();
			throw new ProductException("selectAll 에러 : " + e.getMessage());

		} finally { // 자원을 역순으로 반납해준다. (꼭!)

			close(rset);
			close(stmt);

		}

		return list; // list를 넘겨준다.
	}
	
	public ArrayList<ProductIO> selectAllIO(Connection conn) throws ProductException {
		ArrayList<ProductIO> list = null;

		// 전체 조회 같은 경우 자동커밋을 하지 앟아도 된다.
		Statement stmt = null; // sql 패키지의 Statement
		ResultSet rset = null; // Select 결과를 담아 올 객체

		String sql = prop.getProperty("selectALLIO");
		try {

			// 3. 쿼리문을 실행할 statement 객체 생성
			stmt = conn.createStatement();

			rset = stmt.executeQuery(sql);

			list = new ArrayList<ProductIO>();

			while (rset.next()) {

				ProductIO p = new ProductIO();
				
				p.setIoNum(rset.getInt("입출고번호"));
				p.setProductid(rset.getString("상품ID"));
				p.setpName(rset.getString("상품명"));
				p.setIoDate(rset.getDate("입출고일"));
				p.setAmount(rset.getInt("입출고수량"));
				p.setStatus(rset.getString("입출고상태"));
				

				list.add(p); // 반복문을 통해 한행씩 받아와서 리스트에 객체로 추가해준다.

			}
		

		} catch (Exception e) {
			e.printStackTrace();
			throw new ProductException("selectAllIO 에러 : " + e.getMessage());

		} finally { // 자원을 역순으로 반납해준다. (꼭!)

			close(rset);
			close(stmt);

		}

		return list; // list를 넘겨준다.		return null;
	
	}
	public ArrayList<ProductIO> selectIn(Connection conn) throws ProductException {
		ArrayList<ProductIO> list = null;
		
		// 전체 조회 같은 경우 자동커밋을 하지 앟아도 된다.
		Statement stmt = null; // sql 패키지의 Statement
		ResultSet rset = null; // Select 결과를 담아 올 객체
		
		String sql = prop.getProperty("selectIn");
		try {
			
			// 3. 쿼리문을 실행할 statement 객체 생성
			stmt = conn.createStatement();
			
			rset = stmt.executeQuery(sql);
			
			list = new ArrayList<ProductIO>();
			
			while (rset.next()) {
				
				ProductIO p = new ProductIO();
				p.setIoNum(rset.getInt("입출고번호"));
				p.setProductid(rset.getString("상품ID"));
				p.setpName(rset.getString("상품명"));
				p.setIoDate(rset.getDate("입출고일"));
				p.setAmount(rset.getInt("입출고수량"));
				p.setStatus(rset.getString("입출고상태"));
				
				list.add(p); // 반복문을 통해 한행씩 받아와서 리스트에 객체로 추가해준다.
				
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new ProductException("selectAllIO 에러 : " + e.getMessage());
			
		} finally { // 자원을 역순으로 반납해준다. (꼭!)
			
			close(rset);
			close(stmt);
			
		}
		
		return list; // list를 넘겨준다.		return null;
		
	}
	public ArrayList<ProductIO> selectOut(Connection conn) throws ProductException {
		ArrayList<ProductIO> list = null;
		
		// 전체 조회 같은 경우 자동커밋을 하지 앟아도 된다.
		Statement stmt = null; // sql 패키지의 Statement
		ResultSet rset = null; // Select 결과를 담아 올 객체
		
		String sql = prop.getProperty("selectOut");
		try {
			
			// 3. 쿼리문을 실행할 statement 객체 생성
			stmt = conn.createStatement();
			
			rset = stmt.executeQuery(sql);
			
			list = new ArrayList<ProductIO>();
			
			while (rset.next()) {
				
				ProductIO p = new ProductIO();
				p.setIoNum(rset.getInt("입출고번호"));
				p.setProductid(rset.getString("상품ID"));
				p.setpName(rset.getString("상품명"));
				p.setIoDate(rset.getDate("입출고일"));
				p.setAmount(rset.getInt("입출고수량"));
				p.setStatus(rset.getString("입출고상태"));
				
				list.add(p); // 반복문을 통해 한행씩 받아와서 리스트에 객체로 추가해준다.
				
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new ProductException("selectAllIO 에러 : " + e.getMessage());
			
		} finally { // 자원을 역순으로 반납해준다. (꼭!)
			
			close(rset);
			close(stmt);
			
		}
		
		return list; // list를 넘겨준다.		return null;
		
	}

	public Product selectName(Connection conn, String productName) throws ProductException {
		Product p = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		String sql = prop.getProperty("selectName");
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setNString(1, "%" + productName + "%"); 


			rset = pstmt.executeQuery(); // 위에서 쿼리가 들어가 생성되었기 때문에 여기서는 생략한다.

			if (rset.next()) {

				p = new Product();
				
				p.setProductId(rset.getString("PRODUCT_ID"));
				p.setProductName(rset.getString("P_NAME"));
				p.setProductPrice(rset.getInt("PRICE"));
				p.setpDescription(rset.getString("DESCRIPTION"));
				p.setProductStock(rset.getInt("STOCK"));
				
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw new ProductException("selectOne 에러 : " + e.getMessage());

		} finally { // 자원을 역순으로 반납해준다. (꼭!)

			close(rset);
			close(pstmt);

		}

		return p;
	}

	public int insertProduct(Connection conn,Product p) throws ProductException {
		int result = 0;
		PreparedStatement pstmt = null;

		// insertProduct=INSERT INTO PRODECT_STUCK VALUES (?, ?, ?, ?)
		String sql = prop.getProperty("insertProductDAO");
		try {
			
			pstmt = conn.prepareStatement(sql);
			conn.setAutoCommit(false); // 자동 커밋 방지
			pstmt.setString(1, p.getProductId());
			pstmt.setString(2, p.getProductName());
			pstmt.setInt(3, p.getProductPrice());
			pstmt.setString(4, p.getpDescription());
		

			result = pstmt.executeUpdate(); // 처리한 행의 갯수가 리턴된다.

		} catch (Exception e) {
			e.printStackTrace();
			throw new ProductException("insertProductDAO 에러 : " + e.getMessage());

		} finally { // 자원을 역순으로 반납해준다. (꼭!)

				close(pstmt);
				close(conn);


		}

		return result;
	}

	public int deleteProduct(Connection conn, String ProductId) throws ProductException {
		int result = 0;
		Product p = null;
		PreparedStatement pstmt = null;
		
		//deleteProduct=DELETE FROM MEMBER WHERE PRODUCT_ID = ?
		String sql = prop.getProperty("deleteProduct");

		try {
		


			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, ProductId);

			conn.setAutoCommit(false);
			result = pstmt.executeUpdate();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally { // 자원을 역순으로 반납해준다. (꼭!)
			close(pstmt);
			close(conn);
			}

		return result;
	}

	

	public int updateProduct(Connection conn, Product p) throws ProductException {

		int result = 0;

		PreparedStatement pstmt = null; // sql 패키지의 Statement
										// 쿼리 뒤에

		try {

			String sql = prop.getProperty("updateProduct"); // 되기때문에

			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, p.getProductName());
			pstmt.setInt(2, p.getProductPrice());
			pstmt.setString(3, p.getpDescription());
			pstmt.setString(4, p.getProductId());
			
	

			conn.setAutoCommit(false);
			// 4. 쿼리문 전송, 실행결과를 ResultSet으로 받기
			result = pstmt.executeUpdate(sql);

		} catch (Exception e) {

			e.printStackTrace();
			throw new ProductException("updateProductDAO 에러 : " + e.getMessage());
		} finally { // 자원을 역순으로 반납해준다. (꼭!)

			try {
				close(pstmt);
				close(conn);

			} catch (Exception e) {
				e.printStackTrace();
			}

		}

		return result;
	}


}
