package quizAnswer;

import java.util.Scanner;

// Ÿ�̸Ӹ� ������� �Ѵ�
// ���ڸ� �Է��ϸ� ī��Ʈ�� ����ϰ� ī��Ʈ�� �Ϸ�Ǹ� "����" ��� �����Ѵ�
// 1�� ������ �ִ� �ڵ�� Thread.sleep(1000) �̴�

public class Quiz016 {
	
	public static void main(String[] args) {
		System.out.println("���ڸ� �Է��ϼ���");
		Scanner sc = new Scanner(System.in);
		
		
		// ����
		int a = Integer.parseInt(sc.next());
		
		for (int i = 0; i < a; i++) {
			System.out.println(i+"��");
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println("����");
		
		
	}
}
