package ch06;

class Person{
	String name = "홍길동";
	int age = 15;
	String job = "학생";
}

public class ObjectEx02 {
	public static void main(String[] args) {
		
		// 모든 오브젝트는  toString 할 수 있다
		int num = 10;
		String s = Integer.toString(num);
		System.out.println(s);
		
		Person p = new Person();
		System.out.println(p.toString());
		System.out.println(p);
		
		StringBuilder sb = new StringBuilder();
		sb.append("안녕");
		sb.append("반가워");
		
		System.out.println(sb.toString());
	}
}
