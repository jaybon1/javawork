package quizAnswer;

import java.util.Scanner;

// Scanner ���� ���� ���ͳݿ��� �˻�

// if�� �� ������ �ɾ �ڵ带 �����ϴ� ����̴�

// if - else if - else �����̴�

// 0���� ������ "0���� �۽��ϴ�" �� ���
// 10���� ������ "10���� �۽��ϴ�" �� ���
// 10�̻��̸� "10�̻� �Դϴ�" �� ���

public class Quiz013 {
	
	public static void main(String[] args) {
		System.out.println("���ڸ� �Է��ϼ���");
		Scanner sc = new Scanner(System.in);
		
		// ����
		int a = Integer.parseInt(sc.next());
		if(a < 0) {
			System.out.println("0���� �۽��ϴ�");
		} else if(a < 10) {
			System.out.println("10���� �۽��ϴ�");
		} else if(a >= 10) {
			System.out.println("10�̻� �Դϴ�");
		}
		
	}
}
