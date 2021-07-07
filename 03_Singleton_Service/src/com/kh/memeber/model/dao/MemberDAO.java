package com.kh.memeber.model.dao;

//커넥션을 가져오기 위한 임포트
import static com.kh.common.JDBCTemplate.close;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.kh.memeber.model.vo.Member;

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
public class MemberDAO {

	public ArrayList<Member> selectAll(Connection conn) {
		ArrayList<Member> list = null;

		// 전체 조회 같은 경우 자동커밋을 하지 앟아도 된다.
		Statement stmt = null; // sql 패키지의 Statement
		ResultSet rset = null; // Select 결과를 담아 올 객체

		String sql = "SELECT * FROM MEMBER"; // 실행이 자동으로 되기때문에 쿼리 뒤에 세미콜론은 붙이지 않는다.

		try {

			System.out.println("conn = " + conn); // 성공하면 connection 값, 실패하면 null값이 출력
			// 3. 쿼리문을 실행할 statement 객체 생성
			stmt = conn.createStatement();

			rset = stmt.executeQuery(sql);

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

		} catch (Exception e) {
			e.printStackTrace();

		} finally { // 자원을 역순으로 반납해준다. (꼭!)

			close(rset);
			close(stmt);

		}

		return list; // list를 넘겨준다.
	}

	/*
	 * public void updateMember(String memberId,String coulumn,String contents) {
	 * System.out.println(memberId + coulumn + contents); Member m = new Member();
	 * 
	 * 
	 * Connection conn = null; // sql패키지의 Connection PreparedStatement pstmt = null;
	 * // sql 패키지의 Statement
	 * 
	 * String sql = "UPDATE MEMBER SET  ?" + "= ?" + " WHERE USERID = + "+"?";
	 * System.out.println(sql); try {
	 * Class.forName("oracle.jdbc.driver.OracleDriver");
	 * System.out.println("드라이버 등록"); // 성공 시 확인용
	 * 
	 * 
	 * conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe",
	 * "student", "student");
	 * 
	 * System.out.println("conn = " + conn); // 성공하면 connection 값, 실패하면 null값이 출력
	 * 
	 * pstmt.setNString(1, coulumn); pstmt.setNString(2, contents);
	 * pstmt.setNString(3, memberId);
	 * 
	 * 
	 * pstmt.executeQuery(sql);
	 * 
	 * 
	 * 
	 * 
	 * 
	 * } catch (ClassNotFoundException e) { // TODO Auto-generated catch block
	 * e.printStackTrace(); } catch (SQLException e) { // TODO Auto-generated catch
	 * block e.printStackTrace(); } finally { // 자원을 역순으로 반납해준다. (꼭!)
	 * 
	 * try { pstmt.close(); conn.close(); } catch (SQLException e) { // TODO
	 * Auto-generated catch block e.printStackTrace(); }
	 * 
	 * }
	 * 
	 * 
	 * }
	 */

	public Member selectOne(Connection conn, String memberId) {
		Member m = null;
		// Statement stmt = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		String sql = "SELECT * FROM MEMBER WHERE USERID =  ?"; // 매개변수 자리를 ? 로 처리한다.

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("드라이버 등록"); // 성공 시 확인용

			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "student", "student");

			System.out.println("conn = " + conn); // 성공하면 connection 값, 실패하면 null값이 출력

			// 3. 쿼리문을 실행할 statement 객체 생성
			pstmt = conn.prepareStatement(sql); // sql문을 먼저 담아 쿼리를 실행할 객체를 생성한다.
			pstmt.setNString(1, memberId); // 쿼리의 물음표 자리에 memebeId가들어간다.

			// 4. 쿼리문 전송, 실행결과를 ResultSet으로 받기

			/* rset = stmt.executeQuery(sql); */
			rset = pstmt.executeQuery(); // 위에서 쿼리가 들어가 생성되었기 때문에 여기서는 생략한다.

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

			close(rset);
			close(pstmt);

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
			pstmt.setNString(1, m.getUserId());
			pstmt.setNString(2, m.getPassword());
			pstmt.setNString(3, m.getUserName());
			pstmt.setNString(4, m.getGender());
			pstmt.setInt(5, m.getAge());
			pstmt.setNString(6, m.getEmail());
			pstmt.setNString(7, m.getPhone());
			pstmt.setNString(8, m.getEmail());
			pstmt.setNString(9, m.getHobby());

			result = pstmt.executeUpdate(); // 처리한 행의 갯수가 리턴된다.

			if (result > 0)
				conn.commit(); // 커밋 적용
			else
				conn.rollback(); // 되돌리기

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

