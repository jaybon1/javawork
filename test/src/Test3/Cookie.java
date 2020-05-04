package Test3;

import java.awt.Image;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cookie {
	
	Image image; // ��Ű �̹���
	
	// ��Ű�� ��ǥ�� ���� ����
	int x;
	int y;
	int width;
	int height;
	
	// ��Ű�� ����
	int alpha;
	
	// ��Ű�� ����
	int state;
}
