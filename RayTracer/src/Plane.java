import java.awt.Color;

public class Plane implements Object {
	
	Vector pt;
	Vector normal;
	
	
	public Plane(Vector p, Vector n){
		pt = p;
		normal = n.normalize();
		
	}
	

	@Override
	public Vector intersect(Ray r) {

		
		double ln = r.direction.dot(normal);
		
		if(ln == 0){
			return null;
		}
		
		Vector lp = new Vector(r.origin,pt);
		
		double t = lp.dot(normal) / ln;
		
		
		
		//p = po + tv
		return r.origin.add(r.direction.scale(t));
	}

	@Override
	public int getShade(Vector t, Light l, boolean shadow, Vector camera) {
		
		if(shadow){
			return 0;
		}
		
		return 100;
	}
	
	

}
