
public class Rotator {
	
	double c,s; //cos, sin
	Utilities u;
	
	public Rotator(){
		u = new Utilities();	
	}
	
	/*
	 * rotate around x
	 */
	public double[][] getXRotationMatrix(double theta){
		c = Math.cos(theta);
		s = Math.sin(theta);
		return new double[][]{{1,0,0},{0,c,-1*s},{0,s,c}};
	}

	/*
	 * rotate around y
	 */
	public double[][] getYRotationMatrix(double theta){
		c = Math.cos(theta);
		s = Math.sin(theta);
		return new double[][]{{c,0,s},{0,1,0},{-1*s,0,c}};
	}

	/*
	 * rotate around z
	 */
	public double[][] getZRotationMatrix(double theta){
		c = Math.cos(theta);
		s = Math.sin(theta);
		return new double[][]{{c,-1*s,0},{s,c,0},{0,0,1}};
	}
	
	/*
	 * returns a matrix that will rotate a point around the arbitrary 
	 * axis a by theta degrees
	 */
	public double[][] getArbRotationMatrix(double[] a,  double theta){
		
		 a = u.normalize(a);
		 c = Math.cos(theta);
		 s = Math.sin(theta);
		 
		double[][] mat = new double[3][3];
		
		/*
		 * exploits the relationship between the rows and columns to populate 
		 * the transformation matrix
		 * 	-uses matrix on pg 100 Mathematical Structures for Computer Science 
		 */
		for(int i=0; i<3; i++){
			mat[i][(i+0)%3] = c + (1-c)*(a[(i%3)] * a[(i%3)]);
			mat[i][(i+1)%3] = (1-c)*(a[(i%3)] * a[(i+1)%3]) - (s * a[(i+2)%3]);
			mat[i][(i+2)%3] = (1-c)*(a[(i%3)] * a[(i+2)%3]) + (s * a[(i+1)% 3]);	
		}
		
		return mat;
		
	}
}
