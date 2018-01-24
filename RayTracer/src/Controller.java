import java.awt.BorderLayout;
import java.awt.Container;
import java.net.MalformedURLException;
import java.net.URL;

import javax.swing.JFrame;

public class Controller {

	public static void main(String[] args){
		
		
		
		//JFrame setup
		JFrame frame = new JFrame();
		frame.setTitle("Ray Tracer");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		Canvas canvas = new Canvas();//create canvas to draw on 
		RayTracer r = new RayTracer();//create ray tracer 
		r.trace();//do tracing 
		canvas.setImage(r.img);//set img from ray tracer 
		
		
		//JFrame setup 
		Container c = frame.getContentPane();
		c.setLayout(new BorderLayout());
		c.add(canvas, BorderLayout.CENTER);
		
		frame.pack();
		frame.setVisible(true);
	
		
	}
	
	//function to print vectors 
	public static void print(Vector v){
		System.out.println(v.x + " " + v.y + " " + v.z);
	}
}
