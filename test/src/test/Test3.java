package test;

import java.util.Scanner;

public class Test3 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		System.out.println("������������ �Է��ϼ���");
		
		while (true) {
			String s = sc.next();
			if(s.equals("����")) {
				System.out.println(1);
			} else if(s.equals("����")) {
				System.out.println(2);
			}else if(s.equals("��")) {
				System.out.println(3);
			}
			
			
			
			System.out.println("�����ðڽ��ϱ�");
			s = sc.next();
			if(s.equals("y")) {
				break;
			} else if(s.equals("n")) {
				System.out.println("������������ �Է��ϼ���");
			}

		}
		System.out.println("������ ����Ǿ����ϴ�.");
		
	}
}
