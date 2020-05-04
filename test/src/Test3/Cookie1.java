package Test3;

import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.AlphaComposite;
import java.awt.BorderLayout;

public class Cookie1 {

	private JFrame frame;
	
	
	// 배경 이미지
	ImageIcon backIc = new ImageIcon("배경이미지1"); // 제일 뒷 배경
	ImageIcon secondBackIc = new ImageIcon("배경이미지2"); // 2번째 배경

	
	// 쿠키 이미지 아이콘들
	ImageIcon cookieIc = new ImageIcon("testimg/cookieTest.png"); // 기본모션
	ImageIcon jumpIc = new ImageIcon("testimg/jumpTest.png"); // 점프모션
	ImageIcon doubleJumpIc = new ImageIcon("testimg/doubleJumpTest.png"); // 더블점프모션
	ImageIcon fallIc = new ImageIcon("testimg/fallTest.png"); // 낙하모션(더블 점프 후)
	
	
	// 젤리 이미지 아이콘들
	ImageIcon jellyIc = new ImageIcon("testimg/jellyTest.png");
	ImageIcon jellyEffectIc = new ImageIcon("testimg/jellyEffect.png");
	
	
	// 발판 이미지 아이콘들
	ImageIcon field1Ic = new ImageIcon("testimg/footTest.png"); // 발판
	ImageIcon field2Ic = new ImageIcon("testimg/footTest2.png"); //공중발판
	
	
	// 장애물 이미지 아이콘들
	ImageIcon tacle10Ic = new ImageIcon("testimg/tacleTest10.png"); // 1칸 장애물
	ImageIcon tacle20Ic = new ImageIcon("testimg/tacleTest20.png"); // 2칸 장애물
	ImageIcon tacle35Ic = new ImageIcon("testimg/tacleTest35.png");	// 3.5칸 장애물
	
	
	// 리스트 생성
	List<Jelly> jellyList = new ArrayList<>(); // 젤리 리스트

	List<Field> fieldList = new ArrayList<>(); // 발판 리스트

	List<Tacle> tacleList = new ArrayList<>(); // 장애물 리스트
	
	int mapSpeed = 4;
	
	int nowField = 2000; // 발판의 높이를 저장.
	
	boolean fall = false; // 현재 떨어지는지 확인
	
	boolean jump = false; // 현재 점프중인지 확인
	int doubleJump = 0; // 점프 카운트 (2가되면 더블점프 상태이다)
	
	boolean invincible = false; // 쿠키 무적 상태 여부
	
	boolean downKeyOn = false; // 다운키 눌렀는지 여부
	
	
	int[] sizeArr; // 이미지의 넓이와 높이를 가져오는 1차원 배열
	int[][] colorArr; // 이미지의 x y 좌표의 색값을 저장하는 2차원배열
	
	
	Image buffImage; // 더블버퍼 이미지
	Graphics buffg; // 더블버퍼 g
	
	
	private AlphaComposite alphaComposite; // 투명도 관련 객체

	
	class MyPanel extends JPanel{
		
