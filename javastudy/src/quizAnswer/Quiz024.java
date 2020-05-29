package quizAnswer;

// break문을 이용하지 말고 1 ~ 20까지 출력하고 for문을 탈출해보자

public class Quiz024 {
	public static void main(String[] args) {

		// 정답
		for (int i = 0; i < 100; i++) {
			

			System.out.println(i+1);
			
			if(i > 18) {
				i = 100;
			}
		}
	}
}