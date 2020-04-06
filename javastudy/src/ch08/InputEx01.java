package ch08;

import java.io.IOException;
import java.io.InputStream;

// 스트림은(Byte) => 물의 흐름
// 물이 흐를때 수도꼭지를 잠궈두면 물이 흐르지 않는다
// 물이 흐를 때 수도꼭지를 열면 물이 흐른다(통신시작)
// 물 = 바이트
public class InputEx01 {
	public static void main(String[] args) {
		
		// InputStream 은 int 값을 받는다 => 캐스팅 해줘야함
		// 한개만 받을 수 있다
		InputStream in = System.in; // 키보드
		try {
			int data = in.read();
			System.out.println((char)data);
		} catch (IOException e) {
			System.out.println("I/O 오류가 발생");
		}
	}
}
