package quizAnswer;

import java.util.Scanner;

// while �� ������ ������ ���� ������ �ݺ��Ͽ� ����Ѵ�.

// ���ڸ� �Է��ϸ� �Է��� ���ڸ�ŭ "�ȳ��ϼ���"�� ����϶�

public class Quiz014 {
	
	public static void main(String[] args) {
		System.out.println("���ڸ� �Է��ϼ���");
		Scanner sc = new Scanner(System.in);
		
		// ����
		int a = Integer.parseInt(sc.next());
		
		while (a > 0) {
			System.out.println("�ȳ��ϼ���");
			a = a - 1;
		}
		
	}
}
