package ch13;

// �Ծ�
interface Animal {
	void move(); // �߻�޼���  - �߻�޼��尡 ������ new�� �� �� ���� - �߻����̴� ������Ʈ�� �� �� ���� 
}

abstract class Person {
	abstract void eat();
}

abstract class Person1 {
	void eat() {
		
	}
}

public class AnomyEx01 {
	public static void main(String[] args) {
		//Animal a = new Animal(); �߻� �޼��尡 ���� �Ǿ� ���� �ʾƼ� new �� �� ����
		//Person p = new Person(); �߻� �޼��尡 ���� �Ǿ� ���� �ʾƼ� new �� �� ����
		//Person1 p = new Person1(); �߻�Ŭ������ �߻�޼��尡 ���� ���ɼ��� �ֱ� ������ new�� ���Ƶ�
	}
}