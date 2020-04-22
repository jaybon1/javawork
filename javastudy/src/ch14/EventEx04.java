package ch14;

import java.awt.Container;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class EventEx04 extends JFrame {
	
	private JLabel la; // new �� �����ڿ��� ����
	
	public EventEx04() {
		la = new JLabel("Hello");
		setTitle("MouseEvent ����");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container c = getContentPane();
		c.addMouseListener(new MyMouseListener());
		c.setLayout(null); // absolute ���̾ƿ�
		la.setSize(50, 20); // ���� ������
		la.setLocation(30, 30); // ���� ��ġ
		c.add(la);
		setSize(250, 250);
		setVisible(true); // paint()
		
	}
	
	class MyMouseListener extends MouseAdapter{
		
		// ���콺�� Ŭ���ϰ� ������ ��
		@Override
		public void mousePressed(MouseEvent e) {
			int x = e.getX(); // x ��ǥ
			int y = e.getY(); // y ��ǥ
			la.setLocation(x, y); // �� ��ġ ����
		}
	}

	public static void main(String[] args) {

		new EventEx04();

	}

}