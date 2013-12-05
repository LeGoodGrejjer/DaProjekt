package project;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;

public class PolygonKul {

	private BufferedImage image;
	int[]rgb;
	
	public PolygonKul(BufferedImage image){
		this.image = image;
		
		loopImage();
	}
	        
	
	public void loopImage(){
		int[][] result = new int[image.getHeight()][image.getWidth()];
		
		for (int row = 0; row < image.getHeight(); row++) {
            for (int col = 0; col < image.getWidth(); col++) {
               result[row][col] = image.getRGB(col, row);
               
               printPixelARGB(result[row][col]);
            }
         }
	}
	
	public void printPixelARGB(int pixel) {
	    int alpha = (pixel >> 24) & 0xff;
	    int red = (pixel >> 16) & 0xff;
	    int green = (pixel >> 8) & 0xff;
	    int blue = (pixel) & 0xff;
	    System.out.println("argb: " + alpha + ", " + red + ", " + green + ", " + blue);
	  }
}
