import java.awt.geom.Path2D;
import java.awt.geom.PathIterator;

public class Grapher {

	double scalar = 30;
	
	public Grapher(){
		
	}
	
	
	//creates path2d object from set of coordinates 
	public Path2D makeGraph(double coordinates[][]){
		
		if(coordinates == null){
			System.out.println("No coordiantes");
			return null;
		}
		
		//create graph
		Path2D graph = new Path2D.Double();
		
		//move to first index in coordinates array
		graph.moveTo(coordinates[0][0] * scalar, coordinates[0][1] * scalar);
		
		//cycle through the remainder of the coordinates 
		for(int i=1; i<coordinates.length;i++){	
			graph.lineTo(coordinates[i][0]*scalar,coordinates[i][1]*scalar);	
		}
		
		return graph;
	}
}
