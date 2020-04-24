package dateex;

import java.util.ArrayList;
import java.util.StringTokenizer;

public class StringTokenizer2 {
	public static void main(String[] args) {
		
		StringTokenizer st = new StringTokenizer("a=3,b=5,c=6", ","); // 구분자로 ',' 사용
		
		int sum = 0 ;
		
		while (st.hasMoreTokens()) {
			String[] temp = st.nextToken().split("=");
			System.out.println(temp[0]);
			System.out.println(temp[1]);
			sum = sum + Integer.parseInt(temp[1]);
		}
		System.out.println("합은 " + sum);
		
	}
}
