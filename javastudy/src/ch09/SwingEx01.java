package ch09;

import javax.swing.JButton;
import javax.swing.JFrame;

// 1. ������Ʈ
// 2. ��ġ
// 3. �̺�Ʈ
// 4. ������Ʈ Ŀ���͸���¡
// 5. �׸��� �׸��� ���

class MyButton extends JButton{
	// �⺻ ������Ʈ���� ���� �ȵ�� ����� �޾Ƽ� ���� �ٹ� ���� �ִ�
}

public class SwingEx01 extends JFrame { // JFrame�� ����Ͽ� GUI ��������
	
	public SwingEx01() {
		// TODO Auto-generated constructor stub
		setTitle("ù��° ������");
		setSize(300,300);
		add(new JButton("Ŭ��"));
		setVisible(true);
	}
	
	public static void main(String[] args) {
		new SwingEx01();
	}
	
}

