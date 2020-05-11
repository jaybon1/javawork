package panels;

import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class SelectPanel extends JPanel {
	ImageIcon start = new ImageIcon("img/select/GameStartBtn.png");
	ImageIcon ch01 = new ImageIcon("img/select/selectCh1.png");
	ImageIcon ch02 = new ImageIcon("img/select/selectCh2.png");
	ImageIcon ch03 = new ImageIcon("img/select/selectCh3.png");
	ImageIcon ch04 = new ImageIcon("img/select/selectCh4.png");

	public SelectPanel() {
		// 시작 버튼
				JButton StartBtn = new JButton(start);
				StartBtn.setBounds(254, 334, 291, 81);
				add(StartBtn);
				StartBtn.setBorderPainted(false);
				StartBtn.setContentAreaFilled(false);
				StartBtn.setFocusPainted(false);
				
				
				// 캐릭터 ch1
				JButton ch1 = new JButton(ch01);
				ch1.setBounds(90, 102, 150, 200);
				add(ch1);
				ch1.setBorderPainted(false);
				ch1.setContentAreaFilled(false);
				ch1.setFocusPainted(false);
				
				// 캐릭터 ch2
				JButton ch2 = new JButton(ch02);
				ch2.setBounds(238, 102, 150, 200);
				add(ch2);
				ch2.setBorderPainted(false);
				ch2.setContentAreaFilled(false);
				ch2.setFocusPainted(false);
				
				// 캐릭터 ch3
				JButton ch3 = new JButton(ch03);
				ch3.setBounds(386, 102, 150, 200);
				add(ch3);
				ch3.setBorderPainted(false);
				ch3.setContentAreaFilled(false);
				ch3.setFocusPainted(false);
				
				// 캐릭터 ch4
				JButton ch4 = new JButton(ch04);
				ch4.setBounds(534, 102, 150, 200);
				add(ch4);
				ch4.setBorderPainted(false);
				ch4.setContentAreaFilled(false);
				ch4.setFocusPainted(false);
				
				
				// 캐릭터 선택 타이틀
				JLabel selectTxt = new JLabel("");
				selectTxt.setHorizontalAlignment(SwingConstants.CENTER);
				selectTxt.setIcon(new ImageIcon("img/select/selectTxt.png"));
				selectTxt.setBounds(174, 20, 397, 112);
				add(selectTxt);
				
				// 배경
				JLabel selectBg = new JLabel("");
				selectBg.setForeground(Color.ORANGE);
				selectBg.setHorizontalAlignment(SwingConstants.CENTER);
				selectBg.setIcon(new ImageIcon("img/select/selectBg.png"));
				selectBg.setBounds(0, 0, 784, 461);
				add(selectBg);
	}
}
