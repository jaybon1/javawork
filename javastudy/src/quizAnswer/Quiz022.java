package quizAnswer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;

// Math.random() �� HashSet�� �̿��Ͽ�  6�ڸ��� �ζǹ�ȣ�� ���弼��

// ����ϴ� �� System.out.println(arr);

// ���� : [1,4,15,26,36,41]

public class Quiz022 {
	public static void main(String[] args) {
		
		HashSet<Integer> hs = new HashSet<>();
		
		while (hs.size() < 6) {
			
			double a = Math.random();
			hs.add((int)(a*45) + 1);
		}

		System.out.println(hs);
		
	}
}
