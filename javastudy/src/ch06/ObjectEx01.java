package ch06;

class Animal{
	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return super.hashCode();
	}
}

public class ObjectEx01 {
	public static void main(String[] args) {
		
		// ��Ʈ���� ���� ������ �ּҰ� ����
		String d1 = "��";
		String d2 = "��";
		
		System.out.println(d1.equals(d2));
		System.out.println(d1 == d2);
		
		// new�� �̿��ϸ� �ּҰ� �޶�����
		String d3 = new String("��");
		String d4 = new String("��");
		
		System.out.println(d3.equals(d4));
		System.out.println(d3 == d4);
		
		System.out.println(d3.getClass()); // Ŭ������ ��θ� �˷���
		System.out.println(new ObjectEx01().getClass());
		
		//�ؽ��ڵ� -> �ؽ� �˰����� = ������ ������ ���ڷ� ����
		//�ּҰ� �ٸ��� �ٸ��� ���´�, ������ ��Ʈ�� Ÿ���� ���� ������ ���� �������� �Ǿ� �ִ�
		System.out.println(d3.hashCode());
		System.out.println(d4.hashCode());
		
		Animal a1 = new Animal();
		Animal a2 = new Animal();
		
		System.out.println(a1.hashCode());
		System.out.println(a2.hashCode());
		
		System.out.println(a1 instanceof Animal);
		System.out.println(a2 instanceof Animal);
	}
}