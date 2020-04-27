package ch16;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class DeleteEx01 {

	public static void main(String[] args) {
		try {
			String SQL = "delete from users where id = ?";
			
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
			pstmt.setInt(1, 1); // DB�� ������ 1���ͽ����Ѵ�

			
			// ���ۿ� ����(commit�� �޷��ִ�) - execute()�� commit�� ����
			pstmt.executeUpdate();
			System.out.println("����Ʈ�Ϸ�");

			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}

