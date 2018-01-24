import java.awt.BorderLayout;
import java.awt.Container;
import javax.swing.JFrame;

public class Controller {
	
	
	public static void main(String[] args){
		
		
		//create jframe
		JFrame frame = new JFrame();
		frame.setTitle("Fractal");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//instantie canvas and gui
		Canvas canvas = new Canvas();
		GUI gui = new GUI(canvas);
		
		//add canvas and gui to jframe 
		Container c = frame.getContentPane();
		c.setLayout(new BorderLayout());
		c.add(canvas, BorderLayout.CENTER);
		c.add(gui,BorderLayout.WEST);
		
		frame.pack();
		frame.setVisible(true);
	
	}
}
