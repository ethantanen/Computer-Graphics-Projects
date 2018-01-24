import java.util.ArrayList;
/*
 * stores information on an octahedrons vertices and faces 
 * 
 * -look to Tetrahedron for general comments 
 */
public class Octahedron implements Polyhedra {
	
	double[][] vertex;
	double lamda;
	
	public Octahedron(){
		vertex = new double[][]{{1,0,0},{-1,0,0},{0,1,0},{0,-1,0},{0,0,1},{0,0,-1}};
		lamda = 2;
	}
	
	@Override
	public void saveFaces(double[][] v, ArrayList<double[][]> faces) {
		faces.add(makeFace(v[0],v[2],v[5],1));
		faces.add(makeFace(v[5],v[2],v[1],2));
		faces.add(makeFace(v[0],v[5],v[3],3));
		faces.add(makeFace(v[3],v[5],v[1],4));
		faces.add(makeFace(v[1],v[2],v[4],5));
		faces.add(makeFace(v[4],v[2],v[0],6));
		faces.add(makeFace(v[1],v[4],v[3],7));
		faces.add(makeFace(v[3],v[4],v[0],8));
		
	}
	
	@Override
	public double[][] getVertex(){
		return vertex;
	}
	
	public double[][] makeFace(double[] v1, double[] v2, double[] v3, double color){
		return new double[][]{v1,v2,v3,new double[]{color}};
	}

	@Override
	public double getLamda() {
		return lamda;
	}

	
	

}
