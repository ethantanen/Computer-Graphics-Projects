import java.awt.Color;
import java.awt.image.BufferedImage;

public class RayTracer {
	
	Light light;//a single light source in the scene 
	Vector camera;//the location of the camera 
	Object[] objects;//an array of all the spheres in the scene 
	BufferedImage img;//the img to write to
	
	public RayTracer(){
		
		//setup location of pt light source and camera 
		light = new Light(new Vector(100,-1000,2000),100);
		camera = new Vector(0,0,1000);

		//spheres on screen --> last param is a color, functionality not incorporated yet 
		Sphere s1 = new Sphere(100,new Vector(150,200,0),null);
		Sphere s2 = new Sphere(50,new Vector(400,200,0),null);
		Sphere s3 = new Sphere(75, new Vector(300,250,100),null);
		Plane p1 = new Plane(new Vector(0,1000,0), new Vector(0,-1,0));
		
		//array of all sphere objects 
		objects = new Object[]{s1,s2,s3,p1};
		
		//buffered img that uses rgb system 
		img = new BufferedImage(800,800,BufferedImage.TYPE_INT_RGB);
		
	}
	
	/*
	 * ray tracer function that shoots one primary ray w/ one shadow ray 
	*/
	public void trace(){
		
		//cycle through each pixel
		for(int x=0; x<img.getWidth(); x++){
			for(int y=0; y<img.getHeight(); y++){
				
				//create a ray from camera through pixel --> px location is {x,y,0}
				Ray ray = new Ray(camera, new Vector(x-camera.x,y-camera.y,0-camera.z));
				
				/*
				 * variables to store information about the closest intersection: 
				 * 	-closest intersection 
				 * 	-the sphere the intersection is found on
				 */
				Vector closest_hit = new Vector(Double.MAX_VALUE,Double.MAX_VALUE, Double.MAX_VALUE); //begin closest hit as far away as possible 
				Object closest_object = null; 
				//cycle through spheres checking for closest one, stores current intersection in variable named current_intersect 
				for(int i=0; i<objects.length; i++){
					
					Vector current_intersect = objects[i].intersect(ray);
					
					//if there's an intersection, check if it's the closest sphere 
					if(current_intersect != null){
						if(current_intersect.magnitude() < closest_hit.magnitude()){
							//update variables storing closest hit information 
							closest_hit = current_intersect;
							closest_object = objects[i];
						}
					}
				}
				
			
				
				//if there was an intersection shoot a single shadow ray towards the light 
				if(closest_object!= null){
				
					//shoot shadow ray 
					Ray shadow = new Ray(closest_hit,new Vector(closest_hit,light.origin));//ray begins at the hit and points towards the light 
					
					Boolean isShadow = false;//boolean to determine if the px should be shaded as a shadow or an illuminated pt 

					//check shadow ray against each sphere 
					for(int i=0; i<objects.length; i++){
					
						/*
						 * The shadow ray is not checked against the current sphere
						 * b/c the origin of the ray is on the surface of the sphere
						 * and will always produce a shadow based on the intersection
						 * calculation
						 */
						if(!objects[i].equals(closest_object)){
							//if there was an intersection update the isShadow boolean 
							if(objects[i].intersect(shadow) != null){
								isShadow = true;	
							}
						}
					}
					
					/*
					 * If the px is in the shadow use the getShade method with the true flag
					 * so the px is only illuminated w/ ambient light. If the px is not in the 
					 * shadow shade the px using a combination of ambient, diffuse and specular
					 * light.
					 */
					if(isShadow == true){	
						int shade = closest_object.getShade(closest_hit,light, false,camera);
						shade-= shade/2;
						Color c = new Color(shade,shade,shade);
						img.setRGB(x, y,c.getRGB());
					}else{
						int shade = closest_object.getShade(closest_hit,light,false,camera);
						Color c = new Color(shade,shade,shade);
						img.setRGB(x, y, c.getRGB());
					}
				}
			}
		}	
	}

	
	
}
