package com.kh.memeber.service;

import static com.kh.common.JDBCTemplate.close;
import static com.kh.common.JDBCTemplate.commit;
import static com.kh.common.JDBCTemplate.getConnection;
import static com.kh.common.JDBCTemplate.rollback;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import com.kh.memeber.model.dao.MemberDAO;
import com.kh.memeber.model.exeception.MemberException;
import com.kh.memeber.model.vo.Member;

/* Service 클래스에서 메소드 작성 방법
 * 1) Controller로 부터 인자를 전달받음
 * 2) Connection 객체 생성
 * 3) Dao 객체 생성
 * 4) Dao로 생성한 Connection 객체와 인자를 전달
 * 5) Dao 수행 결과를 가지고 비즈니스 로직 및 트랜잭션 관리를 함 */

public class MemberService {

	public ArrayList<Member> selectAll() throws MemberException {
		Connection conn = getConnection();
		ArrayList<Member> list = new MemberDAO().selectAll(conn);
		return list;
	}


	public Member selectOne(String memberId) throws MemberException {
		Connection conn = getConnection();
		Member m = new MemberDAO().selectOne(conn, memberId);
		return m ;
	}

	public int insertMember(Member m) throws MemberException {
		Connection conn = getConnection();
		int result = new MemberDAO().insertMember(conn, m);
		if (result > 0) { // 성공 된 갯수를 반환
			commit(conn);
		} else {
			rollback(conn);
		}
		return result;
	}

	public List<Member> selectName(String memberName) throws MemberException {
		Connection conn = getConnection();
		List<Member> list = new MemberDAO().selectName(conn, memberName);

		return list;
	}

	public int deleteMember(String userId) throws MemberException {
		Connection conn = getConnection();

		int result = new MemberDAO().deleteMember(conn, userId);

		if (result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		
		
		return result;
	}

	public int updateMember(Member m) throws MemberException {
		Connection conn = getConnection();

		int result = new MemberDAO().updateMember(conn,m);
		
		if (result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		
		return result;
	}

	public List<Member> selectAllDeleteMember() throws MemberException {
		Connection conn = getConnection();
		ArrayList<Member> list = new MemberDAO().SelectAllDeleteMember(conn);
		return list;
	}

	public void exitProgram() {
		close(getConnection());

	}
}
