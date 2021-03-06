package aadb1;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class CustomerList {
	Connection con; // 멤버변수

	int index;
	
	// 객체 배열을 쓰는 방법
	class Customer {
		int custId;
		String name;
		String adress;
		String phone;
	}
	
	Customer cArr[];

	public CustomerList() {
		
		// 객체 배열
		cArr = new Customer[10];
		for (int i = 0; i < cArr.length; i++) {
			cArr[i] = new Customer();
		}
		
	}
	
	public void run() throws Exception {
		getConnection();
		getCustomer();
		printCustomer();
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
	void getCustomer() throws Exception {
		String query = "SELECT custid, name, address, phone FROM customer"; // sql문
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery(query);
		
		index = 0;
		while (rs.next()) {
			cArr[index].custId = rs.getInt(1);
			cArr[index].name = rs.getString(2);
			cArr[index].adress = rs.getString(3);
			cArr[index].phone = rs.getString(4);
			
			index++;
		}
		con.close();
	}
	
	void printCustomer() throws Exception {
		System.out.println("custid, name, address, phone");
		for (int i = 0; i < index; i++) {
			System.out.println(cArr[i].custId + ", " + cArr[i].name + ", " + cArr[i].adress + ", " + cArr[i].phone);
		}
	}
	
	
	
}
