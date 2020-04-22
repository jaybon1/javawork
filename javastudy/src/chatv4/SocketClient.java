package chatv4;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class SocketClient {
	Socket socket; // ���� ����
	BufferedReader reader;
	PrintWriter writer;
	Scanner sc;

	public SocketClient() {
		try {
			socket = new Socket("192.168.0.60", 20000); // ������ �����ǿ� ��Ʈ�� �Է��ϰ� ������ ����
			SocketThread st = new SocketThread(); // ���� ������ ������ ������ ����
			st.start(); // ������ ����

			writer = new PrintWriter(socket.getOutputStream(), true); // ���۵�����Ϳ� ���� ��Ʈ�� ����
			sc = new Scanner(System.in);
			while (true) {
				String line = sc.nextLine(); // ä��â
				// ALL:�ȳ�
				// MSG:white:�ȳ�
				writer.println(line);
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

	public static void main(String[] args) {
		new SocketClient();
	}
}