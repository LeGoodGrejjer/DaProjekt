package project;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.util.ArrayList;
import java.util.List;

import javax.swing.text.TabableView;

public class PolygonKul {

	private BufferedImage image;
	int[]rgb;
	int col = 0;
	int row = 0;
	Graphics g;
	
	public PolygonKul(BufferedImage image, Graphics g){
		this.image = image;
		this.g = g;
	}
	        
	
	public void loopImage(Graphics2D g){
		
		List<Vector2> paintPointsArrayList = new ArrayList<Vector2>();
		int startcol = -1;
		int startrow = -1;
		int n = 0;
		
		int count = 0;
		
		while(col != startcol || row != startrow){
			n++;
			//System.out.println(col+", "+row);
			
			int alpha00 = (image.getRGB(col, row) >> 24) & 0xff;
	        int alpha01 = (image.getRGB(col+1, row) >> 24) & 0xff;
	        int alpha10 = (image.getRGB(col, row+1) >> 24) & 0xff;
	        int alpha11 = (image.getRGB(col+1, row+1) >> 24) & 0xff;
	        String str = "";
	        if(alpha00 != 0){
	     	   str+="1";
	        }else{
	     	   str+="0";
	        }
	        
	        if(alpha01 != 0){
	     	   str+="1";
	        }else{
	     	   str+="0";
	        }
	        
	        if(alpha10 != 0){
	     	   str+="1";
	        }else{
	     	   str+="0";
	        }
	        
	        if(alpha11 != 0){
	     	   str+="1";
	        }else{
	     	   str+="0";
	        }
	        
	        
	        switch (Integer.parseInt(str)) {
			case 0:{
				//System.out.println("ddas");
				if(col == image.getWidth()-2){
					row++;
					col = 0;
				}else{
					col++;
				}
			}
				break;
				
			case 1:{
				if(startcol == -1){
					startcol = col;
					startrow = row;
				}
				row++;
			}
				break;
				
			case 10:{
				col--;
			}
				break;
				
			case 11:{
				col--;
			}
				break;
				
			case 100:{
				col++;
			}
				break;
				
			case 101:{
				row++;
			}
				break;
				
			case 110:{
				col--;
			}
				break;
				
			case 111:{
				col--;
			}
				break;
				
			case 1000:{
				row--;
			}
				break;
				
			case 1001:{
				row--;
			}
				break;
				
			case 1010:{
				row--;
			}
				break;
				
			case 1011:{
				row--;
			}
				break;
				
			case 1100:{
				col++;
			}
				break;
				
			case 1101:{
				row++;
			}
				break;
				
			case 1110:{
				col++;
			}
				break;
				
			case 1111:{
				System.out.println("det blev nagot allvarligt fel. Det borde hanteras av grafikmotorn... ey!");
				
			}
				break;

			default:
				break;
			}
	        
	        if(startcol != -1){
	        	count++;
	        	if(count == 10){
	        		count = 0;
	        		paintPointsArrayList.add(new Vector2(col, row));
	        	}
	        	
	        	//System.out.println("sdsdf");
	        }
	        
	        if(Integer.parseInt(str) == 1111){
	        	break;
	        }
	        
		}
		
		boolean tarBort = true;
		while(tarBort){
			tarBort = false;
			for(Vector2 vic : paintPointsArrayList){
				if(paintPointsArrayList.indexOf(vic) >= 2){
					Vector2 tempVec1 = paintPointsArrayList.get(paintPointsArrayList.indexOf(vic)-2);
					Vector2 tempVec2 = paintPointsArrayList.get(paintPointsArrayList.indexOf(vic)-1);
					
					Vector2 vec2till1 = Vector2.delta(tempVec2, tempVec1).normalize();
					Vector2 vec2till3 = Vector2.delta(tempVec2, vic).normalize();
					float dot = Vector2.dot(vec2till3, vec2till1);
					
					float deltaAngle = (float)Math.acos(dot);
					System.out.println(deltaAngle);
					if(deltaAngle > Math.PI)
						deltaAngle -= Math.PI;
					
					
					if(deltaAngle > Math.toRadians(163f)){
						System.out.println("deleted");
						tarBort = true;
						paintPointsArrayList.remove(tempVec2);
						break;
					}
				}
			}
		}
		
		for(Vector2 vic : paintPointsArrayList){
			g.setColor(Color.red);
        	g.drawRect((int)vic.x, (int)vic.y, 1, 1);
        	
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
