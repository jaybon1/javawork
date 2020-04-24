package dateex;

import java.util.StringTokenizer;

public class StringTokenizer1 {
	public static void main(String[] args) {
		
		StringTokenizer st = new StringTokenizer("수인/병근/효선/광열", "/"); // 구분자로 '1' 사용
		
		while (st.hasMoreTokens()) {
			System.out.println(st.nextToken());
		}
		
	}
}
