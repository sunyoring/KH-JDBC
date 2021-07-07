package com.kh.member.service;

import static com.kh.common.JDBCTemplate.*;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import com.kh.member.model.dao.MemberDAO;
import com.kh.member.model.vo.Member;

/* Service 클래스에서 메소드 작성 방법
 * 1) Controller로 부터 인자를 전달받음
 * 2) Connection 객체 생성
 * 3) Dao 객체 생성
 * 4) Dao로 생성한 Connection 객체와 인자를 전달
 * 5) Dao 수행 결과를 가지고 비즈니스 로직 및 트랜잭션 관리를 함 */

public class MemberService {

	public void exitProgram() {
		close(getConnection());
	}

	public ArrayList<Member> selectAll() {
		Connection conn = getConnection();

		ArrayList<Member> list = new MemberDAO().selectAll(conn);

		return list;
	}

	public Member selectOne(String memberId) {
		Connection conn = getConnection();

		Member m = new MemberDAO().selectOne(conn, memberId);

		return m;
	}

	public List<Member> selectName(String memberName) {
		Connection conn = getConnection();

		List<Member> list = new MemberDAO().selectName(conn, memberName);

		return list;
	}

	public int insertMember(Member m) {
		Connection conn = getConnection();

		int result = new MemberDAO().insertMember(conn, m); // 성공하면 1 반환

		if (result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}

		return result;
	}

	public int updateMember(Member m) {
		Connection conn = getConnection();

		int result = new MemberDAO().updateMember(conn, m);

		if (result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}

		return result;
	}

	public int deleteMember(String memberId) {
		Connection conn = getConnection();

		int result = new MemberDAO().deleteMember(conn, memberId); // 성공하면 1 반환

		if (result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}

		return result;
	}

	public List<Member> selectAllDeleteMember() {
		Connection conn = getConnection();
		
		ArrayList<Member> list = new MemberDAO().selectAllDeleteMember(conn);
		
		return list;
	}

}
