package Test5;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

import java.awt.CardLayout;



public class Main extends listenAdapter {

	private JFrame frame;

	private IntroPanel introPanel; // ��Ʈ��

	private SelectPanel selectPanel; // ĳ���� ����

	private GamePanel gamePanel; // ��������

	private EndPanel endPanel; // ��������

	private CardLayout cl; // ī�� �����̿� ������Ʈ
	
	public CardLayout getCl() {
		return cl;
	}
	
	private CookieImg ci; // ��Ű�̹���

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main window = new Main();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Main() {
		initialize();
	}

	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 800, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		cl = new CardLayout(0, 0); // ī�巹�̾ƿ� ��ü ����
		frame.getContentPane().setLayout(cl); // �������� ī�巹�̾ƿ����� ����

		introPanel = new IntroPanel();
		selectPanel = new SelectPanel();
		gamePanel = new GamePanel(frame, cl);
		endPanel = new EndPanel();

		introPanel.addMouseListener(this);

		introPanel.setLayout(null);
		selectPanel.setLayout(null);
		gamePanel.setLayout(null);
		endPanel.setLayout(null);

		frame.getContentPane().add(introPanel, "intro");
		frame.getContentPane().add(selectPanel, "select");
		frame.getContentPane().add(gamePanel, "game");
		frame.getContentPane().add(endPanel, "end");
		
		ci = new CookieImg(
				new ImageIcon("img/c1.gif"),
				new ImageIcon("testimg/jumpTest.png"),
				new ImageIcon("testimg/doubleJumpTest.png"),
				new ImageIcon("testimg/fallTest.png"),
				new ImageIcon("testimg/slideTest.png"),
				new ImageIcon("testimg/hitTest.png")
				);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String str = e.getActionCommand();
		System.out.println(str);
	}
	
	public void mousePressed(MouseEvent e) {
		if (e.getComponent().toString().contains("IntroPanel")) {
			for (int i = 255; i < 100 ; i++) {
				introPanel.changeAlpha(i);
				try {
					Thread.sleep(10);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}				
			}
		}
		
		cl.show(frame.getContentPane(), "game");
		gamePanel.gameSet(ci);
		gamePanel.gameStart();
		gamePanel.requestFocus();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getComponent().getName() == null) {
			// ������Ʈ ���� ����
		}
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		System.out.println("a");
	}

}