package ch11;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.ScrollPane;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class TextFieldEx extends JFrame {
	
	private JTextField tf;
	private JTextArea ta;
	private ScrollPane sp;
	
	
	private void init() {
		tf = new JTextField("", 20);
		sp = new ScrollPane();
		ta = new JTextArea("", 5, 20);
	}
	
	
	public TextFieldEx() {
		init();
		setTitle("텍스트 에디터, 텍스트 박스 연습");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container c = getContentPane();
		
		tf.setPreferredSize(new Dimension(100, 50));
		
		

		//ta.setEnabled(false);
		ta.setBackground(Color.ORANGE);
		ta.setForeground(Color.BLACK);
		
		sp.add(ta);
		System.out.println(sp.toString());
		
		
		tf.addKeyListener(new KeyAdapter() {
			
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					String value = tf.getText();
					ta.append(value + "\n");
					tf.setText("");
				}
					
			}
			
		});
		
		c.add(tf, BorderLayout.SOUTH);
		c.add(sp, BorderLayout.CENTER);
		
		setSize(300, 300);
		setVisible(true);
		
	}
	
	public static void main(String[] args) {
		new TextFieldEx();
	}
}
