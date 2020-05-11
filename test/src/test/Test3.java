package test;

import java.util.Scanner;

public class Test3 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		System.out.println("가위바위보를 입력하세요");
		
		while (true) {
			String s = sc.next();
			if(s.equals("가위")) {
				System.out.println(1);
			} else if(s.equals("바위")) {
				System.out.println(2);
			}else if(s.equals("보")) {
				System.out.println(3);
			}
			
			
			
			System.out.println("나가시겠습니까");
			s = sc.next();
			if(s.equals("y")) {
				break;
			} else if(s.equals("n")) {
				System.out.println("가위바위보를 입력하세요");
			}

		}
		System.out.println("게임이 종료되었습니다.");
		
	}
}
