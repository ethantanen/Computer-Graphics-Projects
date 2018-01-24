import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class Canvas extends JPanel {

	BufferedImage img;
	Graphics2D g2d;
	
	public Canvas(){
		
		setPreferredSize(new Dimension(600,400));
		setBackground(Color.LIGHT_GRAY);
		
		
	}
	
	
	public void paintComponent(Graphics z){
		
		g2d = (Graphics2D)z;
		g2d.drawImage(img, null, 0, 0);
		

	}
	
	public void setImage(BufferedImage i){
		img = i;
		repaint();
	}
	
	
	
	
	
}
