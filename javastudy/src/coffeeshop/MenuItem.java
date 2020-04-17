package coffeeshop;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data   // getter, setter �ڵ����� (���� ������ ����)
@NoArgsConstructor // ����Ʈ ������
@AllArgsConstructor // ��� �Ű������� ���� ������
public class MenuItem {
	private String name;
	private int price;
}
