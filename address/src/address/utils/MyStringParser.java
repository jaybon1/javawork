package address.utils;

public class MyStringParser {
	
	public static int getId(String selectedList) {
		// .은 파싱이 안됨
		// \\. 이나 [.]을 이용하면 된다.
		return Integer.parseInt(selectedList.split("[.]")[0]);
	}
	
}
