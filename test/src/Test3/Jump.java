package Test3;

import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Timestamp;
import java.time.LocalDateTime;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;

public class Jump {

	private JFrame frame;

	int field = 250; // ���ϰ� ���ߴ� ����

	ImageIcon ic = new ImageIcon("img/c1.gif");
	Image img = ic.getImage();

	int imgY = 5; // �̹����� �����ϴ� �ð�

	boolean fall = false; // ���� ���������� Ȯ��
	boolean jump = false; // ���� ���������� Ȯ��

	// �ð� ��������
	static long getTime() {
		return Timestamp.valueOf(LocalDateTime.now()).getTime();
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Jump window = new Jump();
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
	public Jump() {
		initialize();
	}

	class MyPanel extends JPanel {
		public MyPanel() {
			
			setFocusable(true);
			
			new Thread(new Runnable() {

				@Override
				public void run() {
					while (true) {

						// �߹ٴ� ��ġ�� �̹����� Y��ġ + �̹����� ���� �̴�.
						int foot = imgY + img.getHeight(null);

						// �߹ٴ��� ������ ���� ������ �۵�
						if (jump == false && foot < field && fall == false) { // ���������� �ʰ� ���߿� ������ �������� ���� �ƴ� �� �۵�
							fall = true; // �������� ������ ��ȯ
							System.out.println("���Ͻ���");
							long t1 = getTime(); // ����ð��� �����´�
							long t2;
							int set = 1; // ó�� ���Ϸ� (0~10) ���� �׽�Ʈ�غ���
							while (foot < field) { // ���� ���� ��� ������ �ݺ� 
								t2 = getTime() - t1; // ���� �ð����� t1�� ����
								int jumpY = set + (int) ((t2) / 40); // ���Ϸ��� �ø���.
								imgY = imgY + jumpY; // Y��ǥ�� ���Ϸ��� ���Ѵ�
								foot = imgY + img.getHeight(null); // ���� �߹ٴ� ��ġ�� �����Ѵ�
								repaint(); // �ٽñ׸���
								try {
									Thread.sleep(10);
								} catch (InterruptedException e) {
									e.printStackTrace();
								}

							}
							fall = false;
						}
						try {
							Thread.sleep(10);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}

				}
			}).start();
			
			addKeyListener(new KeyAdapter() {

				@Override
				public void keyPressed(KeyEvent e) {
					if (e.getKeyCode() == KeyEvent.VK_SPACE && jump == false) { // �����̽� Ű�� ������ ���� ���� �ƴҶ�

						new Thread(new Runnable() {

							@Override
							public void run() {

								// �߹ٴ� ��ġ�� �̹����� Y��ġ + �̹����� ���� �̴�.
								int foot = imgY + img.getHeight(null); // �߹ٴ��� ��ġ Y��ǥ+ �̹����� ����

								if (fall == false) { // �������� ���� �ƴ� �� ����
									jump = true; // ���������� ����
									System.out.println("��������");
									long t1 = getTime(); // ����ð��� �����´�
									long t2;
									int set = 8; // ���� ��� ����(0~20) ������ �ٲ㺸��
									int jumpY = 8; // 1�̻����θ� �����ϸ� �ȴ�.(while�� ���� ����)
									while (jumpY > 0) { // ��� ���̰� 0�϶����� �ݺ�
										t2 = getTime() - t1; // ���� �ð����� t1�� ����
										jumpY = set - (int) ((t2) / 40); // jump �� �����Ѵ�.
										imgY = imgY - jumpY; // Y���� �����Ѵ�.
										foot = imgY + img.getHeight(null); // �߹ٴ� ��ġ�� �����Ѵ�.
										repaint();
										try {
											Thread.sleep(10);
										} catch (InterruptedException e) {
											e.printStackTrace();
										}
									}
									jump = false;
								}
							}
						}).start();
					}
				}
			});

		}

		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			g.drawImage(img, 100, imgY, this);

		}

	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel panel = new MyPanel();
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
	}

}
