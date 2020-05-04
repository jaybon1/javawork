package Test3;

import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Color;

public class Fade {

	private JFrame frame;

	int field = 400; // ���� ����

	// 1�̸� ������ �ְ� 0�̸� ����
	String fieldStr = "1111011101100110010101101111000111011111111111111111111111111111111111111111111111111111111";

	List<Foot> fieldList = new ArrayList<>(); // ���� ��ü�� ���� ����Ʈ

	int count = 0; // ���� Ȯ�� ����
	
	int foot = 0;

	int range = 0; // ĳ���Ͱ� ��� ���� ����

	int nowField = field; // ĳ���ͳ��̿� ���� ������ġ ���� ����

	ImageIcon landIc = new ImageIcon("img/land1.png");
	Image landimg = landIc.getImage();

	// substring���� ���� ���� �˻�
	static int getGround(String ground, int index) {
		return Integer.parseInt(ground.substring(index, index + 1));
	}

	Image buffImage; // ������� ����
	Graphics buffg;

	ImageIcon ic = new ImageIcon("img/c1run.gif");
	Image img = ic.getImage();

	int fallOverY = ic.getImage().getHeight(null); // �⺻ �̹��� ����(���߿� �簢������ �������)
	
	
	boolean downKeyOn = false;

	ImageIcon icJump = new ImageIcon("img/c1jump.gif");
	ImageIcon icDoubleJump = new ImageIcon("img/c1doubleJump.gif");
	ImageIcon icfall = new ImageIcon("img/c1fall.png");
	ImageIcon icfallOver = new ImageIcon("img/c1fallOver.png");
	ImageIcon icnap = new ImageIcon("img/c1nap.gif");


	ImageIcon backIc = new ImageIcon("img/back1.png");
	Image back = backIc.getImage();
	Image back1 = backIc.getImage();
	int backX = 0;
	int back1X = back.getWidth(null);

	int imgY = 0; // �̹����� �����ϴ� ��ǥ

	boolean fall = false; // ���� ���������� Ȯ��
	boolean jump = false; // ���� ���������� Ȯ��

	int doubleJump = 0; // ���� ī��Ʈ (2���Ǹ� �������� �����̴�)


	// �ð� ��������
	static long getTime() {
		return Timestamp.valueOf(LocalDateTime.now()).getTime();
	}
	
