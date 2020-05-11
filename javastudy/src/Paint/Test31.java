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
	Socket socket; // 소켓 저장
	BufferedReader reader;
	PrintWriter writer;
	Scanner sc;
	String line; // 좌표값을 저장하는 스트링
	int x; // x 값
	int y; // y 값
	boolean drag = false;

	public Test31() {
		initialize();
		try {
			socket = new Socket("localhost", 20000); // 상대방의 아이피와 포트를 입력하고 변수에 저장
			SocketThread st = new SocketThread(); // 소켓 연결을 유지할 스레드 생성
			st.start(); // 스레드 시작

			writer = new PrintWriter(socket.getOutputStream(), true); // 버퍼드라이터에 소켓 스트림 연결
			sc = new Scanner(System.in);
			new Thread(new Runnable() {
				
				@Override
				public void run() {
					while (true) {
						
						while (!drag) { // 드래그 상태가 아니면 대기
							try {
								Thread.sleep(10);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
						}
						
						writer.println(line); // 드래그 상태면 좌표를 서버로 전송 
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

	class SocketThread extends Thread { //아직 미사용		
		
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

	class MyPanel extends JPanel {  // 커스텀 패널
		public MyPanel() {
			addMouseMotionListener(new MouseMotionAdapter() {

				
				@Override
				public void mouseDragged(MouseEvent e) { // 마우스  드래스시 드래그 true
					drag = true;
					line = e.getX()+","+e.getY(); // 마우스 x y 좌표 저장
					x = e.getX(); // 화면에그리기 위한x
					y = e.getY(); // 화면에그리기 위한y
					repaint();
				}
			});
			
			addMouseListener(new MouseAdapter() {
				@Override
				public void mouseReleased(MouseEvent e) { // 마우스 드래그 해제시 drag가 false가 된다
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
