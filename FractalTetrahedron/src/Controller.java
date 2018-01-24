import java.awt.BorderLayout;
import java.awt.Container;
import java.lang.reflect.Array;
import java.util.ArrayList;

import javax.swing.JFrame;

public class Controller {
	
	public static void main(String[] args){
		/*
		
		FractalCenters f = new FractalCenters();
		ArrayList<double[][]> a = f.getFaces();
		
		System.out.println(a.size());
		
		for(int i=0; i<a.size(); i++){
			for(int j=0; j<a.get(i).length-1;j++){
				System.out.println(a.get(i)[j][0] + " " + a.get(i)[j][1] + " " + a.get(i)[j][2]);
			}
			System.out.println();
		}*/
		
		
		
		JFrame frame = new JFrame();
		frame.setTitle("Fractal");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Canvas_ canvas = new Canvas_();
		GUI gui = new GUI(canvas);
		
		Container c = frame.getContentPane();
		c.setLayout(new BorderLayout());
		c.add(canvas, BorderLayout.CENTER);
		c.add(gui,BorderLayout.WEST);
		
		frame.pack();
		frame.setVisible(true);
		
	}
}

