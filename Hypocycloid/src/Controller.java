import java.awt.BorderLayout;
import java.awt.Container;

import javax.swing.JFrame;

public class Controller {
	
	public static void main(String[] args){
		
		JFrame frame = new JFrame();
		frame.setTitle("Hypocycloid");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Canvas canvas = new Canvas();
		
		Container c = frame.getContentPane();
		c.setLayout(new BorderLayout());
		c.add(canvas, BorderLayout.CENTER);
		
		frame.pack();
		frame.setVisible(true);
		
	}
}
