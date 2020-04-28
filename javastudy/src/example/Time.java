package example;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public class Time {
	static 	long getTime() {
		return Timestamp.valueOf(LocalDateTime.now()).getTime();
	}
	
	static void jump(int num, int fieldY) {
		System.out.println("점프시작");

		long t1 = getTime();
		long t2;
		while (true) {
			t2 = getTime() - t1;
			num = num - (9 - (int)((t2)/200));
			System.out.println(num);
			if(num > fieldY) {
				break;
			}
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
		}
	}
	
	public static void main(String[] args) {
		
		jump(500, 500);
		
	}
}
