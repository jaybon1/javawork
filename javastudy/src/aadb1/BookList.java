package aadb1;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class BookList {
	Connection con; // 멤버변수

	int index;
	
	// 객체 배열을 쓰는 방법
	class Book {
		int bookId;
		String bookname;
		String publisher;
		int price;
	}
	
	Book cArr[];

	public BookList() {
		
		// 객체 배열
		cArr = new Book[15];
		for (int i = 0; i < cArr.length; i++) {
			cArr[i] = new Book();
		}
		
	}
	
	public void run() throws Exception {
		getConnection();
		getBook();
		printBook();
	}
	
	public void getConnection() {
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
	
	// 객체 배열을 쓰는 방법
	void getBook() throws Exception {
		String query = "SELECT bookid, bookname, publisher, price FROM book"; // sql문
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery(query);
		
		index = 0;
		while (rs.next()) {
			cArr[index].bookId = rs.getInt(1);
			cArr[index].bookname = rs.getString(2);
			cArr[index].publisher = rs.getString(3);
			cArr[index].price = rs.getInt(4);
			
			index++;
		}
		con.close();
	}
	
	void printBook() throws Exception {
		System.out.println("bookid, bookname, publisher, price");
		for (int i = 0; i < index; i++) {
			System.out.println(cArr[i].bookId + ", " + cArr[i].bookname + ", " + cArr[i].publisher + ", " + cArr[i].price);
		}
	}
	
	
	
}
