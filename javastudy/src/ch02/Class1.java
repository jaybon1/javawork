package ch02;

class �츮�� {
	String ���̸�;
	int ���;
	int ����;
	String �ּ�;
	
	// �ڿ� ��ȣ�� �ٸ��� �տ� �̸��� ������ �����ε�
	public �츮��( int ���, String ���̸�, int ����, String �ּ�) {
		this.���̸� = ���̸�;
		this.��� = ���;
		this.���� = ����;
		this.�ּ� = �ּ�;
	}
	
}

public class Class1 {
	public static void main(String[] args) {
		�츮�� my = new �츮��();
		
		System.out.println(my.getName(121));
	}
}