package com.kh.memeber.controller;

import java.util.ArrayList;
import java.util.List;

import com.kh.memeber.model.vo.Member;
import com.kh.memeber.service.MemberService;
import com.kh.memeber.view.MemberMenu;

public class MemberController {

	//view 와 dao(db연결)를 연결해주는 객체
	//view <-> controller <-> dao <-> db
	public void selectAll() {
		
		MemberMenu menu = new MemberMenu();
		ArrayList<Member> list = new MemberService().selectAll();
		
		if(!list.isEmpty()) { //null로 비교하지 않는다.
			menu.displayMemberList(list);
		}else {
			menu.displayError("해당되는 데이터가 없습니다.");
		}
	}



	public void selectOne(String memberId) {

		MemberMenu menu = new MemberMenu();
		Member m = new MemberService().selectOne(memberId);
		
		if(m != null ) {
			menu.displayMember(m);
		}else {
			menu.displayError(memberId + "에 해당하는 데이터가 없습니다.");

		}
	}

	


	public void insertMember(Member m) {
		int result = new MemberService().insertMember(m);
		if(result > 0) { // 성공 된 갯수를 반환
			new MemberMenu().displaySucces("회원가입성공");
		}else {
			new MemberMenu().displayError("회원가입실패");
		}
	}


/*
	public void selectOneByName(String memberName) {

		MemberMenu menu = new MemberMenu();
		Member m = new MemberService().selectTwo(memberName);
		
		if(m != null ) {
			menu.displayMember(m);
		}else {
			menu.displayError(memberName + "에 해당하는 데이터가 없습니다.");

		}
		
	}
*/

/*
	public void updateMember(String memberId, int column,String contents) {
		MemberMenu menu = new MemberMenu();
		String columnName = null;
		switch(column) {
		case 1 : columnName = "EMAIL";
			break;
		case 2 : columnName = "PHONE";
			break;
		case 3 : columnName = "ADDRESS";
			break;
		default :
			menu.displayError(column + "번에 해당하는 메뉴가 존재하지 않습니다.");
		}
			
		new MemberDAO().updateMember(memberId,columnName,contents);
		
	}

*/
/*
	public void deleteMember(String memberId) {
		new MemberDAO().deleteMemeber(memberId);

		
	}
*/


	public void selectName(String memberName) {
		//중복이름을 잡기 위해 리스트로 받아준다.
		
		MemberMenu menu = new MemberMenu();
		List<Member> list = new MemberService().selectName(memberName);
		
		if(!list.isEmpty()) { //null로 비교하지 않는다.
			menu.displayMemberList(list);
		}else {
			menu.displayError("해당되는 데이터가 없습니다.");
		}
	}



	public void updateMember(Member m) {
		int result = new MemberService().updateMmeber(m);
		if(result > 0) { // 성공 된 갯수를 반환
			new MemberMenu().displaySucces("회원수정성공");
		}else {
			new MemberMenu().displayError("회원수정실패");
		}
	}
	
	public void deleteMember(String userId) {
		int result = new MemberService().deleteMember(userId);
		if(result > 0) { // 성공 된 갯수를 반환
			new MemberMenu().displaySucces("회원삭제성공");
		}else {
			new MemberMenu().displayError("회원삭제실패");
		}
	}



	public void exitProgram() {

		new MemberService().exitProgram();
	}



	public void selectAllDeleteMember() {
		MemberMenu menu = new MemberMenu();
		List<Member> list = new MemberService().selectAllDeleteMember();
		
		if(!list.isEmpty()) {
			menu.displayMemberList(list);
		}else {
			menu.displayError("해당되는 데이터가 없습니다.");
		}
		
	}










}
