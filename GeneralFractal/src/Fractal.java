import java.util.ArrayList;

/*
 * abstract class for fractal generating classes
 */
abstract class Fractal {
	
	ArrayList<double[][]> faces;//stores faces 
	Polyhedra poly;//type of polyhedra to fract 
	
	public abstract void fract(double[][] v, int depth);//recursive portion of fractal generation
	
	//returns faces 
	ArrayList<double[][]> getFaces(){
		return faces;
	}
	
	/*
	 * determines center of a polygon by averaging the coordinates 
	 */
	 public double[] getCenter(double[][] v) {
		
		double[] center = new double[3];
		double xc=0,yc=0,zc=0;
		
		for(int i=0; i<v.length; i++){
			xc += v[i][0];
			yc += v[i][1];
			zc += v[i][2];
		}
		
		center[0] = xc/v.length;
		center[1] = yc/v.length;
		center[2] = zc/v.length;
		
		return center;
	}
	
	//calculates a point on a vector by scaling a vector from pt1 to pt2 by lamda 
	 public double[] getPoint(double[]pt1, double[]pt2,double lamda){
		return new double[]{pt1[0] + ((pt2[0]-pt1[0])/lamda), pt1[1] + ((pt2[1] - pt1[1])/lamda), pt1[2] + ((pt2[2]-pt1[2])/lamda)};	
	}
	
	
}
