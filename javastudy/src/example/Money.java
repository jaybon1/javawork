package example;

import java.util.Scanner;

public class Money {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("�ݾ����Է��Ͻÿ�");
		int money = sc.nextInt();
		System.out.println("�������� " + money / 50000 + "��");
		money = money % 50000;

		System.out.println("������ " + money / 10000 + "��");
		money = money % 10000;

		System.out.println("õ���� " + money / 1000 + "��");
		money = money % 1000;

		System.out.println("��� " + money / 100 + "��");
		money = money % 100;

		System.out.println("���ʿ� " + money / 50 + "��");
		money = money % 50;

		System.out.println("�ʿ� " + money / 10 + "��");
		money = money % 10;

		System.out.println("�Ͽ� " + money / 1 + "��");
		money = money % 1;

	}
}
