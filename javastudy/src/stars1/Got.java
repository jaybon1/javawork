package stars1;

class Animal {
	String getString() {
		return "����";
	}

	public String getName() {

		return null;
	}
}

class Dog extends Animal {
	final String NAME = "������";

	public String getName() {
		return NAME;
	}
}

class Cat extends Animal {
	final String NAME = "������";

	public String getName() {
		return NAME;
	}
}

class Bird extends Animal {
	final String NAME = "��";

	public String getName() {
		return NAME;
	}
}

class cow extends Animal {
	final String NAME = "����ī��";

	public String getName() {
		return NAME;
	}
}

class ori extends Animal {
	final String NAME = "�в���";

	public String getName() {
		return NAME;
	}
}

public class Got {
	static void attack(Animal u1, Animal u2) {
		System.out.println(u2.getName() + "�� " + u1.getName() + "���� ���ô��ϰ� �ֽ��ϴ�.");
	}

	public static void main(String[] args) {

		Dog d1 = new Dog();
		Cat c1 = new Cat();
		Bird b1 = new Bird();
		cow co1 = new cow();
		ori o1 = new ori();

		System.out.println(d1.NAME + "ź��!!!");
		System.out.println(c1.NAME + "ź��!!!");
		System.out.println(b1.NAME + "ź��!!!");
		System.out.println(co1.NAME + "ź��!!!");
		System.out.println(o1.NAME + "ź��!!!");

		attack(o1, co1);

	}

}