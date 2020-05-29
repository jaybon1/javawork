package address.db;

import java.sql.Connection;
import java.sql.DriverManager;

import org.junit.Test;

public class DBConnection {
	public static Connection getConnection() {
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "c##madang", "c##madang");
			return conn;
		} catch (Exception e) {
			System.out.println("DB 연결 실패 : " + e.getMessage());
			e.printStackTrace();
		}
		return null;
	}
	
	public static void main(String[] args) {
		Connection c1 = getConnection();
	}
}
