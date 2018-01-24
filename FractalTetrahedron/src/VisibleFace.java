
public class VisibleFace implements Comparable<VisibleFace> {
	
	double[][] face;
	double z;
	
	public VisibleFace(double z_, double[][] f_){
		face = f_;
		z = z_;
	}
	
	public double[][] getFace(){
		return face;
	}
	
	public double getZ(){
		return z;
	}

	@Override
	public int compareTo(VisibleFace other) {
		return Double.compare(this.z, other.z);
	}

}
