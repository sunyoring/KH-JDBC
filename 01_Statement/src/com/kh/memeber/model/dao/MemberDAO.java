package com.kh.memeber.model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.kh.memeber.model.vo.Member;

/* 1.Connection 객체연결하기 
 * 2.Statement 객체 생성하기 
 * 3.ResultSet 객체 생성하기 
 * 4.Sql작성하기 
 * 5.ResultSet  결과담기 
 * 6.list 에 객체 하나씩 담기 
 * 7.close 하기 (자원반납 - 생성의 역순)
 */

public class MemberDAO {

	public ArrayList<Member> selectAll() {
		ArrayList<Member> list = null;

		Connection conn = null; // sql패키지의 Connection
		Statement stmt = null; // sql 패키지의 Statement
		ResultSet rset = null; // Select 결과를 담아 올 객체

		String sql = "UPDATE MEMBEER SET USERID =  * FROM MEMBER"; // 실행이 자동으로 되기때문에 쿼리 뒤에 세미콜론은 붙이지 않는다.

		// 1. jdbc driver 등록
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("드라이버 등록"); // 성공 시 확인용

			// 2. 등록된 클래스를 이용해 DB 연결
			// 드라이버타입@ip주소:포트번호:db이름(SID)
			// orcl:사용자정의설치 , thin : 자동으로 설치 //ip주소 -> localhost 로 변경해도됨

			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "student", "student");

			System.out.println("conn = " + conn); // 성공하면 connection 값, 실패하면 null값이 출력

			// 3. 쿼리문을 실행할 statement 객체 생성
			stmt = conn.createStatement();

			// 4. 쿼리문 전송, 실행결과를 ResultSet으로 받기

			rset = stmt.executeQuery(sql);

			// 5. 받을 결과값을 객체에 옮겨서 저장하기

			list = new ArrayList<Member>();

