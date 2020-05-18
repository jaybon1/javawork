package address.db;

import java.sql.Connection;
import java.sql.DriverManager;

import org.junit.Test;

public class DBConnection {
	public static Connection getConnection() {
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "cos", "bitc5600");
			return conn;
		} catch (Exception e) {
			System.out.println("DB 연결 실패 : " + e.getMessage());
		}
		return null;
	}
	
	public static void main(String[] args) {
		Connection c1 = getConnection();
		Connection c2 = getConnection();
		
		System.out.println(c1);
		System.out.println(c2);
		
	}
}
