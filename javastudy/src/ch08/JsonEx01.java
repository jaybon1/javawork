package ch08;

import com.google.gson.Gson;

import lombok.AllArgsConstructor;

@AllArgsConstructor
class Family {
	int ��;
	String �ƹ���;
	String ��Ӵ�;
}

public class JsonEx01 {
	public static void main(String[] args) {
		Gson gson = new Gson();
		// fromJson() �Լ� : json(String) => java object
		// toJson() �Լ� : java object => json(String)

		Family a = new Family(3, "ȫ�Ǽ�", "�ἶ");

		String personJson = gson.toJson(a);
		System.out.println(personJson);

	}
}
