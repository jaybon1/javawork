package Paint;

import java.awt.event.MouseEvent;

public class Test2 extends listenAdapter {

public Test2() {
	add
}
	
	@Override
	public void mouseMoved(MouseEvent e) {
		System.out.println(e.getX() + " " + e.getY());
	}
	
	public static void main(String[] args) {
		Test2 t2 = new Test2();
		
		
		
		while (true) {
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
