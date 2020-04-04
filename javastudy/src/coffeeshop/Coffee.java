package coffeeshop;

import lombok.Data;

@Data // getter, setter �ڵ�����
public class Coffee {
	private String name;
	private int price;
	
	public Coffee(MenuItem menuItem) {
		this.name = menuItem.getName();
		this.price = menuItem.getPrice();
	}
}
