package coffeeshop.test;

class Aaa{
	int a = 1;
	String aa = "��"; // �� / ���ּ�
	
}


// �̱��� ����
class ������������ {
	int �� = 0;
	
	private static ������������ ����1  = new ������������();
	
	public static ������������ get����1() {
		return ����1;
	}
}


public class SingleTonEx01 {

	public static void main(String[] args) {

		
		������������ d1 = ������������.get����1();
		������������ d2 = ������������.get����1();
		System.out.println(d1.��);
		d1.�� = 1000;
		
		System.out.println(d1.��);
		
		d2.�� = d2.�� + 1000;
		
		System.out.println(d1.��);
	
		
		// d1 ��  d2�� ����
		
		
		System.out.println(d1.hashCode());
		System.out.println(d2.hashCode());
	}
}


//�̱����� �ش� ��ü�� ���α׷� �ȿ� �� 1�� �ִ� ��  