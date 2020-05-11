package Paint;

import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class SocketClient extends listenAdapter {
	Socket socket; // ���� ����
	BufferedReader reader;
	PrintWriter writer;
	Scanner sc;
	String line;

	public SocketClient() {
		try {
			socket = new Socket("192.168.0.60", 20000); // ������ �����ǿ� ��Ʈ�� �Է��ϰ� ������ ����
			SocketThread st = new SocketThread(); // ���� ������ ������ ������ ����
			st.start(); // ������ ����

			writer = new PrintWriter(socket.getOutputStream(), true); // ���۵�����Ϳ� ���� ��Ʈ�� ����
			sc = new Scanner(System.in);
			while (true) {
//				String line = sc.nextLine(); // ä��â
				// ALL:�ȳ�
				// MSG:white:�ȳ�
				writer.println(line);
				Thread.sleep(10);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	class SocketThread extends Thread {
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
	@Override
	public void mouseMoved(MouseEvent e) {
		System.out.println(e.getX());
		line = Integer.toString(e.getX());
	}
	
	public static void main(String[] args) {
		new SocketClient();
	}
}