	Color backFade = new Color(0, 0, 0, 0);
	
	
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Fade window = new Fade();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	class MyPanel extends JPanel {
		public MyPanel() {

			setFocusable(true);

			for (int i = 0; i < fieldStr.length(); i++) { // fieldStr�� ���� ��ŭ �ݺ�

				int tempX = i * landimg.getWidth(null); // �ݺ��� ������ X��ǥ�� ���δ�.

				if (getGround(fieldStr, i) == 1) { // fieldStr�� ���� �ִ� ��ġ���� ������ ��ġ�Ѵ�.
					fieldList.add(new Foot(landimg, tempX, 400, landimg.getWidth(null), landimg.getHeight(null)));
				}
			}

			new Thread(new Runnable() { // ������Ʈ ���� ������

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

			new Thread(new Runnable() { // ��� �� ���� ��ǥ �̵� ������

				@Override
				public void run() {
					while (true) {
						foot = imgY + fallOverY; // foot

						if (backX < -(back.getWidth(null))) {
							backX = back.getWidth(null);
						}
						if (back1X < -(back.getWidth(null))) {
							back1X = back.getWidth(null);
						}

						backX--;
						back1X--;

						for (int i = 0; i < fieldList.size(); i++) {
							fieldList.get(i).setX(fieldList.get(i).getX() - 4);
						}

						range = (int) (landimg.getWidth(null) * 1.3); // ĳ���Ͱ� ������ �� �ִ� ��ġ

						for (int i = 0; i < fieldList.size(); i++) { // range�ȿ� ������ ������ 1 ������ 0
							if (fieldList.get(i).getX() >= 0 && fieldList.get(i).getX() < range) {
								count = 1;
								break;
							} else if (i == fieldList.size() - 1) {
								count = 0;
							}
						}

						if (count == 0) { // count�� 0�̸� ���Ϸ� count�� 1�̸� ĳ���� ���̿� ���� ������ġ ����
							nowField = 2000;
						} else if (count == 1 && foot > field) {
							nowField = 2000;
						} else if (count == 1 && foot < field) {
							nowField = field;
						}

						try {
							Thread.sleep(10);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				}
			}).start();

			// ���Ͼ�����
			new Thread(new Runnable() {

				@Override
				public void run() {
					while (true) {

						// �߹ٴ� ��ġ�� �̹����� Y��ġ + �̹����� ���� �̴�.

						// �߹ٴ��� ���Ǻ��� ���� ������ �۵�
						if (foot < nowField && !jump && !fall) { // ���߿� ������ ���������� �ʰ� �������� ���� �ƴ� �� �۵�
							fall = true; // �������� ������ ��ȯ
							System.out.println("����");
							
							if (doubleJump == 2) {
								img = icfall.getImage();
							}

							long t1 = getTime(); // ����ð��� �����´�
							long t2;
							int set = 1; // ó�� ���Ϸ� (0~10) ���� �׽�Ʈ�غ���
							while (foot < nowField) { // ���� ���ǿ� ��� ������ �ݺ�
								t2 = getTime() - t1; // ���� �ð����� t1�� ����
								int fallY = set + (int) ((t2) / 40); // ���Ϸ��� �ø���.

								if (foot + fallY >= nowField) { // �߹ٴ�+���Ϸ��� ���Ǻ��� ���ٸ� ���Ϸ��� �����Ѵ�.
									//fall = false;
									fallY = nowField - foot;
								}

								imgY = imgY + fallY; // Y��ǥ�� ���Ϸ��� ���Ѵ�

								foot = imgY + fallOverY; // ���� �߹ٴ� ��ġ�� �����Ѵ�

								if (jump) { // �������ٰ� ���� ������ �ϸ� ��������
									break;
								}

								try {
									Thread.sleep(10);
								} catch (InterruptedException e) {
									e.printStackTrace();
								}

							}
							fall = false;
							
							if (downKeyOn && !jump && !fall && img != icnap.getImage()) {
								img = icnap.getImage();
							} else if (!downKeyOn && !jump && !fall && img != ic.getImage()) {
								img = ic.getImage();
							}

							if (jump == false) { // ���� ���� ��� ���� ���� �ƴ� �� �������� ī��Ʈ�� 0���� ����
								doubleJump = 0;
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

			addKeyListener(new KeyAdapter() {

				@Override
				public void keyPressed(KeyEvent e) {
					if (e.getKeyCode() == KeyEvent.VK_SPACE && doubleJump < 2) { // �����̽� Ű�� ������ ���������� 2�� �ƴҶ�

						new Thread(new Runnable() {

							@Override
							public void run() {

								doubleJump++; // ���� Ƚ�� ����

								int nowJump = doubleJump; // �̹��� �������� ������������ ����

								jump = true; // ���������� ����

								if (doubleJump == 1) {
									System.out.println("����");
									img = icJump.getImage();
								} else if (doubleJump == 2) {
									System.out.println("��������");
									img = icDoubleJump.getImage();
								}

								// �߹ٴ� ��ġ�� �̹����� Y��ġ + �̹����� ���� �̴�.
								int foot = imgY + fallOverY; // �߹ٴ��� ��ġ Y��ǥ+ �̹����� ����

								long t1 = getTime(); // ����ð��� �����´�
								long t2;
								int set = 8; // ���� ��� ����(0~20) ������ �ٲ㺸��
								int jumpY = 8; // 1�̻����θ� �����ϸ� �ȴ�.(while�� ���� ����)
								while (jumpY > 0) { // ��� ���̰� 0�϶����� �ݺ�
									t2 = getTime() - t1; // ���� �ð����� t1�� ����
									jumpY = set - (int) ((t2) / 40); // jumpY �� �����Ѵ�.
									imgY = imgY - jumpY; // Y���� �����Ѵ�.
									foot = imgY + fallOverY; // �߹ٴ� ��ġ�� �����Ѵ�.

									if (nowJump != doubleJump) { // ������ �ѹ� ���Ǹ� ù��° ������ �����
										break;
									}
									try {
										Thread.sleep(10);
									} catch (InterruptedException e) {
										e.printStackTrace();
									}
								}

								if (nowJump == doubleJump) { // ������ ��¥ ������ ���� Ȯ��
									jump = false;
								}

							}
						}).start();
					}
					if (e.getKeyCode() == KeyEvent.VK_DOWN) {
						
						downKeyOn = true;
						
						if (img != icnap.getImage() && !jump && !fall) {
							img = icnap.getImage();
						}
					}
					
					if (e.getKeyCode() == KeyEvent.VK_ENTER) {
						new Thread(new Runnable() {
							
							@Override
							public void run() {
								for (int i = 0; i < 256; i+=2) {
									backFade = new Color(0, 0, 0, i);
									try {
										Thread.sleep(10);
									} catch (InterruptedException e) {
										e.printStackTrace();
									}
								}
								for (int i = 255; i >= 0; i-=2) {
									backFade = new Color(0, 0, 0, i);
									try {
										Thread.sleep(10);
									} catch (InterruptedException e) {
										e.printStackTrace();
									}
								}
							}
						}).start();
					}
					
					
				}

				@Override
				public void keyReleased(KeyEvent e) {

					if (e.getKeyCode() == KeyEvent.VK_DOWN) {
						
						downKeyOn = false;
						
						if (img != ic.getImage() && !jump && !fall) {
							img = ic.getImage();
						}
					}
				}

			});
		}

// ����Ʈ������Ʈ ����
//		@Override
//		protected void paintComponent(Graphics g) {
//			super.paintComponent(g);
//			for (int i = 0; i < fieldList.size(); i++) {
//				Image tempImg = fieldList.get(i).getImage();
//				int tempX = fieldList.get(i).getX();
//				int tempY = fieldList.get(i).getY();
//				int tempWidth = fieldList.get(i).getWidth();
//				int tempHeight = fieldList.get(i).getHeight();
//				g.drawImage(tempImg, tempX, tempY, tempWidth, tempHeight, null);
//			}
//			
//			g.drawImage(img, landimg.getWidth(null) / 2, imgY, this);
//		}

		// ������Ʈ ������۸�
		@Override
		public void update(Graphics g) {

			buffg.drawImage(back, backX, 0, back.getWidth(this) + 10, (int) (back.getHeight(this) * 1.5), null);
			buffg.drawImage(back1, back1X, 0, back.getWidth(this) + 10, (int) (back.getHeight(this) * 1.5), null);
			
			buffg.setColor(backFade);
			buffg.fillRect(0, 0, this.getWidth(), this.getHeight());
			

			for (int i = 0; i < fieldList.size(); i++) {
				Image tempImg = fieldList.get(i).getImage();
				int tempX = fieldList.get(i).getX();
				int tempY = fieldList.get(i).getY();
				int tempWidth = fieldList.get(i).getWidth();
				int tempHeight = fieldList.get(i).getHeight();
				buffg.drawImage(tempImg, tempX, tempY, tempWidth, tempHeight, null);
			}

			buffg.drawImage(img, landimg.getWidth(null) / 2, imgY, img.getWidth(null), img.getHeight(null), this);

//			buffg.drawLine(range, 0, range, 600); // ���� �׽�Ʈ��

			g.drawImage(buffImage, 0, 0, this);

		}

		// ����Ʈ ������Ʈ ������۸�
		@Override
		protected void paintComponent(Graphics g) {

			if (buffg == null) {
				buffImage = createImage(this.getWidth(), this.getHeight());
				if (buffImage == null) {
					System.out.println("���� ���۸��� ���� ��ũ�� ���� ����");
				} else {
					buffg = buffImage.getGraphics();
				}
			}
			update(g);

		}

	}

	/**
	 * Create the application.
	 */
	public Fade() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 800, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel panel = new MyPanel();
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
	}

}
