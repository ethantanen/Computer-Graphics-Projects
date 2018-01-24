
public class Utilities {
	
	
	//projection of v1 onto v2
	public double magProjection(double[] v1, double[] v2){	
		return dotProduct(v1,v2)/magnitude(v2);
	}
	
	public double[] vecProjection(double[] v1, double[] v2){
		double[] proj = new double[v1.length];
		double[] v2_norm = normalize(v2);
		double magProj = dotProduct(v1,v2)/magnitude(v2);
		for(int i=0; i<proj.length; i++){
			proj[i] = v2_norm[i] * magProj;
		}
		return proj;
	}
	
	//intersection w/ sphere
	
	//dot product
	public double dotProduct(double[] v1, double[] v2){
		double sum = 0;
		for(int i=0; i<v1.length; i++){
			sum += v1[i] * v2[i];
		}
		return sum;
	}
	
	//normalize
	public double[] normalize(double[] v){
		double mag = magnitude(v);
		double[] norm = new double[v.length];
		for(int i=0; i<norm.length; i++){
			norm[i] = v[i]/mag;
		}
		return norm;
	}
	
	//get vector <- can be used to get normal from sphere 
	public double[] getVector(double[] from, double[] to){
		
		double[] vec = new double[to.length];

		for(int i=0; i<vec.length; i++){
			vec[i] = to[i] - from[i];
		}
		return vec;
	}
	
	//magnitude
	public double magnitude(double[] v){
		double sum = 0;
		for(int i=0; i<v.length; i++){
			sum += (v[i] * v[i]);
		}
		return Math.sqrt(sum);
	}
	
	//scalar multiply
	public double[] scalarMultiply(double[] v, double s){
		double[] vs = new double[v.length];
		for(int i=0; i<v.length; i++){
			vs[i]=v[i]*s;
		}
		return vs;
	}
	
	public double[] vecAdd(double[] v1, double[] v2){
		double[] a = new double[v1.length];
		for(int i=0; i<v1.length; i++){
			a[i] = v1[i] + v2[i];		
		}
		return a;
	}

}
