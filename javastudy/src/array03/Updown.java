package array03;

import java.util.Random;
import java.util.Scanner;

public class Updown {
	public static void main(String[] args) {
		Random random = new Random();
		int k = random.nextInt(100);
		int min = 0;
		int max = 99;
		
		Scanner sc = new Scanner(System.in);

		System.out.println("���� �����Ͽ����ϴ�. ���߾� ������.");
		
		while (true) {

			System.out.println(min + "-" + max);
			
			int say = sc.nextInt();
			
			if (say == k) {
				System.out.println("�¾ҽ��ϴ�.");
				System.out.println("�ٽ��Ͻðڽ��ϱ�?(y/n)");
				String yn = sc.next();
				if (yn.equals("y")) {
					System.out.println("���� �����Ͽ����ϴ�. ���߾� ������.");
					k = random.nextInt(100);
					min = 0;
					max = 99;
				} else {
					System.out.println("�����մϴ�.");
					break;
				}
			} else if (say > k) {
				System.out.println("�� ����");
				if (max > say) {
					max = say;
				}
			} else if (say < k) {
				System.out.println("�� ����");
				if (min < say) {
					min = say;
				}
			}

		}

	}
}