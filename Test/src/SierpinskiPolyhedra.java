import java.util.ArrayList;

public class SierpinskiPolyhedra extends Fractal {
	
	/*
	 * constructor 
	 * @Param p type of shape to make fractal
	 * @Param depth recursion depth
	 */
	public SierpinskiPolyhedra(Polyhedra p, int depth){
		
		//update global variables 
		poly = p;
		faces = new ArrayList<>();
		
		//create the fractal w/ the polyhedras verticies and depth   
		fract(poly.getVertex(),depth);
		
	}
	
	/*
	 *A recursive algorithm for producing seirpinskis tetrahedra by recursively calculating the 
	 *centers of the successively smaller polyhedra 
	 *
	 *@Param v the vertices of the current polyhedra
	 *@Param  depth number of recursions left 
	 */
	@Override
	public void fract(double[][] v, int depth){
		
		//base case: when depth is 0 save the current face using the polyhedra's saveFace method
		if(depth == 0){
			poly.saveFaces(v, faces);
			return;
		}
		
		//calculate center of the current polyhedra 
		double[] center = getCenter(v);
		
		//create an array to store the new centers to recurse with, there is a new polyhedra for each verticie in the polyhedra  
		double[][] newCenters = new double[v.length][3]; 
		
		//calculate new centers by calculate the midpoint between the vertices and the center of the current polyhedra 
		for(int i=0; i<newCenters.length; i++){
			newCenters[i] = midpoint(center,v[i]);
		}
	
		//this bit of code manages the recursive calls by calculating the vertices of the next polyhedra 
		for(int i=0; i<newCenters.length; i++){
			
			//an array to store new vertices, same number of vertices as the current polyhedra 
			double[][] newVerticies = new double[v.length][3];

			//calculate new vertices
			for(int j=0; j<newVerticies.length; j++){
				
				//the vertices of the new tetrahadra are pts reached by adding a vector from the old center to the new center to the new center
				//new polyhedra have side lengths half that of the original vertice
				for(int k=0; k<newVerticies[0].length; k++){
					newVerticies[j][k] = newCenters[i][k]+(newCenters[j][k]-center[k]); 
				}
				
			}

			//do the same processes with the new set of vertices and decrement the depth 
			fract(newVerticies,depth-1);
		}
	}
}
