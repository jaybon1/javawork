package test;

import java.util.Random;
import java.util.Scanner;

public class Test5 {
	

	public static void main(String[] args) {
		
		System.out.println("���� ���� ���� �Է��ϼ���");
		Scanner sc = new Scanner(System.in);


		String[] rcs = new String[] { "����", "����", "��" };
		
		String ip = sc.nextLine();
	
		System.out.println(ip + "�� �½��ϴ�.");

		int i = (int) (Math.random() * rcs.length);

		System.out.println("��ǻ�ʹ�" + rcs[i] + "�� �½��ϴ�.");

		if (ip.equals("����") && rcs[i] == "����") {
			System.out.println("�¸�");
		}

		else if (ip.equals("����") && rcs[i] == "��") {
			System.out.println("�й�!");
		}

		else if (ip.equals("����") && rcs[i] == "��") {
			System.out.println("�¸�");
		} else if (ip.equals("����") && rcs[i] == "����") {
			System.out.println("�й�!");
		} else if (ip.equals("��") && rcs[i] == "����") {
			System.out.println("�¸�");
		} else if (ip.equals("��") && rcs[i] == "����") {
			System.out.println("�й�!");
		} else if (ip.equals(rcs[i])) {
			System.out.println("���ºο�");
		}

		System.out.println("�ٽ� �Ͻðڽ��ϱ�? Y or N");
		String p = sc.nextLine();
		if ((p.equals("Y") || p.equals("y"))) {
			
			
		}

		else if ((p.equals("N") || p.equals("n"))) {
			System.out.println("�����ϼ̽��ϴ�");
			
		}
		
		else {
			System.out.println("�߸� �Է��ϼ̽��ϴ�.");
			
		}

//		boolean boo = true;
//		while(boo) {
//			run();
//			System.out.println("�ٽ� �Ͻðڽ��ϱ�? Y or N");
//			String p = sc.nextLine();
//			if ((p.equals("Y") || p.equals("y"))) {
//				run();				// �ٽ��ϵ��� ���� �ö󰡴� ���??
//			}
//	
//			if ((p.equals("N") || p.equals("n"))) {
//				System.out.println("�����ϼ̽��ϴ�");
//				break;
//			}
//		}
		
		
		
	}
}
