import java.awt.Color;

public class Cube {
	
	double[][] v; 
	int[][] f;
	
	public Cube(){
		
		v = new double[8][3]; //8 vertex w/ 3 coordinates
		f = new int[6][4]; //6 faces w/ 4 vertex
		
		//cube coordinates 
		v[0] = new double[]{-1,1,-1};
		v[1] = new double[]{1,1,-1};
		v[2] = new double[]{-1,1,1};
		v[3] = new double[]{1,1,1};
		v[4] = new double[]{-1,-1,-1};
		v[5] = new double[]{1,-1,-1};
		v[6] = new double[]{-1,-1,1};
		v[7] = new double[]{1,-1,1};
	
		
		//the vertex are assigned clockwise from the perspective of outside the cube looking in, last value is color
		f[0] = new int[]{0,1,3,2,1}; 
		f[1] = new int[]{3,1,5,7,2}; 
		f[2] = new int[]{1,0,4,5,3}; 
		f[3] = new int[]{0,2,6,4,4}; 
		f[4] = new int[]{4,6,7,5,5};
		f[5] = new int[]{2,3,7,6,6}; 
			
	}
	
	public double[][] getVertecies(int[] face){
		double[][] v_ = new double[4][2];
		for(int i=0; i<face.length-1; i++){
			v_[i] = v[face[i]];
		}
		return v_;
	}
}
