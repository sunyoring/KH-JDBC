package com.kh.memeber.view;

import java.util.List;
import java.util.Scanner;

import com.kh.memeber.controller.MemberController;
import com.kh.memeber.model.vo.Member;

public class MemberMenu {

	private static Scanner sc = new Scanner(System.in);
	private MemberController mController = new MemberController();

	public void mainMenu() {

		int choice;

		do {
			System.out.println("\n*************회원관리프로그램*************");
			System.out.println("1. 회원전체조회"); // SELECT
			System.out.println("2. 회원 아이디조회"); // SELECT
			System.out.println("3. 회원 이름조회"); // SELECT
			System.out.println("4. 회원 가입"); // INSERT
			System.out.println("5. 회원 정보변경");// UPDATE
			System.out.println("6. 회원 탈퇴");// DELETE
			System.out.println("9. 프로그램 끝내기");
			System.out.println("번호선택 : ");

			choice = sc.nextInt();
			switch (choice) {
			case 1:
				mController.selectAll();
				break;
			case 2:
				mController.selectOne(inputMemberID());
				break;
			case 3:
				mController.selectName(inputMemberName2());
				
				/*mController.selectOneByName(inputMemberName());*/
				break;
			case 4:
				mController.insertMember(inputMember());
				break;
			case 5:
		
				/*mController.updateMember2(updateMmeber2());*/
				
				mController.updateMember(inputMemberID(),updateMenu(),updateContents());
				break;
			case 7:
				mController.deleteMember(inputMemberID());
				break;
			case 9:
				System.out.println("정말로 끝내시겠습니까 ? (y/n)");
				if ('y' == sc.next().toLowerCase().charAt(0))
					return;
				break;
			default:
				// 잘못입력했다 라고 띄워줌 break 안함
				System.out.println("번호를 잘못 입력하셨습니다.");
			}

		} while (true);
	}




	private Member updateMmeber2() {
		Member m = new Member();
		
		System.out.println("암호 : ");
		m.setPassword(sc.next());
		System.out.println("나이 : ");
		m.setAge(sc.nextInt());
		System.out.println("성별(M/F) : ");
		m.setGender(sc.next().toUpperCase());
		System.out.println("이메일 : ");
		m.setEmail(sc.next());
		System.out.println("전화번호(- 빼고 입력) : ");
		m.setPhone(sc.next());
		System.out.println("주소 : ");
		m.setAddress(sc.nextLine());
	
		return m;
	}




	private String inputMemberName2() {
		System.out.println("조회할 회원 이름 입력 : ");
		return sc.next();
	}




	private int updateMenu() {
		
	
		System.out.println("******변경 항목 선택******");
		System.out.println("1. 이메일");
		System.out.println("2. 전화번호");
		System.out.println("3. 주소");
		System.out.println("항목 번호를 선택하세요 : ");

		return sc.nextInt();	
	}

	private String updateContents() {
	
		System.out.println("변경할 내용을 입력하세요 : ");
		sc.nextLine();
		String contents = sc.nextLine();
		return contents;
	}


	private Member inputMember() {
		Member m = new Member();
		System.out.println("새로운 회원정보를 입력하세요 >> ");
		System.out.println("아이디 : ");
		m.setUserId(sc.next());
		System.out.println("암호 : ");
		m.setPassword(sc.next());
		System.out.println("이름 : ");
		m.setUserName(sc.next());
		System.out.println("나이 : ");
		m.setAge(sc.nextInt());
		System.out.println("성별(M/F) : ");
		m.setGender(sc.next().toUpperCase());
		System.out.println("이메일 : ");
		m.setEmail(sc.next());
		System.out.println("전화번호(- 빼고 입력) : ");
		m.setPhone(sc.next());
		System.out.println("주소 : ");
		sc.nextLine();
		m.setAddress(sc.next());
		System.out.println("취미(,로 구분 공백없이 입력) : ");
		m.setHobby(sc.next());
		return m;
	}

	private String inputMemberID() {
		System.out.println("아이디 입력 : ");
		
		return sc.next();
	}
	
	private String inputMemberName() {
		System.out.println("이름 입력 : ");
		return sc.next();
	}

	public void displayMemberList(List<Member> list) {
		System.out.println("\n 조회 된 전체 회원 정보는 다음과 같습니다.");
		System.out.println("\n아이디\t비밀번호\t이름\t나이\t성별\t이메일\t전화번호\t주소\t취미\t가입일");
		System.out.println("--------------------------------------------------------------");
		
		for(Member m : list) {
			System.out.println(m);
		}
		
	}

	public void displayError(String message) {
		System.out.println("서비스 요청 처리 실패  : " + message);
		
	}

	public void displayMember(Member m) {
		System.out.println("\n 조회 된 전체 회원 정보는 다음과 같습니다.");
		System.out.println("\n아이디\t비밀번호\t이름\t나이\t성별\t이메일\t전화번호\t주소\t취미\t가입일");
		System.out.println("--------------------------------------------------------------");	
		System.out.println(m.toString());
	}

	public void displaySucces(String message) {
		System.out.println("서비스 요청 결과 : " + message);
	}

}
