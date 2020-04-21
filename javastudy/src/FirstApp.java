import java.awt.EventQueue;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;

public class FirstApp {
	
	static int numX = 100;

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FirstApp window = new FirstApp();
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
	public FirstApp() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		JLabel lblNewLabel = new JLabel("Java");
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.addKeyListener(new KeyAdapter() {
			
			
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == 39) {
					numX = numX + 3;
					lblNewLabel.setBounds(numX, 126, 189, 87);
					frame.getContentPane().add(lblNewLabel);
				} else if(e.getKeyCode() == 37) {
					numX = numX - 3;
					lblNewLabel.setBounds(numX, 126, 189, 87);
					frame.getContentPane().add(lblNewLabel);
				}
			}
			
		});
		lblNewLabel.setBounds(numX, 126, 189, 87);
		frame.getContentPane().add(lblNewLabel);
	}
}












