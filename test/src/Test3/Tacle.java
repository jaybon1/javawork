package Test3;

import java.awt.Image;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Tacle {
	
	Image image; // ��ֹ� �̹���
	
	// ��ֹ��� ��ǥ�� ���� ����
	int x;
	int y;
	int width;
	int height;
	
	// ��ֹ� ����
	int state;
}
