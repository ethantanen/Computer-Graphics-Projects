import java.util.ArrayList;

public class MengerSponge extends Fractal {
	
	Cube cube;
	Utilities u;
	int[][] ev;
	
	public MengerSponge(int depth){
		cube = new Cube();
		faces = new ArrayList<>();
		u = new Utilities();
		ev = new int[][]{{0,1},{0,2},{0,4},{3,1},{3,2},{3,7},{5,1},{5,4},{5,7},{6,2},{6,4},{6,7}};
		
		fract(cube.getVertex(),depth);
		
		
	}

	
	@Override
	public void fract(double[][] v, int depth) {
		
		//base case: save faces 
		if(depth == 0){
			cube.saveFaces(v, faces);
			return;
		}
		//arrays to hold center of cube and new centers for the recursive call
		double[] center = getCenter(v);
		double[][] cornerCenter = new double[8][3];
		double[][] edgeCenter = new double[12][3];
		
		//calculate centers of cubes that rest in the vertices
		for(int i=0; i<cornerCenter.length; i++){
			//cycle through each coordinate for the particular center 
			for(int j=0; j<cornerCenter[0].length; j++){
				cornerCenter[i][j] = center[j] + (2.0/3.0)*(v[i][j] - center[j]);//new center is calculated by adding a vector 2/3 the length of the vector from the vertex to the center to the center
			}
		}
		
		
		//calculate centers of cubes that fall between vertices
		for(int i=0; i<edgeCenter.length; i++){
			double[] mid = midpoint(v[ev[i][0]],v[ev[i][1]]);
			for(int j=0; j<edgeCenter[0].length; j++){
				edgeCenter[i][j] = center[j] + (2.0/3.0)*(mid[j] - center[j]);//new center is calculated by adding a vector 2/3 the length of the vector from the midpoint to the center to the center
				
			}
		}
		
		
		//calculate vertices based on centers 
		
		
		//corners
		for(int i=0; i<cornerCenter.length; i++){
			double[][] newVerticies = new double[v.length][3];
			for(int j=0; j<newVerticies.length; j++){
				for(int k=0; k<newVerticies[0].length; k++){
					newVerticies[j][k] = cornerCenter[i][k] + ((cornerCenter[j][k]-center[k])/2);
				}
			}
			fract(newVerticies,depth-1);
		}
		
		//edges
		for(int i=0; i<edgeCenter.length; i++){
			double[][] newVerticies = new double[v.length][3];
			for(int j=0; j<newVerticies.length; j++){
				for(int k=0; k<newVerticies[0].length; k++){
					newVerticies[j][k] = edgeCenter[i][k] + ((cornerCenter[j][k]-center[k])/2);
				}
			}
			fract(newVerticies,depth-1);
		}
	}
	
	
}
