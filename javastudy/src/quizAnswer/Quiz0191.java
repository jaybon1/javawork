package quizAnswer;

import java.util.Random;

// new Random() ������ ���õ� ����� �����Ѵ�
// ���� ����� nextInt()�� ��ȣ �ȿ� ���ڸ� ������ 0 ~ �ش� ���� �̸� ���� ����Ѵ� (2)�� �Է��ߴٸ� 0 1 �� ��µ� 

// ������ ���� a�� ������ �����ϰ� ĳ�����Ͽ� 
// ���α׷��� �����ϸ� 1 ~ 45 �� �����ϰ� �������� ����� ������ 

public class Quiz0191 {
	public static void main(String[] args) {
		
		// ����
		Random ran = new Random();
		int a = ran.nextInt(45);
		
		System.out.println(a+1);

		
	}
	
}
