package test;

import java.awt.EventQueue;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Timestamp;
import java.time.LocalDateTime;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;

public class Tank {

	private JFrame frame;
	private int fieldY = 300;
	
	private int p1X = 50;
	private int p1Y = 300;
	
	
	private JLabel lblNewLabel = new JLabel("New label");
	
	private boolean jumpOver = true;
	private boolean pushSpace;
	private int birdX;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Tank window = new Tank();
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
	public Tank() {
		initialize();
	}
	
	class MyPanel extends JPanel{
		public MyPanel() {
			setFocusable(true);
			new Thread(new Runnable() {
				
				@Override
				public void run() {
					while (true) {
						if(pushSpace == true && lblNewLabel.getY() >= fieldY) {
							birdX++;
							System.out.println("게이지상승 " +birdX);
							try {
								Thread.sleep(10);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
						}
						lblNewLabel.setSize(100, 20);
						lblNewLabel.setLocation(p1X, p1Y);
					}
				}
			}).start();
			this.addKeyListener(new KeyAdapter() {
				@Override
				public void keyPressed(KeyEvent e) {
					if(e.getKeyCode() == KeyEvent.VK_SPACE) {
						pushSpace = true;
					}
				}
				@Override
				public void keyReleased(KeyEvent e) {
					if(e.getKeyCode() == KeyEvent.VK_SPACE) {
						pushSpace = false;
						if(jumpOver) {
							new Thread(new Runnable() {
								
								@Override
								public void run() {
									jump();									
								}
							}).start();
						}
					}
				}
			});
		}
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 1000, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		MyPanel panel = new MyPanel();
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		panel.setBounds(p1X, p1Y, 50, 300);
		panel.add(lblNewLabel);
		
		
	}
	
	long getTime() {
		return Timestamp.valueOf(LocalDateTime.now()).getTime();
	}
	
	// 점프 메서드
	void jump() {
		System.out.println("점프시작");
		jumpOver = false;
		
		long t1 = getTime();
		long t2;
		while (true) {
			t2 = getTime() - t1;
			p1Y = p1Y - (8 - (int)((t2)/60));
			p1X = p1X + (int)(birdX/10);
			if(p1Y >= fieldY) {
				p1Y = fieldY;
				break;
			}
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
		}
		birdX = 0;
		jumpOver = true;
		System.out.println("점프끝");
		System.out.println(lblNewLabel.getX()+  "   "+lblNewLabel.getY());
	}

}
