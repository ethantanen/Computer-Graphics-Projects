import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Stack;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class Canvas extends JPanel {

	Graphics2D g2d;
	HashMap<String,String> key;
	String instructions;
	Stack<double[]> stack;
	
	
	public Canvas(String i){

		setPreferredSize(new Dimension(500,500));
		setBackground(Color.LIGHT_GRAY);
		instructions = i;
		stack = new Stack<>();
	}
	
	
	public void paintComponent(Graphics z){
		g2d = (Graphics2D)z;
		createImage();
		
	}
	
	public void createImage(){
		
		g2d.translate(0,getHeight()/2);
		g2d.scale(1, -1);
		
		String[] ins = instructions.split("");
		String 	com;
		
		double x=0,y=0,angle=0;
		
		
		for(int i=0; i<ins.length; i++){
			
			com = ins[i];
			
			if(com.equals("F") || com.equals("G")){
				
				double oldx = x, oldy =y;
				x += 5 * Math.cos(Math.toRadians(angle));
				y += 5
						* Math.sin(Math.toRadians(angle));
				
				g2d.setColor(Color.orange);
				g2d.draw(new Line2D.Double(oldx, oldy, x, y));
				

			}else if(com.equals("+")){

				angle -= 25;
					
			}else if(com.equals("-")){
				
				
				
				angle += 25;
				
			}else if(com.equals("[")){
				stack.push(new double[]{x,y,angle});

			}else if(com.equals("]")){
				double[] o = stack.pop();
				x = o[0];
				y = o[1];
				
				angle = o[2];
			}
		}
		
		
	}

}
