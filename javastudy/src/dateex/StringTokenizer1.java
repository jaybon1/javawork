package dateex;

import java.util.StringTokenizer;

public class StringTokenizer1 {
	public static void main(String[] args) {
		
		StringTokenizer st = new StringTokenizer("����/����/ȿ��/����", "/"); // �����ڷ� '1' ���
		
		while (st.hasMoreTokens()) {
			System.out.println(st.nextToken());
		}
		
	}
}
