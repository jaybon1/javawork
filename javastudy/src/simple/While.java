package simple;

public class While {
	public static void main(String[] args) {

		// while
		int i = 0;
		while (i < 100) {
			
			// i�� 1�� �Ǹ� i�� ������Ű�� while ���� �ٽ� ������
			if (i == 1) {
				i++;
				continue;
			
			// i�� 5���� ũ�� while ���� �������Ͷ�
			} else if (i > 5) {
				break;
				
			// �׿ܿ��� i�� ����Ʈ�ض�
			} else {
				System.out.println(i);
			}
			i++;
		}
		
		System.out.println();
		
		
		for (int j = 0; j < 100; j++) {
			
			if (j == 1) {
				continue;
			
			// i�� 5���� ũ�� while ���� �������Ͷ�
			} else if (j > 5) {
				break;
				
			// �׿ܿ��� i�� ����Ʈ�ض�
			} else {
				System.out.println(j);
			}
		}

	}
}