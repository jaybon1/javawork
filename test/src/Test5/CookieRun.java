package Test5;

import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

import java.awt.Button;
import java.awt.CardLayout;
import javax.swing.JPanel;

public class CookieRun extends listenAdapter {

	private JFrame frame;

	private JPanel introPanel; // ��Ʈ��

	private JPanel loginPanel; // �α���

	private JPanel signPanel; // ȸ������

	private JPanel lankPanel; // ��ŷ�г�

	private JPanel selectPanel; // ĳ���� �� �� ����

	private JPanel gamePanel; // ��������

	private JPanel endPanel; // ��������

	private JPanel clearPanel; // ���� Ŭ����

	private CardLayout cl;

	ImageIcon introIc = new ImageIcon("img/out/intro1.png");

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

	public CookieRun() {
		initialize();
	}

	class IntroPanel extends JPanel {

		public IntroPanel() {
			addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					cl.show(frame.getContentPane(), "login");
				}
			});
		}

		@Override
		protected void paintComponent(Graphics g) {
			g.drawImage(introIc.getImage(), -60, 0,/* this.getWidth(), this.getHeight(),*/ null);
		}
	}

	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 800, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		cl = new CardLayout(0, 0);
		frame.getContentPane().setLayout(cl);

		introPanel = new IntroPanel();
		introPanel.setLayout(null);
		loginPanel = new JPanel();
		signPanel = new JPanel();
		lankPanel = new JPanel();
		selectPanel = new JPanel();
		gamePanel = new JPanel();
		endPanel = new JPanel();
		clearPanel = new JPanel();

		frame.getContentPane().add(introPanel, "intro");
		frame.getContentPane().add(loginPanel, "login");
		frame.getContentPane().add(signPanel, "sign");
		frame.getContentPane().add(lankPanel, "lank");
		frame.getContentPane().add(selectPanel, "select");
		frame.getContentPane().add(gamePanel, "game");
		frame.getContentPane().add(endPanel, "end");
		frame.getContentPane().add(clearPanel, "clear");
	}

}
