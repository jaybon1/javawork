package web;

import java.awt.EventQueue;

import javax.swing.JFrame;

import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;

public class WebViewApp {

	private JFrame frame;
	private JFXPanel jf;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					WebViewApp window = new WebViewApp();
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
	public WebViewApp() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		jf = new JFXPanel();
		frame.getContentPane().add(jf);
		
		Platform.runLater(new Runnable() {

			public void run() {

				WebEx2.initAndLoadWebView(jf);

			}

		});
		
		
	}

}
