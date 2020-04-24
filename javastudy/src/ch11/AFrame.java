package ch11;

import java.awt.Container;
import java.awt.Font;

import javax.swing.JFrame;

class MyFrame extends JFrame{
	Container c = getContentPane();
	public MyFrame() {
		this(500,500);
	}
	
	public MyFrame(int width, int height) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(width, height);
	}
	
}

// �̸������� �������� ��ӹ޾Ƽ� �̿�
class BFrame extends MyFrame{
	public BFrame() {
		setVisible(true);
	}
}

public class AFrame {
	public static void main(String[] args) {
		new BFrame();
	}
}
