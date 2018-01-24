import java.awt.*;
import javax.swing.*;
public class Controller {
	
	public static void main (String args[]){
		
		JFrame frame = new JFrame();
		frame.setTitle("Spiraling Squares");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Canvas canvas = new Canvas();
		GUI gui = new GUI(canvas);
		
		Container c = frame.getContentPane();
		
		canvas.setLayout(new BorderLayout());
		
		c.add(canvas,BorderLayout.CENTER);
		c.add(gui,BorderLayout.WEST);
		frame.pack();
		frame.setVisible(true);
		
		
	}

}
