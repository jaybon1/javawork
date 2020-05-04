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

public class Foots1 {

	private JFrame frame;

	int field = 400; // ���� ����


	List<String> fieldStr = new ArrayList<>(); // ��Ʈ������ �� �ʵ�迭�� �����Ѵ�

	List<List> fieldList = new ArrayList<>(); //  ���� ����Ʈ�� ��� ����Ʈ

	

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
					Foots1 window = new Foots1();
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
			
			// �ʿ� ������ ������ ����̴�. (0�̸� ���, 1�̸� ����)
			// �迭�� ������ ���� ����
			fieldStr.add("0000000000000000000000001111111111111110000000000000000000000000000000000000000000000000000");
			fieldStr.add("0000000000000000111111111000000000000001000000000000000000000000000000000000000000000000000");
			fieldStr.add("0000000000001111000000000111111000000000100000000011111111111111111111111000000000000000000");
			fieldStr.add("0000011111110000000000000000000001111100111111111100000000000000000000000000000000000000000");
			fieldStr.add("1111100000000111111111111111110000000000000000000000000000000000000000000000000000000000000");
			
			Collections.reverse(fieldStr);  // �迭�� ������ ���� �����߱� ������ ����� �ٴ��� 0���� �ǵ��� ��

			
			for (int i = 0; i < fieldStr.size(); i++) {  // fieldStr�� ���� (���� 5��)��ŭ �ݺ��Ѵ�.

				int tempY = 400 - 50 * i; // �ٴ��� 400�̸� 1��� �ö� ������ Y���� 50�� �پ���.
				
				List<Foot> tempList = new ArrayList<>(); // �ӽ� ����Ʈ (for�� �ȿ��� �����߱� ������ 1ȸ���Ҷ����� ���� ���� �� �ʱ�ȭ�ȴ�.)

				for (int j = 0; j < fieldStr.get(i).length(); j++) { // fieldStr�� ����� ���ڿ��� ���̸�ŭ �ݺ��Ѵ�.
					
					int tempX = landimg.getWidth(null) * j; // ������ X���� 0���� ������ ���̸�ŭ �þ��.
					
					if (getGround(fieldStr.get(i), j) == 1) { // getGround�Լ��� ���ڿ� ������� 1���� 0���� �Ǻ��Ѵ�.
						
						// 1�̶�� ���� �߰� 0�̶�� ����
						tempList.add(new Foot(landimg, tempX, tempY, landimg.getWidth(null), landimg.getHeight(null)));
					}
				}
				
				// ������ �����ص� Foot �ӽø���Ʈ�� ���������� �����ص� ����Ʈ�� �����Ѵ�.
				fieldList.add(tempList);
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
						
						
						for (int i = 0; i < fieldList.size(); i++) { // fieldList�� �����ŭ (���� 5��)

							for (int j = 0; j < fieldList.get(i).size(); j++) { // fieldList.get(i)�� ������ ��ŭ (���� ������ ������ŭ)
								
								// (Foot)�� ĳ���� ������ ������ ���������� �߰���.
								Foot f = (Foot)fieldList.get(i).get(j); //���� ������ �ҷ��´�.
								
								f.setX(f.getX() - 4); // ������ X���� -4���ش�.
							}
						}
						
						
						range = (int) (landimg.getWidth(null) * 1.3); // ĳ���Ͱ� ������ �� �ִ� ��ġ ( ���߿� �����ؾߵ�)
						
						List<Integer> countList = new ArrayList<>(); // ������ �ȿ� �ӽ������� ������ ����Ʈ
						
						for (int i = 0; i < fieldList.size(); i++) { // fieldList�� �����ŭ (���� 5��)

							for (int j = 0; j < fieldList.get(i).size(); j++) { // fieldList.get(i)�� ������ ��ŭ (���� ������ ������ŭ)
								
								count = 0; // count�� 0���� �����Ѵ�.
								
								Foot f = (Foot)fieldList.get(i).get(j); // ���� ������ �ҷ��´�.
								
								if (f.getX() >= 0 && f.getX() < range) { // ������ ĳ�� ���� ���̶��
									count = 1; // ī��Ʈ 1�� �����ϰ� �극��ũ�Ѵ�.
									break;
								}
							}
							countList.add(count); // ����Ʈ�� count�� �߰��Ѵ�. ���� 1���� ������ �ִٸ� [1, 0, 0, 0, 0] �̵ȴ�.
						}
						
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
		

		for (int i = 0; i < fieldList.size(); i++) {

			for (int j = 0; j < fieldList.get(i).size(); j++) {

				Foot f = (Foot) fieldList.get(i).get(j); // ���� ����

				buffg.drawImage(f.getImage(), f.getX(), f.getY(), f.getWidth(), f.getHeight(), null); // ������ �׷��ش�

			}
		}

		buffg.drawImage(img, landimg.getWidth(null) / 2, imgY, img.getWidth(null), img.getHeight(null), this);

		g.drawImage(buffImage, 0, 0, this);

	}


	}

	/**
	 * Create the application.
	 */
	public Foots1() {
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
