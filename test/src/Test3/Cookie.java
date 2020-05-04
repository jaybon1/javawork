package Test3;

import java.awt.Image;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cookie {
	
	Image image; // 쿠키 이미지
	
	// 쿠키의 좌표와 넓이 높이
	int x;
	int y;
	int width;
	int height;
	
	// 쿠키의 투명도
	int alpha;
	
	// 쿠키의 상태
	int state;
}
