
public class Test8 {
	public static void main(String[] args) {
		String a = "�����ٶ�";
		System.out.println(a == "�����ٶ�");
		String b = new String("�����ٶ�");
		System.out.println(a == b);
	}
}
