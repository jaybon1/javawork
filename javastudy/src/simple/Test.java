package simple;

import java.util.ArrayList;
import java.util.Iterator;

public class Test {
	public static void main(String[] args) {
		
		ArrayList<Integer> test = new ArrayList<>();
		for (int i = 1; i < 5000; i++) {
			test.add(i);
		}
		
		for (int j = 4999; j > 0; j++) {
			int temp = j;
			int a;
			
			int sum = j;
			for (int i = Integer.toString(j).length(); i > 0; i--) {
				a = (int) (temp / (Math.pow(10, Integer.toString(temp).length() -1)));
				temp = (int) (temp % (Math.pow(10, Integer.toString(temp).length() -1)));
				sum = sum + a;
			}
			
			for (Integer integer : test) {
				if(sum == integer) {
					
				}
			}
		}
	}
}
