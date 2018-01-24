import java.awt.BorderLayout;
import java.awt.Container;
import javax.swing.JFrame;

public class Controller {
	
	public static void main(String[] args){
		
	
		//create jframe 
		JFrame f = new JFrame();
		f.setTitle("Cube Renderor");
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Canvas canvas = new Canvas();
		GUI gui = new GUI(canvas);
		
		Container c = f.getContentPane();
		c.setLayout(new BorderLayout());
		
		//add canvas and gui to the jframe
		c.add(canvas, BorderLayout.CENTER);
		c.add(gui, BorderLayout.WEST);
		
		f.pack();
		f.setVisible(true);
		
	}
	
}
