package Test5;

import java.awt.EventQueue;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.plaf.basic.BasicTreeUI.TreeCancelEditingAction;

import oracle.net.ns.Packet;

import java.awt.AlphaComposite;
import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;

public class CookieRun {

	private JFrame frame;

	private JPanel panel; // �г�
	private JPanel panel1; // �г�

	private Button escButton; // esc ��ư (�׽�Ʈ ��)

	// ��� �̹���
	private ImageIcon backIc = new ImageIcon("testimg/backTest.png"); // ���� �� ���
	private ImageIcon secondBackIc = new ImageIcon("����̹���2"); // 2��° ���

	// ��Ű �̹��� �����ܵ�
	private ImageIcon cookieIc = new ImageIcon("img/c1.gif"); // �⺻���
	private ImageIcon jumpIc = new ImageIcon("testimg/jumpTest.png"); // �������
	private ImageIcon doubleJumpIc = new ImageIcon("testimg/doubleJumpTest.png"); // �����������
	private ImageIcon fallIc = new ImageIcon("testimg/fallTest.png"); // ���ϸ��(���� ���� ��)
	private ImageIcon slideIc = new ImageIcon("testimg/slideTest.png"); // �����̵� ���
	private ImageIcon hitIc = new ImageIcon("testimg/hitTest.png"); // �ε����� ���

	// ���� �̹��� �����ܵ�
	private ImageIcon jelly1Ic = new ImageIcon("testimg/jelly1Test.png");
	private ImageIcon jelly2Ic = new ImageIcon("testimg/jelly2Test.png");
	private ImageIcon jelly3Ic = new ImageIcon("testimg/jelly3Test.png");
	private ImageIcon jellyHPIc = new ImageIcon("testimg/jellyHPTest.png");

	private ImageIcon jellyEffectIc = new ImageIcon("testimg/effectTest.png");

	// ���� �̹��� �����ܵ�
	private ImageIcon field1Ic = new ImageIcon("testimg/footTest.png"); // ����
	private ImageIcon field2Ic = new ImageIcon("testimg/footTest2.png"); // ���߹���

	// ��ֹ� �̹��� �����ܵ�
	private ImageIcon tacle10Ic = new ImageIcon("testimg/tacleTest10.png"); // 1ĭ ��ֹ�
	private ImageIcon tacle20Ic = new ImageIcon("testimg/tacleTest20.png"); // 2ĭ ��ֹ�
	private ImageIcon tacle30Ic = new ImageIcon("testimg/tacleTest30.png"); // 3ĭ ��ֹ�
	private ImageIcon tacle40Ic = new ImageIcon("testimg/tacleTest40.png"); // 3ĭ ��ֹ�

	// ����Ʈ ����
	private List<Jelly> jellyList = new ArrayList<>(); // ���� ����Ʈ

	private List<Field> fieldList = new ArrayList<>(); // ���� ����Ʈ

	private List<Tacle> tacleList = new ArrayList<>(); // ��ֹ� ����Ʈ

	private int runPage = 0; // �� ȭ�� �̵��Ҷ����� ü���� ��� ���� ����

	private int runStage = 1; // ���������� Ȯ���ϴ� �����̴�. (�̱���)

	private int resultScore = 0; // ��������� �����ϴ� ����

	private int gameSpeed = 3; // ���� �ӵ�

	private int nowField = 2000; // ������ ���̸� ����.

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

	class MyPanel extends JPanel {

