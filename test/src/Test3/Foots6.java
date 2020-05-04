package Test3;

import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.lang.reflect.Field;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.AlphaComposite;
import java.awt.BorderLayout;

public class Foots6 {

	private JFrame frame;

	int field = 400; // ���� ����

	List<Jelly> jellyList = new ArrayList<>();

	List<Foot> fieldList = new ArrayList<>(); // ���� ����Ʈ

	List<Tacle> tacleList = new ArrayList<>(); // ��ֹ� ����Ʈ

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

	ImageIcon jelly1 = new ImageIcon("img/jelly1.png");
	ImageIcon eff1 = new ImageIcon("img/effect/tw1.png");

	ImageIcon tacle1 = new ImageIcon("img/tacle1.png");

	int[][] colorArr; // �̹����� x y ��ǥ�� ������ �����ϴ� 2�����迭 colorArr[0][0] �� ȣ���ϸ� 16777215 ���� ���´�.
	int[] sizeArr; // �̹����� ���̿� ���̸� �������� 1���� �迭 temp

	ImageIcon icJump = new ImageIcon("img/c1jump.gif");
	ImageIcon icDoubleJump = new ImageIcon("img/c1doubleJump.gif");
	ImageIcon icfall = new ImageIcon("img/c1fall.png");
	ImageIcon icfallOver = new ImageIcon("img/c1fallOver.png");
	ImageIcon icnap = new ImageIcon("img/c1nap.gif");
	ImageIcon ichit = new ImageIcon("img/hit.gif");
	int cookieAlpha = 255;

	ImageIcon backIc = new ImageIcon("img/back1.png");
	Image back = backIc.getImage();
	Image back1 = backIc.getImage();
	int backX = 0;
	int back1X = back.getWidth(null);

	int imgY = 0; // �̹����� �����ϴ� ��ǥ

	boolean fall = false; // ���� ���������� Ȯ��
	boolean jump = false; // ���� ���������� Ȯ��

	int doubleJump = 0; // ���� ī��Ʈ (2���Ǹ� �������� �����̴�)
	
	boolean invincible = false;
	
	private AlphaComposite alphaComposite;

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
					Foots6 window = new Foots6();
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

			try {
				sizeArr = Bf3.getSize("img/firstMap.png");
				colorArr = Bf3.getPic("img/firstMap.png");
			} catch (Exception e1) {
				e1.printStackTrace();
			}

			int maxX = sizeArr[0];
			int maxY = sizeArr[1];

			for (int i = 0; i < maxX; i += 2) { // ������ 4ĭ�� �����ϴ� �����̱� ������ 2,2������� �ݺ����� ������.
				for (int j = 0; j < maxY; j += 2) {
					if (colorArr[i][j] == 0) { // ������ 0 �ϰ�� (������)
						fieldList.add(new Foot(landimg, i * 40, j * 40, 80, 80)); // ��ǥ�� 40�� ���ϰ�, ���̿� ���̴� 80���� �Ѵ�.
					}

				}
			}

			for (int i = 0; i < maxX; i += 1) { // ������ 1ĭ�� �����ϱ� ������ 1,1������� �ݺ����� ������.
				for (int j = 0; j < maxY; j += 1) {
					if (colorArr[i][j] == 16776960) { // ������ 16776960�� ��� (�����)
						jellyList.add(new Jelly(jelly1.getImage(), i * 40, j * 40, 30, 30, 255)); // ��ǥ�� 40�� ���ϰ�, ���̿�
																									// ���̴� 30���� �Ѵ�.
					}

				}
			}

			for (int i = 0; i < maxX; i += 2) { // ��ֹ��� 4ĭ �̻��� �����Ѵ�. ���� ����
				for (int j = 0; j < maxY; j += 2) {
					if (colorArr[i][j] == 16711680) { // ������ 16711680�� ��� (������)
						tacleList.add(new Tacle(tacle1.getImage(), i * 40, j * 40, 80, 80)); // ��ǥ�� 40�� ���ϰ�, ���̿� ���̴�
																								// 30���� �Ѵ�.
					}

				}
			}
			
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

