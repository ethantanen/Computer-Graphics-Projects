import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Path2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

import javax.swing.event.MouseInputAdapter;

public class Path extends MouseInputAdapter {
	
	Canvas canvas; //path needs some notion of the canvas 
	Path2D path;//path of drawing 
	Rectangle2D rect; //notion of red box 
	Point p;//mouse position 
	
	boolean isNewLine;//boolean to determine if a new line is needed 
	
	public Path(Canvas c, Path2D p, Rectangle2D r){
		canvas = c;
		path = p;
		rect = r;
		isNewLine = true;
	}
	

	@Override 
	public void mouseDragged(MouseEvent e){
		
		p = e.getPoint(); //get mouse position 
		
		if(!canvas.contains(p)){//if the mouse is out of the frame start a new line 
			isNewLine = true;
			return;
		}
		
		System.out.println("Drag");
		
		//when a new line is drawn move the path2d to the mouse positon 
		if(isNewLine){
			path.moveTo(p.getX(), p.getY());
			isNewLine = false; 
		}
		
		//make a path to the mouse position 
		path.lineTo(p.getX(), p.getY());
		
		//repaint the canvas 
		canvas.repaint();
	}
	
	@Override
	public void mouseReleased(MouseEvent e){
		//switch the boolean to true in order to inform the program that a new line is to be formed 
		System.out.println("Release");
		isNewLine = true;
	}
	
	@Override
	public void mouseClicked(MouseEvent e){
		//if the mouse is clicked check if its in the red box, if it is erase the canvas 
		System.out.println("Clicked");
		p = e.getPoint();
		if(rect.contains(p)){
			canvas.erase();
		}
	}
	
	//if the canvas is erased, a new path object is created and the instance must be updated 
	public void setPath(Path2D p){
		path = p;
	}
}
