package panels;

import java.awt.AlphaComposite;
import java.awt.Button;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import ingame.Back;
import ingame.Cookie;
import ingame.CookieImg;
import ingame.Field;
import ingame.Jelly;
import ingame.MapObject;
import ingame.Tacle;
import util.Util;

public class GamePanel extends JPanel {
	
	// ��Ű �̹��� �����ܵ�
	private ImageIcon cookieIc; // �⺻���
	private ImageIcon jumpIc; // �������
	private ImageIcon doubleJumpIc; // �����������
	private ImageIcon fallIc; // ���ϸ��(���� ���� ��)
	private ImageIcon slideIc; // �����̵� ���
	private ImageIcon hitIc; // �ε����� ���

	// ��� �̹���
	private ImageIcon backIc; // ���� �� ���
	private ImageIcon secondBackIc; // 2��° ���

	// ���� �̹��� �����ܵ�
	private ImageIcon jelly1Ic;
	private ImageIcon jelly2Ic;
	private ImageIcon jelly3Ic;
	private ImageIcon jellyHPIc;

	private ImageIcon jellyEffectIc;

	// ���� �̹��� �����ܵ�
	private ImageIcon field1Ic; // ����
	private ImageIcon field2Ic; // ���߹���

	// ��ֹ� �̹��� �����ܵ�
	private ImageIcon tacle10Ic; // 1ĭ ��ֹ�
	private ImageIcon tacle20Ic; // 2ĭ ��ֹ�
	private ImageIcon tacle30Ic; // 3ĭ ��ֹ�
	private ImageIcon tacle40Ic; // 4ĭ ��ֹ�

	// ����Ʈ ����
	private List<Jelly> jellyList; // ���� ����Ʈ

	private List<Field> fieldList; // ���� ����Ʈ

	private List<Tacle> tacleList; // ��ֹ� ����Ʈ

	private int mapLength = 0;

	private int runPage = 0; // �� ȭ�� �̵��Ҷ����� ü���� ��� ���� ����

	private int runStage = 1; // ���������� Ȯ���ϴ� �����̴�. (�̱���)

	private int resultScore = 0; // ��������� �����ϴ� ����

	private int gameSpeed = 3; // ���� �ӵ�

	private int nowField = 2000; // ������ ���̸� ����.

	private JButton escButton; // esc ��ư (�׽�Ʈ ��)

	private boolean escKeyOn = false; // �Ͻ������� ���� escŰ Ȯ��

	private boolean downKeyOn = false; // �ٿ�Ű �������� ����

	int face; // ��Ű�� ����
	int foot; // ��Ű�� ��

	// �̹��� ���Ϸ� �� ���� �����´�.
	private int[] sizeArr; // �̹����� ���̿� ���̸� �������� 1���� �迭
	private int[][] colorArr; // �̹����� x y ��ǥ�� �ȼ� ������ �����ϴ� 2�����迭

	private Image buffImage; // ������� �̹���
	private Graphics buffg; // ������� g

	private AlphaComposite alphaComposite; // ���� ���� ������Ʈ

	Cookie c1; // ��Ű ������Ʈ
	Back b11; // ���1-1 ������Ʈ
	Back b12; // ���1-2 ������Ʈ

	MapObject mo1;
	MapObject mo2;
	MapObject mo3;
	MapObject mo4;
	
	JFrame superFrame;
	CardLayout cl;
	
	public GamePanel(JFrame superFrame, CardLayout cl) {
		this.superFrame = superFrame;
		this.cl = cl;
	}
	
	public void gameSet(CookieImg ci) {
		
		setFocusable(true);
		
		initCookieImg(ci);
		
		initObject(); // �ν��Ͻ� ����
		
	}
	
	public void gameStart() {
		
		mapMove(); // ��� ���� ��ֹ� �۵�
		
		fall(); // ���� ������ �ߵ�
				
		runRepaint(); // ������Ʈ ���ѹݺ� ����
		
		initListener(); // Ű������ �߰�
	}

