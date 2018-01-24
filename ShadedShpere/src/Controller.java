
import java.awt.BorderLayout;
import java.awt.Container;
import javax.swing.JFrame;

public class Controller {
	
	public static void main(String[] args){
		
		JFrame frame = new JFrame();
		frame.setTitle("Shaded Sphere");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Canvas canvas = new Canvas();
		GUI gui = new GUI(canvas);
		
		Container c = frame.getContentPane();
		c.setLayout(new BorderLayout());
		c.add(canvas, BorderLayout.EAST);
		c.add(gui, BorderLayout.WEST);
		
		frame.pack();
		frame.setVisible(true);
		
	}
}
