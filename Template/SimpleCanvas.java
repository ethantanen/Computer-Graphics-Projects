
/**
 * SimpleCanvas.java
 *
 * Part of the basic graphics Template.
 *
 **/

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;


public class SimpleCanvas extends JPanel implements MouseListener
{

   Graphics2D g2d;
   Polygon triangle,rect1,rect2,rect3;
   Color c1,c2,c3,c4;


    public SimpleCanvas ()
	 {
		//The following is another way to guarantee correct size.	 
		setPreferredSize(new Dimension(500,500));
		setBackground(Color.lightGray);
		addMouseListener(this);
      
	      c1 = Color.green;
	      c2 = Color.orange;
	      c3 = Color.black;
	      c4 = Color.gray;
	      
			
    }

    public void paintComponent(Graphics g)
    {
		super.paintComponent(g);  //without this no background color set.
	
		g2d = (Graphics2D)g; //cast so we can use JAVA2D.
		g2d.translate(getWidth()/2,getHeight()/2);
		
		g2d.setPaint(c1);
      int[] triangle_x = {0,0,100};
      int[] triangle_y = {0,100,0};
      Polygon triangle = new Polygon(triangle_x,triangle_y,3);
      g2d.fill(triangle);
      g2d.draw(triangle);
      
      g2d.setPaint(c2);
      int[] rect1_x = {0,100,100,0};
      int[] rect1_y = {0,0,-100,-100};
      rect1 = new Polygon(rect1_x,rect1_y,4);
      g2d.fill(rect1);
      g2d.draw(rect1);
      //g2d.fillRect(0,-100,100,100);
      
 		g2d.setPaint(c3);
      int[] rect2_x = {0,0,-100,-100};
      int[] rect2_y = {0,100,100,0};
      rect2 = new Polygon(rect2_x,rect2_y,4);
      g2d.fill(rect2);
      g2d.draw(rect2);
      
      
      g2d.setPaint(c4);
      int[] rect3_x = {0,100,200,100};
      int[] rect3_y = {100,0,100,200}; 
      rect3 = new Polygon(rect3_x,rect3_y,4);
      g2d.fill(rect3);
      g2d.draw(rect3);     
      
		
	 }
	 
	 public void mouseClicked(MouseEvent e)
	 {
	   System.out.println("Mouse Clicked");
      
      c1 = Color.red;
      c2 = Color.green;
      c3 = Color.blue;
      c4 = Color.yellow;
      
      repaint();
	   
          
	 }
	 
    //Empty methods to satisfy MouseListener interface
	 public void mouseEntered(MouseEvent e){}
	 
	 public void mouseExited(MouseEvent e){}
	  
	 public void mousePressed(MouseEvent e){}
	 	
	 public void mouseReleased(MouseEvent e){}
	 	
		



}// SimpleCanvas
