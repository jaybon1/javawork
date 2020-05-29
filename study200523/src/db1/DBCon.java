package db1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DBCon {
	
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
	
	public static void close(Connection conn, PreparedStatement pstmt) {
		try {
			conn.close();
			pstmt.close();
		} catch (Exception e) {
			System.out.println("DB종료시 오류가 발생 : " +e.getMessage());
		}
	}
	
	public static void close(Connection conn, PreparedStatement pstmt, ResultSet rs) {
		try {
			conn.close();
			pstmt.close();
			rs.close();
		} catch (Exception e) {
			System.out.println("DB종료시 오류가 발생 : " +e.getMessage());
		}
	}
	
	public static void main(String[] args) {
		Connection c1 = getConnection();
		
	}
	
}
