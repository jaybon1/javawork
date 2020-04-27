package smsSend;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.DropMode;
import javax.swing.JButton;

public class SmsApp {

	private JFrame frame;
	private JTextField textField;
	private JTextArea textArea;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SmsApp window = new SmsApp();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public SmsApp() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 300, 353);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Sms App");
		lblNewLabel_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1.setFont(new Font("±¼¸²", Font.BOLD, 15));
		lblNewLabel_1.setBounds(106, 9, 96, 15);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setIcon(new ImageIcon("C:\\src\\javawork\\smsSend\\img\\blue.png"));
		lblNewLabel.setBounds(0, 0, 284, 33);
		panel.add(lblNewLabel);
		
		textField = new JTextField();
		textField.setHorizontalAlignment(SwingConstants.CENTER);
		textField.setToolTipText("\uC218\uC2E0\uC778\uC744 \uC785\uB825\uD558\uC138\uC694");
		textField.setBounds(0, 53, 284, 21);
		panel.add(textField);
		textField.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(0, 96, 284, 166);
		panel.add(scrollPane);
		
		textArea = new JTextArea();
		textArea.setLineWrap(true);
		textArea.setFont(new Font("±¼¸²", Font.PLAIN, 12));
		scrollPane.setViewportView(textArea);
		
		JButton btnNewButton = new JButton("Send");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				new SmsModule(textField.getText(), textArea.getText());
			}
		});
		btnNewButton.setBackground(new Color(100, 149, 237));
		btnNewButton.setBounds(0, 271, 284, 43);
		panel.add(btnNewButton);
		
		JLabel lblNewLabel_2 = new JLabel("To.");
		lblNewLabel_2.setBounds(10, 37, 123, 15);
		panel.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Write Text");
		lblNewLabel_3.setToolTipText("\uB0B4\uC6A9\uC744 \uC785\uB825\uD558\uC138\uC694");
		lblNewLabel_3.setBounds(10, 77, 96, 15);
		panel.add(lblNewLabel_3);
	}
}
