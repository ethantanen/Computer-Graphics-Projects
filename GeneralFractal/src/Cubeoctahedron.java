import java.util.ArrayList;

/*
 * information on cubeoctahedron vertex and faces 
 * 
 * -look to Tetrahedron for general comments 
 */
public class Cubeoctahedron implements Polyhedra {
	
	
	double[][] vertex;
	double lamda;
	
	public Cubeoctahedron(){
		
		vertex = new double[][]{
			{1,0,1},
			{1,1,0},
			{0,1,1},
			{1,0,-1},
			{0,1,-1},
			{1,-1,0},
			{0,-1,-1},
			{0,-1,1},
			{-1,0,1},
			{-1,1,0},
			{-1,0,-1},
			{-1,-1,0}
		};
		
		lamda = 2.74;
	}
	
	

	@Override
	public void saveFaces(double[][] v, ArrayList<double[][]> faces) {

		faces.add(makeFace(v[3],v[5],v[0], v[1],1));
		faces.add(makeFace(v[11],v[10],v[9], v[8],1));
		faces.add(makeFace(v[7],v[8],v[2], v[0],1));
		faces.add(makeFace(v[10],v[6],v[3], v[4],1));
		faces.add(makeFace(v[9],v[4],v[1], v[2],1));
		faces.add(makeFace(v[5],v[6],v[11], v[7],1));
		faces.add(makeFace(v[2],v[1],v[0],2));
		faces.add(makeFace(v[1],v[4],v[3],2));
		faces.add(makeFace(v[6],v[5],v[3],2));//this face is listed incorrectly in the txt file 
		faces.add(makeFace(v[5],v[7],v[0],2));
		faces.add(makeFace(v[9],v[2],v[8],2));
		faces.add(makeFace(v[4],v[9],v[10],2));
		faces.add(makeFace(v[11],v[6],v[10],2));
		faces.add(makeFace(v[7],v[11],v[8], 2));
	}

	@Override
	public double[][] getVertex() {
		return vertex;
	}
	
	//makes square face 
	public double[][] makeFace(double[] v1, double[] v2, double[] v3,double[] v4, double color){
		return new double[][]{v1,v2,v3,v4,new double[]{color}};
	}
	
	//makes triangle face 
	public double[][] makeFace(double[] v1, double[] v2, double[] v3,double color){
		return new double[][]{v1,v2,v3,new double[]{color}};
	}



	@Override
	public double getLamda() {
		return lamda;
	}

}
