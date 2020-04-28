package example;

import java.util.Scanner;
import java.util.StringTokenizer;

public class Stoken {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String a = sc.nextLine();
		StringTokenizer b = new StringTokenizer(a, " ");
		System.out.println(b.countTokens());
	}
}

