import javax.swing.*;
import java.awt.*;
import java.awt.geom.*;
import java.util.ArrayList;

public class Canvas extends JPanel {
	
	Graphics2D g2d; //canvas to hold facial features 
	
	Pieces pieces; //object to retrieve facial feature shapes  
	ArrayList<Shape> face; //array to hold facial features
	ArrayList<Color> color; //array to hold facial features colors 
	
	Transform transformer; //object used to transform features 
	Path2D feature; //the current feature in question
	
	int i; //an iterator 
	
	public Canvas(){
		setPreferredSize(new Dimension(600,600));
		setBackground(Color.white);
		
		pieces = new Pieces();
		transformer = new Transform();	
		face = new ArrayList<>();
		color = new ArrayList<>();
	}
	
	public void paintComponent(Graphics z) {

		super.paintComponent(z);
		g2d = (Graphics2D) z;
		
		//cycle through facial features and reprint 
		for(i=0; i<face.size(); i++){
			g2d.setPaint(color.get(i));
			g2d.fill(face.get(i));
			g2d.draw(face.get(i));
		}	
	}
	
	/*
	 * determine which feature to add to the canvas and color it with the 
	 * provided color 
	 */
	public void setFeature(String f,Color c){
		switch(f){
			case "Face": feature = pieces.getFace(); break;
			case "Eye": feature = pieces.getEye(); break;
			case "Nose": feature = pieces.getNose(); break;
			case "Ear": feature = pieces.getEar(); break;
			case "Brow": feature = pieces.getBrow(); break;
			case "Mouth": feature = pieces.getMouth();
		}
		face.add(feature);
		color.add(c);
		repaint();	
	}
	
	/*
	 * determine how to move the facial feature and move it by a constant amount
	 */
	public void moveFeature(String m){
		switch(m){
			case "Left": transformer.translate(feature, -10, 0); break;
			case "Right":transformer.translate(feature, 10,0); break;
			case "Up": transformer.translate(feature, 0, -10); break;
			case "Down":transformer.translate(feature,0,10); break;
			case "Rotate Left": transformer.rotate(feature, -.5); break;
			case "Rotate Right": transformer.rotate(feature, .5); break;
			case "Scale Up": transformer.scale(feature, 1.2); break;
			case "Scale Down": transformer.scale(feature, .8); break;
		}
		repaint();
	}
	
	
	/*
	 * returns true if there is a feature and false if not 
	 */
	public boolean isFeature(){
		if(feature != null)
			return true;
		return false; 
	}

	/*
	 * deletes the contents of the face and color array lists 
	 */
	public void erase() {
		face.clear();
		color.clear();
		repaint();
	}
}
