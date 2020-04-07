package ch15;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class NetworkEx01 {
	public static void main(String[] args) {

		try {
			// 1�� �ּ� ��ü �����
			URL url = new URL("https://www.naver.com");

			// 2�� ��Ʈ�� ����
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			
			// 3�� ���ۿ���(���ڿ�)
			BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8"));
			
			// ���Ͽ� ��Ʈ�� �����ϱ�
			// ���� �ۼ��� �ν��Ͻ�
			// �����ϴ� ���� html ������ �����ȴ�
			FileWriter fw = new FileWriter("C:\\utils\\test.html");
			
			// 4�� StringBuilder�� ArrayList<String>�� ����ϴ�
			// append�� String�� �����ѵ� ȣ���ϸ� �� �д´�
			StringBuilder sb = new StringBuilder();
			String input = "";

			while ((input = br.readLine()) != null) {
				System.out.println(input);
				sb.append(input);
			}
			
			
			// toString() ��絥���͸� ����ȭ ��
			fw.write(sb.toString());

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}