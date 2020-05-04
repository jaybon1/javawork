package Test3;

import java.awt.EventQueue;
import java.awt.Graphics;
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
import java.awt.BorderLayout;

public class Foots3 {

	private JFrame frame;

	int field = 400; // ���� ����

	//���� ���� ����
	String f4 = "0000000000000000000000001111111111111110000000000000000000000000000000000000000000000000000";
	String f3 = "0000000000000000111111111000000000000001000000000000000000000000000000000000000000000000000";
	String f2 = "0000000000001111000000000111111000000000100000000011111111111111111111111000000000000000000";
	String f1 = "0000011111110000000000000000000001111100111111111100000000000000000000000000000000000000000";
	String f0 = "1111100000000111111111111111110000000000000000000000000000000000000000000000000000000000000";


	List<Foot> footList4 = new ArrayList<>(); //  ���� ����Ʈ
	List<Foot> footList3 = new ArrayList<>(); //  ���� ����Ʈ
	List<Foot> footList2 = new ArrayList<>(); //  ���� ����Ʈ
	List<Foot> footList1 = new ArrayList<>(); //  ���� ����Ʈ
	List<Foot> footList0 = new ArrayList<>(); //  ���� ����Ʈ

	

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

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Foots3 window = new Foots3();
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
			
			for (int i = 0; i < f4.length(); i++) {
				int tempY = 200;
				int tempX = i*landimg.getWidth(null);
				if(getGround(f4, i) == 1) {
					footList4.add(new Foot(landimg, tempX, tempY, landimg.getWidth(null), landimg.getHeight(null)));
				}
			}
			for (int i = 0; i < f3.length(); i++) {
				int tempY = 250;
				int tempX = i*landimg.getWidth(null);
				if(getGround(f3, i) == 1) {
					footList3.add(new Foot(landimg, tempX, tempY, landimg.getWidth(null), landimg.getHeight(null)));
				}
			}
			for (int i = 0; i < f2.length(); i++) {
				int tempY = 300;
				int tempX = i*landimg.getWidth(null);
				if(getGround(f2, i) == 1) {
					footList2.add(new Foot(landimg, tempX, tempY, landimg.getWidth(null), landimg.getHeight(null)));
				}
			}
			for (int i = 0; i < f1.length(); i++) {
				int tempY = 350;
				int tempX = i*landimg.getWidth(null);
				if(getGround(f1, i) == 1) {
					footList1.add(new Foot(landimg, tempX, tempY, landimg.getWidth(null), landimg.getHeight(null)));
				}
			}
			for (int i = 0; i < f0.length(); i++) {
				int tempY = 400;
				int tempX = i*landimg.getWidth(null);
				if(getGround(f0, i) == 1) {
					footList0.add(new Foot(landimg, tempX, tempY, landimg.getWidth(null), landimg.getHeight(null)));
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
						
						for (int i = 0; i < footList4.size(); i++) {
							footList4.get(i).setX(footList4.get(i).getX() -4);
						}
						for (int i = 0; i < footList3.size(); i++) {
							footList3.get(i).setX(footList3.get(i).getX() -4);
						}
						for (int i = 0; i < footList2.size(); i++) {
							footList2.get(i).setX(footList2.get(i).getX() -4);
						}
						for (int i = 0; i < footList1.size(); i++) {
							footList1.get(i).setX(footList1.get(i).getX() -4);
						}
						for (int i = 0; i < footList0.size(); i++) {
							footList0.get(i).setX(footList0.get(i).getX() -4);
						}
						
						
						range = (int) (landimg.getWidth(null) * 1.3); // ĳ���Ͱ� ������ �� �ִ� ��ġ ( ���߿� �����ؾߵ�)
						
						List<Integer> countList = new ArrayList<>(); // ������ �ȿ� �ӽ������� ������ ����Ʈ
						
						for (int i = 0; i < footList4.size(); i++) {
							count = 0;
							int tempX = footList4.get(i).getX();
							if (tempX >= 0 && tempX < range) { // ������ ĳ�� ���� ���̶��
								count = 1; // ī��Ʈ 1�� �����ϰ� �극��ũ�Ѵ�.
								break;
							}
						}
						countList.add(count);
						
						for (int i = 0; i < footList3.size(); i++) {
							count = 0;
							int tempX = footList3.get(i).getX();
							if (tempX >= 0 && tempX < range) { // ������ ĳ�� ���� ���̶��
								count = 1; // ī��Ʈ 1�� �����ϰ� �극��ũ�Ѵ�.
								break;
							}
						}
						countList.add(count);
						
						for (int i = 0; i < footList2.size(); i++) {
							count = 0;
							int tempX = footList2.get(i).getX();
							if (tempX >= 0 && tempX < range) { // ������ ĳ�� ���� ���̶��
								count = 1; // ī��Ʈ 1�� �����ϰ� �극��ũ�Ѵ�.
								break;
							}
						}
						countList.add(count);
						
						for (int i = 0; i < footList1.size(); i++) {
							count = 0;
							int tempX = footList1.get(i).getX();
							if (tempX >= 0 && tempX < range) { // ������ ĳ�� ���� ���̶��
								count = 1; // ī��Ʈ 1�� �����ϰ� �극��ũ�Ѵ�.
								break;
							}
						}
						countList.add(count);
						
						for (int i = 0; i < footList0.size(); i++) {
							count = 0;
							int tempX = footList0.get(i).getX();
							if (tempX >= 0 && tempX < range) { // ������ ĳ�� ���� ���̶��
								count = 1; // ī��Ʈ 1�� �����ϰ� �극��ũ�Ѵ�.
								break;
							}
						}
						countList.add(count);
						
						
						Collections.reverse(countList);
						
						
						
						
						
						int tempField = 2000; // �ӽú����� ���� ��ġ�� �����Ѵ�.
						
						for (int i = countList.size() - 1; i >= 0; i--) { // �� ���� ���Ǻ��� Ȯ���ؾ��ϱ� ������ �迭������ ��ĵ�Ѵ�.
							
							int tempY = 400 - 50 * i; // ������ ���� ������ ������ 400 - 50*4 = 200�̵ȴ�.
							
							if(countList.get(i) == 1 && foot <= tempY ) { // �ش� ���̿� ������ �ְ� (1), �߹ٴ��� ���̺��� ���ų� ���ٸ�!
								
								tempField = tempY; // �ӽú����� tempY�� �ȴ�.
								
								break; // ������ Ȯ�������� ������ Ż���Ѵ�.
							}
							
						}
						
						nowField = tempField; // ���������� nowField�� tempField�� ����������.(������ ������ �־��� ������ �´ٸ� 200)
						

						System.out.println(nowField);
						

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

		buffg.drawImage(back, backX, 0, back.getWidth(this) + 10, (int) (back.getHeight(this) * 1.5), null);
		buffg.drawImage(back1, back1X, 0, back.getWidth(this) + 10, (int) (back.getHeight(this) * 1.5), null);
		

		for (int i = 0; i < footList4.size(); i++) {
			Image tempImg = footList4.get(i).getImage();
			int tempX = footList4.get(i).getX();
			int tempY = footList4.get(i).getY();
			int tempWidth = footList4.get(i).getWidth();
			int tempHeight = footList4.get(i).getHeight();
			buffg.drawImage(tempImg, tempX, tempY, tempWidth, tempHeight, null);
		}
		for (int i = 0; i < footList3.size(); i++) {
			Image tempImg = footList3.get(i).getImage();
			int tempX = footList3.get(i).getX();
			int tempY = footList3.get(i).getY();
			int tempWidth = footList3.get(i).getWidth();
			int tempHeight = footList3.get(i).getHeight();
			buffg.drawImage(tempImg, tempX, tempY, tempWidth, tempHeight, null);
		}
		for (int i = 0; i < footList2.size(); i++) {
			Image tempImg = footList2.get(i).getImage();
			int tempX = footList2.get(i).getX();
			int tempY = footList2.get(i).getY();
			int tempWidth = footList2.get(i).getWidth();
			int tempHeight = footList2.get(i).getHeight();
			buffg.drawImage(tempImg, tempX, tempY, tempWidth, tempHeight, null);
		}
		for (int i = 0; i < footList1.size(); i++) {
			Image tempImg = footList1.get(i).getImage();
			int tempX = footList1.get(i).getX();
			int tempY = footList1.get(i).getY();
			int tempWidth = footList1.get(i).getWidth();
			int tempHeight = footList1.get(i).getHeight();
			buffg.drawImage(tempImg, tempX, tempY, tempWidth, tempHeight, null);
		}
		for (int i = 0; i < footList0.size(); i++) {
			Image tempImg = footList0.get(i).getImage();
			int tempX = footList0.get(i).getX();
			int tempY = footList0.get(i).getY();
			int tempWidth = footList0.get(i).getWidth();
			int tempHeight = footList0.get(i).getHeight();
			buffg.drawImage(tempImg, tempX, tempY, tempWidth, tempHeight, null);
		}
		
		

		buffg.drawImage(img, landimg.getWidth(null) / 2, imgY, img.getWidth(null), img.getHeight(null), this);

		g.drawImage(buffImage, 0, 0, this);

	}


	}

	/**
	 * Create the application.
	 */
	public Foots3() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 800, 480);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel panel = new MyPanel();
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
	}

}
