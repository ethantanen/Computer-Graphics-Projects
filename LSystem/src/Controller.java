import java.awt.BorderLayout;
import java.awt.Container;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

import javax.swing.JFrame;

public class Controller {

	public static void main(String[] args){
		
	
		
		JFrame frame = new JFrame();
		frame.setTitle("L-System");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		HashMap<String,String> h = new HashMap<>();
		//h.put("0", "1[0]0");
		//h.put("1", "11" );
		h.put("X"," F[-X][X]F[-X]+FX");
		h.put("F", "FF");
		
				
	
		
		LSystem s = new LSystem();
		s.generate("X", h, 6);
		String d = s.getFinalWord();
		
		
		Canvas canvas = new Canvas(d);
		
		Container c = frame.getContentPane();
		c.setLayout(new BorderLayout());
		c.add(canvas, BorderLayout.CENTER);
		
		frame.pack();
		frame.setVisible(true);
	
		
	}
	
	public static void print(String s){
		System.out.println(s);
	}
	
	
}
