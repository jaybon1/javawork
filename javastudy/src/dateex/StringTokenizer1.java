package dateex;

import java.util.StringTokenizer;

public class StringTokenizer1 {
	public static void main(String[] args) {
		
		StringTokenizer st = new StringTokenizer("a=3,b=5,c=6", ","); // �����ڷ� ',' ���
		
		while (st.hasMoreTokens()) {
			System.out.println(st.nextToken());
		}
		
	}
}
