import javax.swing.*;
import java.awt.*;
import java.awt.geom.Path2D;

public class Canvas extends JPanel {
	
	Graphics2D g2d;
	Polar polar; //used to retrieve coordinates 
	Grapher grapher;//used to create shape 
	double[][] coordinates;//used to store coordinates 
	
	public Canvas(){
		
		setPreferredSize(new Dimension(600,600));
		setBackground(Color.LIGHT_GRAY);
		
		polar = new Polar();
		grapher = new Grapher();
	
	}
	
	public void paintComponent(Graphics z){
		
		super.paintComponent(z);
		g2d = (Graphics2D) z;
		
		g2d.translate(getWidth()/2,getHeight()/2);
		
		if(coordinates == null){
			System.out.println("coordinates empty");
			return;
		}
		
		Path2D path = grapher.makeGraph(coordinates);
		g2d.draw(path);
		
	}

	public void setGraph(String b, double num, double theta) {
		theta = Math.toRadians(theta);
		switch(b){
			case "cos(aθ)": coordinates = polar.cosOfScalarTheta(num,theta);break;
			case "aθ": coordinates = polar.scalarTheta(num,theta);break;
			case "a(1+cos(θ))": coordinates = polar.aOnePlusCos(num,theta); break;
			case "(e^cos(θ))+2cos(4θ)...": coordinates = polar.SecondToLastOnList(num,theta); break;
			case "r": coordinates = polar.constant(theta);break;
		
		}
		repaint();
		
	}

}
