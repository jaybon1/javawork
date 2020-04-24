package sms;

import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class SmsApp extends JFrame {	
	TextField tf;
	TextArea ta;
	JButton jb;
	
	class Content extends JPanel{
		
		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			TextField tf = new TextField();
			
		}
	}
	
	private Content con = new Content();
	
	public SmsApp() {
		setTitle("sms 테스트");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setContentPane(con);
		setLayout(new FlowLayout());
		
		add(new JLabel("전화번호"));
		add(tf = new TextField("", 20));
		add(new JLabel("내용"));
		add(ta = new TextArea(7, 20));
		jb = new JButton("전송");
		jb.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				new ExampleSend(tf.getText(), ta.getText());
			}
		});
		add(jb);
		
		
		setSize(300, 420);
		setVisible(true);
		System.out.println(tf.getText());
	}
	
	public static void main(String[] args) {
		new SmsApp();
	}
}
