package example;

import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;

public class ThreadSwing1 {

	private JFrame frame;

	ImageIcon ic = new ImageIcon("img/pa.png"); // 이미지아이콘 객체 생성
	Image img = ic.getImage(); // 이미지 객체 생성

	int imgX = 50;
	int imgY = 50;

	MyPanel panel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ThreadSwing1 window = new ThreadSwing1();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ThreadSwing1() {
		initialize();
	}

	class MyPanel extends JPanel {

		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g); // 캔버스 비우기
			g.drawImage(img, imgX, imgY, this); // 이미지 그리기
			g.drawi

		}

		public MyPanel() {
			setFocusable(true); // 입력 포커스 받기

			addMouseMotionListener(new MouseAdapter() { // 드래그는 모션리스너다

				@Override
				public void mouseDragged(MouseEvent e) {
					imgX = e.getX(); // 마우스의 좌표를 받아서 이미지 좌표에 저장
					imgY = e.getY();
					repaint();
					System.out.println(e.getX());
				}
			});
		}
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		panel = new MyPanel();
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);

	}

}