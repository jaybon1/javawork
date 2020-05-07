package Test5;

import java.awt.AlphaComposite;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

class IntroPanel extends JPanel {

	private int introAlpha = 255;

	private AlphaComposite alphaComposite; // ���� ���� ������Ʈ

	ImageIcon introIc = new ImageIcon("img/out/intro.png"); // ��Ʈ�� �̹���

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		// ���� ����
		Graphics2D g2 = (Graphics2D) g;

		alphaComposite = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, (float) introAlpha / 255);
		g2.setComposite(alphaComposite);

		// ��Ű�� �׸���
		g.drawImage(introIc.getImage(), -60, 0, /* this.getWidth(), this.getHeight(), */ null);

		// alpha���� �ǵ�����
		alphaComposite = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, (float) 255 / 255);
		g2.setComposite(alphaComposite);

	}

	public void changeAlpha(int introAlpha) {
		this.introAlpha = introAlpha;
		repaint();
	}

}