	/*
	 * public Member selectTwo(String memberName) { Member m = null; Connection conn
	 * = null; Statement stmt = null; ResultSet rset = null;
	 * 
	 * String sql = "SELECT * FROM MEMBER WHERE USERNAME" + "= '" + memberName +
	 * "'";
	 * 
	 * try { Class.forName("oracle.jdbc.driver.OracleDriver");
	 * System.out.println("드라이버 등록"); // 성공 시 확인용
	 * 
	 * conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe",
	 * "student", "student");
	 * 
	 * System.out.println("conn = " + conn); // 성공하면 connection 값, 실패하면 null값이 출력
	 * 
	 * // 3. 쿼리문을 실행할 statement 객체 생성 stmt = conn.createStatement();
	 * 
	 * // 4. 쿼리문 전송, 실행결과를 ResultSet으로 받기
	 * 
	 * rset = stmt.executeQuery(sql);
	 * 
	 * if (rset.next()) {
	 * 
	 * m = new Member(); m.setUserId(rset.getString("USERID"));
	 * m.setPassword(rset.getString("PASSWORD"));
	 * m.setUserName(rset.getString("USERNAME"));
	 * m.setGender(rset.getString("GENDER")); // sql에서는 char이지만 자바에서 String으로 받아주면
	 * 된다. m.setAge(rset.getInt("AGE")); m.setEmail(rset.getString("EMAIL"));
	 * m.setPhone(rset.getString("PHONE")); m.setAddress(rset.getString("ADDRESS"));
	 * m.setHobby(rset.getString("HOBBY"));
	 * m.setEnrollDate(rset.getDate("ENROLLDATE")); }
	 * 
	 * } catch (ClassNotFoundException e) { // TODO Auto-generated catch block
	 * e.printStackTrace(); } catch (SQLException e) { // TODO Auto-generated catch
	 * block e.printStackTrace(); } finally { // 자원을 역순으로 반납해준다. (꼭!)
	 * 
	 * try { rset.close(); stmt.close(); conn.close(); } catch (SQLException e) { //
	 * TODO Auto-generated catch block e.printStackTrace(); }
	 * 
	 * }
	 * 
	 * return m; }
	 */
	public int deleteMember(Connection conn, String memberId) {
		int result = 0;
		Member m = null;
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

			if (result > 0)
				conn.commit(); // 커밋 적용
			else
				conn.rollback(); // 되돌리기

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

	public List<Member> selectName(Connection conn, String memberName) {
		// 이름 매개변수를 받는다.

		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<Member> list = new ArrayList<>();
		try {
			String sql = "SELECT * FROM MEMBER WHERE USERNAME LIKE ?";

			pstmt = conn.prepareStatement(sql);
			pstmt.setNString(1, "%" + memberName + "%");

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

		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			close(rset);
			close(pstmt);

		}

		return list;
	}

	public int updateMmeber(Connection conn,Member m) {

		int result = 0;

		PreparedStatement pstmt = null; // sql 패키지의 Statement
		// 멤버 객체에서 getter로 가져온다.
		String sql = "UPDATE MEMBER SET PASSWORD = ? , EMAIL = ? , PHONE = ? , ADDRESS = ? WHRE USERID = ?"; // 실행이 자동으로
																												// 되기때문에
																												// 쿼리 뒤에
																												// 세미콜론은
																												// 붙이지
																												// 않는다.
		try {
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, m.getPassword());
			pstmt.setString(2, m.getEmail());
			pstmt.setString(3, m.getPhone());
			pstmt.setString(4, m.getAddress());
			pstmt.setString(5, m.getUserId());
			
			conn.setAutoCommit(false);

			result = pstmt.executeUpdate(sql);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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

	public ArrayList<Member> SelectAllDeleteMember(Connection conn) {
		ArrayList<Member> list = null; 
		Statement stmt = null; // sql 패키지의 Statement
		ResultSet rset = null; // Select 결과를 담아 올 객체

		String sql = "SELECT * FROM MEMBER"; // 실행이 자동으로 되기때문에 쿼리 뒤에 세미콜론은 붙이지 않는다.

		try {

			System.out.println("conn = " + conn); // 성공하면 connection 값, 실패하면 null값이 출력
			// 3. 쿼리문을 실행할 statement 객체 생성
			stmt = conn.createStatement();

			rset = stmt.executeQuery(sql);

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

		} catch (Exception e) {
			e.printStackTrace();

		} finally { // 자원을 역순으로 반납해준다. (꼭!)

			close(rset);
			close(stmt);

		}

		return list; // list를 넘겨준다.
	}

}
