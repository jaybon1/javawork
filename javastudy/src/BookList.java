import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class BookList {
	Connection con;

	public BookList() {

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

	void printBook() throws Exception {
		String query = "SELECT bookid, bookname, publisher, price FROM Book"; // sql��
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