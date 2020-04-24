package dateex;

import java.util.HashSet;
import java.util.Random;

public class Lotto {
	public static void main(String[] args) {
		
		
		// HashSet 순서가 없음 (엄청빠름)
		// TreeSet 순서대로 정렬 (HashSet보다 느림)
		HashSet<Integer> lotto = new HashSet<>();
		Random r = new Random();
		
		while (lotto.size() < 6) {
			int value = r.nextInt(45) + 1;
			lotto.add(value);
		}

		System.out.println(lotto);
		
	}
}
