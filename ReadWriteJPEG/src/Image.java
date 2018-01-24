import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

public class Image{
	
	BufferedImage img;
	Graphics2D g2d;
	
	public Image(URL u){
		
		try {
			img = ImageIO.read(u);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		drawOnImage(new Rectangle2D.Double(100,100,100,100));
		exportImage("moose.jpg");
		
	}
	
	public void drawOnImage(Shape shape){
		g2d = img.createGraphics();
		g2d.translate(img.getWidth()/2, img.getHeight()/2);
		g2d.setPaint(Color.ORANGE);
		g2d.fill(shape);
		g2d.draw(shape);
	}
	
	public void exportImage(String file_name){
		try {
			ImageIO.write(img, "jpg", new File(file_name));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public BufferedImage getBufferedImage(){
		return img;
	}
	
	
}