package ch09;

import javax.swing.JButton;
import javax.swing.JFrame;

// 1. 컴포넌트
// 2. 배치
// 3. 이벤트
// 4. 컴포넌트 커스터마이징
// 5. 그림을 그리는 방법

class MyButton extends JButton{
	// 기본 컴포넌트들이 맘에 안들면 상속을 받아서 직접 꾸밀 수도 있다
}

public class SwingEx01 extends JFrame { // JFrame을 상속하여 GUI 가져오기
	
	public SwingEx01() {
		// TODO Auto-generated constructor stub
		setTitle("첫번째 프레임");
		setSize(300,300);
		add(new JButton("클릭"));
		setVisible(true);
	}
	
	public static void main(String[] args) {
		new SwingEx01();
	}
	
}

