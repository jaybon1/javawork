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
			
			
			// 드라이버로드
			Class.forName("oracle.jdbc.driver.OracleDriver"); // 싱글톤과 비슷하나, forName으로 호출 할때  객체가 생성된다 
			
			
			// 스트림 연결
			Connection conn = DriverManager.getConnection(url, userid, pwd);
			
			
			// 버퍼 달기(?를 사용하게 해준다)
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			
			
			// 물음표 4개에 값을 넣는다
			pstmt.setInt(1, 1); // DB는 순서가 1부터시작한다

			
			// 버퍼에 쓰기(commit이 달려있다) - execute()는 commit이 없다
			pstmt.executeUpdate();
			System.out.println("딜리트완료");

			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}

