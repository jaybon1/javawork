package test2;

import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Image;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;

public class Cookie1 {

	private JFrame frame;

	MyPanel panel;
	Field f1;
	Cookie c1;
	Stage s1;
	Stage s2;
	Foot nowFoot;
	int countFoot = 0;
	int footNum = 0;

	long firstTime = CookieUtil.getTime();
	long nowTime;

	int field;
	int stair1;

	ImageIcon ic = new ImageIcon("img/bonusFirstCookie.png");
	Image cookie = ic.getImage();

	ImageIcon backIc = new ImageIcon("img/back1.png");
	Image backImg = backIc.getImage();

	ImageIcon footIc = new ImageIcon("img/land0001_tm003_fh.png");
	Image foot = footIc.getImage();

	List<Foot> foots = new ArrayList<>();

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
		frame.setBounds(100, 100, 480, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		panel = new MyPanel();
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
	}

	class MyPanel extends JPanel {
		public MyPanel() {

			f1 = new Field();
			c1 = new Cookie(cookie, 0, 125, 0);
			s1 = new Stage(backImg, 0, 0, 0);
			s2 = new Stage(backImg, 0, backImg.getWidth(null), 0);

			for (int i = 0; i < 5; i++) {
				int a = i * 124;
				foots.add(new Foot(foot, 0, a, 200));
				nowFoot = foots.get(0);

			}

			// 화면갱신 쓰레드
			new Thread(new Runnable() {

				@Override
				public void run() {
					while (true) {
						repaint();
						try {
							Thread.sleep(10);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				}
			}).start();

			// 화면 이동 쓰레드
			new Thread(new Runnable() {

				@Override
				public void run() {
					while (true) {

						if (s1.getX() < -(s1.getImage().getWidth(null))) {
							s1.setX(s1.getImage().getWidth(null));
						}
						if (s2.getX() < -(s2.getImage().getWidth(null))) {
							s2.setX(s2.getImage().getWidth(null));
						}

						s1.setX(s1.getX() - 1);
						s2.setX(s2.getX() - 1);

						try {
							Thread.sleep(10);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				}
			}).start();

			// 발판 이동 쓰레드
			new Thread(new Runnable() {

				@Override
				public void run() {
					while (true) {
						for (int i = 0; i < foots.size(); i++) {
							foots.get(i).setX(foots.get(i).getX() - 1);
						}
						countFoot++;
						
						footNum = (int)(countFoot/124);
						System.out.println(footNum);
						
						try {
							Thread.sleep(10);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}

				}
			}).start();

			// 발판 확인 쓰레드
			new Thread(new Runnable() {

				@Override
				public void run() {
					while (true) {
						for (int i = 0; i < foots.size(); i++) {
							int nowFootX = foots.get(i).getX();
							if (nowFootX >= 125 && nowFootX < 248) {
								nowFoot = foots.get(i);
							}
						}
						try {
							Thread.sleep(10);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				}
			}).start();

			// 쿠키 낙하 쓰레드
			new Thread(new Runnable() {

				@Override
				public void run() {
					while (true) {
						
						if(Integer.parseInt((f1.field.charAt(footNum)+"")) == 1) {
							if(c1.getY() + c1.getImage().getHeight(null) < 200) {
								c1.setY(c1.getY() + 1);
							}
						} else {
							c1.setY(c1.getY() + 1);
						}
						
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
			super.paintComponent(g);

			g.drawImage(s1.getImage(), s1.getX(), s1.getY(), s1.getImage().getWidth(this) + 1,
					s1.getImage().getHeight(this), this);
			g.drawImage(s2.getImage(), s2.getX(), s2.getY(), s2.getImage().getWidth(this) + 1,
					s1.getImage().getHeight(this), this);

			for (int i = 0; i < foots.size(); i++) {
				g.drawImage(foots.get(i).getImage(), foots.get(i).getX(), foots.get(i).getY(), this);
			}

			g.drawImage(c1.getImage(), c1.getX(), c1.getY(), this);

		}
	}

}

class CookieUtil {

	// 시간 가져오기
	static long getTime() {
		return Timestamp.valueOf(LocalDateTime.now()).getTime();
	}

}
