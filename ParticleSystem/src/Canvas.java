import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Path2D;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;

import javax.swing.JPanel;

public class Canvas extends JPanel {
	
	Graphics2D g2d;
	Rotator r;
	Utilities u;
	
	ParticleSystem ps;
	
	double current_time, elapsed_time, rate;
	
	
	public Canvas(){
		
		setPreferredSize(new Dimension(800,600));
		setBackground(Color.DARK_GRAY);
	
		r = new Rotator();
		u = new Utilities();
		
		ps = new ParticleSystem(this,new Barrier(400,300));
		
		current_time = System.currentTimeMillis();
		rate = .1;

	}
	
	/*
	 * 
	 */
	public void paintComponent(Graphics z){
		
		//prepare canvas
		super.paintComponent(z);
		g2d = (Graphics2D) z;
		//g2d.scale(1, -1);
		
		
		//check if enough time has passed to release new particles into the system
		if(checkTime()){
			ps.emit();//add new particles to canvas 
			ps.update();//update position and velocity of current particles 
			ps.checkCollisions();
			ps.clean();//check for expired particles to remove them from the array list
		}
		
		
		ps.draw(g2d);//draw particles onto g2d
		
		
		repaint();//repeat!
		
	}

	/*
	 * determines if the elapsed time is greater than the rate
	 * 	-returns true if it has and false otherwise
	 * 	-the rate is defined above as a number of seconds 
	 */
	private boolean checkTime() {
		elapsed_time = (System.currentTimeMillis() - current_time)/1000;
		if(elapsed_time > rate){
			current_time = System.currentTimeMillis();
			return true;
		}
		return false;
	}
}
