package Test5;

import java.awt.Image;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Jelly {
	private Image image; // ���� �̹���
	
	// �������� ��ǥ�� ũ��
	private int x;
	private int y;
	private int width;
	private int height;
	
	// ������ ����
	private int score;
}

