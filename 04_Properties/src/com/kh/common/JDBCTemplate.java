package com.kh.common;

import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

public class JDBCTemplate {
	/*
	 * SingleTon Pattern: 클래스에 대한 객체가 프로그램 구동내내 한개만 작성되어 사용 하게 함. static 으로 작성
	 */

	// 기존 DB 처리 작업(select, insert, update, delete 등) 시 마다
	// 새로운 Connection 객체를 생성하여 DB와 연결하여 진행함
	// 이렇게 되면 한 사용자가 여러 개의 커넥션 객체를 만들어 DB와 접속을 하게 됨.
	// -> 여러 객체 생성으로 인한 메모리 낭비 + DB에는 접속 가능한 커넥션 객체 수 가 제한되어 있음.

	// 이를 해결하기 위하여 사용자당 커넥션 객체를 하나만 만들 수 있게 하여
	// 메모리 낭비 및 DB 연결 객체수 오버를 방지하게하는 싱글톤 패턴을 적용!

	// * 싱글톤(Single Tone) 패턴
	// 프로그램 구동 시 메모리에 객체를 딱 한개만 기록되게 하는 디자인 패턴
	// 모든 필드와 메소드를 static으로 선언하여
	// static 영역에 공용으로 사용할 수 있는 단 하나의 객체를 만듦.

	// 한 개의 공용 커넥션 객체를 저정할 참조변수 선언.
	// 외부에서 직접 접근을 할 수 없게 private 선언

	private static Connection conn = null;

	public static Connection getConnection() {
		/* 이전 프로 젝트에서는 
		 * JDBC 드라이버 로드 , db 연결을 위한 정보 (url, id, password )를 직접 코드에 작성 (정적코딩)
		 * --> 추후 DB 자체 변경또는 연결정보가 변경되는 경우 코드를 직접 수정하고 다시 컴파일 해야함
		 * --> 유지 보수에 불편 
		 * 
		 * 별도의 Properties 파일을 만들어 프로그램실행시 동적으로 DB 연결 정보를 불러올수있도록 진행
		 * 
		 */
		
		if (conn == null) {
			try {
				// 외부에서 정보를 읽어와서 저장할 Properties 객체 선언 및 생성
				Properties prop = new Properties();
				
				prop.load(new FileReader("resources/driver.properties"));
				
				Class.forName(prop.getProperty("driver"));
				conn = DriverManager.getConnection(
						prop.getProperty("url"), 
						prop.getProperty("user"), 
						prop.getProperty("password"));
				
				conn.setAutoCommit(false);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return conn;
	}

	public static void commit(Connection conn) {
		try {
			if (conn != null && !conn.isClosed()) {
				conn.commit();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void rollback(Connection conn) {
		try {
			if (conn != null && !conn.isClosed()) {
				conn.rollback();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void close(Connection conn) {
		try {
			if (conn != null && !conn.isClosed()) {
				conn.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void close(Statement stmt) { 
		// PreparedStatement는 Statement 하위 클래스라 따로 안해줘도 됨
		try {
			if (stmt != null && !stmt.isClosed()) {
				stmt.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void close(ResultSet rset) {
		try {
			if (rset != null && !rset.isClosed()) {
				rset.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
