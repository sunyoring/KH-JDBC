package com.kh.common;

import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

public class JDBCTemplate {

	private static Connection conn = null;

	public static Connection getConnection() {
		
		

		if (conn == null) { // 연결상태가 null일 때에만 처음에는 null이므로연결 됨.

			try {
				
				
				//외부에서 정보를 읽어와 저장할 Properties 객체 선언 및 생성
				Properties prop = new Properties();
				
				prop.load(new FileReader("resources/driver.properties"));
				

				Class.forName(prop.getProperty("driver"));
				conn = DriverManager.getConnection(prop.getProperty("url"),prop.getProperty("user"),prop.getProperty("password"));
				conn.setAutoCommit(false);

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return conn;

	}
	
	
	public static void commit(Connection conn) {
			try {
				
				if(conn != null && conn.isClosed())  conn.commit();				
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	public static void rollback(Connection conn) {
		try {
			
			if(conn != null && !conn.isClosed())  conn.rollback();				
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void close(Connection conn) {
		try {
			
			if(conn != null && !conn.isClosed())  conn.close();				
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	//PreparedStatement 는 Statement의 하위 클래스로 따로 처리 해주지 않아도 된다.
	public static void close(Statement stmt) { 
		try {
			
			if(stmt != null && !stmt.isClosed())  stmt.close();				
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static void close(ResultSet rset) { 
		try {
			
			if(rset != null && !rset.isClosed())  rset.close();				
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
		
		
	}
	
	

