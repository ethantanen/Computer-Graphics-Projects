import java.util.ArrayList;

/*
 * information on icosahedron vertex and faces 
 * 
 * -look at Tetrahedron for general comments 
 */
public class Icosahedron implements Polyhedra {
	
	
	double[][] vertex;
	double lamda;
	
	public Icosahedron(){
		vertex = new double[][]{
			{-1.0,0.0,0.0},
			{-0.44721359549995804,-0.5257311121191337,0.723606797749979},
			{-0.44721359549995787,0.5257311121191336,0.7236067977499789},
			{-0.44721359549995804,0.8506508083520401,-0.276393202250021},
			{-0.4472135954999579,0.0,-0.8944271909999159},
			{-0.4472135954999579,-0.85065080835204,-0.2763932022500212},
			{0.4472135954999579,0.0,0.8944271909999159},
			{0.4472135954999579,0.85065080835204,0.2763932022500211},
			{0.44721359549995804,0.5257311121191337,-0.723606797749979},
			{0.44721359549995787,-0.5257311121191336,-0.7236067977499789},
			{0.44721359549995804,-0.8506508083520401,0.2763932022500209},
			{1.0,0.0,0.0}
		};	
		
		lamda = 2.61;
	}
	
	

	@Override
	public void saveFaces(double[][] v, ArrayList<double[][]> faces) {

		faces.add(makeFace(v[2],v[1],v[0],1));
		faces.add(makeFace(v[3],v[2],v[0],2));
		faces.add(makeFace(v[4],v[3],v[0],3));
		faces.add(makeFace(v[5],v[4],v[0],4));
		faces.add(makeFace(v[0],v[1],v[5],5));
		faces.add(makeFace(v[1],v[2],v[6],6));
		faces.add(makeFace(v[2],v[3],v[7],7));
		faces.add(makeFace(v[3],v[4],v[8],8));
		faces.add(makeFace(v[4],v[5],v[9],9));
		faces.add(makeFace(v[10],v[5],v[1],10));
		faces.add(makeFace(v[9],v[8],v[4],1));
		faces.add(makeFace(v[10],v[9],v[5],2));
		faces.add(makeFace(v[1],v[6],v[10],3));
		faces.add(makeFace(v[7],v[6],v[2],4));
		faces.add(makeFace(v[8],v[7],v[3],5));
		faces.add(makeFace(v[8],v[9],v[11],6));
		faces.add(makeFace(v[9],v[10],v[11],7));
		faces.add(makeFace(v[11],v[10],v[6],8));
		faces.add(makeFace(v[6],v[7],v[11],9));
		faces.add(makeFace(v[7],v[8],v[11],10));

	}

	@Override
	public double[][] getVertex() {
		return vertex;
	}
	
	public double[][] makeFace(double[] v1, double[] v2, double[] v3, double color){
		return new double[][]{v1,v2,v3,new double[]{color}};
	}
	

	@Override
	public double getLamda(){
		return lamda;
	}

}
