package composite;

import lombok.Data;

@Data
public class FrenchFried {
	private int price;
	private String desc;
	
	public FrenchFried() {
		this(2000, "����Ĩ");
	}
	
	public FrenchFried(int price, String desc) {
		this.price = price;
		this.desc = desc;
		System.out.println(desc + "�� ����������ϴ�.");
	}
	
	public static void main(String[] args) {
		System.out.println(new FrenchFried().getPrice());
	}
}
