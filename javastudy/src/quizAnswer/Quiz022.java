package quizAnswer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;

// Math.random() 과 HashSet을 이용하여  6자리의 로또번호를 만드세요

// 출력하는 법 System.out.println(arr);

// 예시 : [1,4,15,26,36,41]

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
