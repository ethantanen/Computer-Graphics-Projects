import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
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
	Cube cube;
	Rotator r;
	Utilities u;
	double e;
	double cubeScalar; 
	double[][] rotateMat;
	final int x,y,z;
	boolean isWire;
	TreeMap<Double,int[]> visibleFaces;
	
	public Canvas(){
		
		setPreferredSize(new Dimension(400,600));
		setBackground(Color.LIGHT_GRAY);
		
		//constants used to easily retrieve x,y,z coordinates 
		x=0;
		y=1;
		z=2;
		
		visibleFaces = new TreeMap<>(); //a tree map that will store faces to be drawn in order of ascending z values
		isWire = true; //a boolean to determine if the cube should be rendered as a wire frame or not
		cube = new Cube();//an instance of the cube
		e = 5;//the distance from the eye 
		cubeScalar = 100;//cube sides are all of length 2, scalar used to blow it up
		rotateMat = new double[3][3];//cube to store the current rotation matrix
		r = new Rotator();
		u = new Utilities();

	}
	
	/*
	 * 
	 */
	public void paintComponent(Graphics z){
		
		//prepare canvas
		super.paintComponent(z);
		g2d = (Graphics2D) z;
		g2d.translate(getWidth()/2, getHeight()/2);
		g2d.scale(1, -1);
		
		//check if user wanted wire frame or not 
		if(isWire == true){
			for(int i=0; i<cube.f.length; i++){
				g2d.draw(perspective(cube.f[i]));
			}
		}else{
			//check faces visibility 
			for(int i=0; i<cube.f.length; i++){
				visibility(cube.f[i]);	
			}
			
			//draw the faces starting with the face with the smallest z value 
			for (Entry<Double, int[]> entry : visibleFaces.entrySet()) {
				int color = entry.getValue()[4];
				Path2D shape = perspective(entry.getValue());
			    g2d.setPaint(new Color(color*30,50,50));
			    g2d.fill(shape);
			    g2d.draw(shape); 
			}
			
			//clear the visible face tree to be repopulated next time
			//it's probably quicker to check if a face is already in the tree rather than clearing the tree each time 
			visibleFaces.clear();	
		}
	}
	
	/*
	 * determines which faces are visible by determining the crossproduct of two vectors on 
	 * the face and seeing whether of not the z coordinates is positive or negative
	 * -negative: not visible
	 * -positive: visible 
	 */
	public boolean visibility(int[] f){
		
		double[][] vert = cube.getVertecies(f);
		
		double[] v1 = new double[]{vert[1][x] - vert[0][x], vert[1][y] - vert[0][y],vert[1][z] - vert[0][z]};
		double[] v2 = new double[]{vert[3][x] - vert[0][x], vert[3][y] - vert[0][y],vert[3][z] - vert[3][z]};
		
		double[] cp = u.crossProd(v2, v1);
		
		if(cp[z] > 0){
			visibleFaces.put(cp[z], f);
			return true;
		}else{
			return false;
		}
	}
	
	/*
	 * determines the perspective transformation for all the vertices in a face
	 * -only considers x and y because z will always be zero
	 */
	public Path2D perspective(int[] f){
		
		Path2D path = new Path2D.Double();//the shape to be painted 
		double [][] v = cube.getVertecies(f); //the four vertices that form the face 
		double[] vt;//the transformed vertex

		//move to first vertex
		vt = perspectiveCoords(v[0]);
		path.moveTo(vt[x], vt[y]);
		
		//draw lines between the first vertex and the next three 
		for(int i=1; i<v.length; i++){
			vt = perspectiveCoords(v[i]);
			path.lineTo(vt[x], vt[y]);
		}
		
		/*
		 * closes path using the closePath b/c it will always be a straight
		 *line to connect back to the first vertex
		 */
		path.closePath();
		
		return path;
	}
	
	/*
	 * performs a perspective transformation on a given pt
	 * - x' = x/(1-(z/e))
	 * - y' = y/(1-(z/e))
	 */
	public double[] perspectiveCoords(double[] v){
		
		double[] c = new double[2];
		
		c[0] = (v[x]/(1-(v[z]/e)))*cubeScalar; 
		c[1] = (v[y]/(1-(v[z]/e)))*cubeScalar;
		
		return c;	
	}

	public void rotateCube(String text, double xArb, double yArb, double zArb) {
		
		double theta = (2*Math.PI)/180;
				
		switch(text){
		  case "XCC": rotateMat = r.getXRotationMatrix(theta);    break;
		  case "XC":  rotateMat = r.getXRotationMatrix(-1*theta); break;
		  case "YCC": rotateMat = r.getYRotationMatrix(theta);    break;
		  case "YC":  rotateMat = r.getYRotationMatrix(-1*theta); break;
		  case "ZCC": rotateMat = r.getZRotationMatrix(theta);    break;
		  case "ZC":  rotateMat = r.getZRotationMatrix(-1*theta); break;
		  case "ARBCC": rotateMat = r.getArbRotationMatrix(new double[]{xArb, yArb, zArb},theta); break;
		  case "ARBC": rotateMat = r.getArbRotationMatrix(new double[]{xArb, yArb, zArb},-1*theta); break;
		
		}		
		
		//multiply each vertex by the transformation matrix
		for(int i=0; i<cube.v.length; i++){
			cube.v[i] = u.matMult(rotateMat, cube.v[i]);
		}
				
		repaint();
		
	}

	public void setWireFrame(boolean b) {
		isWire = b;	
	}
	
	
}
