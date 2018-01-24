import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

public class Texture {
	
	BufferedImage img;
	URL url;
	
	public Texture(){
		try {
			url = new URL("file:///Users/ethantanen/Desktop/ComGraph/workspace/RayTracer/src/texture.jpg");
			img = ImageIO.read(url);
		} catch (IOException e) {
			System.out.println("Error W/ Textures");
			e.printStackTrace();
		}
	}
	
	
	//this function retrieves px of texture based on normal, only make sense for spherical textue mapping 
	public int getPixel(Vector n){
		//n is normal at that pixel 
		n = n.normalize();
		double u = img.getWidth() *  ((n.x/2 + .5));
		double v = img.getHeight() * ((n.y/2 + .5));		
		int i = img.getRGB((int)u, (int)v);
		return i;	 
	}

}
