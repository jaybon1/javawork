package test;

import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.BorderLayout;


public class Test2 {
	
	// ���� �̹����� �߰��Ѵ�.
	ImageIcon jellyIc1= new ImageIcon("img/jelly1.png");
	Image jelly1 = jellyIc1.getImage();
	
	ImageIcon bonusFirstCookieIc = new ImageIcon("img/bonusFirstCookie.png");
	Image bonusFirstCookie = bonusFirstCookieIc.getImage();
	
	ImageIcon backIc = new ImageIcon("img/back1.png");
	Image backImg = backIc.getImage();
	
	//��Ű
	int c1X = 50;
	int c1Y = 50;
	
	// 1��° �̹���
	int back1X = 0;
	
	// 2��° �̹����� �ڵ��� �;��ϹǷ� backImg�� ���̸� �����´�.
	int back2X = backImg.getWidth(null);
	
	List<Item> imgList = new ArrayList<>();

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Test2 window = new Test2();
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
	public Test2() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 900, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new MyPanel();
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
	}
	
	class MyPanel extends JPanel{
		
		public MyPanel() {
			
			setFocusable(true);
			
			
			// �������� ����Ʈ�� �߰��Ѵ�.
			imgList.add(new Item(jelly1, 0, 1600, 100));
			imgList.add(new Item(jelly1, 0, 1700, 100));
			imgList.add(new Item(jelly1, 0, 1800, 100));
			
			new Thread(new Runnable() {
				
				@Override
				public void run() {
					while (true) {


						
						back1X--;
						back2X--;
						
						// �̹����� ȭ������� ������ ������
						// X���� �̹����� ������ǥ�� �ٽ� �ű��
						
						// 1���̹����� ���� ������ 2�� �ڿ� �ٰ�
						// 2���̹����� ������ �ٽ� 1 ���ڿ� �ٴ´�
						if(back1X < -(backImg.getWidth(null))) {
							back1X = backImg.getWidth(null)-1;
						}
						if(back2X < -(backImg.getWidth(null))) {
							back2X = backImg.getWidth(null)-1;
						}
						
						
						// �����۵��� �̹����� ��� �̵���Ų��.
						for (int i = 0; i < imgList.size(); i++) {
							imgList.get(i).setX(imgList.get(i).getX()-3);
						}
						
						// �����۵��� ĳ���� �̹��� ���� �ȿ� ������ ���ŵȴ�.
						for (int i = 0; i < imgList.size(); i++) {
							if(imgList.get(i).getX() > c1X
								&& imgList.get(i).getX() < c1X + bonusFirstCookie.getWidth(null)
								&& imgList.get(i).getY() > c1Y
								&& imgList.get(i).getY() < c1Y + bonusFirstCookie.getHeight(null)) {
								
								imgList.remove(imgList.get(i));
							}
						}
						
						
						repaint();
						try {
							Thread.sleep(10);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				}
				
			}).start();
			
		}
		
		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g); // ĵ������ ����ִ� �޼���
			
			g.drawImage(backImg, back1X, 0, this); // 1�� �׸�
			g.drawImage(backImg, back2X, 0, this); // 2�� �׸�
			
			
			// ����Ʈ�� �߰��� ��ŭ �̹����� �׸���.
			for (int i = 0; i < imgList.size(); i++) {				
				g.drawImage(imgList.get(i).getImage(), imgList.get(i).getX(), imgList.get(i).getY(), this);
			}
			
			g.drawImage(bonusFirstCookie, c1X, c1Y, this);
			
			
		}
	}

}
