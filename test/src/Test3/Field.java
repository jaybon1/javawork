package Test3;

import java.awt.Image;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Field {
	
	Image image; // 발판 이미지
	
	// 발판의 좌표와 넓이 높이
	int x;
	int y;
	int width;
	int height;
}

