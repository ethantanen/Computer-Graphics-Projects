import java.awt.*;
import javax.swing.*;

public class Controller {
	
	public static void main(String args[]){
		
		JFrame frame = new JFrame();
	    frame.setTitle("Shaded Triangles");
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    
	    Canvas canvas = new Canvas();
	    
	    Container c = frame.getContentPane();
	    
	    c.add(canvas);
		
	    frame.pack();
	    frame.setVisible(true);
	}

}
