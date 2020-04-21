package ch09;

import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.TextArea;

import javax.swing.JButton;
import javax.swing.JFrame;

public class SwingEx01 extends JFrame {
	
	public SwingEx01() {
		Container contentPane = getContentPane(); // �гΰ�ü�� ����
		// JFrame�� �⺻�г��� Border�ε� Flow�� �ٲ���
		contentPane.setLayout(new FlowLayout());
		contentPane.add(new JButton("ù��° ��ư")); // ��ư�� ��ȯ
		contentPane.add(new TextArea(3, 30));
		setTitle("300x300 ���� ������ �����");
		setSize(300, 300);
		setVisible(true); // painting �̶� �׸��� �׷���
	}
	
	public static void main(String[] args) {
		new SwingEx01();
	}
}
