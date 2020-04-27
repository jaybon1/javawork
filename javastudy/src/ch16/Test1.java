package ch16;

class JobOver{
	static void a() {
		System.out.println("작업이 끝났습니다");
	}
}

public class Test1 {
	
	static void accept(JobOver jobOver) {
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				jobOver.a();
			}
		}).start();
	}
	
	public static void main(String[] args) {
		
		accept(new JobOver());
		
		for (int i = 0; i < 6; i++) {
			System.out.println(i);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
