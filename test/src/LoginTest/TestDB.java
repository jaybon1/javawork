package LoginTest;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class TestDB {

	// 전역 공간에 쿼리에 필요한 레퍼런스를 준비하기
	Connection con;
	PreparedStatement pstmt;
	ResultSet rs;
	

	// DB에 연결하기
	public void openCon() throws Exception {
		// 오라클 주소, 아이디 비밀번호
		String url = "jdbc:oracle:thin:@localhost:1521:XE";
		String userid = "cos";
		String pwd = "bitc5600";

		// 자바 라이브러리 파일 로드
		Class.forName("oracle.jdbc.driver.OracleDriver");
		System.out.println("드라이버 로드 성공");

		// 네트워크 입출력 객체 생성
		System.out.println("데이터베이스 연결 준비...");
		con = DriverManager.getConnection(url, userid, pwd);
		System.out.println("데이터베이스 연결 성공");
	}

	// DB 연결 끊기
	public void closeCon() throws Exception {
		if(con != null) {
			con.close();	
		}
		if(pstmt != null) {
			pstmt.close();			
		}
		if(rs != null) {
			rs.close();			
		}
		
		System.out.println("데이터베이스 연결 해제");
	}

	// 데이터 넣기
	public int insertData(Users user) {
		try {
			openCon();
			String SQL = "INSERT INTO users(id, pw, name, phone) VALUES(?,?,?,?)";
			pstmt = con.prepareStatement(SQL);
			pstmt.setString(1, user.getId());
			pstmt.setString(2, user.getPw());
			pstmt.setString(3, user.getName());
			pstmt.setString(4, user.getPhone());
			int result = pstmt.executeUpdate();
			return result;
		} catch (Exception e) {
			System.out.println("insertData : "+ e.getMessage());
		} finally {
			try {
				closeCon();
			} catch (Exception e) {
				System.out.println("insertData catch : "+ e.getMessage());
			}
		}
		return -1;
	}
	
	// 데이터 바꾸기
	public int updateData(Users user, String pw, String name, String phone) {
		try {
			openCon();
			String SQL = "UPDATE users SET pw = ?, name = ?, phone = ? WHERE id = ?";
			pstmt = con.prepareStatement(SQL);
			pstmt.setString(1, pw);
			pstmt.setString(2, name);
			pstmt.setString(3, phone);
			pstmt.setString(4, user.getId());
			int result = pstmt.executeUpdate();
			return result;
		} catch (Exception e) {
			System.out.println("updateData : "+ e.getMessage());
		}finally {
			try {
				closeCon();
			} catch (Exception e) {
				System.out.println("insertData catch : "+ e.getMessage());
			}
		}
		
		return -1;
	}
	
	// 튜플 가져오기
	public Users selectData(String id) {
		try {
			openCon();
			String SQL = "SELECT id, pw, name, phone FROM users WHERE id = ?";
			pstmt = con.prepareStatement(SQL);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				return new Users(rs.getString("id"),rs.getString("pw"),rs.getString("name"),rs.getString("phone"));				
			} else {
				return null;
			}
			
		} catch (Exception e) {
			System.out.println("selectData : "+ e.getMessage());
		}finally {
			try {
				closeCon();
			} catch (Exception e) {
				System.out.println("selectData catch : "+ e.getMessage());
			}
		}
		return null;
	}
	
	// 데이터 지우기 - 지금 필요없는 기능
	public int deleteData(String id) {
		try {
			openCon();
			String SQL = "DELETE FROM users WHERE id = ?";
			pstmt = con.prepareStatement(SQL);
			pstmt.setString(1, id);
			int result = pstmt.executeUpdate();
			return result;
		} catch (Exception e) {
			System.out.println("deleteData : "+ e.getMessage());
		}finally {
			try {
				closeCon();
			} catch (Exception e) {
				System.out.println("deleteData catch : "+ e.getMessage());
			}
		}
		return -1;
	}
	
	// 모든 데이터 가져오기 - 지금 필요없는 기능
	public List<Users> selectAllData() {
		try {
			openCon();
			String SQL = "SELECT id, pw, name, phone FROM users";
			pstmt = con.prepareStatement(SQL);
			rs = pstmt.executeQuery();
			List<Users> userList = new ArrayList<>();
			while (rs.next()) {
				Users user = new Users(rs.getString("id"),rs.getString("pw"),rs.getString("name"),rs.getString("phone"));
				userList.add(user);
			}
			return userList;
			
		} catch (Exception e) {
			System.out.println("selectAllData : "+ e.getMessage());
		}finally {
			try {
				closeCon();
			} catch (Exception e) {
				System.out.println("selectAllData catch : "+ e.getMessage());
			}
		}
		return null;
	}
	
	public static void main(String[] args) {
		TestDB td = new TestDB();
		try {
//			Users user = new Users("hi","123","가나다","01011112222");
//			td.insertData(user);
			
//			td.deleteData("hi");
			
//			Users user = new Users("hi","123","가나다","01011112222");
//			td.updateData(user, "123", "가나다", "01011112223");
			
//			System.out.println(td.selectData("yoman"));
			
//			List<Users> userList = td.selectAllData();
//			for (Users users : userList) {
//				System.out.println(users);
//			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
