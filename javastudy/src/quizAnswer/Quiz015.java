package quizAnswer;

// intŸ�� ������ intŸ�� �̸� int Ÿ���� ���ϵȴ� 10/3 -> 3
// intŸ�� ������ intŸ�� �̸� int Ÿ���� ���ϵȴ� 10%3 -> 1


// 3600�ʴ� 1�ð�
// 60�ʴ� 1��

// ���ݺ��� 34567�� �ڿ� ����Ϸ��� �Ѵ�.
// ��ð� ��� �ڿ� ����ϴ��� ����ϼ���.
// ��¿��� : 0�ð� 0�� 0�� �ڿ� ����մϴ�.

public class Quiz015 {
	public static void main(String[] args) {
		
		// ����
		int a = 34567;
		int hour = 0;
		int minute = 0;
		int second = 0;
		
		hour = a/3600;
		
		a = a % 3600;
		
		minute = a/60;
		
		a = a % 60;
		
		second = a;
		
		System.out.println(hour+"�ð� "+minute+"�� "+second+"�� �ڿ� ����մϴ�.");
		
	}
}
