package address.utils;

public class MyStringParser {
	
	public static int getId(String selectedList) {
		// .�� �Ľ��� �ȵ�
		// \\. �̳� [.]�� �̿��ϸ� �ȴ�.
		return Integer.parseInt(selectedList.split("[.]")[0]);
	}
	
}
