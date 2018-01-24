import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.awt.geom.Line2D;
import java.awt.geom.Path2D;

import javax.swing.JPanel;

public class Canvas extends JPanel {
	
	Graphics2D g2d;
	Path2D path;
	
	float green;
	
	public Canvas(){
		
		setPreferredSize(new Dimension(1000,800));
		setBackground(Color.LIGHT_GRAY);
		
		path = new Path2D.Double();
		green = 40;
		
	}
	
	public void paintComponent(Graphics z){
		super.paintComponent(z);
		g2d = (Graphics2D) z;
		g2d.translate(getWidth()/2, getHeight()+250);
		g2d.scale(1,-1);
		path.moveTo(0, 0);
		branch(500,0,0,0,10);	
	}
	
	public void branch(int len, double angle,double x, double y, int color){
		
		if(len <1){
			//reverseBranch(len,angle,x,y);
			return;
		}
		
		double x2 = len*Math.sin(Math.toRadians(angle)) + x ;
		double y2 = len*Math.cos(Math.toRadians(angle)) + y ;
		
		
		if(len != 500){
			g2d.setStroke(new BasicStroke(len/50));
			g2d.setColor(new Color(50,color,0));
			g2d.draw(new Line2D.Double(x,y,x2,y2));
		}
		
		
		//sierpinskis triangle 
		/*
		branch(len/2, 0, x2,y2,color+=10);
		branch(len/2, 120, x2,y2,color+=10);
		branch(len/2,240, x2,y2,color+=10);
		*/
		/*
		branch(len/2, 0, x2,y2,color+=10);
		branch(len/2, 30, x2,y2,color+=10);
		branch(len/2,30, x2,y2,color+=10);
		*/
		
		//cool patio pattern/ grid thing 
		branch(len/2, 0, x2,y2,50);
		branch(len/2, 90, x2,y2,50);
		branch(len/2, 180, x2,y2,50);
		branch(len/2, 270, x2,y2,50);
		 
		
			/*
		//random branch test 
		branch(len/2, Math.random()*100, x2, y2,color+=10);
		branch(len/2, -1*Math.random()*100, x2, y2,color+=10);
		*/
		

	}

	/*
	 * same as above save for the len gets larger rather than smaller 
	 */
	private void reverseBranch(int len, double angle, double x, double y) {
		if(len > 10){
			return;
		}
		
		double x2 = len*Math.sin(Math.toRadians(angle)) + x ;
		double y2 = len*Math.cos(Math.toRadians(angle)) + y ;
		
		g2d.setStroke(new BasicStroke(len/2));
		g2d.draw(new Line2D.Double(x,y,x2,y2));

		reverseBranch(len += 5, Math.random()*120, x2,y2);
		reverseBranch(len += 5, Math.random()*120, x2,y2);
		
	}

}
