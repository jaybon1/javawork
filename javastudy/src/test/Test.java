package test;

import java.util.ArrayList;

public class Test {
	public static void main(String[] args) {
		ArrayList<Integer> arr = new ArrayList<>();
		ArrayList<Integer> arr1 = new ArrayList<>();
		arr.add(-1);
		arr.add(1);
		arr.add(3);
		arr.add(-2);
		arr.add(2);
		
		for (int i = 0; i < arr.size(); i++) {
			if(arr.get(i) < 0) {				
				arr1.add(arr.get(i));
				arr.remove(i);
			}
		}
		
		for (int i = 0; i < arr.size(); i++) {
			arr1.add(arr.get(i));
		}

		System.out.println(arr1.toString());

	}
}
