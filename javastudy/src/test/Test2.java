package test;

public class Test2 {
	public static void main(String[] args) {
		int allTime = 86400;
		int count = 0;
		
		for (int i = 0; i < allTime; i++) {
			if((i/3600)%10 == 3) {
				count = count + 3600;
				i = i + 3600;
			} else if(((i%3600)/60)/10 == 3) {
				count = count + 60;
				i = i + 60;
			} else if(((i%3600)/60)%10 == 3) {
				count = count + 10;
				i = i + 10;
			}
		}
		System.out.println(count);
	}
}
