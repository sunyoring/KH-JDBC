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
				break;
			case 3:
				break;
			case 4:
				break;
			case 5:
				break;
			case 7:
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

}
