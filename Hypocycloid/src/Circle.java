import java.awt.geom.Ellipse2D;

public class Circle {
	
	double x,y,r;
	
	Circle parent;
	Circle child;
	
	double megarad;
	
	public Circle(double x_, double y_, double r_){
		
		x = x_-r_;
		y = y_-r_;
		r = r_;
	}
	
	public Circle(Circle p, double r_){
		p.child = this;
		parent = p;
		r = r_;
		x = parent.x + parent.r + r/2.5; //weird line of code having to do with ellipse using a bounding rectangle 
		y = parent.y + parent.r + r/2.5;
		megarad = parent.r + r/2.5;
	}
	
	public Ellipse2D getCircle(){
		return new Ellipse2D.Double(x,y,r*2,r*2);
	}
	
	public void update(){
		
		if(parent == null)
			return;
		
		double theta = .1;
		
		x = parent.x + megarad*Math.cos(theta);
		y = parent.y + megarad*Math.sin(theta);
	}

}
