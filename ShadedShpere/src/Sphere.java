
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;

public class Sphere {
	
	BufferedImage img;
	Graphics2D g2d;
	Ellipse2D circle;
	Utilities u;
	int radius;
	String r_bin,g_bin,b_bin,rgb_int; //variables to store binary representation of colors and integer representation of the chosen rgb val
	
	public Sphere(int r){
		
		radius =  r;
		img = new BufferedImage(500,500,BufferedImage.TYPE_INT_RGB);
		circle = new Ellipse2D.Double(0,0,radius*2,radius*2);
		u = new Utilities();
		initializeImage();
		
	}
	
	
	/*
	 * shades the sphere based on the location of a light source and the camera
	 */
	public void shadeCircle(double[] light, double[] camera){
		
		double z,cos_light;//variables to hold z coordinate and cos 
		int shade, shade_bin; //integer to hold the base ten and base two versions of the color 
		double[] normal,lamp,pt; //arrays to hold normal vector, vector from pt to eye a,d vector from light source to point 
		
		//return if invalid parameters 
		if(light.length != 3 || camera.length != 3){
			System.out.println("Wrong light/camera coordinates");
			return;
		}
		
		//cycle through every pixel in the image 
		for(int x=0; x<img.getWidth(); x++){
			for(int y=0; y<img.getHeight(); y++){
				
				//determine if the pixel is within the circle 
				if(circle.contains(new Point(x,y))){
					
					//calculate z
					z = calculateZ(x,y);
					
					//determine point on sphere 
					pt = new double[]{x,y,z};
					
					//calculate vector through origin and pt, sphere upper left corner positioned at 0,0 so the origin lies at radius,radius  
					normal = u.getVector(new double[]{radius, radius,0},pt);
					normal = u.normalize(normal);

					//determine vector from point to light source 
					lamp = u.getVector(pt,light);
					lamp = u.normalize(lamp);
					
					//find angle between light source and camera 
					cos_light = u.dotProd(lamp,normal);
					
					//determine shade based on angle between light source and point
					shade = getShade(cos_light);
					shade_bin = getRGBInt(shade,shade,shade);
			
					//set the image 
					img.setRGB(x, y, shade_bin);
				}
			}
		}
	}

	//gets the shade within the range 100-150
	private int getShade(double cos) {	
		return (int) (100 + (cos*150));
	}

	//calculate z coordinates based on x,y and radius
	private double calculateZ(double x, double y) {
		return Math.sqrt(Math.abs((radius*radius)-((radius-x)*(radius-x))-((radius-y)*(radius-y))));
	}
	
	/*
	 * returns the int representation of an rgb value 
	 */
	public int getRGBInt(int r, int g, int b){
		
		//check if r,g,b are within rgb range, if not return black
		if(r<0 || g<0 || b<0)
			return 0<<24;
		if(r>255 || g > 255 || b > 355)
			return 0<<24;
		
		//convert r,g,b to binary 
		r_bin = Integer.toBinaryString(r);
		g_bin = Integer.toBinaryString(g);
		b_bin = Integer.toBinaryString(b);	
		
		//toBinaryString does not guarantee 8 bits, so zeros are used to pad the number  
		while(r_bin.length() != 8){
			r_bin= "0" + r_bin;
			g_bin= "0" + g_bin;
			b_bin= "0" + b_bin;
		}
		
		//concatenate strings together 
		rgb_int = r_bin + g_bin + b_bin;
		
		//return rgb in integer format 
		return Integer.parseInt(rgb_int,2); //second argument is radix which is base 2
	}
	
	//setups the graphics2d object and draws the circle 
	private void initializeImage() {
		g2d = img.createGraphics();
		drawCircle();
	}

	//paints the circle on the image
	public void drawCircle(){
		g2d.setPaint(Color.BLACK);
		g2d.fill(circle);
		g2d.draw(circle);	
	}
	
	//returns the img
	public BufferedImage getImage(){
		return img;
	}
	
}