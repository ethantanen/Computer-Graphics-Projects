import javax.swing.*;
import java.awt.*;
import java.awt.geom.*;

public class Canvas extends JPanel{

	Graphics2D g2d; 
	
	//coordinates of the corners of the largest triangle 
	double[] x_big;
	double[] y_big;
	
	//path for the big and small triangle 
	Path2D big,small; 
	
	//rectangle to encompasses the triangles for the purpose of shading   
	Rectangle bigBorder;
	Rectangle smallBorder;
	
	
	public Canvas(){
		
		//coordinates w/ repeated first point 
		x_big = new double[]{-100,100,0,-100};
		y_big = new double[]{0,0,250,0};
		
		
		setPreferredSize(new Dimension(300, 400));
		setBackground(Color.white);
		
		//makes the big triangle 
		big = makeTriangle(x_big,y_big);
		//determines the location of the small triangel based on the larger of the two 
		small = findNextTriangle(x_big,y_big);
		
		//bounding boxes 
		bigBorder = big.getBounds();
		smallBorder = small.getBounds();
		

	}
	
	/*
	 * finds next triangle based on coordinates of the larger triangle 
	 * 
	 * @param x,y coordinates for the larger triangle 
	 * 
	 */
	private Path2D findNextTriangle(double[] x, double[] y) {
		
		Path2D t = new Path2D.Double();
		double[] x_new = new double[3];
		double[] y_new = new double[3];
		
		for(int i=0; i<3; i++){
			
			x_new[i] = .5*(x[i] + x[i+1]);
			y_new[i] = .5*(y[i] + y[i+1]);
			
		}
		
		return makeTriangle(x_new,y_new);
	}

	
	public void paintComponent(Graphics x) {

		super.paintComponent(x);

		g2d = (Graphics2D) x;
		
		//colors the bakground w/ a gradient 
		Rectangle2D rect = new Rectangle2D.Double(0,0,getWidth(),getHeight());
		g2d.setPaint(new GradientPaint(0,0,Color.black,0,getHeight(),Color.blue));
		g2d.fill(rect);
		g2d.draw(rect);
		
		//translates the origin to center of the horizontal and slightly below center of the height 
		g2d.translate(getWidth()/2, getHeight()/1.4);
		//flips the coordinates to coincide w/ standard axises 
		g2d.scale(1, -1);
		
		//adds big triangle to canvas 
		g2d.setPaint(new GradientPaint(bigBorder.x,bigBorder.y,Color.black,bigBorder.x,bigBorder.height,Color.blue));
		g2d.fill(big);
		g2d.draw(big);
		
		//adds small triangle to canvas 
		g2d.setPaint(new GradientPaint(smallBorder.x,smallBorder.y,Color.blue,smallBorder.x,smallBorder.height,Color.black));
		g2d.fill(small);
		g2d.draw(small);
		
		
	}
	
	/*
	 * makes a path2d triangle shape from a list of x and y coordinates
	 * 
	 * @param x,y x and y coordinates
	 * @param t what is to become the triangle 
	 * 
	 */
	private Path2D makeTriangle(double[] x, double[] y){
		
		Path2D t = new Path2D.Double();
		
		t.moveTo(x[0], y[0]);
		
		for(int i=1; i<3; i++){
			t.lineTo(x[i],y[i]);
		}
		
		
		System.out.println(t.toString());
		
		return t;
		
	}
	
	
	
	
	
	
	
}
