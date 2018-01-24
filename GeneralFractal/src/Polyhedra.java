import java.util.ArrayList;

/*
 * an interface for methods related to storing polyhedra 
 */
public interface Polyhedra {
	public void saveFaces(double[][] v, ArrayList<double[][]> faces);//creates and saves a face of a polyhedra based on the faces verticies  
	public double[][] getVertex();//returns the vertex array
	public double getLamda();//returns the polyhedras scaling factor 
}
