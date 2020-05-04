package Test3;

import java.awt.Image;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Tacle {
	
	Image image; // 장애물 이미지
	
	// 장애물의 좌표와 넓이 높이
	int x;
	int y;
	int width;
	int height;
	
	// 장애물 상태
	int state;
}

