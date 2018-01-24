
public interface Object {
	public Vector intersect(Ray r); //returns a shade based on an intersection point, a spheres color and a light source 
	int getShade(Vector t, Light l, boolean shadow, Vector camera);
		
		
		

}
