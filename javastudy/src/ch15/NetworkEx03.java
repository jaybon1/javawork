package ch15;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import com.google.gson.Gson;

public class NetworkEx03 {

	static void airinfo(long depPlandtime) {
		try {
			// 1�� �ּ� ��ü �����
			URL url = new URL(
					"http://openapi.tago.go.kr/openapi/service/DmstcFlightNvgInfoService/getFlightOpratInfoList?serviceKey=zkymID3s0eE1ymiOd0WYTwCEMeo4qKgV5e9DGU6QHceEc%2BWgah6BN5uEhr9tFRjolXkryBNbw9CNLnn6z9LTpg%3D%3D&numOfRows=50&pageNo=1&depAirportId=NAARKJJ&arrAirportId=NAARKPC&depPlandTime="+depPlandtime+"&_type=json");

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
			
			
			for (int i = 0; i < air1.getResponse().getBody().getItems().getItem().size(); i++) {
				System.out.println("�װ��� : " + air1.getResponse().getBody().getItems().getItem().get(i).getAirlineNm());
				System.out.println("������ : " + air1.getResponse().getBody().getItems().getItem().get(i).getArrAirportNm());
				System.out.println("�����ð� : " + air1.getResponse().getBody().getItems().getItem().get(i).getArrPlandTime());
				System.out.println("����� : " + air1.getResponse().getBody().getItems().getItem().get(i).getDepAirportNm());
				System.out.println("��߽ð� : " + air1.getResponse().getBody().getItems().getItem().get(i).getDepPlandTime());
				System.out.println("�װ���ID : " + air1.getResponse().getBody().getItems().getItem().get(i).getVihicleId());
				System.out.println("���ڳ�̿�� : " + air1.getResponse().getBody().getItems().getItem().get(i).getEconomyCharge());
				System.out.println("������Ƽ����� : " + air1.getResponse().getBody().getItems().getItem().get(i).getPrestigeCharge());
				System.out.println();
			}
			
			

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void main(String[] args) {
		airinfo(20200407);
	}
}