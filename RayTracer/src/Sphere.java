import java.awt.Color;

public class Sphere implements Object{
	
	//sphere has a radius and a location 
	double radius;
	Vector center;
	Vector color;
	Texture texture;
	double[] c_;
    
	public Sphere(double r, Vector c, Vector col){
		radius = r;
		center = c;
		c_ = new double[]{.5,.7,.8};
		texture = new Texture();
	}

	
	/*
	 * Returns closest intersection if there is one and null if there is not, info on pg65
	 * Mathematical Structures for Comptuer Science by Steven Janke 
	 */
	@Override
	public Vector intersect(Ray r) {
		
		Vector w = new Vector(r.origin,center);
		Vector v =r.direction;
		v = v.normalize();
		
		if(v.dot(w) < 0)
			return null;
		
		//calculate a using magnitude of sides c and b and pythagreom theorem 
		double c = w.magnitude();
		double b = v.project(w).magnitude();
		double a = Math.sqrt((c*c)-(b*b));

		//zero solutions 
		if(a > radius)
			return null;
				
		double d = Math.sqrt((radius*radius)-(a*a));//solve pythagreom theorem for smaller triangle yielding intersection
		Vector p = r.origin.add(v.scale(b-d));//calculate actual point using simple vector algerbra 
		
		return p;	//return the intersection point
	}
	
	//returns a shade based on an intersection point, a spheres color and a light source  
	@Override
	public int getShade(Vector t, Light l, boolean shadow, Vector camera) {
		double ambient=0,diffuse=0,specular=0;
		
		//calculate diffuse light
		Vector n = new Vector(center,t).normalize();//normal to intersection
		Vector L = new Vector(t,l.origin).normalize();//vector to light 
		Vector R = n.project(L).scale(2).subtract(L).normalize();//reflected vector 
		Vector c = new Vector(t,camera).normalize();

		
		//get texture color 
		Vector normal = new Vector(center,t);
		int color =  texture.getPixel(normal);
		Color col = new Color(color);
				
		
		//calculate light 
		ambient  =  c_[0]  * l.intensity[0];
		diffuse  =  c_[1]  * l.intensity[1] * n.dot(L);
		specular =  c_[2]  * l.intensity[2] * R.dot(c);
			

		
		if(shadow){
			return (int)(col.getBlue() + (col.getBlue()* (ambient)));
		
		}else{
			return (int)(col.getBlue() + (col.getBlue()* (ambient+diffuse+specular)));
		}
		
		
	
	}

	
}