		public MyPanel() {

			setFocusable(true);

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

			// �� ���� �ҷ�����
			try {
				sizeArr = Util.getSize("testimg/firstMap1.png"); // �� ����� �迭�� ����
				colorArr = Util.getPic("testimg/firstMap1.png"); // �� �ȼ����� �迭�� ����
			} catch (Exception e1) {
				e1.printStackTrace();
			}

			int maxX = sizeArr[0]; // ���� ����
			int maxY = sizeArr[1]; // ���� ����

			for (int i = 0; i < maxX; i += 1) { // ������ 1ĭ�� �����ϱ� ������ 1,1������� �ݺ����� ������.
				for (int j = 0; j < maxY; j += 1) {
					if (colorArr[i][j] == 16776960) { // ������ 16776960�� ��� �⺻���� ����
						// ��ǥ�� 40�� ���ϰ�, ���̿� ���̴� 30���� �Ѵ�.
						jellyList.add(new Jelly(jelly1Ic.getImage(), i * 40, j * 40, 30, 30, 1234));

					} else if (colorArr[i][j] == 13158400) { // ������ 13158400�� ��� ������� ����
						// ��ǥ�� 40�� ���ϰ�, ���̿� ���̴� 30���� �Ѵ�.
						jellyList.add(new Jelly(jelly2Ic.getImage(), i * 40, j * 40, 30, 30, 2345));

					} else if (colorArr[i][j] == 9868800) { // ������ 9868800�� ��� ������� ����
						// ��ǥ�� 40�� ���ϰ�, ���̿� ���̴� 30���� �Ѵ�.
						jellyList.add(new Jelly(jelly3Ic.getImage(), i * 40, j * 40, 30, 30, 3456));

					} else if (colorArr[i][j] == 16737280) { // ������ 16737280�� ��� �� ���� ����
						// ��ǥ�� 40�� ���ϰ�, ���̿� ���̴� 30���� �Ѵ�.
						jellyList.add(new Jelly(jellyHPIc.getImage(), i * 40, j * 40, 30, 30, 4567));
					}
				}
			}

			for (int i = 0; i < maxX; i += 2) { // ������ 4ĭ�� �����ϴ� �����̱� ������ 2,2������� �ݺ����� ������.
				for (int j = 0; j < maxY; j += 2) {
					if (colorArr[i][j] == 0) { // ������ 0 �ϰ�� (������)
						// ��ǥ�� 40�� ���ϰ�, ���̿� ���̴� 80���� �Ѵ�.
						fieldList.add(new Field(field1Ic.getImage(), i * 40, j * 40, 80, 80));

					} else if (colorArr[i][j] == 6579300) { // ������ 6579300 �ϰ�� (ȸ��)
						// ��ǥ�� 40�� ���ϰ�, ���̴� 80���� ���̴� 40 �Ѵ�.
						fieldList.add(new Field(field1Ic.getImage(), i * 40, j * 40, 80, 40));
					}
				}
			}

			for (int i = 0; i < maxX; i += 2) { // ��ֹ��� 4ĭ �̻��� �����Ѵ�. ���� ����
				for (int j = 0; j < maxY; j += 2) {
					if (colorArr[i][j] == 16711680) { // ������ 16711680�� ��� (������) 1ĭ
						// ��ǥ�� 40�� ���ϰ�, ���̿� ���̴� 80���� �Ѵ�.
						tacleList.add(new Tacle(tacle10Ic.getImage(), i * 40, j * 40, 80, 80, 0));

					} else if (colorArr[i][j] == 16711830) { // ������ 16711830�� ��� (��ȫ) 2ĭ
						// ��ǥ�� 40�� ���ϰ�, ���̿� ���̴� 160���� �Ѵ�.
						tacleList.add(new Tacle(tacle20Ic.getImage(), i * 40, j * 40, 80, 160, 0));

					} else if (colorArr[i][j] == 16711935) { // ������ 16711830�� ��� (����ũ) 3ĭ
						// ��ǥ�� 40�� ���ϰ�, ���̿� ���̴� 240���� �Ѵ�.
						tacleList.add(new Tacle(tacle30Ic.getImage(), i * 40, j * 40, 80, 240, 0));
					}
				}
			}

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

			mapMove(); // ��� ���� ��ֹ� �۵� �޼���

			fall(); // ���� ������ �ߵ� �޼���

			addKeyListener(new KeyAdapter() { // Ű ������ �߰�

				@Override
				public void keyPressed(KeyEvent e) {
					if (e.getKeyCode() == KeyEvent.VK_ESCAPE) { // escŰ�� ������ ��
						if (!escKeyOn) {
							escKeyOn = true;
							add(escButton);
							repaint(); // ȭ���� ��Ӱ� �ϱ����� ������Ʈ
						} else {
							remove(escButton);
							escKeyOn = false;
						}
					}

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
		
		void a() {
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
			
			//���� ����
			Graphics2D g2 = (Graphics2D)buffg; 
			
			super.paintComponent(buffg); // ���� �̹����� �����.
			
			// ����̹����� �׸���
			buffg.drawImage(b11.getImage(), b11.getX(), 0, null);
			buffg.drawImage(b12.getImage(), b12.getX(), 0, null);

			// ������ �׸���
			for (int i = 0; i < fieldList.size(); i++) {

				Field tempFoot = fieldList.get(i);

				// ����� �� ��Ƹ԰� �ϱ����� ��ġ
				if (tempFoot.getX() > -90 && tempFoot.getX() < 810) { // x���� -90~810�� ��ü�鸸 �׸���.
					
					buffg.drawImage(
						tempFoot.getImage(), 
						tempFoot.getX(), 
						tempFoot.getY(), 
						tempFoot.getWidth(),
						tempFoot.getHeight(), 
						null);
				}

			}
			
			// ������ �׸���
			for (int i = 0; i < jellyList.size(); i++) {

				Jelly tempJelly = jellyList.get(i);

				if (tempJelly.getX() > -90 && tempJelly.getX() < 810) {
					
					buffg.drawImage(
						tempJelly.getImage(), 
						tempJelly.getX(), 
						tempJelly.getY(), 
						tempJelly.getWidth(),
						tempJelly.getHeight(), 
						null);
				}
			}
			
			// ��ֹ��� �׸���
			for (int i = 0; i < tacleList.size(); i++) {

				Tacle tempTacle = tacleList.get(i);

				if (tempTacle.getX() > -90 && tempTacle.getX() < 810) {
					
					buffg.drawImage(
						tempTacle.getImage(), 
						tempTacle.getX(), 
						tempTacle.getY(), 
						tempTacle.getWidth(),
						tempTacle.getHeight(), 
						null);
				}
			}
			
			if(c1.isInvincible()) {
				// ��Ű�� alpha���� �޾ƿ´�
				alphaComposite = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, (float)c1.getAlpha()/255);
				g2.setComposite(alphaComposite);
				
				// ��Ű�� �׸���
				buffg.drawImage(c1.getImage(), c1.getX()-20, c1.getY(), (int)(cookieIc.getImage().getWidth(null)*0.9),(int)(cookieIc.getImage().getHeight(null)*0.9), null);
				
				// alpha���� �ǵ�����
				alphaComposite = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, (float)255/255);
				g2.setComposite(alphaComposite);
			} else {
				// ��Ű�� �׸���
//				buffg.drawImage(c1.getImage(), c1.getX(), c1.getY(), c1.getWidth(), c1.getHeight(), null);
				buffg.drawImage(c1.getImage(), c1.getX()-20, c1.getY(), (int)(cookieIc.getImage().getWidth(null)*0.9),(int)(cookieIc.getImage().getHeight(null)*0.9), null);
			}
		    
		    buffg.setColor(Color.BLACK);
		    buffg.drawString(Integer.toString(resultScore), 700, 40); // ����
		    
		    buffg.setColor(Color.GREEN);
		    buffg.fillRect(50, 40, c1.getHealth()/2, 30); // ü�°�����
		    
		    if(escKeyOn) { // escŰ�� ������� ȭ���� �帮�� �����
		    	
		    	// alpha���� �������ϰ� �����
				alphaComposite = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, (float)100/255);
			    g2.setComposite(alphaComposite);
			    
			    buffg.setColor(Color.BLACK);
			    
		    	buffg.fillRect(0, 0, 850, 550);
		    	
				// alpha���� �ǵ�����
				alphaComposite = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, (float)255/255);
			    g2.setComposite(alphaComposite);
		    }
			
			// �����̹����� ȭ�鿡 ����Ѵ�
			g.drawImage(buffImage, 0, 0, this);
			g.drawRect(160, 280, 80, 120);
			
		}

	}

