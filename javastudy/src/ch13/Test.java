package ch13;

class A {
	int a = 10;
}

public class Test {
	
	static void sample(A a) {
		System.out.println(a.a);
	}
	
	
	public static void main(String[] args) {
		
	// �⺻�ڷ����� ������ ��ü�� �����Ѵ�
	int a1 = 10;
	int a2 = a1;
	
	a1 = a1 + 10;
	System.out.println(a1);
	System.out.println(a2);
	
	
	// Ŭ�����ڷ����� �ν��Ͻ��� �ּҰ��� �����Ѵ�
	A test = new A();
	A test1 = test;
	
	test.a = test.a + 10;
	System.out.println(test.a);
	System.out.println(test1.a);
	
	
	// ���� �ٸ� �ν��Ͻ� �̱� ������ �����Ͱ� ���� ���ȴ�
	A test2 = new A();
	A test3 = new A();
	
	test2.a = test2.a + 10;
	System.out.println(test2.a);
	System.out.println(test3.a);
	}
}