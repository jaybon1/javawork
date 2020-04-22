package aadb1;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class CustomerList {
	Connection con;

	int index;
	
	// ��ü �迭�� ���� ���
	class Customer {
		int custId;
		String name;
		String adress;
		String phone;
	}
	
	Customer cArr[];

	public CustomerList() {
		
		// ��ü �迭
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
	void getCustomer() throws Exception {
		String query = "SELECT custid, name, address, phone FROM customer"; // sql��
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