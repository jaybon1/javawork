package Test3;

import java.awt.image.BufferedImage;
import java.awt.image.PixelGrabber;
import java.io.File;

import javax.imageio.ImageIO;

public class Bf2 {
	
	static int[] getSize(String src) throws Exception { // 이미지의 사이즈를 가져오는 메서드
		File imgf = new File(src); // 이미지 경로를 입력받아서 파일객체를 만든다.
		BufferedImage img = ImageIO.read(imgf); // 이미지를 버퍼드이미지에 넣는다.
		int width = img.getWidth(); // 이미지의 넓이
		int height = img.getHeight(); // 이미지의 높이
		int[] tempPos = {width, height}; // 넓이 높이를 배열로 만든다.
		return tempPos; // 리턴한다.
	}
	
	static int[][] getPic(String src) throws Exception{ // 아래 코드는 어디다가 갖다두고 쓰자.
		File imgf = new File(src);
		BufferedImage img = ImageIO.read(imgf);
		int width = img.getWidth();
		int height = img.getHeight();
		int[] pixels=new int[width*height];
		PixelGrabber grab = new PixelGrabber(img, 0, 0, width, height, pixels, 0,width);
		grab.grabPixels();
		
		int[][] picture=new int[width][height];
		for(int i=0;i<pixels.length;i++)
		      picture[i%width][i/width]=pixels[i] + 16777216;
		return picture;
	}
	
	public static void main(String[] args) {
        int x = 10;
        int y = 21;
        try {
        	int[] tempArr1 = getSize("img/firstMap.png");
        	int maxX = tempArr1[0];
        	int maxY = tempArr1[1];
        	
        	int[][] tempArr = getPic("img/firstMap.png");
        	
        	for (int i = 0; i <	 maxX; i+=1) {
        		for (int j = 0; j < maxY; j+=1) {
        			System.out.println(tempArr[i][j]);
					
				}
			}
        	
        	
		} catch (Exception e) {
			e.printStackTrace();
		}
        
	}
}
