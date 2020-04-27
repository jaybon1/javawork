package ch14;

import java.util.Scanner;

public class Test3 {
	public static void main(String[] args) {
		
		int[][] a = new int[2][1];
		a[1][0] = 1;
		
		for (int i = 0; i < a.length; i++) {
			for (int j = 0; j < a[i].length; j++) {
				System.out.print(a[i][j] + " ");
			}
			System.out.println();
		}
		
		Scanner sc = new Scanner(System.in);
	}
}
