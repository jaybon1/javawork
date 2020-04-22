import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class CustomerList {
	Connection con;

	int index;

//	����
//	int custId;
//	String name;
//	String adress;
//	String phone;
//	
//	// �迭
//	int custIdArr[] = new int[10];
//	String nameArr[] = new String[10];
//	String adressArr[] = new String[10];
//	String phoneArr[] = new String[10];

//	// ��ü�� ���� ��� (���� Ŭ����)
//	class Customer {
//		int custId;
//		String name;
//		String adress;
//		String phone;
//	}
//	
//	Customer c1;
	
	// ��ü �迭�� ���� ���
	class Customer {
		int custId;
		String name;
		String adress;
		String phone;
	}
	
	Customer cArr[];

	public CustomerList() {
//		// ��ü
//		c1 = new Customer();
		
		// ��ü �迭
		cArr = new Customer[10];
		for (int i = 0; i < cArr.length; i++) {
			cArr[i] = new Customer();
		}

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

//  //1�� ���
//	void printCustomer() throws Exception {
//		String query = "SELECT custid, name, address, phone FROM customer"; // sql��
//		Statement stmt = con.createStatement();
//		ResultSet rs = stmt.executeQuery(query);
//		System.out.println("custid \tname \t\taddress \tphone");
//		while (rs.next()) {
//			System.out.print("\t" + rs.getInt(1));
//			System.out.print("\t" + rs.getString(2));
//			System.out.print("\t\t" + rs.getString(3));
//			System.out.println("\t" + rs.getString(4));
//		}
//		con.close();
//	}

//	// ������ ���� ���
//	void getCustomer() throws Exception {
//		String query = "SELECT custid, name, address, phone FROM customer"; // sql��
//		Statement stmt = con.createStatement();
//		ResultSet rs = stmt.executeQuery(query);
//		System.out.println("custid, tname, address, phone");
//		while (rs.next()) {
//			custId = rs.getInt(1);
//			name = rs.getString(2);
//			adress = rs.getString(3);
//			phone = rs.getString(4);
//			
//			printCustomer();
//		}
//		con.close();
//	}
//	
//	void printCustomer() throws Exception {
//		System.out.println(custId + ", " + custId + ", " + adress + ", " + phone);
//	}

//	// �迭�� ���� ���
//	void getCustomer() throws Exception {
//		String query = "SELECT custid, name, address, phone FROM customer"; // sql��
//		Statement stmt = con.createStatement();
//		ResultSet rs = stmt.executeQuery(query);
//		
//		index = 0;
//		while (rs.next()) {
//			custIdArr[index] = rs.getInt(1);
//			nameArr[index] = rs.getString(2);
//			adressArr[index] = rs.getString(3);
//			phoneArr[index] = rs.getString(4);
//			
//			index++;
//		}
//		con.close();
//	}
//	
//	void printCustomer() throws Exception {
//		System.out.println("custid, tname, address, phone");
//		for (int i = 0; i < index; i++) {
//			System.out.println(custIdArr[i] + ", " + nameArr[i] + ", " + adressArr[i] + ", " + phoneArr[i]);
//		}
//	}

//	// ��ü�� ���� ���
//	void getCustomer() throws Exception {
//		String query = "SELECT custid, name, address, phone FROM customer"; // sql��
//		Statement stmt = con.createStatement();
//		ResultSet rs = stmt.executeQuery(query);
//		System.out.println("custid, tname, address, phone");
//
//		while (rs.next()) {
//			c1.custId = rs.getInt(1);
//			c1.name = rs.getString(2);
//			c1.adress = rs.getString(3);
//			c1.phone = rs.getString(4);
//			
//			printCustomer();
//
//		}
//		con.close();
//	}
//
//	void printCustomer() throws Exception {
//		System.out.println(c1.custId + ", " + c1.name + ", " + c1.adress + ", " + c1.phone);
//	}
	
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
		System.out.println("custid, tname, address, phone");
		for (int i = 0; i < index; i++) {
			System.out.println(cArr[i].custId + ", " + cArr[i].name + ", " + cArr[i].adress + ", " + cArr[i].phone);
		}
	}
	
	
	
}