	void mapMove() {
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				while (true) {
					
					if(runPage > 800) { // 800�ȼ� �̵� ���� ü���� 10�� �����Ѵ� (���� �ʱ��̿� ���߾� ���ҷ� ����)
						c1.setHealth(c1.getHealth()-10);
						runPage = 0;
					}
					
					runPage +=gameSpeed; // ȭ���� �̵��ϸ� runPage�� �̵��� ��ŭ ����ȴ�.
					
					
					if (b11.getX() < -(b11.getWidth()-1)) { // ���1-1 �� -(������)���� ������, �� ȭ������� ��γ����� ��� 1-2�ڿ� ����
						b11.setX(b11.getWidth());
					}
					if (b12.getX() < -(b12.getWidth()-1)) { // ���1-2 �� -(������)���� ������, �� ȭ������� ��γ����� ��� 1-1�ڿ� ����
						b12.setX(b12.getWidth());
					}
					
					
					// ����� x��ǥ�� -1 ���ش� (�������� �帣�� ȿ��)
					b11.setX(b11.getX()-gameSpeed/3); 
					b12.setX(b12.getX()-gameSpeed/3);
					
					
					// ������ġ�� -4 �� ���ش�. (�������� �帣�� ȿ��)
					for (int i = 0; i < fieldList.size(); i++) {
						
						Field tempField = fieldList.get(i); // �ӽ� ������ ����Ʈ �ȿ� �ִ� ���� ������ �ҷ�����
						
						if(tempField.getX() < -90) { // ������  x��ǥ�� -90 �̸��̸� �ش� ������ �����Ѵ�.(����ȭ)
							
							fieldList.remove(tempField);
							
						} else {
							
							tempField.setX(tempField.getX() - gameSpeed);  // �� ���ǿ� �ش��� �ȵǸ� x��ǥ�� ������
							
						}
					}
					
					// ������ġ�� -4 �� ���ش�.
					for (int i = 0; i < jellyList.size(); i++) {
						
						Jelly tempJelly = jellyList.get(i); // �ӽ� ������ ����Ʈ �ȿ� �ִ� ���� ������ �ҷ�����
						
						if(tempJelly.getX() < -90) { // ������ x ��ǥ�� -90 �̸��̸� �ش� ������ �����Ѵ�.(����ȭ)
							
							fieldList.remove(tempJelly);
							
						} else {
							
							tempJelly.setX(tempJelly.getX() - gameSpeed); // �� ���ǿ� �ش��� �ȵǸ� x��ǥ�� ������
							
							foot = c1.getY() + c1.getHeight(); // ĳ���� �� ��ġ �罺ĵ
							
							if( // ĳ������ ���� �ȿ� ������ ������ �������� �Դ´�.
								c1.getImage() != slideIc.getImage()
								&& tempJelly.getX() + tempJelly.getWidth()*20/100 >= c1.getX()
								&& tempJelly.getX() + tempJelly.getWidth()*80/100 <= face
								&& tempJelly.getY() + tempJelly.getWidth()*20/100 >= c1.getY()
								&& tempJelly.getY() + tempJelly.getWidth()*80/100 <= foot
								&& tempJelly.getImage() != jellyEffectIc.getImage()) {
								
								tempJelly.setImage(jellyEffectIc.getImage()); // ������ �̹����� ����Ʈ�� �ٲ۴�
								resultScore = resultScore + tempJelly.getScore(); // �������� ���� ������ ���Ѵ�
								
								
							} else if( // �����̵� �ϴ� ĳ������ ���� �ȿ� ������ ������ �������� �Դ´�.
								c1.getImage() == slideIc.getImage()
								&& tempJelly.getX() + tempJelly.getWidth()*20/100 >= c1.getX()
								&& tempJelly.getX() + tempJelly.getWidth()*80/100 <= face
								&& tempJelly.getY() + tempJelly.getWidth()*20/100 >= c1.getY() + c1.getHeight()*1/3
								&& tempJelly.getY() + tempJelly.getWidth()*80/100 <= foot
								&& tempJelly.getImage() != jellyEffectIc.getImage()) {
								
								tempJelly.setImage(jellyEffectIc.getImage()); // ������ �̹����� ����Ʈ�� �ٲ۴�
								resultScore = resultScore + tempJelly.getScore(); // �������� ���� ������ ���Ѵ�
								
							}
						}
					}
					
					// ��ֹ���ġ�� - 4 �� ���ش�.
					for (int i = 0; i < tacleList.size(); i++) {
						
						Tacle tempTacle = tacleList.get(i); // �ӽ� ������ ����Ʈ �ȿ� �ִ� ���� ��ֹ��� �ҷ�����
						
						if(tempTacle.getX() < -90) { 
							
							fieldList.remove(tempTacle); // ��ֹ��� x ��ǥ�� -90 �̸��̸� �ش� ������ �����Ѵ�.(����ȭ)
							
						} else {
							
							tempTacle.setX(tempTacle.getX() - gameSpeed);	// �� ���ǿ� �ش��� �ȵǸ� x��ǥ�� ������
							
							face = c1.getX() + c1.getWidth(); // ĳ���� ���� ��ġ �罺ĵ
							foot = c1.getY() + c1.getHeight(); // ĳ���� �� ��ġ �罺ĵ
							
							if( // �������°� �ƴϰ� �����̵� ���� �ƴϸ� ĳ������ ���� �ȿ� ��ֹ��� ������ �ε�����
									!c1.isInvincible()
									&& c1.getImage() != slideIc.getImage()
									&& tempTacle.getX() + tempTacle.getWidth()/2 >= c1.getX()
									&& tempTacle.getX() + tempTacle.getWidth()/2 <= face
									&& tempTacle.getY() + tempTacle.getHeight()/2 >= c1.getY()
									&& tempTacle.getY() + tempTacle.getHeight()/2 <= foot) {
								
								hit(); // �ǰ� + ���� ������ �޼���
								
							} else if( // �����̵� �ƴҽ� ������ֹ�
									!c1.isInvincible()
									&& c1.getImage() != slideIc.getImage()
									&& tempTacle.getX() + tempTacle.getWidth()/2 >= c1.getX()
									&& tempTacle.getX() + tempTacle.getWidth()/2 <= face
									&& tempTacle.getY() <= c1.getY()
									&& tempTacle.getY() + tempTacle.getHeight()*95/100 > c1.getY()) {
								
								
								hit(); // �ǰ� + ���� ������ �޼���
								
							}else if( // �������°� �ƴϰ� �����̵� ���̸� ĳ������ ���� �ȿ� ��ֹ��� ������ �ε�����
									!c1.isInvincible()
									&& c1.getImage() == slideIc.getImage()
									&& tempTacle.getX() + tempTacle.getWidth()/2 >= c1.getX()
									&& tempTacle.getX() + tempTacle.getWidth()/2 <= face
									&& tempTacle.getY() + tempTacle.getHeight()/2 >= c1.getY() + c1.getHeight()*2/3
									&& tempTacle.getY() + tempTacle.getHeight()/2 <= foot) {
								
								hit(); // �ǰ� + ���� ������ �޼���
								
							} else if( // �����̵��� ������ֹ�
									!c1.isInvincible()
									&& c1.getImage() == slideIc.getImage()
									&& tempTacle.getX() + tempTacle.getWidth()/2 >= c1.getX()
									&& tempTacle.getX() + tempTacle.getWidth()/2 <= face
									&& tempTacle.getY() < c1.getY()
									&& tempTacle.getY() + tempTacle.getHeight()*95/100 > c1.getY() + c1.getHeight()*2/3) {
								
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

						if (tempX > c1.getX()-60 && tempX <= face) { // ������ ĳ�� ���� ���̶�� 

							tempField = fieldList.get(i).getY(); // ������ y���� tempField�� �����Ѵ�

							
							foot = c1.getY() + c1.getHeight(); // ĳ���� �� ��ġ �罺ĵ
							
							// ������ġ�� tempNowField���� ����, �߹ٴ� ���� �Ʒ� �ִٸ�
							// ��, ĳ���� �� �Ʒ���  ���� ���� �ִ� �����̶�� tempNowField�� �����Ѵ�.
							if (tempField < tempNowField && tempField >= foot) {

								tempNowField = tempField;

							}
						}
					}

					nowField = tempNowField; // ����� nowField�� ������Ʈ �Ѵ�.
					
					
					
					if(escKeyOn) { // escŰ�� ������ ������ �����
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

	void hit() {
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
				
				if(c1.getImage() == hitIc.getImage()) { // 0.5�� ���� �̹����� �ٲ��� �ʾҴٸ� �⺻�̹����� ����
					
					c1.setImage(cookieIc.getImage());
					
				}
				
				
				for (int j = 0; j < 11; j++) { // 2.5�ʰ� ĳ���Ͱ� �����δ�. (�ǰ��� ���� ���¸� �ν�)
					
					if(c1.getAlpha() == 80) { // �̹����� ���İ��� 80�̸� 160���� 
						
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

	void fall() {
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				while (true) {
					
					foot = c1.getY() + c1.getHeight(); // ĳ���� �� ��ġ �罺ĵ
					
					// �߹ٴ��� ���Ǻ��� ���� ������ �۵�
					if (
						!escKeyOn // �Ͻ������� �ߵ� �ȵ��� ��
					    &&foot < nowField  // ���߿� ������
						&& !c1.isJump() // ���� ���� �ƴϸ�
						&& !c1.isFall()) { // �������� ���� �ƴ� ��
					
						c1.setFall(true);  // �������� ������ ��ȯ
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

							c1.setY(c1.getY()+fallY); // Y��ǥ�� ���Ϸ��� ���Ѵ�

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
								tempT2 = Util.getTime() -tempT1;
								t1 = t1 + tempT2;
							}
							
							try {
								Thread.sleep(10);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}

						}
						c1.setFall(false);

						if (
							downKeyOn // �ٿ�Ű�� �������°�
							&& !c1.isJump() // ���� ���°� �ƴϰ�
							&& !c1.isFall() // ���� ���°� �ƴϰ�
							&& c1.getImage() != slideIc.getImage()) { // ��Ű �̹����� �����̵� �̹����� �ƴ� ���
							
							c1.setImage(slideIc.getImage()); // ��Ű �̹����� �����̵�� ����
							
						} else if (
							!downKeyOn // �ٿ�Ű�� �������°� �ƴϰ�
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

	void jump() {
		new Thread(new Runnable() {

			@Override
			public void run() {

				c1.setCountJump(c1.getCountJump()+1); // ���� Ƚ�� ����

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
					
					c1.setY(c1.getY()-jumpY); // Y���� �����Ѵ�.

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

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CookieRun window = new CookieRun();
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
	public CookieRun() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 800, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		panel = new MyPanel();
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);

		escButton = new Button("���ư���");
		escButton.setBounds(350, 240, 50, 30);
		escButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				panel.remove(escButton);
				escKeyOn = false;
			}
		});
	}
}