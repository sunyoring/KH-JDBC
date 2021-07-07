package com.kh.member.view;

import java.util.List;
import java.util.Scanner;

import com.kh.common.JDBCTemplate;
import com.kh.member.controller.MemberController;
import com.kh.member.model.vo.Member;

public class MemberMenu {

	private static Scanner sc = new Scanner(System.in);
	private MemberController mController = new MemberController();

	public void mainMenu() {

		int choice; // 번호 선택 변수

		do {
			System.out.println("\n**********회원관리프로그램**********");
			System.out.println("1.회원전체조회"); // SELECT
			System.out.println("2.회원 아이디조회"); // SELECT
			System.out.println("3.회원 이름조회"); // SELECT
			System.out.println("4.회원 가입"); // INSERT
			System.out.println("5.회원 정보변경"); // UPDATE
			System.out.println("6.회원 탈퇴"); // DELETE
			System.out.println("7.탈퇴 회원 조회하기"); // SELECT
			System.out.println("9.프로그램 끝내기");
			System.out.print("번호선택: ");

			choice = sc.nextInt();
			switch (choice) {
			case 1:
				mController.selectAll();
				break;
			case 2:
				mController.selectOne(inputMemberId());
				break;
			case 3:
				mController.selectName(inputMemberName());
				break;
			case 4:
				mController.insertMember(inputMember());
				break;
			case 5:
				mController.updateMember(updateMember());
				break;
			case 6:
				mController.deleteMember(inputMemberId());
				break;
			case 7:
				mController.selectAllDeleteMember();
				break;
			case 9:
				System.out.println("정말로 끝내시겠습니까 (y/n)");
				if ('y' == sc.next().toLowerCase().charAt(0)) {
					mController.exitProgram();
					return;
				}
				break;
			default:
				System.out.println("번호를 잘못 입력하였습니다.");
				break;
			}

		} while (true);

	}

	private Member updateMember() {
		Member m = new Member();

		System.out.print("아이디 : ");
		m.setUserId(sc.next());
		System.out.print("비밀번호 : ");
		m.setPassword(sc.next());
		System.out.print("이메일 : ");
		m.setEmail(sc.next());
		System.out.print("전화번호(-빼고입력) : ");
		m.setPhone(sc.next());
		System.out.print("주소 : ");
		sc.nextLine();
		m.setAddress(sc.nextLine());
		System.out.print("취미(,로 공백없이 입력) : ");
		m.setHobby(sc.next());
		
		return m;
	}

	private String inputMemberName() {
		System.out.print("조회할 회원 이름 입력: ");
		return sc.next();

	}

	private Member inputMember() {
		Member m = new Member();

		System.out.println("새로운 회원정보를 입력하세요 >> ");
		System.out.print("아이디 : ");
		m.setUserId(sc.next());
		System.out.print("비밀번호 : ");
		m.setPassword(sc.next());
		System.out.print("이름 : ");
		m.setUserName(sc.next());
		System.out.print("나이 : ");
		m.setAge(sc.nextInt());
		System.out.print("성별(M/F) : ");
		m.setGender(sc.next().toUpperCase());
		System.out.print("이메일 : ");
		m.setEmail(sc.next());
		System.out.print("전화번호(-빼고입력) : ");
		m.setPhone(sc.next());
		System.out.print("주소 : ");
		sc.nextLine();
		m.setAddress(sc.nextLine());
		System.out.print("취미(,로 공백없이 입력) : ");
		m.setHobby(sc.next());

		return m;
	}

	private String inputMemberId() {
		System.out.print("아이디 입력 : ");
		return sc.next();
	}

	public void displayMemberList(List<Member> list) {
		System.out.println("\n조회된 전체 회원정보는 다음과 같습니다.");
		System.out.println("\n아이디\t비밀번호\t이름\t성별\t나이\t이메일\t전화번호\t주소\t취미\t가입일");
		System.out.println("-----------------------------------------------------------");

		for (Member member : list) {
			System.out.println(member);
		}
	}

	public void displayError(String message) {
		System.out.println("서비스 요청 처리 실패 : " + message);
	}

	public void displayMember(Member m) {
		System.out.println("\n조회된 회원정보는 다음과 같습니다.");
		System.out.println("\n아이디\t비밀번호\t이름\t성별\t나이\t이메일\t전화번호\t주소\t취미\t가입일");
		System.out.println("-----------------------------------------------------------");

		System.out.println(m);
	}

	public void displaySuccess(String message) {
		System.out.println("서비스 요청 결과 : " + message);
	}

}
