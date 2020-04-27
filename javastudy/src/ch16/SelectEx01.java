package ch16;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class SelectEx01 {
	
	

	public static void main(String[] args) {
		try {
			String SQL = "select id, name, email, password from users";
			
			String url = "jdbc:oracle:thin:@localhost:1521:XE";
			String userid = "c##madang";
			String pwd = "c##madang";
			
			// ����̹��ε�
			Class.forName("oracle.jdbc.driver.OracleDriver"); // �̱���� ����ϳ�, forName���� ȣ�� �Ҷ�  ��ü�� �����ȴ� 
			
			// ��Ʈ�� ����
			Connection conn = DriverManager.getConnection(url, userid, pwd);
			
			// ���� �ޱ�(?�� ����ϰ� ���ش�)
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			
			// ���ۿ� ����(ResultSet�� ���Ϲ���)
			ResultSet rs = pstmt.executeQuery(); // ResultSet�� Ʃ���� �����ϴ� Ŀ���� �����´�
			
			List<Users> users = new ArrayList<>(); // �������� ���� List�� �������
			
			while(rs.next()) { // rs.next() �� ���� ���� ������ true ������ false�� �����Ѵ�
				
				 // �÷���ȣ�� ��� ������ �÷����� ���°� ������������
				Users user = new Users(rs.getInt("id"), rs.getString("name"), rs.getString("email"), rs.getString("password"));
				users.add(user);
			}
			
			for (Users user : users) {				
				System.out.println(user.getId());
				System.out.println(user.getName());
				System.out.println(user.getEmail());
				System.out.println(user.getPassword());
				System.out.println();
			}
			
			System.out.println("����Ʈ�Ϸ�");

			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}

