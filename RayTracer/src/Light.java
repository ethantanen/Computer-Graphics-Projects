//Point light 
public class Light{
	
	//light has an origin and an intensity 
	Vector origin;
	double[] intensity; 
	
	public Light(Vector o, double i){
		origin = o;
		intensity = new double[]{.1,.4,.5};
	}

}
