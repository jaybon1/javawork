package simple;

class Animal{
	
}

public class ObjectEx01 {
	public static void main(String[] args) {
		String d1 = "��";
		String d2 = "��";
		
		System.out.println(d1.equals(d2));
		System.out.println(d1 == d2);
		
		String d3 = new String("��");
		String d4 = new String("��");
		
		System.out.println(d3.equals(d4));
		System.out.println(d3 == d4);
	}
}