	@Override
	protected void paintComponent(Graphics g) {

		// ������۴� �׸��� �̸��׷����� ȭ�鿡 ����Ѵ�.

		// ������� ����
		if (buffg == null) {
			buffImage = createImage(this.getWidth(), this.getHeight());
			if (buffImage == null) {
				System.out.println("���� ���۸��� ���� ��ũ�� ���� ����");
			} else {
				buffg = buffImage.getGraphics();
			}
		}

		// ���� ����
		Graphics2D g2 = (Graphics2D) buffg;

		super.paintComponent(buffg); // ���� �̹����� �����.

		// ����̹����� �׸���
		buffg.drawImage(b11.getImage(), b11.getX(), 0, null);
		buffg.drawImage(b12.getImage(), b12.getX(), 0, null);

		// ������ �׸���
		for (int i = 0; i < fieldList.size(); i++) {

			Field tempFoot = fieldList.get(i);

			// ����� �� ��Ƹ԰� �ϱ����� ��ġ
			if (tempFoot.getX() > -90 && tempFoot.getX() < 810) { // x���� -90~810�� ��ü�鸸 �׸���.

				buffg.drawImage(tempFoot.getImage(), tempFoot.getX(), tempFoot.getY(), tempFoot.getWidth(),
						tempFoot.getHeight(), null);
			}

		}

		// ������ �׸���
		for (int i = 0; i < jellyList.size(); i++) {

			Jelly tempJelly = jellyList.get(i);

			if (tempJelly.getX() > -90 && tempJelly.getX() < 810) {

				buffg.drawImage(tempJelly.getImage(), tempJelly.getX(), tempJelly.getY(), tempJelly.getWidth(),
						tempJelly.getHeight(), null);
			}
		}

		// ��ֹ��� �׸���
		for (int i = 0; i < tacleList.size(); i++) {

			Tacle tempTacle = tacleList.get(i);

			if (tempTacle.getX() > -90 && tempTacle.getX() < 810) {

				buffg.drawImage(tempTacle.getImage(), tempTacle.getX(), tempTacle.getY(), tempTacle.getWidth(),
						tempTacle.getHeight(), null);
			}
		}

		if (c1.isInvincible()) {
			// ��Ű�� alpha���� �޾ƿ´�
			alphaComposite = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, (float) c1.getAlpha() / 255);
			g2.setComposite(alphaComposite);

			// ��Ű�� �׸���
			buffg.drawImage(c1.getImage(), c1.getX() - 20, c1.getY(), (int) (cookieIc.getImage().getWidth(null) * 0.9),
					(int) (cookieIc.getImage().getHeight(null) * 0.9), null);

			// alpha���� �ǵ�����
			alphaComposite = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, (float) 255 / 255);
			g2.setComposite(alphaComposite);
		} else {
			// ��Ű�� �׸���
//			buffg.drawImage(c1.getImage(), c1.getX(), c1.getY(), c1.getWidth(), c1.getHeight(), null);
			buffg.drawImage(c1.getImage(), c1.getX() - 20, c1.getY(), (int) (cookieIc.getImage().getWidth(null) * 0.9),
					(int) (cookieIc.getImage().getHeight(null) * 0.9), null);
		}

		buffg.setColor(Color.BLACK);
		buffg.drawString(Integer.toString(resultScore), 700, 40); // ����

		buffg.setColor(Color.GREEN);
		buffg.fillRect(50, 40, c1.getHealth() / 2, 30); // ü�°�����

		if (escKeyOn) { // escŰ�� ������� ȭ���� �帮�� �����

			// alpha���� �������ϰ� �����
			alphaComposite = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, (float) 100 / 255);
			g2.setComposite(alphaComposite);

			buffg.setColor(Color.BLACK);

			buffg.fillRect(0, 0, 850, 550);

			// alpha���� �ǵ�����
			alphaComposite = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, (float) 255 / 255);
			g2.setComposite(alphaComposite);
		}

		// �����̹����� ȭ�鿡 ����Ѵ�
		g.drawImage(buffImage, 0, 0, this);
		g.drawRect(160, 280, 80, 120);

	}

	private void initCookieImg(CookieImg ci) {
		// ��Ű �̹��� �����ܵ�
		cookieIc = ci.getCookieIc(); // �⺻���
		jumpIc = ci.getJumpIc(); // �������
		doubleJumpIc = ci.getDoubleJumpIc(); // �����������
		fallIc = ci.getFallIc(); // ���ϸ��(���� ���� ��)
		slideIc = ci.getSlideIc(); // �����̵� ���
		hitIc = ci.getHitIc(); // �ε����� ���
	}

	private void makeMo() {

		mo1 = new MapObject(new ImageIcon("img/Objectimg/map1img/bg1.png"), new ImageIcon("img/Objectimg/map1img/bg2.png"),
				new ImageIcon("img/Objectimg/map1img/jelly1.png"), new ImageIcon("img/Objectimg/map1img/jelly2.png"),
				new ImageIcon("img/Objectimg/map1img/jelly3.png"), new ImageIcon("img/Objectimg/map1img/life.png"),
				new ImageIcon("img/Objectimg/map1img/effectTest.png"), new ImageIcon("img/Objectimg/map1img/fieldIc1.png"),
				new ImageIcon("img/Objectimg/map1img/fieldIc2.png"), new ImageIcon("img/Objectimg/map1img/tacle1.gif"),
				new ImageIcon("img/Objectimg/map1img/tacle2.png"), new ImageIcon("img/Objectimg/map1img/tacle3.png"),
				
				new ImageIcon("testimg/tacleTest40.png"));
		mo2 = new MapObject(new ImageIcon("testimg/backTest.png"), new ImageIcon("����̹���2"),
				new ImageIcon("testimg/jelly1Test.png"), new ImageIcon("testimg/jelly2Test.png"),
				new ImageIcon("testimg/jelly3Test.png"), new ImageIcon("testimg/jellyHPTest.png"),
				new ImageIcon("testimg/effectTest.png"), new ImageIcon("testimg/footTest.png"),
				new ImageIcon("testimg/footTest2.png"), new ImageIcon("testimg/tacleTest10.png"),
				new ImageIcon("testimg/tacleTest20.png"), new ImageIcon("testimg/tacleTest30.png"),
				new ImageIcon("testimg/tacleTest40.png"));
		mo3 = new MapObject(new ImageIcon("testimg/backTest.png"), new ImageIcon("����̹���2"),
				new ImageIcon("testimg/jelly1Test.png"), new ImageIcon("testimg/jelly2Test.png"),
				new ImageIcon("testimg/jelly3Test.png"), new ImageIcon("testimg/jellyHPTest.png"),
				new ImageIcon("testimg/effectTest.png"), new ImageIcon("testimg/footTest.png"),
				new ImageIcon("testimg/footTest2.png"), new ImageIcon("testimg/tacleTest10.png"),
				new ImageIcon("testimg/tacleTest20.png"), new ImageIcon("testimg/tacleTest30.png"),
				new ImageIcon("testimg/tacleTest40.png"));
		mo4 = new MapObject(new ImageIcon("testimg/backTest.png"), new ImageIcon("����̹���2"),
				new ImageIcon("testimg/jelly1Test.png"), new ImageIcon("testimg/jelly2Test.png"),
				new ImageIcon("testimg/jelly3Test.png"), new ImageIcon("testimg/jellyHPTest.png"),
				new ImageIcon("testimg/effectTest.png"), new ImageIcon("testimg/footTest.png"),
				new ImageIcon("testimg/footTest2.png"), new ImageIcon("testimg/tacleTest10.png"),
				new ImageIcon("testimg/tacleTest20.png"), new ImageIcon("testimg/tacleTest30.png"),
				new ImageIcon("testimg/tacleTest40.png"));
	}

	private void initImageIcon(MapObject mo) {

		// ��� �̹���
		backIc = mo.getBackIc(); // ���� �� ���
		secondBackIc = mo.getSecondBackIc(); // 2��° ���

		// ���� �̹��� �����ܵ�
		jelly1Ic = mo.getJelly1Ic();
		jelly2Ic = mo.getJelly2Ic();
		jelly3Ic = mo.getJelly3Ic();
		jellyHPIc = mo.getJellyHPIc();

		jellyEffectIc = mo.getJellyEffectIc();

		// ���� �̹��� �����ܵ�
		field1Ic = mo.getField1Ic(); // ����
		field2Ic = mo.getField2Ic(); // ���߹���

		// ��ֹ� �̹��� �����ܵ�
		tacle10Ic = mo.getTacle10Ic(); // 1ĭ ��ֹ�
		tacle20Ic = mo.getTacle20Ic(); // 2ĭ ��ֹ�
		tacle30Ic = mo.getTacle30Ic(); // 3ĭ ��ֹ�
		tacle40Ic = mo.getTacle40Ic(); // 4ĭ ��ֹ�
	}

	private void initMap(int num, int mapLength) {

		String tempMap = null;
		int tempMapLength = 0;

		if (num == 1) {
			tempMap = "testimg/firstMap.png";
		} else if (num == 2) {
			tempMap = "testimg/firstMap.png";
		} else if (num == 3) {
			tempMap = "testimg/firstMap.png";
		} else if (num == 4) {
			tempMap = "testimg/firstMap.png";
		}

		// �� ���� �ҷ�����
		try {
			sizeArr = Util.getSize(tempMap); // �� ����� �迭�� ����
			colorArr = Util.getPic(tempMap); // �� �ȼ����� �迭�� ����
		} catch (Exception e1) {
			e1.printStackTrace();
		}

		tempMapLength = sizeArr[0];
		int maxX = sizeArr[0]; // ���� ����
		int maxY = sizeArr[1]; // ���� ����

		for (int i = 0; i < maxX; i += 1) { // ������ 1ĭ�� �����ϱ� ������ 1,1������� �ݺ����� ������.
			for (int j = 0; j < maxY; j += 1) {
				if (colorArr[i][j] == 16776960) { // ������ 16776960�� ��� �⺻���� ����
					// ��ǥ�� 40�� ���ϰ�, ���̿� ���̴� 30���� �Ѵ�.
					jellyList.add(new Jelly(jelly1Ic.getImage(), i * 40 + mapLength * 40, j * 40, 30, 30, 1234));

				} else if (colorArr[i][j] == 13158400) { // ������ 13158400�� ��� ������� ����
					// ��ǥ�� 40�� ���ϰ�, ���̿� ���̴� 30���� �Ѵ�.
					jellyList.add(new Jelly(jelly2Ic.getImage(), i * 40 + mapLength * 40, j * 40, 30, 30, 2345));

				} else if (colorArr[i][j] == 9868800) { // ������ 9868800�� ��� ������� ����
					// ��ǥ�� 40�� ���ϰ�, ���̿� ���̴� 30���� �Ѵ�.
					jellyList.add(new Jelly(jelly3Ic.getImage(), i * 40 + mapLength * 40, j * 40, 30, 30, 3456));

				} else if (colorArr[i][j] == 16737280) { // ������ 16737280�� ��� �� ���� ����
					// ��ǥ�� 40�� ���ϰ�, ���̿� ���̴� 30���� �Ѵ�.
					jellyList.add(new Jelly(jellyHPIc.getImage(), i * 40 + mapLength * 40, j * 40, 30, 30, 4567));
				}
			}
		}

		for (int i = 0; i < maxX; i += 2) { // ������ 4ĭ�� �����ϴ� �����̱� ������ 2,2������� �ݺ����� ������.
			for (int j = 0; j < maxY; j += 2) {
				if (colorArr[i][j] == 0) { // ������ 0 �ϰ�� (������)
					// ��ǥ�� 40�� ���ϰ�, ���̿� ���̴� 80���� �Ѵ�.
					fieldList.add(new Field(field1Ic.getImage(), i * 40 + mapLength * 40, j * 40, 80, 80));

				} else if (colorArr[i][j] == 6579300) { // ������ 6579300 �ϰ�� (ȸ��)
					// ��ǥ�� 40�� ���ϰ�, ���̴� 80���� ���̴� 40 �Ѵ�.
					fieldList.add(new Field(field1Ic.getImage(), i * 40 + mapLength * 40, j * 40, 80, 40));
				}
			}
		}

		for (int i = 0; i < maxX; i += 2) { // ��ֹ��� 4ĭ �̻��� �����Ѵ�. ���� ����
			for (int j = 0; j < maxY; j += 2) {
				if (colorArr[i][j] == 16711680) { // ������ 16711680�� ��� (������) 1ĭ
					// ��ǥ�� 40�� ���ϰ�, ���̿� ���̴� 80���� �Ѵ�.
					tacleList.add(new Tacle(tacle10Ic.getImage(), i * 40 + mapLength * 40, j * 40, 80, 80, 0));

				} else if (colorArr[i][j] == 16711830) { // ������ 16711830�� ��� (��ȫ) 2ĭ
					// ��ǥ�� 40�� ���ϰ�, ���̿� ���̴� 160���� �Ѵ�.
					tacleList.add(new Tacle(tacle20Ic.getImage(), i * 40 + mapLength * 40, j * 40, 80, 160, 0));

				} else if (colorArr[i][j] == 16711935) { // ������ 16711830�� ��� (����ũ) 3ĭ
					// ��ǥ�� 40�� ���ϰ�, ���̿� ���̴� 240���� �Ѵ�.
					tacleList.add(new Tacle(tacle30Ic.getImage(), i * 40 + mapLength * 40, j * 40, 80, 240, 0));
				}
			}
		}

		this.mapLength = this.mapLength + tempMapLength;
	}

	private void initObject() {

		jellyList = new ArrayList<>(); // ���� ����Ʈ

		fieldList = new ArrayList<>(); // ���� ����Ʈ

		tacleList = new ArrayList<>(); // ��ֹ� ����Ʈ

		makeMo();

		initImageIcon(mo1);
		initMap(1, mapLength);

		initImageIcon(mo1);
		initMap(2, mapLength);

		initImageIcon(mo1);
		initMap(3, mapLength);

		initImageIcon(mo1);
		initMap(4, mapLength);

		// ��Ű �ν��Ͻ� ���� / �⺻ �ڷ�� Ŭ�����ȿ� ���� �Ǿ� �ֱ� ������ �̹����� �־���.
		c1 = new Cookie(cookieIc.getImage());

		// ��Ű�� ���� ��ġ / ��Ű�� x���� ���̸� ���� ��
		face = c1.getX() + c1.getWidth();

		// ��Ű�� �߹� ��ġ / ��Ű�� y���� ���̸� ���� ��
		foot = c1.getY() + c1.getHeight();

		// ���1-1 �ν��Ͻ� ����
		b11 = new Back(backIc.getImage(), 0, 0, // y �� (���� �ʿ�)
				backIc.getImage().getWidth(null), backIc.getImage().getHeight(null));

		// ���1-2 �ν��Ͻ� ����
		b12 = new Back(backIc.getImage(), backIc.getImage().getWidth(null), 0, // y �� (���� �ʿ�)
				backIc.getImage().getWidth(null), backIc.getImage().getHeight(null));

	}

	private void initListener() {
		addKeyListener(new KeyAdapter() { // Ű ������ �߰�

			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
					superFrame.requestFocus();
					cl.show(superFrame.getContentPane(), "end");
				}
				
				
//				if (e.getKeyCode() == KeyEvent.VK_ESCAPE) { // escŰ�� ������ ��
//					if (!escKeyOn) {
//						escKeyOn = true;
//						add(escButton);
//						repaint(); // ȭ���� ��Ӱ� �ϱ����� ������Ʈ
//					} else {
//						remove(escButton);
//						escKeyOn = false;
//					}
//				}

				if (!escKeyOn) {
					if (e.getKeyCode() == KeyEvent.VK_SPACE && c1.getCountJump() < 2) { // �����̽� Ű�� ������ ���������� 2�� �ƴҶ�

						jump(); // ���� �޼��� ����

					}
					if (e.getKeyCode() == KeyEvent.VK_DOWN) { // �ٿ�Ű�� ������ ��

						downKeyOn = true; // downKeyOn ������ true��

						if (c1.getImage() != slideIc.getImage() // ��Ű�̹����� �����̵� �̹����� �ƴϰ�
								&& !c1.isJump() // ���� ���� �ƴϸ�
								&& !c1.isFall()) { // ���� �ߵ� �ƴ� ��

							c1.setImage(slideIc.getImage()); // �̹����� �����̵��̹����� ����

						}
					}
				}
			}

			@Override
			public void keyReleased(KeyEvent e) {

				if (e.getKeyCode() == KeyEvent.VK_DOWN) { // �ٿ�Ű�� ���� ��

					downKeyOn = false; // downKeyOn ������ false��

					if (c1.getImage() != cookieIc.getImage() // ��Ű�̹����� �⺻�̹����� �ƴϰ�
							&& !c1.isJump() // ���� ���� �ƴϸ�
							&& !c1.isFall()) { // ���� �ߵ� �ƴ� ��

						c1.setImage(cookieIc.getImage()); // �̹����� �⺻�̹����� ����
					}
				}
			}
		});
	}

	private void runRepaint() {
		// ������Ʈ ���� ������
		new Thread(new Runnable() {

			@Override
			public void run() {
				while (true) {
					repaint();

					if (escKeyOn) { // esc Ű�� ������� ������Ʈ�� �����
						while (escKeyOn) {
							try {
								Thread.sleep(10);
							} catch (Exception e) {
								e.printStackTrace();
							}
						}
					}

					try {
						Thread.sleep(10);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		}).start();
	}

	private void mapMove() {
		new Thread(new Runnable() {

			@Override
			public void run() {
				while (true) {

					if (runPage > 800) { // 800�ȼ� �̵� ���� ü���� 10�� �����Ѵ� (���� �ʱ��̿� ���߾� ���ҷ� ����)
						c1.setHealth(c1.getHealth() - 10);
						runPage = 0;
					}

					runPage += gameSpeed; // ȭ���� �̵��ϸ� runPage�� �̵��� ��ŭ ����ȴ�.

					if (b11.getX() < -(b11.getWidth() - 1)) { // ���1-1 �� -(������)���� ������, �� ȭ������� ��γ����� ��� 1-2�ڿ� ����
						b11.setX(b11.getWidth());
					}
					if (b12.getX() < -(b12.getWidth() - 1)) { // ���1-2 �� -(������)���� ������, �� ȭ������� ��γ����� ��� 1-1�ڿ� ����
						b12.setX(b12.getWidth());
					}

					// ����� x��ǥ�� -1 ���ش� (�������� �帣�� ȿ��)
					b11.setX(b11.getX() - gameSpeed / 3);
					b12.setX(b12.getX() - gameSpeed / 3);

					// ������ġ�� -4 �� ���ش�. (�������� �帣�� ȿ��)
					for (int i = 0; i < fieldList.size(); i++) {

						Field tempField = fieldList.get(i); // �ӽ� ������ ����Ʈ �ȿ� �ִ� ���� ������ �ҷ�����

						if (tempField.getX() < -90) { // ������ x��ǥ�� -90 �̸��̸� �ش� ������ �����Ѵ�.(����ȭ)

							fieldList.remove(tempField);

						} else {

							tempField.setX(tempField.getX() - gameSpeed); // �� ���ǿ� �ش��� �ȵǸ� x��ǥ�� ������

						}
					}

					// ������ġ�� -4 �� ���ش�.
					for (int i = 0; i < jellyList.size(); i++) {

						Jelly tempJelly = jellyList.get(i); // �ӽ� ������ ����Ʈ �ȿ� �ִ� ���� ������ �ҷ�����

						if (tempJelly.getX() < -90) { // ������ x ��ǥ�� -90 �̸��̸� �ش� ������ �����Ѵ�.(����ȭ)

							fieldList.remove(tempJelly);

						} else {

							tempJelly.setX(tempJelly.getX() - gameSpeed); // �� ���ǿ� �ش��� �ȵǸ� x��ǥ�� ������

							foot = c1.getY() + c1.getHeight(); // ĳ���� �� ��ġ �罺ĵ

							if ( // ĳ������ ���� �ȿ� ������ ������ �������� �Դ´�.
							c1.getImage() != slideIc.getImage()
									&& tempJelly.getX() + tempJelly.getWidth() * 20 / 100 >= c1.getX()
									&& tempJelly.getX() + tempJelly.getWidth() * 80 / 100 <= face
									&& tempJelly.getY() + tempJelly.getWidth() * 20 / 100 >= c1.getY()
									&& tempJelly.getY() + tempJelly.getWidth() * 80 / 100 <= foot
									&& tempJelly.getImage() != jellyEffectIc.getImage()) {

								tempJelly.setImage(jellyEffectIc.getImage()); // ������ �̹����� ����Ʈ�� �ٲ۴�
								resultScore = resultScore + tempJelly.getScore(); // �������� ���� ������ ���Ѵ�

								if (tempJelly.getImage() == jellyHPIc.getImage()) {
									c1.setHealth(c1.getHealth() + 100);
								}

							} else if ( // �����̵� �ϴ� ĳ������ ���� �ȿ� ������ ������ �������� �Դ´�.
							c1.getImage() == slideIc.getImage()
									&& tempJelly.getX() + tempJelly.getWidth() * 20 / 100 >= c1.getX()
									&& tempJelly.getX() + tempJelly.getWidth() * 80 / 100 <= face
									&& tempJelly.getY() + tempJelly.getWidth() * 20 / 100 >= c1.getY()
											+ c1.getHeight() * 1 / 3
									&& tempJelly.getY() + tempJelly.getWidth() * 80 / 100 <= foot
									&& tempJelly.getImage() != jellyEffectIc.getImage()) {

								tempJelly.setImage(jellyEffectIc.getImage()); // ������ �̹����� ����Ʈ�� �ٲ۴�
								resultScore = resultScore + tempJelly.getScore(); // �������� ���� ������ ���Ѵ�

								if (tempJelly.getImage() == jellyHPIc.getImage()) {
									c1.setHealth(c1.getHealth() + 100);
								}

							}
						}
					}

					// ��ֹ���ġ�� - 4 �� ���ش�.
					for (int i = 0; i < tacleList.size(); i++) {

						Tacle tempTacle = tacleList.get(i); // �ӽ� ������ ����Ʈ �ȿ� �ִ� ���� ��ֹ��� �ҷ�����

						if (tempTacle.getX() < -90) {

							fieldList.remove(tempTacle); // ��ֹ��� x ��ǥ�� -90 �̸��̸� �ش� ������ �����Ѵ�.(����ȭ)

						} else {

							tempTacle.setX(tempTacle.getX() - gameSpeed); // �� ���ǿ� �ش��� �ȵǸ� x��ǥ�� ������

							face = c1.getX() + c1.getWidth(); // ĳ���� ���� ��ġ �罺ĵ
							foot = c1.getY() + c1.getHeight(); // ĳ���� �� ��ġ �罺ĵ

							if ( // �������°� �ƴϰ� �����̵� ���� �ƴϸ� ĳ������ ���� �ȿ� ��ֹ��� ������ �ε�����
							!c1.isInvincible() && c1.getImage() != slideIc.getImage()
									&& tempTacle.getX() + tempTacle.getWidth() / 2 >= c1.getX()
									&& tempTacle.getX() + tempTacle.getWidth() / 2 <= face
									&& tempTacle.getY() + tempTacle.getHeight() / 2 >= c1.getY()
									&& tempTacle.getY() + tempTacle.getHeight() / 2 <= foot) {

								hit(); // �ǰ� + ���� ������ �޼���

							} else if ( // �����̵� �ƴҽ� ������ֹ�
							!c1.isInvincible() && c1.getImage() != slideIc.getImage()
									&& tempTacle.getX() + tempTacle.getWidth() / 2 >= c1.getX()
									&& tempTacle.getX() + tempTacle.getWidth() / 2 <= face
									&& tempTacle.getY() <= c1.getY()
									&& tempTacle.getY() + tempTacle.getHeight() * 95 / 100 > c1.getY()) {

								hit(); // �ǰ� + ���� ������ �޼���

							} else if ( // �������°� �ƴϰ� �����̵� ���̸� ĳ������ ���� �ȿ� ��ֹ��� ������ �ε�����
							!c1.isInvincible() && c1.getImage() == slideIc.getImage()
									&& tempTacle.getX() + tempTacle.getWidth() / 2 >= c1.getX()
									&& tempTacle.getX() + tempTacle.getWidth() / 2 <= face
									&& tempTacle.getY() + tempTacle.getHeight() / 2 >= c1.getY()
											+ c1.getHeight() * 2 / 3
									&& tempTacle.getY() + tempTacle.getHeight() / 2 <= foot) {

								hit(); // �ǰ� + ���� ������ �޼���

							} else if ( // �����̵��� ������ֹ�
							!c1.isInvincible() && c1.getImage() == slideIc.getImage()
									&& tempTacle.getX() + tempTacle.getWidth() / 2 >= c1.getX()
									&& tempTacle.getX() + tempTacle.getWidth() / 2 <= face
									&& tempTacle.getY() < c1.getY() && tempTacle.getY()
											+ tempTacle.getHeight() * 95 / 100 > c1.getY() + c1.getHeight() * 2 / 3) {

								hit(); // �ǰ� + ���� ������ �޼���
							}
						}
					}

					// ��Ű�� ���� ������ ����ϴ� �ڵ�
					int tempField; // ������ġ�� ��� ��ĵ�ϴ� ��������
					int tempNowField; // ĳ���Ϳ� ������ ���̿� ���� ����Ǵ� ��������, ����� nowField�� �����Ѵ�

					// ��Ű�� �������¶�� ���� ���� �ʱ� ������ 400���� ���� / ������ �ƴ϶�� 2000(��������);
					if (c1.isInvincible()) {
						tempNowField = 400;
					} else {
						tempNowField = 2000;
					}

					for (int i = 0; i < fieldList.size(); i++) { // ������ ������ŭ �ݺ�

						int tempX = fieldList.get(i).getX(); // ������ x��

						if (tempX > c1.getX() - 60 && tempX <= face) { // ������ ĳ�� ���� ���̶��

							tempField = fieldList.get(i).getY(); // ������ y���� tempField�� �����Ѵ�

							foot = c1.getY() + c1.getHeight(); // ĳ���� �� ��ġ �罺ĵ

							// ������ġ�� tempNowField���� ����, �߹ٴ� ���� �Ʒ� �ִٸ�
							// ��, ĳ���� �� �Ʒ��� ���� ���� �ִ� �����̶�� tempNowField�� �����Ѵ�.
							if (tempField < tempNowField && tempField >= foot) {

								tempNowField = tempField;

							}
						}
					}

					nowField = tempNowField; // ����� nowField�� ������Ʈ �Ѵ�.

					if (escKeyOn) { // escŰ�� ������ ������ �����
						while (escKeyOn) {
							try {
								Thread.sleep(10);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
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
	}

	private void hit() {
		new Thread(new Runnable() {

			@Override
			public void run() {

				c1.setInvincible(true); // ��Ű�� �������·� ��ȯ

				System.out.println("�ǰݹ�������");

				c1.setHealth(c1.getHealth() - 100); // ��Ű�� ü���� 100 ��´�

				c1.setImage(hitIc.getImage()); // ��Ű�� �ε��� ������� ����

				c1.setAlpha(80); // ��Ű�� ������ 80���� ����

				try { // 0.5�� ���
					Thread.sleep(500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

				if (c1.getImage() == hitIc.getImage()) { // 0.5�� ���� �̹����� �ٲ��� �ʾҴٸ� �⺻�̹����� ����

					c1.setImage(cookieIc.getImage());

				}

				for (int j = 0; j < 11; j++) { // 2.5�ʰ� ĳ���Ͱ� �����δ�. (�ǰ��� ���� ���¸� �ν�)

					if (c1.getAlpha() == 80) { // �̹����� ���İ��� 80�̸� 160����

						c1.setAlpha(160);

					} else { // �ƴϸ� 80����

						c1.setAlpha(80);

					}
					try {
						Thread.sleep(250);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}

				c1.setAlpha(255); // ��Ű�� ������ �������� ����

				c1.setInvincible(false);
				System.out.println("�ǰݹ�������");
			}
		}).start();
	}

	private void fall() {
		new Thread(new Runnable() {

			@Override
			public void run() {
				while (true) {

					foot = c1.getY() + c1.getHeight(); // ĳ���� �� ��ġ �罺ĵ

					// �߹ٴ��� ���Ǻ��� ���� ������ �۵�
					if (!escKeyOn // �Ͻ������� �ߵ� �ȵ��� ��
							&& foot < nowField // ���߿� ������
							&& !c1.isJump() // ���� ���� �ƴϸ�
							&& !c1.isFall()) { // �������� ���� �ƴ� ��

						c1.setFall(true); // �������� ������ ��ȯ
						System.out.println("����");

						if (c1.getCountJump() == 2) { // ���������� ������ ��� ���� �̹����� ����
							c1.setImage(fallIc.getImage());
						}

						long t1 = Util.getTime(); // ����ð��� �����´�
						long t2;
						int set = 1; // ó�� ���Ϸ� (0~10) ���� �׽�Ʈ�غ���

						while (foot < nowField) { // ���� ���ǿ� ��� ������ �ݺ�

							t2 = Util.getTime() - t1; // ���� �ð����� t1�� ����

							int fallY = set + (int) ((t2) / 40); // ���Ϸ��� �ø���.

							foot = c1.getY() + c1.getHeight(); // ĳ���� �� ��ġ �罺ĵ

							if (foot + fallY >= nowField) { // �߹ٴ�+���Ϸ� ��ġ�� ���Ǻ��� ���ٸ� ���Ϸ��� �����Ѵ�.
								fallY = nowField - foot;
							}

							c1.setY(c1.getY() + fallY); // Y��ǥ�� ���Ϸ��� ���Ѵ�

							if (c1.isJump()) { // �������ٰ� ������ �ϸ� ��������
								break;
							}

							if (escKeyOn) {
								long tempT1 = Util.getTime();
								long tempT2 = 0;
								while (escKeyOn) {
									try {
										Thread.sleep(10);
									} catch (InterruptedException e) {
										e.printStackTrace();
									}
								}
								tempT2 = Util.getTime() - tempT1;
								t1 = t1 + tempT2;
							}

							try {
								Thread.sleep(10);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}

						}
						c1.setFall(false);

						if (downKeyOn // �ٿ�Ű�� �������°�
								&& !c1.isJump() // ���� ���°� �ƴϰ�
								&& !c1.isFall() // ���� ���°� �ƴϰ�
								&& c1.getImage() != slideIc.getImage()) { // ��Ű �̹����� �����̵� �̹����� �ƴ� ���

							c1.setImage(slideIc.getImage()); // ��Ű �̹����� �����̵�� ����

						} else if (!downKeyOn // �ٿ�Ű�� �������°� �ƴϰ�
								&& !c1.isJump() // ���� ���°� �ƴϰ�
								&& !c1.isFall() // ���� ���°� �ƴϰ�
								&& c1.getImage() != cookieIc.getImage()) { // ��Ű �̹����� �⺻ �̹����� �ƴ� ���

							c1.setImage(cookieIc.getImage());
						}

						if (!c1.isJump()) { // ���� ���� ��� ���� ���� �ƴ� �� �������� ī��Ʈ�� 0���� ����
							c1.setCountJump(0);
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
	}

	private void jump() {
		new Thread(new Runnable() {

			@Override
			public void run() {

				c1.setCountJump(c1.getCountJump() + 1); // ���� Ƚ�� ����

				int nowJump = c1.getCountJump(); // �̹������� �������� ������������ ����

				c1.setJump(true); // ���������� ����

				if (c1.getCountJump() == 1) { // ���� Ƚ���� 1�̶��

					System.out.println("����");
					c1.setImage(jumpIc.getImage());

				} else if (c1.getCountJump() == 2) { // ���� Ƚ���� 2���

					System.out.println("��������");
					c1.setImage(doubleJumpIc.getImage());

				}

				long t1 = Util.getTime(); // ����ð��� �����´�
				long t2;
				int set = 8; // ���� ��� ����(0~20) ������ �ٲ㺸��
				int jumpY = 1; // 1�̻����θ� �����ϸ� �ȴ�.(while�� ���� ����)

				while (jumpY >= 0) { // ��� ���̰� 0�϶����� �ݺ�

					t2 = Util.getTime() - t1; // ���� �ð����� t1�� ����

					jumpY = set - (int) ((t2) / 40); // jumpY �� �����Ѵ�.

					c1.setY(c1.getY() - jumpY); // Y���� �����Ѵ�.

					if (nowJump != c1.getCountJump()) { // ������ �ѹ� ���Ǹ� ù��° ������ �����.
						break;
					}

					if (escKeyOn) {
						long tempT1 = Util.getTime();
						long tempT2 = 0;
						while (escKeyOn) {
							try {
								Thread.sleep(10);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
						}
						tempT2 = Util.getTime() - tempT1;
						t1 = t1 + tempT2;
					}

					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}

				if (nowJump == c1.getCountJump()) { // ������ ��¥ ������ ���� Ȯ��
					c1.setJump(false); // �������¸� false�� ����
				}

			}
		}).start();
	}
}
