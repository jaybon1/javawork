package ch01;

interface Chordata {// ������
	// �켱 "������ �迭 �� �ۼ��ִ�" �� ����ϱ����� void�� �����ϼ���"
	void run();
}

abstract class Mammalia implements Chordata {// ������
	// "������ ������ �̻��� ������ �ִ�"�� ����ϱ����� void�� �����ϼ���.
	abstract void tooth();

}

class Caniformia extends Mammalia { // ���Ƹ�
	// "���Ƹ��� ���ߺ��� �ɸ����ִ�" �� ����ϱ����� void �� �����ϼ���.

	@Override
	public void run() {
		System.out.println("������ �迭�� �� �� �ִ�.");
	}

	@Override
	void tooth() {
		System.out.println("������ ������ �̻��� ������ �ִ�.");
	}

	void crazyDog() {
		System.out.println("���Ƹ��� ���ߺ��� �ɸ� �� �ִ�.");
	}
}

class Procyonidae extends Caniformia {// �Ƹ޸�ī�ʱ�����
	int �ٸ�����;
	int �̻�����;
	String �̸�;
	// ���⼭�� �������̵��� �̿��ؼ� Mammalia(������)�������� "������ ������ �̻��� �������ִ�"���� "������ �̻��� �Ϳ���!"��
	// ����ϰ��غ�����

	// �׸��� �����ڸ� 3������ ���� �Ű������� �ٸ����Ͽ� ���￡ ���� �ְ������ �ְ� 3���� ����غ����� (�����ε� �̿�!)

	public Procyonidae(int legsize, int toothsize, String name) {
		�ٸ����� = legsize;
		�̻����� = toothsize;
		�̸� = name;
	}

	public Procyonidae(int legsize, int toothsize) {
		this(legsize, toothsize, "����");
	}

	public Procyonidae() {
		this(20, 3, "����");
	}

	@Override
	void tooth() {
		System.out.println("������ �̻��� �Ϳ���!");
	}

}

/*
 * ���� ���а� Ǫ�ñ� �ٶ��ϴ�
 * 
 * �̰� '����'������ �����Դϴ�.
 * 
 * �������̽�(������)~ class procyonidae(�Ƹ޸�ī �ʱ�����) ������ ���� �з��������� �����̴ϴ�.
 * 
 * ��, �������̽�(������) �̰����� ������ ������ �������̰����� ���Ƹ��� ������ ��ư �̷������� '��ӵ˴ϴ�'
 * 
 * �ϴ� �ȿ� �ּ��� ���а� Ǫ�ʽÿ�.
 * 
 * "��ü������ 'Procyonidae'�θ� ������ �߻����ؼ� �а� ���弼��.
 * 
 * 
 * 
 */

public class Sibal {

	public static void main(String[] args) {
		// ���⼱ Procyonidae�ϳ��� ��ü���ؼ�

		// �����ִ������� ������غ�����.

		// �Ҳ�
		// 1.������ �̻��� �Ϳ���!" ��� <-- �̰� �������̵��� ���̿�
		// 2."������ �迭 �� �ۼ��ִ�" <--���
		// 3."������ ������ �̻��� �������ִ�"<--���
		// 4."���Ƹ��� ���ߺ��� �ɸ����ִ�" <--���
		// 5.�׸��� �����ڸ� 3������ ���� �Ű������� �ٸ����Ͽ� ���￡ ���� �ְ������ �ְ� 3���� ����غ����� (�����ε� �̿�!)

		Procyonidae a = new Procyonidae();
		a.tooth();
		a.run();
		Caniformia b = new Caniformia();
		b.tooth();
		b.crazyDog();
		
		Procyonidae c = new Procyonidae(12,1,"������");
		System.out.println(c.�̸�);

	}

}
