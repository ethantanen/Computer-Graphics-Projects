import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Path2D;
import java.util.ArrayList;
import java.util.Random;

public class ParticleSystem {
	
	double lifespan;
	double[] emitter_loc; 
	double rate;
	
	ArrayList<Particle> particles;
	
	Random rand;
	int num_spawned;
	
	int emitter_type; //0-pt 1-ln
	int emitter_axis;
	
	final int PT = 0;
	final int LN = 1;
	final int X_AXIS = 0;
	final int Y_AXIS = 1;
	
	Canvas canvas;
	Barrier barrier;
	
	
	public ParticleSystem(Canvas c_, Barrier b_){
		
		canvas = c_;
		barrier = b_;
		
		particles = new ArrayList<>();
		rand = new Random();
		
		emitter_loc = new double[]{canvas.getWidth()/2,canvas.getHeight()/2};
		emitter_type = LN;
		emitter_axis = Y_AXIS;
		
		num_spawned = 100;
		
		lifespan = 255;
		
	}
	
	//emits particles based on emit rate 
	public void emit(){
		for(int i=0; i<num_spawned; i++){
			particles.add(getParticle());
		}
	}
	
	//update each particle
	public void update(){
		for(int i=0; i<particles.size(); i++){
			particles.get(i).update();
		}
	}
	
	//draw each particle
	public void draw(Graphics2D g2d){
		for(int i=0; i<particles.size(); i++){
			Particle p = particles.get(i);
			double[] loc = p.getLoc();
			Ellipse2D e = new Ellipse2D.Double(loc[0],loc[1],5,5);
			g2d.setPaint(Color.WHITE);//new Color((int)p.col,(int)p.col/2,(int)60.0));
			g2d.fill(e);
			g2d.draw(e);
			
		}
		
		Path2D b = barrier.getBarrier();
		g2d.setPaint(Color.RED);
		g2d.fill(b);
		g2d.draw(b);
	}
	
	public ArrayList<Particle> getParticles(){
		return particles;
	}
	
	public void clean(){
		for(int i=0; i<particles.size(); i++){
			Particle p = particles.get(i);
			if(p.isDead()){
				particles.remove(i);
			}
		}
	}
	
	//returns a random particle based on the emitter preferences above 
	public Particle getParticle(){
		double[] location = new double[2];
		double[] velocity = new double[]{2*rand.nextDouble()-1,2*rand.nextDouble()-1};
		double[] acceleration = new double[]{2*rand.nextDouble()-1,2*rand.nextDouble()-1};
		int color = rand.nextInt(255);
		if(emitter_type == PT){
			location = new double[]{emitter_loc[0],emitter_loc[1]};
		}else if(emitter_type == LN){
			if(emitter_axis == X_AXIS){
				location = new double[]{rand.nextInt(canvas.getWidth()),emitter_loc[1]};
			}else if(emitter_axis == Y_AXIS){
				location = new double[]{emitter_loc[0],rand.nextInt(canvas.getHeight())};
			}	
		}else{
			location = new double[]{rand.nextInt(canvas.getWidth()),rand.nextInt(canvas.getHeight())};
		}
		return new Particle(location,velocity,acceleration,lifespan,color);	
	}
	
	public void checkCollisions(){
		for(int i=0; i<particles.size(); i++){
			Particle p = particles.get(i);
			//check collision
			if(barrier.checkCollsion(p)){
			
				//calculate 
				
				
				
				
				
			}
		}
	}
	
	

}
