package quizAnswer;

// �Ʒ� �迭�� 20���� �ʱ�ȭ �ϰ�
// for ���� �̿��Ͽ�  20 ����  1 ���� �Է��ϰ� �� �ٷ� ����ϼ���

public class Quiz011 {

	public static void main(String[] args) {

		int[] a;
		
		
		// ����
		a = new int[20];
		
		for (int i = 0; i < a.length; i++) {
			int temp = 20 - i;
			a[i] = temp;
			System.out.println(a[i]);
		}
	}
}
