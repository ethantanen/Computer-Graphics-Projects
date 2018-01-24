import javax.swing.*;
import java.awt.*;
import java.awt.geom.*;

public class Canvas extends JPanel {

	Graphics2D g2d;

	//variables for lambda, number of squares per box, number of boxes that should span the screen
	double lam;
	int num;
	int wid;

	//arrays to hold the unit square matrix w/ repeated first entry 
	double[] unit_x;
	double[] unit_y;

	//arrays to hold matrices scaled to the proper width 
	double[] scaled_x;
	double[] scaled_y;
	
	//box width which is a function of squares per row and screen size 
	double box_width;
	
	//object to hold clockwise and counter clockwise 
	Path2D path_l,path_r;

	//Constructor 
	public Canvas() {

		setPreferredSize(new Dimension(600,600));
		setBackground(Color.white);

		//a unit box begins at origin, has width and height 1
		unit_x = new double[] { 0, 1, 1, 0, 0 };
		unit_y = new double[] { 0, 0, 1, 1, 0 };
		
		//instantiate the scaled coordinate arrays 
		scaled_x = new double[5];
		scaled_y = new double[5];
	}

	public void paintComponent(Graphics z) {

		super.paintComponent(z);
		g2d = (Graphics2D) z;
		
		//if a wid is not chosen yet, wid = 1 in order to make num_box_row the width of the screen
		if(wid ==0)
			wid = 1;
		
		//getWidth/wid # boxes can fit across the screen 
		box_width = getWidth()/wid; 

		//scale unit square
		for(int i=0; i<5; i++){
			scaled_x[i] = unit_x[i] * box_width ;
			scaled_y[i] = unit_y[i] * box_width;
			System.out.println("x,y: " + scaled_x[i] + " " + scaled_y[i]);
		}
		
		//get spiral shape based on lambda (lam) and box count (num)
		path_r = getSpiral(1-lam,scaled_x,scaled_y);
		path_l = getSpiral(lam,scaled_x,scaled_y);
		
		//cycle across screen printing boxes
		for(int h=0; h<wid; h++){
			for(int i=0; i<wid; i++){
				//even rows begin printing w/ opposite shape than odd rows to produce checkerboard pattern 
				if(h%2 == 0){
					//even columns receive complementary lamda to odd columns 
					if(i%2 == 0){
						g2d.draw(path_l);
					}else{
						g2d.draw(path_r);
					}
				}else{
					if(i%2 == 0){
						g2d.draw(path_r);
					}else{
						g2d.draw(path_l);
					}	
				}
				//translates origin to one box width over 
				g2d.translate(box_width,0);	
			}
			//move origin back to left hand side of screen and down one box height 
			g2d.translate(-1*box_width*wid, box_width);		
		}
	}

	/*
	 * produces a spiral  based on lamda, and coordinates for the largest box 
	 * 
	 * @param lamda portion of line segment traversed to produce next square
	 * @param x1,y1 the coordinates to the largest square of the image 
	 */
	private Path2D getSpiral(double lamda, double[] x1, double[] y1) {

		//modifiable arrays to hold coordinates of progressively smalle squares 
		double[] x2 = new double[5];
		double[] y2 = new double[5];
		
		Path2D spiral = new Path2D.Double(); 

		//moves the param arrays intp a local var 
		for(int i=0; i<x1.length; i++){
			x2[i] = x1[i];
			y2[i] = y1[i];
		}
		
		//produces num many squares within the original square 
		for (int k = 0; k < num; k++) {

			//always move to the first array point rather than drawline to produce illusion of squares within squares 
			spiral.moveTo(x2[0], y2[0]);

			//draw lines to the other points in the array 
			for (int i = 1; i < 5; i++) {
				spiral.lineTo(x2[i], y2[i]);
			}

			//determine the coordinates of the next smallest square as a function of lamda 
			for (int i = 0; i < 4; i++) {
				x2[i] = x2[i] + (lamda * (x2[i + 1] - x2[i]));
				y2[i] = y2[i] + (lamda * (y2[i + 1] - y2[i]));
			}

			//updates the fifth element in the array which is a repeat of the first 
			x2[4] = x2[0];
			y2[4] = y2[0];

		}
		//returns the path 
		return spiral; 
	}
}
