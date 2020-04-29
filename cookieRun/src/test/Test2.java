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
	
	// 젤리 이미지를 추가한다.
	ImageIcon jellyIc1= new ImageIcon("img/jelly1.png");
	Image jelly1 = jellyIc1.getImage();
	
	ImageIcon bonusFirstCookieIc = new ImageIcon("img/bonusFirstCookie.png");
	Image bonusFirstCookie = bonusFirstCookieIc.getImage();
	
	ImageIcon backIc = new ImageIcon("img/back1.png");
	Image backImg = backIc.getImage();
	
	//쿠키
	int c1X = 50;
	int c1Y = 50;
	
	// 1번째 이미지
	int back1X = 0;
	
	// 2번째 이미지가 뒤따라 와야하므로 backImg의 넓이를 가져온다.
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
			
			
			// 아이템을 리스트에 추가한다.
			imgList.add(new Item(jelly1, 0, 1600, 100));
			imgList.add(new Item(jelly1, 0, 1700, 100));
			imgList.add(new Item(jelly1, 0, 1800, 100));
			
			new Thread(new Runnable() {
				
				@Override
				public void run() {
					while (true) {


						
						back1X--;
						back2X--;
						
						// 이미지가 화면밖으로 완전히 나가면
						// X축을 이미지의 넓이좌표로 다시 옮긴다
						
						// 1번이미지가 먼저 나가서 2번 뒤에 붙고
						// 2번이미지가 나가면 다시 1 번뒤에 붙는다
						if(back1X < -(backImg.getWidth(null))) {
							back1X = backImg.getWidth(null)-1;
						}
						if(back2X < -(backImg.getWidth(null))) {
							back2X = backImg.getWidth(null)-1;
						}
						
						
						// 아이템들의 이미지를 모두 이동시킨다.
						for (int i = 0; i < imgList.size(); i++) {
							imgList.get(i).setX(imgList.get(i).getX()-3);
						}
						
						// 아이템들이 캐릭터 이미지 범위 안에 들어오면 제거된다.
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
			super.paintComponent(g); // 캔버스를 비워주는 메서드
			
			g.drawImage(backImg, back1X, 0, this); // 1번 그림
			g.drawImage(backImg, back2X, 0, this); // 2번 그림
			
			
			// 리스트에 추가된 만큼 이미지를 그린다.
			for (int i = 0; i < imgList.size(); i++) {				
				g.drawImage(imgList.get(i).getImage(), imgList.get(i).getX(), imgList.get(i).getY(), this);
			}
			
			g.drawImage(bonusFirstCookie, c1X, c1Y, this);
			
			
		}
	}

}
