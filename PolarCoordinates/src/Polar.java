import java.awt.geom.Path2D;

import javax.swing.JFrame;

public class Polar {
	
	
	int total = 3000; //total number of coordiantes calculated 
	int i;
	
	double polar_r[],cartesian[][]; 
	
	
	public Polar(){	
		polar_r = new double[total];
		cartesian = new double[total][2];
	}
	
	public double[][] cosOfScalarTheta(double scalar,double theta){
		for(i=0; i<total; i++){
			polar_r[i] = Math.cos(scalar*(i*theta));	
		}
		convertPolarToCart(theta);
		return cartesian; 	
	}

	public double[][] scalarTheta(double num, double theta) {
		for(i=0; i<total; i++){
			polar_r[i] = num * (i*theta);
		}
		convertPolarToCart(theta);
		return cartesian;
	}
	
	public double[][] aOnePlusCos(double num, double theta){
		for(int i=0; i<total; i++){
			polar_r[i] = num * (1+Math.cos(i*theta));
		}
		convertPolarToCart(theta);
		return cartesian;
	}
	
	public double[][] SecondToLastOnList(double num,double theta){
		for(int i=0; i<total; i++){
			polar_r[i] = (Math.exp(Math.cos(i*theta))) - (2*Math.cos(num*(i*theta))) + Math.pow(5, Math.sin((i*theta)/12));
		}
		convertPolarToCart(theta);
		return cartesian;
	}
	
	public double[][] constant(double theta){
		for(int i=0; i<total; i++){
			polar_r[i] =1;
		}
		convertPolarToCart(theta);
		return cartesian;
	}

	/*
	 * performs calculation to convert from polar to cartesian coordiantes and stores it in the cartesian array 
	 */
	private void convertPolarToCart(double theta) {
		for(i=0; i<total; i++){
			cartesian[i][0] = polar_r[i] * Math.cos(i*theta);
			cartesian[i][1] = polar_r[i] * Math.sin(i*theta);	
		}
	}
	
	
	
}
