package ch01;

abstract class Unit{
	Point P;
               //��� �����κп��� move(Point p) �޼��� �������ؾ� ��밡���ϴ�.
	abstract void move(Point p);
               //���������� �� �޼���
	void stop(){
		System.out.println("����");
	}
}

class Point {
	int x;
	int y;
	
	
	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	
}

class Marine extends Unit{ // ������ ������ Ŭ����
	void move(Point p) {
		System.out.println("������ ����� ���ư� ��ǥ��x : " +p.x + " y : " + p.y);

	}
	void stimPack() {
		System.out.println("�����о־־�~~~~~~");
	}
}

class Tank extends Unit{ // ��ũ�� ���� Ŭ����
	void move(Point p) {
		System.out.println("��ũ�� �ڶ׵ڶ�");
	}
	void changeMode() {
		System.out.println("��庯�� �h ��~~~~~~");
	}
}

class Dropship extends Unit{ // ������� ������ Ŭ����
	void move(Point p) {
		System.out.println("sdfsdf");
	}
	void load() {

	}

	void unload() {
		
	}
}


public class TestUnit {
	public static void main(String[] args) {
		Marine m1 = new Marine();
		Point p1 = new Point(100, 100);
		
		m1.move(p1);
	}
}












