package test;

public class Google1 {
	public static void main(String[] args) {
		int sum = 0;
		for (int i = 1; i <= 10000; i++) {
			String a = Integer.toString(i);

			if (a.contains("8")) {
				for (int j = 0; j < a.length(); j++) {
					if (a.charAt(j) == '8') {
						sum = sum + 1;
					}
				}
			}
		}
		System.out.println(sum);
	}
}
