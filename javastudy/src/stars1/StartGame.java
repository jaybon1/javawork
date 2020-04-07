package stars1;

import stars1.protoss.Dragoon;
import stars1.protoss.Protoss;
import stars1.protoss.Zealot;

public class StartGame {

	public static void move(Behavior b) {
		b.move();
	}

	public static void repair(Behavior b) {
		b.repair();
	}

	public static void attack(Behavior b1, Behavior b2) {
		System.out.println(b1.getName() + "�� " + b2.getName() + "�� �����߽��ϴ�.");
		b1.attack(b2);
		System.out.println(b2.getName() + "�� ���� ü���� " + b2.getHp() + "�Դϴ�");
	}

	public static void main(String[] args) {
		
		Protoss.upgrade();
		
		Zealot z1 = new Zealot("����1");
		Zealot z2 = new Zealot("����2");
		Dragoon d1 = new Dragoon("���1");
		
		attack(z1, d1);
		
		repair(z1);

	}
}