			while (rset.next()) {

				Member m = new Member();
				m.setUserId(rset.getString("USERID"));
				m.setPassword(rset.getString("PASSWORD"));
				m.setUserName(rset.getString("USERNAME"));
				m.setGender(rset.getString("GENDER")); // sql에서는 char이지만 자바에서 String으로 받아주면 된다.
				m.setAge(rset.getInt("AGE"));
				m.setEmail(rset.getString("EMAIL"));
				m.setPhone(rset.getString("PHONE"));
				m.setAddress(rset.getString("ADDRESS"));
				m.setHobby(rset.getString("HOBBY"));
				m.setEnrollDate(rset.getDate("ENROLLDATE"));

				list.add(m); // 반복문을 통해 한행씩 받아와서 리스트에 객체로 추가해준다.

			}

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally { // 자원을 역순으로 반납해준다. (꼭!)

			try {
				rset.close();
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		return list; // list를 넘겨준다.
	}
	
	public void updateMember(String memberId,String coulumn,String contents) {
		Member m = new Member();

		
		Connection conn = null; // sql패키지의 Connection
		Statement stmt = null; // sql 패키지의 Statement

		String sql = "UPDATE MEMBER SET " +coulumn +" = '"+contents+"'"+" WHERE USERID =" +"'"+memberId+"'"; // 실행이 자동으로 되기때문에 쿼리 뒤에 세미콜론은 붙이지 않는다.
		System.out.println(sql);
		// 1. jdbc driver 등록
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("드라이버 등록"); // 성공 시 확인용

			// 2. 등록된 클래스를 이용해 DB 연결
			// 드라이버타입@ip주소:포트번호:db이름(SID)
			// orcl:사용자정의설치 , thin : 자동으로 설치 //ip주소 -> localhost 로 변경해도됨

			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "student", "student");

			System.out.println("conn = " + conn); // 성공하면 connection 값, 실패하면 null값이 출력

			// 3. 쿼리문을 실행할 statement 객체 생성
			stmt = conn.createStatement();

			// 4. 쿼리문 전송, 실행결과를 ResultSet으로 받기

			stmt.executeQuery(sql);

			// 5. 받을 결과값을 객체에 옮겨서 저장하기


			

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally { // 자원을 역순으로 반납해준다. (꼭!)

			try {
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		
	}
	

	public Member selectOne(String memberId) {
		Member m = null;
		Connection conn = null;
		Statement stmt = null;
		ResultSet rset = null;

		String sql = "SELECT * FROM MEMBER WHERE USERID" + "= '" + memberId + "'";

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("드라이버 등록"); // 성공 시 확인용

			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "student", "student");

			System.out.println("conn = " + conn); // 성공하면 connection 값, 실패하면 null값이 출력

			// 3. 쿼리문을 실행할 statement 객체 생성
			stmt = conn.createStatement();

			// 4. 쿼리문 전송, 실행결과를 ResultSet으로 받기

			rset = stmt.executeQuery(sql);

			if (rset.next()) {

				m = new Member();
				m.setUserId(rset.getString("USERID"));
				m.setPassword(rset.getString("PASSWORD"));
				m.setUserName(rset.getString("USERNAME"));
				m.setGender(rset.getString("GENDER")); // sql에서는 char이지만 자바에서 String으로 받아주면 된다.
				m.setAge(rset.getInt("AGE"));
				m.setEmail(rset.getString("EMAIL"));
				m.setPhone(rset.getString("PHONE"));
				m.setAddress(rset.getString("ADDRESS"));
				m.setHobby(rset.getString("HOBBY"));
				m.setEnrollDate(rset.getDate("ENROLLDATE"));
			}

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally { // 자원을 역순으로 반납해준다. (꼭!)

			try {
				rset.close();
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		return m;
	}

	public int insertMember(Member m) {
		int result = 0;
		Connection conn = null;
		Statement stmt = null;
		
		String sql = "INSERT INTO MEMBER VALUES("
				+ "'"+ m.getUserId() + "' , "
				+ "'"+ m.getPassword() + "' , "
				+ "'"+ m.getUserName() + "' , "
				+ "'"+ m.getGender() + "' , "
				+ m.getAge() + " , "
				+"'" + m.getEmail() + "' ,"
				+"'" + m.getPhone() + "' ,"
				+"'" + m.getAddress() + "' ,"
				+"'" + m.getHobby() + "' ,"
				+"SYSDATE )";
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("드라이버 등록"); // 성공 시 확인용

			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "student", "student");

			System.out.println("conn = " + conn); // 성공하면 connection 값, 실패하면 null값이 출력

			// 3. 쿼리문을 실행할 statement 객체 생성
			stmt = conn.createStatement();


			conn.setAutoCommit(false); // 자동 커밋 방지
			result = stmt.executeUpdate(sql); //처리한 행의 갯수가 리턴된다.
			
			if(result > 0) conn.commit(); // 커밋 적용
			else conn.rollback(); // 되돌리기


		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally { // 자원을 역순으로 반납해준다. (꼭!)

			try {
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		
		
		return result;
	}

	public Member selectTwo(String memberName) {
		Member m = null;
		Connection conn = null;
		Statement stmt = null;
		ResultSet rset = null;

		String sql = "SELECT * FROM MEMBER WHERE USERNAME" + "= '" + memberName + "'";

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("드라이버 등록"); // 성공 시 확인용

			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "student", "student");

			System.out.println("conn = " + conn); // 성공하면 connection 값, 실패하면 null값이 출력

			// 3. 쿼리문을 실행할 statement 객체 생성
			stmt = conn.createStatement();

			// 4. 쿼리문 전송, 실행결과를 ResultSet으로 받기

			rset = stmt.executeQuery(sql);

			if (rset.next()) {

				m = new Member();
				m.setUserId(rset.getString("USERID"));
				m.setPassword(rset.getString("PASSWORD"));
				m.setUserName(rset.getString("USERNAME"));
				m.setGender(rset.getString("GENDER")); // sql에서는 char이지만 자바에서 String으로 받아주면 된다.
				m.setAge(rset.getInt("AGE"));
				m.setEmail(rset.getString("EMAIL"));
				m.setPhone(rset.getString("PHONE"));
				m.setAddress(rset.getString("ADDRESS"));
				m.setHobby(rset.getString("HOBBY"));
				m.setEnrollDate(rset.getDate("ENROLLDATE"));
			}

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally { // 자원을 역순으로 반납해준다. (꼭!)

			try {
				rset.close();
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		return m;
	}

	public void deleteMemeber(String memberId) {
		Member m = null;
		Connection conn = null;
		Statement stmt = null;

		String sql = "DELETE FROM MEMBER WHERE USERID" + "= '" + memberId + "'";

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("드라이버 등록"); // 성공 시 확인용

			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "student", "student");

			System.out.println("conn = " + conn); // 성공하면 connection 값, 실패하면 null값이 출력

			// 3. 쿼리문을 실행할 statement 객체 생성
			stmt = conn.createStatement();

			// 4. 쿼리문 전송, 실행결과를 ResultSet으로 받기

			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally { // 자원을 역순으로 반납해준다. (꼭!)

			try {
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}		
	}

}
