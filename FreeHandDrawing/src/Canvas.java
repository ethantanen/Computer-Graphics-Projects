import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.*;

import javax.swing.JPanel;


public class Canvas extends JPanel{
	
	
	Graphics2D g2d;
	
	Path path;
	Path2D drawing;
	Rectangle2D rect;

	public Canvas(){
		
		setPreferredSize(new Dimension(600,600));
		setBackground(Color.GRAY);
		
		rect = new Rectangle2D.Double(0,0,100,100);
		drawing = new Path2D.Double();
		path = new Path(this,drawing,rect);
		
		//add listeners that trigger methods in the path class
		addMouseListener(path);
		addMouseMotionListener(path);
	
		
	}

	public void paintComponent(Graphics z){
		
		super.paintComponent(z);
		g2d = (Graphics2D) z; 
		
		//redraw the path each time 
		g2d.draw(drawing); 
		
		//redraw the red box each time 
		g2d.setPaint(Color.RED);
		g2d.fill(rect);
		g2d.draw(rect);
	
	}

	//erases canvas by instantiating new path objects 
	public void erase() {
		System.out.print("Erase");
		drawing = new Path2D.Double();
		path.setPath(drawing);
		repaint();
	}
	


}