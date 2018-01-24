import java.util.ArrayList;

public class FractalCenters {
	
	ArrayList<double[][]> faces;
	
	public FractalCenters(){
		
		double[][] vertex = new double[][]{{1,1,1},{1,-1,-1},{-1,1,-1},{-1,-1,1}};
		faces = new ArrayList<>();
			
		fract(vertex,1);
		
	}
	
	
	
	public void fract(double[][] v, int depth){
		

		
		
		if(depth == 0){
			faces.add(new double[][]{v[0],v[1],v[2],new double[]{1.0}});
			faces.add(new double[][]{v[0],v[2],v[3],new double[]{2.0}});
			faces.add(new double[][]{v[0],v[3],v[1],new double[]{3.0}});
			faces.add(new double[][]{v[1],v[3],v[2],new double[]{4.0}});
			return;
		}
		
		//calculate center 
		double[] center = getCenter(v);
		
		//determine new centers as a function of the number of vertex
		double[][] newCenters = new double[v.length][3]; //an array of coordiantes 

		//calculate new centers 
		for(int i=0; i<newCenters.length; i++){
			newCenters[i] = midpoint(v[i],center);
		}
	
		//a new tetrahedron for each center
		for(int i=0; i<newCenters.length; i++){
			
			double[][] newVerticies = new double[v.length][3];
			
			//calculate new verticies
			for(int j=0; j<newVerticies.length; j++){
				newVerticies[j] = new double[]{newCenters[i][0]+(newCenters[j][0]),newCenters[i][1]+(newCenters[j][1]),newCenters[i][2]+(newCenters[j][2])};
			}

			//do the same processes with the new set of vertices 
			fract(newVerticies,depth-1);
		
		}
	
	}

	/*
	 * determines center of a polygon by averaging the coordinates 
	 */
	private double[] getCenter(double[][] v) {
		
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
	
	private double[] midpoint(double[]pt1, double[]pt2){
		return new double[]{pt1[0] + ((pt2[0]-pt1[0])/2), pt1[1] + ((pt2[1] - pt1[1])/2), pt1[2] + ((pt2[2]-pt1[2])/2)};
		
	}
	
	
	public ArrayList<double[][]> getFaces(){
		return faces;
	}

}
