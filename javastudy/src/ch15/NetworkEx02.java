package ch15;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import com.google.gson.Gson;

public class NetworkEx02 {
	
	static void airinfo() {
		
	}
	
	public static void main(String[] args) {

		try {
			// 1�� �ּ� ��ü �����
			URL url = new URL(
					"http://openapi.tago.go.kr/openapi/service/DmstcFlightNvgInfoService/getFlightOpratInfoList?serviceKey=zkymID3s0eE1ymiOd0WYTwCEMeo4qKgV5e9DGU6QHceEc%2BWgah6BN5uEhr9tFRjolXkryBNbw9CNLnn6z9LTpg%3D%3D&numOfRows=50&pageNo=1&depAirportId=NAARKJJ&arrAirportId=NAARKPC&depPlandTime=20200407&_type=json");

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
			
			Gson gson = new Gson();
			
			Air air1 = gson.fromJson(sb.toString(), Air.class);
			
			
			

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}