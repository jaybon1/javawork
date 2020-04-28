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
import java.awt.Color;

public class Testbu {

	private JFrame frame;
	ImageIcon ic = new ImageIcon("img/look.png");
	Image img = ic.getImage();
	ImageIcon ic1 = new ImageIcon("img/beauty.png");
	Image img1 = ic1.getImage();

	MyPanel panel;

	int imgX = 50;
	int imgY = 50;
	boolean clicked = false;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Testbu window = new Testbu();
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
	public Testbu() {
		initialize();
	}

	class MyPanel extends JPanel {

		public MyPanel() {
			setFocusable(true);
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
			addMouseListener(new MouseAdapter() {

				@Override
				public void mouseClicked(MouseEvent e) {
					System.out.println();
					int a= e.getX();
					int b= e.getY();
					if (a > imgX && a < (imgX + img.getWidth(null)) && b > imgY
							&& b < (imgY + img.getHeight(null))) {
						System.out.println("selected");
						clicked = true;
					} else if (clicked) {
						imgX = a - (int)(img.getWidth(null) / 2);
						imgY = b - (int)(img.getHeight(null) / 2);
						System.out.println(imgX + " : " + imgY);
						clicked = false;
					}

				}
			});

		}

		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			g.drawImage(img, imgX, imgY, new Color(0,0,0,255), this);

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
