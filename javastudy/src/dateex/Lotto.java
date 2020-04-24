package dateex;

import java.util.HashSet;
import java.util.Random;

public class Lotto {
	public static void main(String[] args) {
		
		
		// HashSet ������ ���� (��û����)
		// TreeSet ������� ���� (HashSet���� ����)
		HashSet<Integer> lotto = new HashSet<>();
		Random r = new Random();
		
		while (lotto.size() < 6) {
			int value = r.nextInt(45) + 1;
			lotto.add(value);
		}

		System.out.println(lotto);
		
	}
}
