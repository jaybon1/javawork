package animal;

// �������̽��� ����� ��� �ڵ带 �װͿ� ���缭 ������ �Ѵ�. (ǥ��)
// ���� ������ ������ �� �� �ִ�. (Ʋ�� ������ �ֱ⶧���� �����͸� ����ϱ� ����.)


// �߻�Ŭ������ ��üŬ�������� �������� �̾Ƽ� ����� (�����)
// �������̽��� �̸� ��Ģ�� ����� �������� �ο� (�����), �ٸ� �ʿ��� ��� �߰��� �� �ִ�.

// �������̽��� ���� �����ϰ� ��ü���� ���� �����ϰ� �����.
interface Animal{
	void run();
	void sound();
}


class Dog implements Animal{

	@Override
	public void run() {
		System.out.println("������ �޸���");
		
	}

	@Override
	public void sound() {
		System.out.println("�۸�");
		
	}


}

class Bird implements Animal {

	@Override
	public void run() {
		System.out.println("���� �޸���");
		
	}

	@Override
	public void sound() {
		System.out.println("±±");
		
	}
	
	public void fly() {
		System.out.println("���� ����");
	}
}

public class AnimalEx02 {
	
	static void start(Animal a) {
		try {
			a.run();
			a.sound();
//			if(a instanceof Bird) { // Bird Ŭ������ ���� �ν��Ͻ��� ���� Ȯ��
//				((Bird)a).fly();
//			}
			((Bird)a).fly();
		} catch (Exception e) {
			System.out.println("����Ӥ�");
		}

	}
	
	public static void main(String[] args) {
		start(new Dog());
		start(new Bird());
	}
}