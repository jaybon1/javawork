package ch05;

// �����

interface Į {
	abstract void kill();

	abstract void cook();

	abstract void repair();
}


// ��� �޴� �߻� Ŭ������ �θ� Ŭ������ ��� Ŭ������ �������̵� �� �ʿ䰡 ����.
// �丮�� ����� - �ʿ���� ��ɸ� �Է��Ѵ�. (cook�޼��常 �ڽ�Ŭ������ ������ �̷��.)
abstract class �丮�� implements Į {
	
	@Override
	public void kill() {}
	
	@Override
	public void repair() {}

}

// �丮�� Ŭ������ �������� ���� ��ɸ� �����ϸ� �ȴ�.
class ������ extends �丮��{

	@Override
	public void cook() {
		// TODO Auto-generated method stub	
	}
}

public class FoodEx03 {

	
	public static void main(String[] args) {
		������ b1 = new ������();

	}
}