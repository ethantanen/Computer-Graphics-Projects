import java.awt.geom.AffineTransform;
import java.awt.geom.Path2D;
import java.awt.geom.PathIterator;
import java.awt.geom.Rectangle2D;

public class Transform {

	double i; //iterator
	double x_,y_;//stores x and y for function in which x and y are not provided i.e. rotate & scale 

	public Transform(){
	
	}
	
	//trasnalte shape by x and y
	public Path2D translate(Path2D shape, double x, double y){
		
		//creates affine transform (AT) that translates by x,y and applies to shape 
		AffineTransform t = new AffineTransform();
		t.translate(x, y);
		shape.transform(t);
		return shape;
	}
	
	//rotate shape by given radians 
	public Path2D rotate(Path2D shape, double radians){
		
		AffineTransform t = new AffineTransform();
		
		Rectangle2D r = shape.getBounds2D();//rect used to determine bounds of shape (high precission)
		
		//determine center of frame
		x_ = r.getWidth()/2 + r.getX(); 
		y_ = r.getHeight()/2 + r.getY();

		//rotate around x_ y_
		t.rotate(radians, x_, y_);
		
		shape.transform(t);
		return shape;
	}
	
	//scale shape by s in both directions 
	public Path2D scale(Path2D shape, double s){
		
		AffineTransform t = new AffineTransform();
		
		//find current coords of center of shape 
		Rectangle2D r = shape.getBounds2D();
		x_ = r.getWidth()/2 + r.getX();
		y_ = r.getHeight()/2 + r.getY();
		
		 t.translate(x_, y_); //move object back
		 t.scale(s,s); //scale equal amounts in both directions                   
		 t.translate(-x_, -y_); //move object to origin 
		 
		 shape.transform(t);
		
		return shape;
	}
	
}
