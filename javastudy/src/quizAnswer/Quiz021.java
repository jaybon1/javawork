package quizAnswer;

import java.util.HashSet;

// HashSet �� �����迭�̴�
// HashSet<Integer> arr ó�� ���� �ȿ��� Ŭ������ WrapperŸ���� ���� �� �ִ�.
// HashSet�� ���� �߰��Ϸ��� arr.add(��);
// HashSet�� ���� ���� add�ϸ� �����Ѵ�(�߰��� �����ʴ´�)
// HashSet�� ���̸� �˰� �ʹٸ� arr.size();
// HashSet�� index�� ���� ������ ���° ���� �� ���� ����.
// HashSet�� ���� ���� �� arr.remove(��);�� ����ϸ� index�� �ƴ� �� ��ü�� ã�Ƽ� �����

// �Ʒ� arr�� �������� 5���� �ְ�
// ���̸� ����ϼ���


public class Quiz021 {
	public static void main(String[] args) {
		
		HashSet<Integer> arr = new HashSet<>();
		
		
		// ����
		arr.add(1);
		arr.add(1);
		arr.add(1);
		arr.add(1);
		arr.add(1);
		
		System.out.println(arr.size());
	}
}
