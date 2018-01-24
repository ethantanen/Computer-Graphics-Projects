import java.util.ArrayList;

public class Fractal {
	
	double[][] vertex;
	ArrayList<double[][]> faces;
	
	final int x,y,z;
	
	public Fractal(){
		
		//standardized index system for points 
		x=0;
		y=1;
		z=2;
		
		vertex = new double[][]{{1,1,1},{1,-1,-1},{-1,1,-1},{-1,-1,1}};
		faces = new ArrayList<>();
		
		fract(vertex[0],vertex[1],vertex[2],vertex[3],2);
	}
	
	public void fract(double[] v0, double[] v1, double[] v2, double[] v3, int depth ){
		
		if(depth == 0){
		
			faces.add(new double[][]{v0,v1,v2,new double[]{1.0}});
			faces.add(new double[][]{v0,v2,v3,new double[]{2.0}});
			faces.add(new double[][]{v0,v3,v1,new double[]{3.0}});
			faces.add(new double[][]{v1,v3,v2,new double[]{4.0}});
			
		}else{
		
			double[] m01 = midPoint(v0,v1);
			double[] m02 = midPoint(v0,v2);
			double[] m03 = midPoint(v0,v3);
			double[] m12 = midPoint(v1,v2);
			double[] m13 = midPoint(v1,v3);
			double[] m23 = midPoint(v2,v3);
			
			//bottom 1
			fract(v0,m01,m02,m03,depth-1);
			
			//bottom 2
			fract(m02,m12,v2,m23,depth-1);
			
			//bottom 3
			fract(m03,m13,m23,v3,depth-1);
			
			//top
			fract(m01,v1,m12,m13,depth-1);
		}
	}


	/*
	 * calculates the mid point between to vertices 
	 */
	private double[] midPoint(double[] v1, double[] v2) {
		return new double[]{(v2[x]+v1[x])/2,(v2[y]+v1[y])/2,(v2[z]+v1[z])/2};
	}

	
	public ArrayList<double[][]> getFaces(){
		return faces;
	}

}
