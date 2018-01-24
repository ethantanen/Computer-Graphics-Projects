
public class Vector{
	
	//coordinates of vector 
	double x,y,z;
	
	public Vector(Vector v1, Vector v2){
		makeVector(v1,v2);
	}
	
	public Vector(double x_, double y_, double z_){
		x=x_;
		y=y_;
		z=z_;
	}
	
	//returns dot product 
	public double dot(Vector v){
		return (this.x*v.x) + (this.y*v.y) + (this.z*v.z);
	}
	
	//returns param vector projected onto this vector 
	public Vector project(Vector v){
		double dot = dot(v);
		double mag = magnitude();
		Vector unit = normalize();
		return unit.scale(dot/mag);//dot/mag is magnitude of projection, projection is in the same direction as this
	}
	
	//return this vector normalized 
	public Vector normalize(){
		double mag = magnitude();
		return new Vector(x/mag,y/mag,z/mag);	
	}

	//returns magnitude of this vector 
	public double magnitude(){
		return Math.sqrt((x*x)+(y*y)+(z*z));
	}
	
	//scales each entry in this vector by s
	public Vector scale(double s){
		return new Vector(x*s,y*s,z*s);
	}
	
	//adds vectors
	public Vector add(Vector v){
		return new Vector(this.x+v.x, this.y+v.y, this.z+v.z);
	}
	
	//subtracts v from this
	public Vector subtract(Vector v){
		return new Vector(this.x-v.x, this.y-v.y, this.z-v.z);
	}
	
	
	
	//makes a vector from two points 
	public void makeVector(Vector p1, Vector p2){
		x = p2.x - p1.x;
		y = p2.y - p1.y;
		z = p2.z - p1.z;
	}
}
