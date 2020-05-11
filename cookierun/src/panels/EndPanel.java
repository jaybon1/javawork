package panels;

import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class EndPanel extends JPanel {
	
	ImageIcon btn = new ImageIcon("img/end/button.png");
	JButton endAcceptBtn;
	JLabel titleLb;
	JLabel scoreLb;
	JLabel backImgLb;
	
	private int resultScore;
	
	public void setResultScore(int resultScore) {
		scoreLb.setText(resultScore+"");
	}

	public EndPanel(Object o) {
		// 확인 버튼
		endAcceptBtn = new JButton(btn);
		endAcceptBtn.setName("endAccept");
		endAcceptBtn.addMouseListener((MouseListener) o);
		endAcceptBtn.setBounds(550, 370, 199, 81);
		endAcceptBtn.setBorderPainted(false);
		endAcceptBtn.setFocusPainted(false);
		endAcceptBtn.setContentAreaFilled(false);
		add(endAcceptBtn);
		
		// 점수 글자 
		titleLb = new JLabel("SCORE");	
		titleLb.setHorizontalAlignment(SwingConstants.CENTER);
		titleLb.setFont(new Font("Gill Sans Ultra Bold", Font.PLAIN, 37));
		titleLb.setBounds(451, 0, 205, 55);
		add(titleLb);
		
		scoreLb = new JLabel("0");
		scoreLb.setHorizontalAlignment(SwingConstants.CENTER);
		scoreLb.setFont(new Font("Harlow Solid Italic", Font.PLAIN, 49));
		scoreLb.setBounds(313, 52, 459, 87);
		add(scoreLb);
		
		backImgLb = new JLabel("");
		backImgLb.setHorizontalAlignment(SwingConstants.RIGHT);
		backImgLb.setBackground(SystemColor.activeCaptionText);
		backImgLb.setIcon(new ImageIcon("img/end/cookierunbg.jpg"));
		backImgLb.setBounds(0, 0, 784, 461);
		add(backImgLb);
	}
}
