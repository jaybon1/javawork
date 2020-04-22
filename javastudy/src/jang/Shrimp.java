package jang;

import java.awt.Color;
import java.awt.Graphics;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.json.XML;

import com.google.gson.Gson;

import airplane.Air;

public class Shrimp extends JFrame {

	int panelX = 400;
	int panelY = 180;

	static int max = 0;
	static int a = 0;
	static int b = 0;
	static int c = 0;
	static int aa = 0;
	static int bb = 0;
	static int cc = 0;

	class MyPanel extends JPanel {

		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			g.setColor(Color.BLACK);
			g.drawString("2019.10", (int) (panelX * (0.2)), (int) (panelY * (0.9)));
			g.drawString("2019.11", (int) (panelX * (0.4)), (int) (panelY * (0.9)));
			g.drawString("2019.12", (int) (panelX * (0.6)), (int) (panelY * (0.9)));

			g.setColor(Color.BLACK);
			g.drawString(Integer.toString(aa), (int) (panelX * (0.2)) + 10, (int) (panelY * (0.8) - a) - 10);
			g.drawString(Integer.toString(bb), (int) (panelX * (0.4)) + 10, (int) (panelY * (0.8) - b) - 10);
			g.drawString(Integer.toString(cc), (int) (panelX * (0.6)) + 10, (int) (panelY * (0.8) - c) - 10);

			g.setColor(Color.BLUE);
			g.fillRect((int) (panelX * (0.2)), (int) (panelY * (0.8) - a), 50, a);
			g.fillRect((int) (panelX * (0.4)), (int) (panelY * (0.8) - b), 50, b);
			g.fillRect((int) (panelX * (0.6)), (int) (panelY * (0.8) - c), 50, c);

		}
	}

	private MyPanel mp = new MyPanel();

	public Shrimp() {
		setTitle("fillXXX ��� ����");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setContentPane(mp);
		mp.setLayout(null);

		setSize(panelX, panelY + 30);
		setVisible(true);
	}

	public static String change(String date) throws Exception {
		// 1�� �ּ� ��ü �����
		URL url = new URL("http://apis.data.go.kr/1192000/openapi/service/ManageExpNationItemService/getExpNationItemList?serviceKey=zkymID3s0eE1ymiOd0WYTwCEMeo4qKgV5e9DGU6QHceEc%2BWgah6BN5uEhr9tFRjolXkryBNbw9CNLnn6z9LTpg%3D%3D&numOfRows=10&pageNo=10&baseDt="+ date +"&nationNm=%EC%A4%91%EA%B5%AD&imxprtSeNm=%EC%88%98%EC%9E%85&itemNm=%EB%B1%80%EC%9E%A5%EC%96%B4");

		// 2�� ��Ʈ�� ����
		HttpURLConnection con = (HttpURLConnection) url.openConnection();

		// 3�� ���� ���� (���ڿ�)
		BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8"));
		
		// 4. ���� ���ϱ�
		StringBuilder sb = new StringBuilder();
		
		String input = "";
		while ((input = br.readLine()) != null) {
			sb.append(input);
		}
		
		br.close(); // ���� �ݱ�
		con.disconnect(); // ��Ʈ�� �ݱ�
		
		org.json.JSONObject jsonOb = XML.toJSONObject(sb.toString());
		String jsonString = jsonOb.toString();
		
		
		// 5. �ڹ� ������Ʈ�� ��ȯ
		Gson gson = new Gson();
		Jang jang = gson.fromJson(jsonString, Jang.class);
		
		return jang.getResponseXml().getBody().getItem().get(0).getImxprtWt();
	}

	public static void main(String[] args) {
		ArrayList<String> amount = new ArrayList<>();
		
		
		try {
			amount.add(change("201910"));
			amount.add(change("201911"));
			amount.add(change("201912"));

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//new Shrimp();
		double max = 0;
		double test = 0;
		for (String string : amount) {
			int i = Integer.parseInt(string);
			test = i;
			if(i > max) {
				max = i;
			}
		}
		
		Shrimp.max = (int)max;
		Shrimp.aa = Integer.parseInt(amount.get(0));
		Shrimp.bb = Integer.parseInt(amount.get(1));
		Shrimp.cc = Integer.parseInt(amount.get(2));
		Shrimp.a = (int)(Integer.parseInt(amount.get(0)) / max * 100);
		Shrimp.b = (int)(Integer.parseInt(amount.get(1)) / max * 100);
		Shrimp.c = (int)(Integer.parseInt(amount.get(2)) / max * 100);
		
		new Shrimp();
	}
}