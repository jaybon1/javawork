package quizAnswer;

import java.util.Random;

// new Random() 랜덤에 관련된 기능을 제공한다
// 랜덤 기능중 nextInt()는 괄호 안에 숫자를 넣으면 0 ~ 해당 숫자 미만 까지 출력한다 (2)를 입력했다면 0 1 이 출력됨 

// 더블형 변수 a의 단위를 조정하고 캐스팅하여 
// 프로그램을 실행하면 1 ~ 45 가 랜덤하게 나오도록 만들어 보세요 

public class Quiz0191 {
	public static void main(String[] args) {
		
		// 정답
		Random ran = new Random();
		int a = ran.nextInt(45);
		
		System.out.println(a+1);

		
	}
	
}
