import java.util.ArrayList;

/*
 * stores information on cubes vertex and faces 
 * 
 * -look to Tetrahedron for general comments 
 */
public class Cube implements Polyhedra{
	
	double[][] vertex;
	double lamda;
	
	public Cube(){
		vertex = new double[][]{{-1,1,-1},{1,1,-1},{-1,1,1},{1,1,1},{-1,-1,-1},{1,-1,-1},{-1,-1,1},{1,-1,1}};
		lamda = 2;
	}
	
	
	@Override
	public void saveFaces(double[][] v, ArrayList<double[][]> faces) {
		
		
		faces.add(new double[][]{v[0],v[1],v[3],v[2],new double[]{1.0}});
		faces.add(new double[][]{v[3],v[1],v[5],v[7],new double[]{2.0}});
		faces.add(new double[][]{v[1],v[0],v[4],v[5],new double[]{3.0}});
		faces.add(new double[][]{v[0],v[2],v[6],v[4],new double[]{4.0}});
		faces.add(new double[][]{v[4],v[6],v[7],v[5],new double[]{5.0}});
		faces.add(new double[][]{v[2],v[3],v[7],v[6],new double[]{6.0}});
		
	}
	
	@Override
	public double[][] getVertex(){
		return vertex;
	}

	@Override
	public double getLamda() {
		return lamda;
	}
	
	
	

}
