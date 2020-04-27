package ch16;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class UpdateEx01 {

	public static void main(String[] args) {
		try {
			String SQL = "update users set password = ? where id = ?";
			
			String url = "jdbc:oracle:thin:@localhost:1521:XE";
			String userid = "c##madang";
			String pwd = "c##madang";
			
			// ����̹��ε�
			Class.forName("oracle.jdbc.driver.OracleDriver"); // �̱���� ����ϳ�, forName���� ȣ�� �Ҷ�  ��ü�� �����ȴ� 
			
			// ��Ʈ�� ����
			Connection conn = DriverManager.getConnection(url, userid, pwd);
			
			// ���� �ޱ�(?�� ����ϰ� ���ش�)
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			
			// ����ǥ 4���� ���� �ִ´�
			pstmt.setString(1, "5678"); // DB�� ������ 1���ͽ����Ѵ�
			pstmt.setInt(2, 2);
			
			// ���ۿ� ����(commit�� �޷��ִ�) - execute()�� commit�� ����
			pstmt.executeUpdate();
			System.out.println("������Ʈ�Ϸ�");

			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}

