
public class Basic {
	public static void main(String[] args) {
//		BookList so = new BookList();
//		try {
//			so.printBook();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
		
		CustomerList cl = new CustomerList();
		try {
			cl.getCustomer();
			cl.printCustomer();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