			new Thread(new Runnable() { // ��� �� ���� ��ǥ �̵� ������

				@Override
				public void run() {
					while (true) {
						foot = imgY + fallOverY; // foot

						if (backX < -(back.getWidth(null)-1)) {
							backX = back.getWidth(null);
						}
						if (back1X < -(back.getWidth(null)-1)) {
							back1X = back.getWidth(null);
						}

						backX--;
						back1X--;
						
						
						

						// ������ġ�� -4 �� ���ش�.
						for (int i = 0; i < fieldList.size(); i++) {
							
							Foot tempFoot = fieldList.get(i);
							
							if(tempFoot.getX() < -90) { // ������  x��ǥ�� -90 �̸��̸� �ش� ������ �����Ѵ�.
								fieldList.remove(tempFoot);
							} else {
								tempFoot.setX(tempFoot.getX() - 4);						
							}
						}

						// ������ġ�� -4 �� ���ش�.
						for (int i = 0; i < jellyList.size(); i++) {
							Jelly tempJelly = jellyList.get(i);
							
							if(tempJelly.getX() < -90) {
								fieldList.remove(tempJelly);
							} else {
								tempJelly.setX(tempJelly.getX() - 4);								
								if(tempJelly.getY() > imgY 
										&& tempJelly.getY() < imgY+fallOverY
										&& tempJelly.getX() > 160
										&& tempJelly.getX() < 240) {
									tempJelly.setImage(eff1.getImage());
									
								}
							}
						}

						// ��ֹ���ġ�� - 4 �� ���ش�.
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
											
											cookieAlpha = 128;
											try {
												Thread.sleep(500);
											} catch (InterruptedException e) {
												e.printStackTrace();
											}
											img = ic.getImage();
											
											
											for (int j = 0; j < 11; j++) {
												if(cookieAlpha == 128) {
													cookieAlpha = 255;
												} else {
													cookieAlpha = 128;
												}
												try {
													Thread.sleep(250);
												} catch (InterruptedException e) {
													e.printStackTrace();
												}
											}
											
											invincible = false;
											System.out.println("��������");
										}
									}).start();
								}
							}
						}

//						range = 160; // ĳ���Ͱ� ������ �� �ִ� ��ġ ( ���߿� �����ؾߵ�)

						List<Integer> countList = new ArrayList<>(); // ������ �ȿ� �ӽ������� ������ ����Ʈ

						int tempField; // ������ġ�� ��� ��ĵ�ϴ� ����
						int tempNowField;
						if (invincible) {
							tempNowField = 400; // nowField�� �������ִ� �ӽ� ����
						} else {
							tempNowField = 2000;
						}

						for (int i = 0; i < fieldList.size(); i++) {

							int tempX = fieldList.get(i).getX(); // ������ x��

							if (tempX >= 160 && tempX < 240) { // ������ ĳ�� ���� ���̶��

								tempField = fieldList.get(i).getY(); // ������ y��

//								System.out.println(imgY + fallOverY + "  " + tempField);
								// ������ġ�� tempNowField���� �۰�, �߹ٴ��� ��ġ�� tempField���� ���� �ִٸ�.
								if (tempField < tempNowField && imgY + fallOverY <= tempField) {

									tempNowField = tempField;

								}
							}
						}

						nowField = tempNowField; // ����� nowField�� ������Ʈ �Ѵ�.

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
									// fall = false;
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
			
			super.paintComponent(buffg);

			buffg.drawImage(back, backX, 0,back.getWidth(this), (int) (back.getHeight(this) * 1.5),
					null);
			buffg.drawImage(back1, back1X, 0, back.getWidth(this), (int) (back.getHeight(this) * 1.5),
					null);

			for (int i = 0; i < fieldList.size(); i++) {

				Foot tempFoot = fieldList.get(i);

				if (tempFoot.getX() > -90 && tempFoot.getX() < 810) { // ����� ��Ƹ��� �ʰ� �ϱ����� ��ġ
					buffg.drawImage(tempFoot.getImage(), tempFoot.getX(), tempFoot.getY(), tempFoot.getWidth(),
							tempFoot.getHeight(), null);
				}

			}

			for (int i = 0; i < jellyList.size(); i++) {

				Jelly tempJelly = jellyList.get(i);

				if (tempJelly.getX() > -90 && tempJelly.getX() < 810) {
					buffg.drawImage(tempJelly.getImage(), tempJelly.getX(), tempJelly.getY(), tempJelly.getWidth(),
							tempJelly.getHeight(), null);
				}
			}

			for (int i = 0; i < tacleList.size(); i++) {

				Tacle tempTacle = tacleList.get(i);

				if (tempTacle.getX() > -90 && tempTacle.getX() < 810) {
					buffg.drawImage(tempTacle.getImage(), tempTacle.getX(), tempTacle.getY(), tempTacle.getWidth(),
							tempTacle.getHeight(), null);
				}
			}
			Graphics2D g2 = (Graphics2D)buffg;

			alphaComposite = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, (float)cookieAlpha/255);
		    g2.setComposite(alphaComposite);
		    
			buffg.drawImage(img, 160, imgY, img.getWidth(null), img.getHeight(null), this);
			
			alphaComposite = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, (float)255/255);
		    g2.setComposite(alphaComposite);
			
			
			g.drawImage(buffImage, 0, 0, this);
			
			g.drawLine(160, 0, 160, 500);
			g.drawLine(240, 0, 240, 500);

		}

	}

	/**
	 * Create the application.
	 */
	public Foots6() {
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
