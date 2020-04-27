package ch16;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class SelectEx02 {
	
	

	public static void main(String[] args) {
		try {
			String SQL = "select id, name, email, password from users where id = ?";
			
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
			pstmt.setInt(1, 3); // DB�� ������ 1���ͽ����Ѵ�

			
			// ���ۿ� ����(ResultSet�� ���Ϲ���)
			ResultSet rs = pstmt.executeQuery(); // ResultSet�� Ʃ���� �����ϴ� Ŀ���� �����´�
			Users users = null;
			if(rs.next()) { // rs.next() �� ���� ���� ������ true ������ false�� �����Ѵ�
				
				 // �÷���ȣ�� ��� ������ �÷����� ���°� ������������
				users = new Users(rs.getInt("id"), rs.getString("name"), rs.getString("email"), rs.getString("password"));
			}
			System.out.println(users.getId());
			System.out.println(users.getName());
			System.out.println(users.getEmail());
			System.out.println(users.getPassword());
			
			System.out.println("����Ʈ�Ϸ�");

			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}

