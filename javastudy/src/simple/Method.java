package simple;


class Num{
	// ������ ����
	private int ���ں���; // ����
	private int ���ں���1; // ����
	private int ���ں���2;
	
	
	// �޼���(�Լ�)�� ����
	public void set���ں���(int ����) {
		���ں��� = ����;
	}
	
	// �����ε�
	public void set���ں���(int ����, int ����1) {
		���ں��� = ����;
		���ں���1 = ����1;
	}
	
	// �����ε�
	public void set���ں���(int ����, int ����1, int ����2) {
		���ں��� = ����;
		���ں���1 = ����1;
		���ں���2 = ����2;
	}
	
	public int get���ں���() {
		return ���ں���;
	}
	
	public int �����׽�Ʈ() {
		
		int i = 1;
		while(i < 100) {
			
			// ���࿡ i�� 5�̻��̸� i�� �����ض�
			if(i > 5) {
				return i;
			}
			i++;
		}
		// ��� ����� ���� �����������ϸ� -1�� �����ض�
		return -1;
	}
}


public class Method {
	public static void main(String[] args) {
		
		Num test1 = new Num();
		
		// ���ں��� �����ϱ�
		test1.set���ں���(12);
		
		// ���ں��� �ޱ�
		System.out.println(test1.get���ں���());
		
		test1.�����׽�Ʈ();
		
		// �����׽�Ʈ
		System.out.println(test1.�����׽�Ʈ());

	}
}