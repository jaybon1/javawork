package Test5;

import java.awt.AlphaComposite;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

class IntroPanel extends JPanel {

	private int introAlpha = 255;

	private AlphaComposite alphaComposite; // 투명도 관련 오브젝트

	ImageIcon introIc = new ImageIcon("img/out/intro.png"); // 인트로 이미지

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		// 투명도 관련
		Graphics2D g2 = (Graphics2D) g;

		alphaComposite = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, (float) introAlpha / 255);
		g2.setComposite(alphaComposite);

		// 쿠키를 그린다
		g.drawImage(introIc.getImage(), -60, 0, /* this.getWidth(), this.getHeight(), */ null);

		// alpha값을 되돌린다
		alphaComposite = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, (float) 255 / 255);
		g2.setComposite(alphaComposite);

	}

	public void changeAlpha(int introAlpha) {
		this.introAlpha = introAlpha;
		repaint();
	}

}
