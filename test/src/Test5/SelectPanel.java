package Test5;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class SelectPanel extends JPanel {
	private JLabel testLa;

	public SelectPanel() {
		testLa = new JLabel("��Ű �� �� ���� �г�");
		testLa.setBounds(0,0, 100, 20);
		add(testLa);
	}
}
