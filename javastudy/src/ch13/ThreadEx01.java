package ch13;

// OS�� RunnableŸ���� heap������ run�޼��带 ȣ���ؾߵȴٴ� �� �̹� �˰� ����
class Sub implements Runnable { // ���ʺ�Ÿ�Ը��� OS�� ���� �޼��带 ã�� �� �ִ�

	String data = "�� ����";

	@Override
	public void run() {
		for (int i = 1; i < 11; i++) {
			System.out.println("���� ������ : " + i);
			try {
				Thread.sleep(1000);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		data = "�ȳ�";
	}
}

public class ThreadEx01 {

	// ���� ������
	public static void main(String[] args) {
		
		Thread t1 = new Thread(new Sub()); // ���� �߰� ���� �޼��带 ã�� OS�� �ش� �޼��带 ����
		t1.start();

		for (int i = 1; i < 6; i++) {
			System.out.println("���� ������ : " + i);
			try {
				Thread.sleep(1000);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}