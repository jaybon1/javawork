package ch15;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import com.google.gson.Gson;

public class NetworkEx03 {

	static void airinfo(long depPlandtime) {
		try {
			// 1번 주소 객체 만들기
			URL url = new URL(
					"http://openapi.tago.go.kr/openapi/service/DmstcFlightNvgInfoService/getFlightOpratInfoList?serviceKey=zkymID3s0eE1ymiOd0WYTwCEMeo4qKgV5e9DGU6QHceEc%2BWgah6BN5uEhr9tFRjolXkryBNbw9CNLnn6z9LTpg%3D%3D&numOfRows=50&pageNo=1&depAirportId=NAARKJJ&arrAirportId=NAARKPC&depPlandTime="+depPlandtime+"&_type=json");

			// 2번 스트림 연결
			HttpURLConnection con = (HttpURLConnection) url.openConnection();

			// 3번 버퍼연결(문자열)
			BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8"));

			// 4번 StringBuilder는 ArrayList<String>과 비슷하다
			// append로 String을 저장한뒤 호출하면 쭉 읽는다
			StringBuilder sb = new StringBuilder();
			String input = "";

			while ((input = br.readLine()) != null) {
				sb.append(input);
			}
			System.out.println(sb.toString());

			Gson gson = new Gson();

			Air air1 = gson.fromJson(sb.toString(), Air.class);
			
			
			for (int i = 0; i < air1.getResponse().getBody().getItems().getItem().size(); i++) {
				System.out.println("항공사 : " + air1.getResponse().getBody().getItems().getItem().get(i).getAirlineNm());
				System.out.println("도착지 : " + air1.getResponse().getBody().getItems().getItem().get(i).getArrAirportNm());
				System.out.println("도착시간 : " + air1.getResponse().getBody().getItems().getItem().get(i).getArrPlandTime());
				System.out.println("출발지 : " + air1.getResponse().getBody().getItems().getItem().get(i).getDepAirportNm());
				System.out.println("출발시간 : " + air1.getResponse().getBody().getItems().getItem().get(i).getDepPlandTime());
				System.out.println("항공기ID : " + air1.getResponse().getBody().getItems().getItem().get(i).getVihicleId());
				System.out.println("이코노미요금 : " + air1.getResponse().getBody().getItems().getItem().get(i).getEconomyCharge());
				System.out.println("프레스티지요금 : " + air1.getResponse().getBody().getItems().getItem().get(i).getPrestigeCharge());
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
