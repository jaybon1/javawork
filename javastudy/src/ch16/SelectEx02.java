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
			
			// 드라이버로드
			Class.forName("oracle.jdbc.driver.OracleDriver"); // 싱글톤과 비슷하나, forName으로 호출 할때  객체가 생성된다 
			
			// 스트림 연결
			Connection conn = DriverManager.getConnection(url, userid, pwd);
			
			// 버퍼 달기(?를 사용하게 해준다)
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			
			// 물음표 4개에 값을 넣는다
			pstmt.setInt(1, 3); // DB는 순서가 1부터시작한다

			
			// 버퍼에 쓰기(ResultSet을 리턴받음)
			ResultSet rs = pstmt.executeQuery(); // ResultSet은 튜플을 지정하는 커서를 가져온다
			Users users = null;
			if(rs.next()) { // rs.next() 는 다음 행이 있으면 true 없으면 false를 리턴한다
				
				 // 컬럼번호를 적어도 되지만 컬럼명을 적는게 가독성이좋다
				users = new Users(rs.getInt("id"), rs.getString("name"), rs.getString("email"), rs.getString("password"));
			}
			System.out.println(users.getId());
			System.out.println(users.getName());
			System.out.println(users.getEmail());
			System.out.println(users.getPassword());
			
			System.out.println("셀렉트완료");

			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}

