package com.kh.memeber.controller;

import java.util.ArrayList;

import com.kh.memeber.model.dao.MemberDAO;
import com.kh.memeber.model.vo.Member;
import com.kh.memeber.view.MemberMenu;

public class MemberController {

	//view 와 dao(db연결)를 연결해주는 객체
	//view <-> controller <-> dao <-> db
	public void selectAll() {
		
		MemberMenu menu = new MemberMenu();
		ArrayList<Member> list = new MemberDAO().selectAll();
		
		if(!list.isEmpty()) { //null로 비교하지 않는다.
			menu.displayMemberList(list);
		}else {
			menu.displayError("해당되는 데이터가 없습니다.");
		}
	}

}
