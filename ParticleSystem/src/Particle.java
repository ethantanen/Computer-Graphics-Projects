
public class Particle {
	
	double loc[];
	double vel[];
	double acc[];
	double col;
	double life;
		
	public Particle(double[] l_,double[] v_, double[] a_, double li_, int c_){
		loc = l_;
		vel = v_;
		acc = a_;
		life = li_;
		col = c_;
		
	}
	
	
	public void update(){
		loc = add(loc,vel);//update location 
		vel = add(vel,acc);//update velocity
		life--;//decrement life span 
	}
	
	public double[] getLoc(){
		return loc;
	}
	
	public double[] add(double[] vec1, double[] vec2){
		for(int i=0; i<vec1.length; i++){
			vec1[i] += vec2[i];
		}
		return vec1;
	}
	
	public boolean isDead(){
		if(life<=0){
			return true;
		}
		return false;
	}
	
	
	
	

	
	
}
