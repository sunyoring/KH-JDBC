package com.kh.member.model.dao;

import static com.kh.common.JDBCTemplate.close;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.kh.member.model.vo.Member;


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

	// 나중에 짤땐 싱글턴패턴(static)으로 하자
	private Properties prop = null;
	
	public MemberDAO() {
		prop = new Properties();
		try {
			prop.load(new FileReader("resources/query.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public ArrayList<Member> selectAll(Connection conn) {
		ArrayList<Member> list = null;

		Statement stmt = null;
		ResultSet rset = null;

//		String sql = "SELECT * FROM MEMBER"; // 뒤에 세미콜론 안붙여도 됨
		String sql = prop.getProperty("selectAll");

		// 1. jdbc driver 등록
		try {

			// 3. 쿼리문을 실행할 statement 객체 생성
			stmt = conn.createStatement();

			// 4. 쿼리문 전송, 실행결과를 resultset으로 받기
			rset = stmt.executeQuery(sql);

			// 5. 받은 결과값을 객체에 옮겨서 저장하기
			list = new ArrayList<Member>();

			// 값이 있으면 true 없으면 false
			while (rset.next()) {
				Member m = new Member();

				/*
				 * USERID PASSWORD USERNAME GENDER AGE EMAIL PHONE ADDRESS HOBBY ENROLLDATE
				 */

				m.setUserId(rset.getString("USERID"));
				m.setPassword(rset.getString("PASSWORD"));
				m.setUserName(rset.getString("USERNAME"));
				m.setGender(rset.getString("GENDER")); // JDBC CHAR타입, 받아오는건 String으로 받아오면 된다고 함
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
			// 역순으로 close
			close(rset);
			close(stmt);
		}

		return list;
	}

	public Member selectOne(Connection conn, String memberId) {
		Member m = null;

		PreparedStatement pstmt = null;
		ResultSet rset = null;

//		String sql = "SELECT * FROM MEMBER WHERE USERID = ?"; // ?로 바꿈 single quotation도 없음
		String sql = prop.getProperty("selectOne");

		try {
			
			// 3. 쿼리문을 실행할 prepareStatement 객체 생성, 쿼리도 바로 넣어줌
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memberId);

			// 4. 쿼리문 전송, 실행결과를 resultset으로 받기, pstmt에선 sql 뺌
			rset = pstmt.executeQuery();

			if (rset.next()) {
				m = new Member();

				m.setUserId(rset.getString("USERID"));
				m.setPassword(rset.getString("PASSWORD"));
				m.setUserName(rset.getString("USERNAME"));
				m.setGender(rset.getString("GENDER")); // JDBC CHAR타입, 받아오는건 String으로 받아오면 된다고 함
				m.setAge(rset.getInt("AGE"));
				m.setEmail(rset.getString("EMAIL"));
				m.setPhone(rset.getString("PHONE"));
				m.setAddress(rset.getString("ADDRESS"));
				m.setHobby(rset.getString("HOBBY"));
				m.setEnrollDate(rset.getDate("ENROLLDATE"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}

		return m;
	}
	
	public List<Member> selectName(Connection conn, String memberName) {
		ArrayList<Member> list = null;

		PreparedStatement pstmt = null;
		ResultSet rset = null;

//		String sql = "SELECT * FROM MEMBER WHERE USERNAME LIKE ?";
		String sql = prop.getProperty("selectName");

		// 1. jdbc driver 등록
		try {
			
			// 3. 쿼리문을 실행할 statement 객체 생성
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, memberName);
			
			// 4. 쿼리문 전송, 실행결과를 resultset으로 받기
			rset = pstmt.executeQuery();
			
			// 5. 받은 결과값을 객체에 옮겨서 저장하기
			list = new ArrayList<Member>();

			// 값이 있으면 true 없으면 false
			while (rset.next()) {
				Member m = new Member();

				/*
				 * USERID PASSWORD USERNAME GENDER AGE EMAIL PHONE ADDRESS HOBBY ENROLLDATE
				 */

				m.setUserId(rset.getString("USERID"));
				m.setPassword(rset.getString("PASSWORD"));
				m.setUserName(rset.getString("USERNAME"));
				m.setGender(rset.getString("GENDER")); // JDBC CHAR타입, 받아오는건 String으로 받아오면 된다고 함
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
	
	public int insertMember(Connection conn, Member m) {
		int result = 0;
		
		PreparedStatement pstmt = null;

//		String sql = "INSERT INTO MEMBER VALUES"
//				+ "(?, ?, ?, ?, ?, ?, ?, ?, ?, SYSDATE)";
		String sql = prop.getProperty("insertMember");

		try {
			
			// 3. 쿼리문을 실행할 statement 객체 생성
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, m.getUserId());
			pstmt.setString(2, m.getPassword());
			pstmt.setString(3, m.getUserName());
			pstmt.setString(4, m.getGender());
			pstmt.setInt(5, m.getAge());
			pstmt.setString(6, m.getEmail());
			pstmt.setString(7, m.getPhone());
			pstmt.setString(8, m.getAddress());
			pstmt.setString(9, m.getHobby());
			pstmt.setDate(10, m.getEnrollDate());

			result = pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}

		return result;
	}

	public int updateMember(Connection conn, Member m) {
		int result = 0;

		PreparedStatement pstmt = null;

//		String sql = "UPDATE MEMBER SET " 
//				+ "PASSWORD = ?, " 
//				+ "EMAIL = ?, " 
//				+ "PHONE = ?, " 
//				+ "ADDRESS ?, " 
//				+ "HOBBY = ? "
//				+ "WHERE USERID = ?";
		String sql = prop.getProperty("updateMember");

		try {
			
			// 3. 쿼리문을 실행할 statement 객체 생성
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, m.getPassword());
			pstmt.setString(2, m.getEmail());
			pstmt.setString(3, m.getPhone());
			pstmt.setString(4, m.getAddress());
			pstmt.setString(5, m.getHobby());
			pstmt.setString(6, m.getUserId());
			
			result = pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}

		return result;
	}
	
	public int deleteMember(Connection conn, String memberId) {
		int result = 0;

		PreparedStatement pstmt = null;

//		String sql = "DELETE FROM MEMBER WHERE USERID = ?";
		String sql = prop.getProperty("deleteMember");

		try {
			
			// 3. 쿼리문을 실행할 statement 객체 생성
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memberId);

			result = pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}

		return result;
	}
	

	public ArrayList<Member> selectAllDeleteMember(Connection conn) {
		ArrayList<Member> list = null;

		Statement stmt = null;
		ResultSet rset = null;

//		String sql = "SELECT * FROM MEMBER_DEL"; // 뒤에 세미콜론 안붙여도 됨
		String sql = prop.getProperty("selectAllDeleteMember");

		// 1. jdbc driver 등록
		try {

			// 3. 쿼리문을 실행할 statement 객체 생성
			stmt = conn.createStatement();

			// 4. 쿼리문 전송, 실행결과를 resultset으로 받기
			rset = stmt.executeQuery(sql);

			// 5. 받은 결과값을 객체에 옮겨서 저장하기
			list = new ArrayList<Member>();

			// 값이 있으면 true 없으면 false
			while (rset.next()) {
				Member m = new Member();

				/*
				 * USERID PASSWORD USERNAME GENDER AGE EMAIL PHONE ADDRESS HOBBY ENROLLDATE
				 */

				m.setUserId(rset.getString("USERID"));
				m.setPassword(rset.getString("PASSWORD"));
				m.setUserName(rset.getString("USERNAME"));
				m.setGender(rset.getString("GENDER")); // JDBC CHAR타입, 받아오는건 String으로 받아오면 된다고 함
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
			// 역순으로 close
			close(rset);
			close(stmt);
		}

		return list;
	}
	
}
