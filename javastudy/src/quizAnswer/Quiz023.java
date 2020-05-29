package quizAnswer;

// break문을 이용하여 1 ~ 20까지 출력하고 for문을 탈출해보자

public class Quiz023 {
	public static void main(String[] args) {

		
		// 정답
		for (int i = 0; i < 100; i++) {
			
			if(i > 20) {
				break;
			}

			System.out.println(i+1);
			
			
		}
	}
}