		public MyPanel() {
			
			setFocusable(true);
			
			// 쿠키 생성   /  x값은 240 y값은 -80  넓이와 높이는 80 , 투명도는 255(불투명)이다.
			Cookie c1 = new Cookie(cookieIc.getImage(), 240, -80, 80, 80, 255, 0);
			
			
			// 쿠키의  x값과 높이를 더한 값
			int body = c1.getX() + c1.getWidth();
			
			// 쿠키의 발밑 위치 /  쿠키의 y값과 높이를 더한 값이다.
			int foot = c1.getY() + c1.getHeight();
			
			
			// 배경1-1 생성
			Back b1 = new Back(backIc.getImage(), 
					0,  
					75, // y 값 (조정 필요)
					backIc.getImage().getWidth(null),
					backIc.getImage().getHeight(null));
			
			// 배경1-2 생성
			Back b2 = new Back(backIc.getImage(), 
					0, 
					backIc.getIconWidth(), // y 값 (조정 필요)
					backIc.getImage().getWidth(null),
					backIc.getImage().getHeight(null));

			
			// 맵 정보 불러오기
			try {
				sizeArr = Bf3.getSize("img/firstMap.png"); // 맵 사이즈를 배열에 저장
				colorArr = Bf3.getPic("img/firstMap.png"); // 맵 픽셀값을 배열에 저장
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			
			int maxX = sizeArr[0]; // 맵의 넓이
			int maxY = sizeArr[1]; // 맵의 높이
			
			for (int i = 0; i < maxX; i += 1) { // 젤리는 1칸을 차지하기 때문에 1,1사이즈로 반복문을 돌린다.
				for (int j = 0; j < maxY; j += 1) {
					if (colorArr[i][j] == 16776960) { // 색값이 16776960일 경우 (노란색)
						// 좌표에 40을 곱하고, 넓이와 높이는 30으로 한다.
						jellyList.add(new Jelly(jellyIc.getImage(), i * 40, j * 40, 30, 30, 0));
					}
				}
			}
			
			for (int i = 0; i < maxX; i += 2) { // 발판은 4칸을 차지하는 공간이기 때문에 2,2사이즈로 반복문을 돌린다.
				for (int j = 0; j < maxY; j += 2) {
					if (colorArr[i][j] == 0) { // 색값이 0 일경우 (검은색)
						// 좌표에 40을 곱하고, 넓이와 높이는 80으로 한다.
						fieldList.add(new Field(field1Ic.getImage(), i * 40, j * 40, 80, 80));
					}
				}
			}

			for (int i = 0; i < maxX; i += 2) { // 장애물은 4칸 이상을 차지한다. 추후 수정
				for (int j = 0; j < maxY; j += 2) {
					if (colorArr[i][j] == 16711680) { // 색값이 16711680일 경우 (빨간색)
						// 좌표에 40을 곱하고, 넓이와 높이는 30으로 한다.
						tacleList.add(new Tacle(tacle10Ic.getImage(), i * 40, j * 40, 80, 80, 0)); 
					}
				}
			}
			
			
			// repaint 전용 쓰레드
			new Thread(new Runnable() {
				
				@Override
				public void run() {
					while (true) {
						repaint();
						try {
							Thread.sleep(10);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				}
			}).start();
			
			
			//
			new Thread(new Runnable() {
				
				@Override
				public void run() {
					
					
					if (b1.getX() < -(b1.getWidth()-1)) {
						b1.setX(b1.getWidth());
					}
					if (b2.getX() < -(b2.getWidth()-1)) {
						b2.setX(b2.getWidth());
					}
					
					b1.setX(b1.getX()-1);
					b2.setX(b2.getX()-1);
					
					
					// 발판위치를 -4 씩 해준다.
					for (int i = 0; i < fieldList.size(); i++) {
						
						Field tempField = fieldList.get(i);
						
						if(tempField.getX() < -90) { // 발판의  x좌표가 -90 미만이면 해당 발판을 제거한다.
							fieldList.remove(tempField);
						} else {
							tempField.setX(tempField.getX() - 4);					
						}
					}
					
					// 젤리위치를 -4 씩 해준다.
					for (int i = 0; i < jellyList.size(); i++) {
						Jelly tempJelly = jellyList.get(i);
						
						if(tempJelly.getX() < -90) {
							fieldList.remove(tempJelly);
						} else {
							tempJelly.setX(tempJelly.getX() - 4);							
							if(tempJelly.getY() > c1.getY()
									&& tempJelly.getY() < foot
									&& tempJelly.getX() > c1.getX()
									&& tempJelly.getX() < c1.get) {
								tempJelly.setImage(eff1.getImage());
								
							}
						}
					}
					
					// 장애물위치를 - 4 씩 해준다.
					for (int i = 0; i < tacleList.size(); i++) {
						Tacle tempTacle = tacleList.get(i);
						if(tempTacle.getX() < -90) {
							fieldList.remove(tempTacle);
						} else {
							tempTacle.setX(tempTacle.getX() - 4);	
							if(
									!invincible
									&& tempTacle.getY() > imgY 
									&& tempTacle.getY() < imgY+fallOverY
									&& tempTacle.getX() > 160
									&& tempTacle.getX() < 240) {
								invincible = true;								
								
								new Thread(new Runnable() {
									
									@Override
									public void run() {
										
										img = ichit.getImage();
										
										cookieAlpha = 80;
										try {
											Thread.sleep(500);
										} catch (InterruptedException e) {
											e.printStackTrace();
										}
										img = ic.getImage();
										
										
										for (int j = 0; j < 11; j++) {
											if(cookieAlpha == 80) {
												cookieAlpha = 160;
											} else {
												cookieAlpha = 80;
											}
											try {
												Thread.sleep(250);
											} catch (InterruptedException e) {
												e.printStackTrace();
											}
										}
										cookieAlpha = 255;
										
										
										invincible = false;
										System.out.println("무적종료");
									}
								}).start();
							}
						}
					}
					
					
					
					
				}
			}).start();
			
			
			
			
			
			
			
		}
		
		@Override
		protected void paintComponent(Graphics g) {
			
			
		}
	}
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Cookie1 window = new Cookie1();
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
	public Cookie1() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 800,480);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
	}
}
