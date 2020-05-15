package test;

import java.awt.EventQueue;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;

import javax.swing.JList;

public class Test8 {

	private JFrame frame;
	private DefaultListModel<JPanel> listModel; // Jlist에 이것을 넣어야 한다.

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Test8 window = new Test8();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	class MyPanel extends JPanel{
		public MyPanel() {
			setSize(400, 50);
		}
		
		@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				g.fillRect(0, 0, 200, 30);
			}
	}

	/**
	 * Create the application.
	 */
	public Test8() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 736, 570);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel mp1 = new MyPanel();
		JPanel mp2 = new MyPanel();
		JPanel mp3 = new MyPanel();
		
		listModel = new DefaultListModel<>();

		
		listModel.addElement(mp1);
		listModel.addElement(mp2);
		listModel.addElement(mp3);
		
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JButton btnNewButton = new JButton("HOME");
		btnNewButton.setBorderPainted(false);
		btnNewButton.setFocusPainted(false);
		btnNewButton.setBackground(Color.WHITE);
		btnNewButton.setBounds(12, 10, 110, 23);
		panel.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("\uC8FC\uBB38\uD398\uC774\uC9C0");
		btnNewButton_1.setBounds(121, 10, 110, 23);
		panel.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("\uB85C\uADF8\uC544\uC6C3");
		btnNewButton_2.setBounds(230, 10, 97, 23);
		panel.add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("\uD68C\uC6D0\uC815\uBCF4 \uAD00\uB9AC");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton_3.setBounds(12, 55, 137, 23);
		panel.add(btnNewButton_3);
		
		JButton btnNewButton_4 = new JButton("\uB367\uAE00\uAD00\uB9AC");
		btnNewButton_4.setBounds(172, 55, 110, 23);
		panel.add(btnNewButton_4);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(23, 99, 658, 390);
		panel.add(scrollPane);
		
		JList list = new JList(listModel);
		list.setCellRenderer(new );
		scrollPane.setViewportView(list);
	}
}
