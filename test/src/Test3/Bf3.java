package Test3;

import java.awt.image.BufferedImage;
import java.awt.image.PixelGrabber;
import java.io.File;

import javax.imageio.ImageIO;

public class Bf3 {
	
	static int[] getSize(String src) throws Exception {
		File imgf = new File(src);
		BufferedImage img = ImageIO.read(imgf);
		int width = img.getWidth();
		int height = img.getHeight();
		int[] tempPos = {width, height};
		return tempPos;
	}
	
	static int[][] getPic(String src) throws Exception{
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
        	
        	for (int i = 0; i <	 maxX; i+=2) {
        		for (int j = 0; j < maxY; j+=2) {
        			if(tempArr[i][j] == 0) {
        				System.out.printf("ÀåÆÇ ÁÂÇ¥ : %d, %d \n", i, j);
        			}
					
				}
			}
        	for (int i = 0; i <	 maxX; i+=1) {
        		for (int j = 0; j < maxY; j+=1) {
        			if(tempArr[i][j] == 16776960) {
        				System.out.printf("Á©¸® ÁÂÇ¥ : %d, %d \n", i, j);
        			}
					
				}
			}
        	for (int i = 0; i <	 maxX; i+=2) {
        		for (int j = 0; j < maxY; j+=2) {
        			if(tempArr[i][j] == 16711680) {
        				System.out.printf("Àå¾Ö¹° ÁÂÇ¥ : %d, %d \n", i, j);
        			}
					
				}
			}
        	
        	
		} catch (Exception e) {
			e.printStackTrace();
		}
        
	}
}
