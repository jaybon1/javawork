import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class BookList {
	Connection con;

	public BookList() {

		// 11g express edition은 orcl 대신 XE를 입력한다
		String url = "jdbc:oracle:thin:@localhost:1521:XE";

		String userid = "c##madang";
		String pwd = "c##madang";

		try { // 드라이버를 찾는 과정
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("드라이버 로드 성공");
		} catch (Exception e) {
		}

		try { // 데이터베이스를 연결하는 과정
			System.out.println("데이터베이스연결 준비...");
			con = DriverManager.getConnection(url, userid, pwd);
			System.out.println("데이터베이스연결 성공");
		} catch (Exception e) {
		}
	}

	void printBook() throws Exception {
		String query = "SELECT bookid, bookname, publisher, price FROM Book"; // sql문
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery(query);
		System.out.println("BOOK NO \tBOOK NAME \t\tPUBLISHER \tPRICE");
		while (rs.next()) {
			System.out.print("\t" + rs.getInt(1));
			System.out.print("\t" + rs.getString(2));
			System.out.print("\t\t" + rs.getString(3));
			System.out.println("\t" + rs.getInt(4));
		}
		con.close();
	}
}
