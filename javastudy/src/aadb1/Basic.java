package aadb1;

public class Basic {
	public static void main(String[] args) {
		try {
			new CustomerList().run();
			new BookList().run();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
