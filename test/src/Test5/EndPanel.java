package Test5;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class EndPanel extends JPanel {
	private JLabel testLa;

	public EndPanel() {
		testLa = new JLabel("���ӳ��г�");
		testLa.setBounds(0,0, 100, 20);
		add(testLa);
	}
}
