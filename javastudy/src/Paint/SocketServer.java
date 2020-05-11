package Paint;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;

public class SocketServer {
	ServerSocket serverSocket; // ���������� �����û�� ����ϴٰ� ������ �Ǹ� ������ �����ϰ� �������Ͽ� ����� ���� ���´�
	Vector<SocketThread> vc; // ���� �����带 �����ϱ� ���� ����
	int x;
	int y;
	String line = null;
	String pos;

	public SocketServer() {

		try {
			serverSocket = new ServerSocket(20000); // ��Ʈ�� 20000������ �ϴ� ���������� ����

			vc = new Vector<>(); // ���� �迭 ����

			while (true) {
				System.out.println("��û ���");
				Socket socket = serverSocket.accept(); // �������Ͽ� ��û�� ���� ���� ���
				System.out.println("��û ����");
				SocketThread st = new SocketThread(socket); // ������ �޴� �����带 ����
				st.start(); // ������ ����
				vc.add(st); // ���� �迭�� ������ ����

			}

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	class SocketThread extends Thread { // ��������� �� �����带 ����� ���� ���� Ŭ����
		Socket socket; // �ܺο��� ���� ������ �ִ� ����
		String id; // ���� id ����
		BufferedReader reader;
		PrintWriter writer;

		public SocketThread(Socket socket) {
			this.socket = socket; // �����尡 ���� �Ǹ� ������ �޾Ƽ� �����Ѵ�
		}

		@Override
		public void run() {

			try {
				reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				writer = new PrintWriter(socket.getOutputStream(), true);

				String line = null;
				while ((line = reader.readLine()) != null) { 
					System.out.println(line); // Ŭ���̾�Ʈ���� ���� ��ǥ ���� ���
				}

			} catch (Exception e) {
				e.printStackTrace();
			}

		}
	}
	
	private void getPos() {
		// ��ǥ���� �ٸ� Ŭ���̾�Ʈ���� ������ �޼��� �����ؾߵ�
	}

	public static void main(String[] args) {
		new SocketServer();
	}
}
