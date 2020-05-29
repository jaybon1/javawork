package quizAnswer;

import java.util.HashSet;

// HashSet 은 가변배열이다
// HashSet<Integer> arr 처럼 꺽쇠 안에는 클래스나 Wrapper타입을 넣을 수 있다.
// HashSet에 값을 추가하려면 arr.add(값);
// HashSet은 같은 값을 add하면 무시한다(추가가 되지않는다)
// HashSet의 길이를 알고 싶다면 arr.size();
// HashSet은 index가 없기 때문에 몇번째 값을 알 수가 없다.
// HashSet은 값을 지울 때 arr.remove(값);를 사용하며 index가 아닌 값 자체를 찾아서 지운다

// 아래 arr에 같은숫자 5개를 넣고
// 길이를 출력하세요


public class Quiz021 {
	public static void main(String[] args) {
		
		HashSet<Integer> arr = new HashSet<>();
		
		
		// 정답
		arr.add(1);
		arr.add(1);
		arr.add(1);
		arr.add(1);
		arr.add(1);
		
		System.out.println(arr.size());
	}
}
