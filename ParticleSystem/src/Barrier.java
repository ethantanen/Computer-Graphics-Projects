import java.awt.geom.Path2D;

public class Barrier {

	Path2D barrier; //barrier will be rendered as a path2d object from the provided coordinates
	Utilities u;
	
	double[][] vertices;
	int[][] edges;
	
	public Barrier(double x, double y){
		vertices = new double[][]{{x,y},{x+100,y+50},{x+100,y-50}};//coordinates of triangle whose pointy end faces left 
		edges = new int[][]{{0,1},{1,2},{2,0}};
		u = new Utilities();
		createBarrier();	
	}
	
	//cylce through coordinates to create a path2d shape 
	public void createBarrier(){
		barrier = new Path2D.Double();
		barrier.moveTo(vertices[0][0], vertices[0][1]);
		for(int i=1; i<vertices.length; i++){
			barrier.lineTo(vertices[i][0], vertices[i][1]);
		}
		barrier.closePath();
	}
	
	//returns the barrier shape 
	public Path2D getBarrier(){
		return barrier;
	}
	
	/*
	 * returns true if a particle is within the bounds of the barrier
	 * -probably not the best collision detection technique
	 */
	public boolean checkCollsion(Particle p){
		double[] particle_loc = p.getLoc();
		if(barrier.contains(particle_loc[0], particle_loc[1]))
			return true;
		return false;
	}
	
	/*
	 * finds closest edge to point 
	 * 
	 * wack...
	 */
	public double[] getNewVelocityCollision(Particle p){
		
		double closest_edge_mag = Double.MAX_VALUE;//index for current closest edge 
		double closest_edge_index = -1;
		
		//determine closes edge 
		for(int i=0; i<edges.length; i++){
			
			double[] p_vec = u.getVector(vertices[edges[i][0]], p.getLoc()); //vector from point to edge
			double[] e_vec = u.getVector(vertices[edges[i][0]], vertices[edges[i][1]]);
				
			double[] proj = u.projection(p_vec, e_vec);
			double proj_mag = u.magnitude(proj);
			
			if(proj_mag < closest_edge_mag){
				closest_edge_mag = proj_mag;
				closest_edge_index = i;
			}
		}
		
		double[] normal = new double[]{e_vec[1],e_vec[0]}; //normal to edge 
		
		//normalize vectors 
		normal = u.normalize(normal);
		
	}
	
}
