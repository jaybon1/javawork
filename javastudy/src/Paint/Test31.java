package Paint;

import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseMotionListener;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JPanel;

import Paint.SocketClient.SocketThread;
import javafx.scene.input.MouseDragEvent;

import java.awt.BorderLayout;
import java.awt.Color;

public class Test31 extends listenAdapter {
	Socket socket; // ���� ����
	BufferedReader reader;
	PrintWriter writer;
	Scanner sc;
	String line; // ��ǥ���� �����ϴ� ��Ʈ��
	int x; // x ��
	int y; // y ��
	boolean drag = false;

	public Test31() {
		initialize();
		try {
			socket = new Socket("localhost", 20000); // ������ �����ǿ� ��Ʈ�� �Է��ϰ� ������ ����
			SocketThread st = new SocketThread(); // ���� ������ ������ ������ ����
			st.start(); // ������ ����

			writer = new PrintWriter(socket.getOutputStream(), true); // ���۵�����Ϳ� ���� ��Ʈ�� ����
			sc = new Scanner(System.in);
			new Thread(new Runnable() {
				
				@Override
				public void run() {
					while (true) {
						
						while (!drag) { // �巡�� ���°� �ƴϸ� ���
							try {
								Thread.sleep(10);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
						}
						
						writer.println(line); // �巡�� ���¸� ��ǥ�� ������ ���� 
						try {
							Thread.sleep(10);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						
					}
				}
			}).start();

		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	class SocketThread extends Thread { //���� �̻��		
		
		@Override
		public void run() {
			try {
				reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				String line = null;
				while ((line = reader.readLine()) != null) {
					System.out.println("from server : " + line);
					
				}
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
	}

	
	

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Test31 window = new Test31();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	class MyPanel extends JPanel {  // Ŀ���� �г�
		public MyPanel() {
			addMouseMotionListener(new MouseMotionAdapter() {

				
				@Override
				public void mouseDragged(MouseEvent e) { // ���콺  �巡���� �巡�� true
					drag = true;
					line = e.getX()+","+e.getY(); // ���콺 x y ��ǥ ����
					x = e.getX(); // ȭ�鿡�׸��� ����x
					y = e.getY(); // ȭ�鿡�׸��� ����y
					repaint();
				}
			});
			
			addMouseListener(new MouseAdapter() {
				@Override
				public void mouseReleased(MouseEvent e) { // ���콺 �巡�� ������ drag�� false�� �ȴ�
					drag = false;
				}
			});
		}

		@Override
		protected void paintComponent(Graphics g) {
//			super.paintComponent(g);
			g.fillArc(x, y, 10, 10, 0, 360);
		}
	}


	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel panel = new MyPanel();
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
	}

}
