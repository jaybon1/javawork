package test;

import java.util.Random;
import java.util.Scanner;

public class Test5 {
	

	public static void main(String[] args) {
		
		System.out.println("가위 바위 보를 입력하세요");
		Scanner sc = new Scanner(System.in);


		String[] rcs = new String[] { "가위", "바위", "보" };
		
		String ip = sc.nextLine();
	
		System.out.println(ip + "를 냈습니다.");

		int i = (int) (Math.random() * rcs.length);

		System.out.println("컴퓨터는" + rcs[i] + "를 냈습니다.");

		if (ip.equals("바위") && rcs[i] == "가위") {
			System.out.println("승리");
		}

		else if (ip.equals("바위") && rcs[i] == "보") {
			System.out.println("패배!");
		}

		else if (ip.equals("가위") && rcs[i] == "보") {
			System.out.println("승리");
		} else if (ip.equals("가위") && rcs[i] == "바위") {
			System.out.println("패배!");
		} else if (ip.equals("보") && rcs[i] == "바위") {
			System.out.println("승리");
		} else if (ip.equals("보") && rcs[i] == "가위") {
			System.out.println("패배!");
		} else if (ip.equals(rcs[i])) {
			System.out.println("무승부요");
		}

		System.out.println("다시 하시겠습니까? Y or N");
		String p = sc.nextLine();
		if ((p.equals("Y") || p.equals("y"))) {
			
			
		}

		else if ((p.equals("N") || p.equals("n"))) {
			System.out.println("수고하셨습니다");
			
		}
		
		else {
			System.out.println("잘못 입력하셨습니다.");
			
		}

//		boolean boo = true;
//		while(boo) {
//			run();
//			System.out.println("다시 하시겠습니까? Y or N");
//			String p = sc.nextLine();
//			if ((p.equals("Y") || p.equals("y"))) {
//				run();				// 다시하도록 위로 올라가는 방법??
//			}
//	
//			if ((p.equals("N") || p.equals("n"))) {
//				System.out.println("수고하셨습니다");
//				break;
//			}
//		}
		
		
		
	}
}
