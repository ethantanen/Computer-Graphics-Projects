import java.awt.geom.*;
/*
 * The class has a series of functions that return a path2d shape that resembles
 * a facial feature.
 * 
 */
public class Pieces {
	
	public Pieces(){
	}

	public Path2D getFace() {
		return  new Path2D.Double(new Ellipse2D.Double(0,0,300,450));
	}

	public Path2D getEye() {
		return new Path2D.Double(new Ellipse2D.Double(0, 0, 75, 50));
	}

	public Path2D getNose() {
		Path2D nose = new Path2D.Double();
		nose.moveTo(0, 0);
		nose.lineTo(0, 150);
		nose.lineTo(100,150);
		nose.closePath();
		return nose;
	}

	public Path2D getEar() {
		Path2D ear = new Path2D.Double();
		ear = new Path2D.Double();
		ear.moveTo(0, 0);
		ear.curveTo(0,0,40,20,50,40);
		ear.curveTo(30, 40, 35, 45, 0, 100);
		ear.curveTo(0,100,15,45,0,10);
		return ear;
	}

	public Path2D getBrow() {
		Path2D brow = new Path2D.Double();
		brow = new Path2D.Double();
		brow.moveTo(0, 20);
		brow.lineTo(100, 20);
		brow.curveTo(100, 20, 50, 0, 0, 20);
		return brow;
	}	
	
	public Path2D getMouth(){
		Path2D mouth = new Path2D.Double();
		mouth.moveTo(0,0);
		mouth.curveTo(0, 0, 75, 50, 150, 0);
		mouth.closePath();
		return mouth;
	}
}	

	
	
	
	
	
	