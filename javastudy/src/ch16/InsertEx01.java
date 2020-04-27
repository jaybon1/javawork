package ch16;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class InsertEx01 {

	public static void main(String[] args) {
		try {
			String SQL = "insert into users(id, name, email, password) values(?,?,?,?)";
			
			String url = "jdbc:oracle:thin:@localhost:1521:XE";
			String userid = "c##madang";
			String pwd = "c##madang";
			
			// ����̹��ε�
			Class.forName("oracle.jdbc.driver.OracleDriver"); // �̱���� ����ϳ�, forName���� ȣ�� �Ҷ�  ��ü�� �����ȴ� 
			
			// ��Ʈ�� ����
			Connection conn = DriverManager.getConnection(url, userid, pwd);
			
			// ���� �ޱ�(?�� ����ϰ� ���ش�)
			PreparedStatement pstmt = conn.prepareStatement(SQL); // ������ ������ �����ش�
			
			// ����ǥ 4���� ���� �ִ´�
			pstmt.setInt(1, 4); // DB�� ������ 1���ͽ����Ѵ�
			pstmt.setString(2, "�常��");
			pstmt.setString(3, "jang@��������.com");
			pstmt.setString(4, "1234");
			
			// ���ۿ� ����(commit�� �޷��ִ�) - execute()�� commit�� ����
			pstmt.executeUpdate();
			System.out.println("�μ�Ʈ�Ϸ�");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}

