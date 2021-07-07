package com.kh.memeber.controller;

import java.util.ArrayList;
import java.util.List;

import com.kh.memeber.model.exeception.MemberException;
import com.kh.memeber.model.vo.Member;
import com.kh.memeber.service.MemberService;
import com.kh.memeber.view.MemberMenu;

public class MemberController {

	// view 와 dao(db연결)를 연결해주는 객체
	// view <-> controller <-> dao <-> db
	
	
	public void selectAll() {

		MemberMenu menu = new MemberMenu();
		ArrayList<Member> list;
		try {
			list = new MemberService().selectAll();

			if (!list.isEmpty()) { // null로 비교하지 않는다.
				menu.displayMemberList(list);
			} else {
				menu.displayNoData();
			}
		} catch (MemberException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
			menu.displayError("회원전체 조회 실패, 관리자에게 문의하시오.");
			System.out.println(e.getMessage());
		}

	}

	public void selectOne(String memberId) {

		MemberMenu menu = new MemberMenu();
		Member m;
		try {
			m = new MemberService().selectOne(memberId);

			if (m != null) {
				menu.displayMember(m);
			} else {
				menu.displayNoData();

			}
		} catch (MemberException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
			menu.displayError("회원 조회 실패, 관리자에게 문의하시오.");
			System.out.println(e.getMessage());
		}

	}

	public void insertMember(Member m) {
		int result;
		try {
			result = new MemberService().insertMember(m);

			if (result > 0) { // 성공 된 갯수를 반환
				new MemberMenu().displaySucces("회원가입성공");
			}
		} catch (MemberException e) {
			e.printStackTrace();
			new MemberMenu().displayError("회원가입 실패, 관리자에게 문의하시오.");
			System.out.println(e.getMessage());

		}

	}

	public void selectName(String memberName) {
		// 중복이름을 잡기 위해 리스트로 받아준다.

		MemberMenu menu = new MemberMenu();
		List<Member> list;
		try {
			list = new MemberService().selectName(memberName);

			if (!list.isEmpty()) { // null로 비교하지 않는다.
				menu.displayMemberList(list);
			} else {
				menu.displayNoData();
			}

		} catch (MemberException e) {
			e.printStackTrace();
			new MemberMenu().displayError("회원가입 실패, 관리자에게 문의하시오.");
			System.out.println(e.getMessage());
		}

	}

	public void updateMember(Member m) {
		
		try {
			int result = new MemberService().updateMember(m);

			if (result > 0) { // 성공 된 갯수를 반환
				new MemberMenu().displaySucces("회원수정성공");
			} 
		} catch (MemberException e) {
			e.printStackTrace();
			new MemberMenu().displayError("회원수정 실패");
			System.out.println(e.getMessage());
		}

	}

	public void deleteMember(String userId) {
		int result;
		try {
			result = new MemberService().deleteMember(userId);

			if (result > 0) { // 성공 된 갯수를 반환
				new MemberMenu().displaySucces("회원 탈퇴 성공");
			}

		} catch (MemberException e) {
			e.printStackTrace();
			new MemberMenu().displayError("회원 탈퇴 실패, 관리자에게 문의하시오.");
			System.out.println(e.getMessage());
		}

	}

	public void exitProgram() {

		new MemberService().exitProgram();
	}

	public void selectAllDeleteMember() {
		MemberMenu menu = new MemberMenu();
		List<Member> list;
		try {
			list = new MemberService().selectAllDeleteMember();

			if (!list.isEmpty()) {
				menu.displayMemberList(list);
			} else {
				 menu.displayNoData();
			}

		} catch (MemberException e) {
			e.printStackTrace();
			new MemberMenu().displayError("탈퇴 회원 조회 실패, 관리자에게 문의하시오.");
			System.out.println(e.getMessage());
		}

	}

	/*
	 * public void selectOneByName(String memberName) {
	 * 
	 * MemberMenu menu = new MemberMenu(); Member m = new
	 * MemberService().selectTwo(memberName);
	 * 
	 * if(m != null ) { menu.displayMember(m); }else { menu.displayError(memberName
	 * + "에 해당하는 데이터가 없습니다.");
	 * 
	 * }
	 * 
	 * }
	 */

	/*
	 * public void updateMember(String memberId, int column,String contents) {
	 * MemberMenu menu = new MemberMenu(); String columnName = null; switch(column)
	 * { case 1 : columnName = "EMAIL"; break; case 2 : columnName = "PHONE"; break;
	 * case 3 : columnName = "ADDRESS"; break; default : menu.displayError(column +
	 * "번에 해당하는 메뉴가 존재하지 않습니다."); }
	 * 
	 * new MemberDAO().updateMember(memberId,columnName,contents);
	 * 
	 * }
	 * 
	 */
	/*
	 * public void deleteMember(String memberId) { new
	 * MemberDAO().deleteMemeber(memberId);
	 * 
	 * 
	 * }
	 */

}
