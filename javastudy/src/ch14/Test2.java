package ch14;

public class Test2 {
	public static void main(String[] args) {
		int[][] a = new int[8][8];
		
		for (int i = 0; i < a.length; i++) {
			for (int j = 0; j < a[i].length; j++) {
				System.out.print(a[i][j] + " ");
			}
			System.out.println();
		}
	}
}
