import java.util.ArrayList;

/*
 * stores information on a tetraherons faces and vertex 
 */
public class Tetrahedron implements Polyhedra{
	
	double[][] vertex;//array to store vertices coordinates 
	double lamda;//scale factor for resizing smaller polyhedra 
	
	public Tetrahedron(){
		vertex = new double[][]{{1,1,1},{1,-1,-1},{-1,1,-1},{-1,-1,1}};//vertex of the largest tetrahedron in the fractal
		/*
		 * each n-flake has a slightly different Hausdorff dimension which is a function of the 
		 * number of new polyhedra per level of recursion and the golden ration
		 */
		lamda = 2;//each tetrahedra will be scaled by a factor of 1/2
	}

	
	/*
	 * this is a method that illustrates the protocol for saving the faces of a tetrahedron. The vertices are listed
	 * in a consistent order to make the crossproduct for checking visibility easily standardized 
	*/
	@Override
	public void saveFaces(double[][] v, ArrayList<double[][]> faces) {
		faces.add(new double[][]{v[2],v[1],v[0],new double[]{1.0}});
		faces.add(new double[][]{v[3],v[2],v[0],new double[]{2.0}});
		faces.add(new double[][]{v[1],v[3],v[0],new double[]{3.0}});
		faces.add(new double[][]{v[2],v[3],v[1],new double[]{4.0}});	
	}
	
	//returns a list of the first polyhedras vertices 
	@Override
	public double[][] getVertex(){
		return vertex;
	}
	
	//returns the polyhedras lambda value 
	@Override
	public double getLamda(){
		return lamda;
	}

}
