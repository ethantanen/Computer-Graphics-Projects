

import java.awt.*;


import javax.swing.JPanel;

public class Canvas extends JPanel {

	Graphics2D g2d;
	Sphere sphere;
	double[] camera,light;
	
	
	public Canvas(){
		
		setPreferredSize(new Dimension(600,600));
		setBackground(Color.black);
		
		sphere = new Sphere(200);
		
		camera =  new double[]{200,0,600};
		light = new double[]{200,100,1000};
		
		sphere.shadeCircle(camera,light);
			
	}
	
	public void paintComponent(Graphics z){
		super.paintComponent(z);
		g2d = (Graphics2D) z;
		g2d.drawImage(sphere.getImage(),null,100,100);
		
		
	}

	public void moveLight(String button) {
		switch(button){
			case "Move Left": light[0] -= 100; sphere.shadeCircle(light, camera); break;
			case "Move Right": light[0] += 100; sphere.shadeCircle(light, camera); break;
			case "Move Up": light[1] -= 100; sphere.shadeCircle(light, camera); break;
			case "Move Down": light[1] += 100; sphere.shadeCircle(light, camera); break;
		}
		
		repaint();
		
	}
	
}
