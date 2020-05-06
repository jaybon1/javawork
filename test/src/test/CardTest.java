package test;

import java.awt.EventQueue;
import java.awt.Graphics;

import javax.swing.JFrame;
import java.awt.CardLayout;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JButton;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Calendar;

public class CardTest extends JFrame implements ActionListener {

	private JFrame frame;
	
	JPanel panel_1;
	
	JPanel panel;
	
	CardLayout cl;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CardTest window = new CardTest();
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
	public CardTest() {
		initialize();
	}
	
	ImageIcon ic = new ImageIcon("img/back1.png");
	
	class MyPanel extends JPanel{
		
		public MyPanel() {
		}
		
		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			g.drawImage(ic.getImage(), 200, 150, null);
		}
		
	}
	class MyPanel1 extends JPanel{
		
		public MyPanel1() {
		}
		
		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			g.drawImage(ic.getImage(), 150, 150, null);
		}
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		cl = new CardLayout(0, 0);
		frame.getContentPane().setLayout(cl);
		
		JPanel panel = new MyPanel();
		frame.getContentPane().add(panel, "name_1284864688221200");
		panel.setLayout(null);
		
		JPanel panel_1 = new MyPanel1();
		frame.getContentPane().add(panel_1, "name_1284877453449200");
		panel_1.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setBounds(181, 73, 57, 15);
		panel.add(lblNewLabel);
		

		JButton btnNewButton_1 = new JButton("게임시작");
		btnNewButton_1.addActionListener(this);
		btnNewButton_1.setBounds(0, 0, 97, 23);
		panel.add(btnNewButton_1);
		
		
		JButton btnNewButton = new JButton("캐릭터선택");
		btnNewButton.addActionListener(this);
		btnNewButton.setBounds(0, 0, 97, 23);
		panel_1.add(btnNewButton);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String str = e.getActionCommand();
		if (str.equals("게임시작")) {
			cl.show(frame.getContentPane(), "name_1284877453449200");
		} else if (str.equals("캐릭터선택")) {
			cl.show(frame.getContentPane(), "name_1284864688221200");
		}
	}
}
