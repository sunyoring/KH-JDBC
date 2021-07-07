package com.kh.memeber.model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

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

		
		//전체 조회 같은 경우 자동커밋을 하지 앟아도 된다.
		Connection conn = null; // sql패키지의 Connection
		Statement stmt = null; // sql 패키지의 Statement
		ResultSet rset = null; // Select 결과를 담아 올 객체

		String sql = "SELECT * FROM MEMBER"; // 실행이 자동으로 되기때문에 쿼리 뒤에 세미콜론은 붙이지 않는다.

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
		System.out.println(memberId + coulumn + contents);
		Member m = new Member();

		
		Connection conn = null; // sql패키지의 Connection
		PreparedStatement pstmt = null; // sql 패키지의 Statement

		String sql = "UPDATE MEMBER SET  ?" + "= ?" + " WHERE USERID = + "+"?"; // 실행이 자동으로 되기때문에 쿼리 뒤에 세미콜론은 붙이지 않는다.
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
			pstmt = conn.prepareStatement(sql);
			pstmt.setNString(1, coulumn);
			pstmt.setNString(2, contents);
			pstmt.setNString(3, memberId);

			// 4. 쿼리문 전송, 실행결과를 ResultSet으로 받기

			pstmt.executeQuery(sql);

			// 5. 받을 결과값을 객체에 옮겨서 저장하기


			

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally { // 자원을 역순으로 반납해준다. (꼭!)

			try {
				pstmt.close();
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
		//Statement stmt = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		String sql = "SELECT * FROM MEMBER WHERE USERID =  ?";  //매개변수 자리를 ? 로 처리한다.

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("드라이버 등록"); // 성공 시 확인용

			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "student", "student");

			System.out.println("conn = " + conn); // 성공하면 connection 값, 실패하면 null값이 출력

			// 3. 쿼리문을 실행할 statement 객체 생성
			pstmt = conn.prepareStatement(sql); //sql문을 먼저 담아 쿼리를 실행할 객체를 생성한다.
			pstmt.setNString(1, memberId); //쿼리의 물음표 자리에 memebeId가들어간다.
			
			// 4. 쿼리문 전송, 실행결과를 ResultSet으로 받기

			/*rset = stmt.executeQuery(sql);*/
			rset = pstmt.executeQuery(); //위에서 쿼리가 들어가 생성되었기 때문에 여기서는 생략한다.

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
				pstmt.close();
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
		PreparedStatement pstmt = null;
		
		String sql = "INSERT INTO MEMBER VALUES(?,?,?,?,?,?,?,?,?,sysdate)";
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("드라이버 등록"); // 성공 시 확인용

			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "student", "student");

			System.out.println("conn = " + conn); // 성공하면 connection 값, 실패하면 null값이 출력

			// 3. 쿼리문을 실행할 statement 객체 생성
			pstmt = conn.prepareStatement(sql);
			conn.setAutoCommit(false); // 자동 커밋 방지
			pstmt.setNString(1,m.getUserId());
			pstmt.setNString(2,m.getPassword());
			pstmt.setNString(3,m.getUserName());
			pstmt.setNString(4,m.getGender());
			pstmt.setInt(5,m.getAge());
			pstmt.setNString(6,m.getEmail());
			pstmt.setNString(7,m.getPhone());
			pstmt.setNString(8,m.getEmail());
			pstmt.setNString(9,m.getHobby());
			
			result = pstmt.executeUpdate(); //처리한 행의 갯수가 리턴된다.
			
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
				pstmt.close();
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
		int result = 0;
		Member m = null;
		Connection conn = null;
		PreparedStatement pstmt = null;

		String sql = "DELETE FROM MEMBER WHERE USERID = ?";

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("드라이버 등록"); // 성공 시 확인용

			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "student", "student");

			System.out.println("conn = " + conn); // 성공하면 connection 값, 실패하면 null값이 출력

			// 3. 쿼리문을 실행할 statement 객체 생성
			pstmt = conn.prepareStatement(sql);
			pstmt.setNString(1, memberId);

			conn.setAutoCommit(false);
			result = pstmt.executeUpdate();
			
			if(result > 0) {
				
			}else {
				
			}
			
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally { // 자원을 역순으로 반납해준다. (꼭!)

			try {
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}		
	}

	public List<Member> selectName(String memberName) {
		//이름 매개변수를 받는다.
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<Member> list = new ArrayList<>();
		String sql = "SELECT * FROM MEMBER WHERE USERNAME LIKE ?";

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("드라이버 등록"); // 성공 시 확인용

			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "student", "student");

			System.out.println("conn = " + conn); // 성공하면 connection 값, 실패하면 null값이 출력

			// 3. 쿼리문을 실행할 statement 객체 생성
			pstmt = conn.prepareStatement(sql);
			pstmt.setNString(1, "%"+memberName+"%");
			
			// 4. 쿼리문 전송, 실행결과를 ResultSet으로 받기

			rset = pstmt.executeQuery();

			
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
				
				list.add(m);
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
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		
		return list;
	}

	public int updateMmeber2(Member m) {

		int result = 0;
		
		Connection conn = null; // sql패키지의 Connection
		PreparedStatement pstmt = null; // sql 패키지의 Statement
		//멤버 객체에서 getter로 가져온다.
		String sql = "UPDATE MEMBER SET PASSWORD = ? , EMAIL = ? , PHONE = ? , ADDRESS = ? WHRE USERID = ?"; // 실행이 자동으로 되기때문에 쿼리 뒤에 세미콜론은 붙이지 않는다.
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
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,m.getPassword());
			pstmt.setString(2,m.getPassword());
			pstmt.setString(3,m.getPassword());
			pstmt.setString(4,m.getPassword());
			pstmt.setString(5,m.getPassword());
			pstmt.setString(6,m.getPassword());
			conn.setAutoCommit(false);
			// 4. 쿼리문 전송, 실행결과를 ResultSet으로 받기
			result = pstmt.executeUpdate(sql);

			if(result > 0) conn.commit();
			else conn.rollback();
			

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally { // 자원을 역순으로 반납해준다. (꼭!)

			try {
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		return 0;
	}

	public int deleteMember2(String userId) {
		int result = 0;
		
		Connection conn = null; // sql패키지의 Connection
		Statement stmt = null; // sql 패키지의 Statement
		//멤버 객체에서 getter로 가져온다.
		String sql = "DELET FROM MEMEBER WHERE USERID = '" + userId + "'"; // 실행이 자동으로 되기때문에 쿼리 뒤에 세미콜론은 붙이지 않는다.
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
			conn.setAutoCommit(false);
			// 4. 쿼리문 전송, 실행결과를 ResultSet으로 받기
			result = stmt.executeUpdate(sql);

			if(result > 0) conn.commit();
			else conn.rollback();
			

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

		return 0;
	}

}
