package aadb1;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class BookList {
	Connection con;

	int index;
	
	// ��ü �迭�� ���� ���
	class Book {
		int bookId;
		String bookname;
		String publisher;
		int price;
	}
	
	Book cArr[];

	public BookList() {
		
		// ��ü �迭
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
		// 11g express edition�� orcl ��� XE�� �Է��Ѵ�
		String url = "jdbc:oracle:thin:@localhost:1521:XE";

		String userid = "c##madang";
		String pwd = "c##madang";

		try { // ����̹��� ã�� ����
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("����̹� �ε� ����");
		} catch (Exception e) {
		}

		try { // �����ͺ��̽��� �����ϴ� ����
			System.out.println("�����ͺ��̽����� �غ�...");
			con = DriverManager.getConnection(url, userid, pwd);
			System.out.println("�����ͺ��̽����� ����");
		} catch (Exception e) {
		}
	}
	
	// ��ü �迭�� ���� ���
	void getBook() throws Exception {
		String query = "SELECT bookid, bookname, publisher, price FROM book"; // sql��
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