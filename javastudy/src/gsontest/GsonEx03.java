package gsontest;

import com.google.gson.Gson;


class Cos {

	private String name;
	private Integer age;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}
}

//{"name":"�ڽ�", "age":40}

public class GsonEx03 {

	public static void main(String[] args) {
		// �ٿ���� json ������
		//Ÿ���� String (BufferdReader�� �����״ϱ�.. ���ڿ��̴�) 
		String jsonDate = "{\"name\":\"�ڽ�\", \"age\":40}";  

		// json ������ -> java ������Ʈ
		Gson gson = new Gson();
		
		Cos c1 = gson.fromJson(jsonDate, Cos.class);
		
		System.out.println(c1.getName());

	}
}