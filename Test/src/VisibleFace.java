/*
 * a class for storing information of visible faces 
 */
public class VisibleFace implements Comparable<VisibleFace> {
	
	double[][] face;//the vertices of the face 
	double z;//the average z values of the vertices 
	
	/*
	 *Constructor
	 * 
	 * @param z_ the avg z component
	 * @param f_ the verticies of the face 
	 */
	public VisibleFace(double z_, double[][] f_){
		face = f_;
		z = z_;
	}
	
	//returns verticie array 
	public double[][] getFace(){
		return face;
	}
	
	//returns avg z component 
	public double getZ(){
		return z;
	}

	/*
	 *overrides compareTo to compare z components of the VisibleFace objects 
	 */
	@Override
	public int compareTo(VisibleFace other) {
		return Double.compare(this.z, other.z);
	}

}
