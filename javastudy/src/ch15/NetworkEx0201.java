package ch15;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import com.google.gson.Gson;

public class NetworkEx0201 {
	
	public static void main(String[] args) {

		try {
			// 1�� �ּ� ��ü �����
			URL url = new URL("���⿡ �ּҸ� �Է��ϼ���");
			
			// 2�� ��Ʈ�� ����
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			
			// 3�� ���ۿ���(���ڿ�)
			BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8"));
			
			// 4�� StringBuilder�� ArrayList<String>�� ����ϴ�
			// append�� String�� �����ѵ� ȣ���ϸ� �� �д´�
			StringBuilder sb = new StringBuilder();
			String input = "";
			
			while ((input = br.readLine()) != null) {
				sb.append(input);
			}
			System.out.println(sb.toString());

			// gson�� �̿��Ͽ� AirŸ���� �ν��Ͻ� �����
			Gson gson = new Gson();		
			
			Air air1 = gson.fromJson(sb.toString(), Air.class);
			
			// �׽�Ʈ�غ���
			System.out.println("�װ��� : " + air1.getResponse().getBody().getItems().getItem().get(0).getAirlineNm());
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
