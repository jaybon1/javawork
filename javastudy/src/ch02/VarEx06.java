package ch02;

class Person {
	String name = "ȫ�浿";
	int age = 33;
	char gender = '��';
	String email = "example@gmail.com";
	String phone = "010-1234-5678";
}

public class VarEx06 {
	public static void main(String[] args) {
		Person p1 = new Person(); // �� ������ heap�� ��
		System.out.println(p1.name);
		System.out.println(p1.age);
		System.out.println(p1.gender);
		System.out.println(p1.email);
		System.out.println(p1.phone);
	}
}
