import java.awt.BorderLayout;
import java.awt.Container;

import javax.swing.JFrame;

public class Controller {
	public static void main(String[] args){
		
		JFrame frame = new JFrame();
		
		frame.setTitle("Free Hand Drawing");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Container c = frame.getContentPane();
		
		Canvas canvas = new Canvas();
		
		c.add(canvas,BorderLayout.CENTER);
				
		frame.pack();
		frame.setVisible(true);
		
	}
}
