import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.color.ColorSpace;
import java.awt.geom.Path2D;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;

import javax.swing.JPanel;

public class Canvas_ extends JPanel {

	Graphics2D g2d;
	int x, y, z;
	Utilities u;
	Rotator r;
	//Fractal fractal;
	FractalCenters fractal;
	ArrayList<double[][]> faces;
	double[][] rotateMat;
	boolean isWire;
	ArrayList<VisibleFace> visibleFaces; //z value, face
	double[] camera;
	
	double minZ;
	double maxZ;

	public Canvas_() {

		
		minZ = Double.MAX_VALUE;
		maxZ = Double.MIN_VALUE;
		
		setPreferredSize(new Dimension(400, 600));
		setBackground(Color.DARK_GRAY);

		x = 0;
		y = 1;
		z = 2;

		isWire = true;

		u = new Utilities();
		r = new Rotator();
		//fractal = new Fractal();
		fractal = new FractalCenters();
		faces = fractal.getFaces();
		visibleFaces = new ArrayList<>();
		camera = new double[]{0,0,100};

	}

	public void paintComponent(Graphics b) {

		// prepare canvas
		super.paintComponent(b);
		g2d = (Graphics2D) b;
		g2d.translate(getWidth() / 2, getHeight() / 2);
		g2d.scale(1, -1);
		g2d.scale(1.4, 1.4);

		if (isWire == true) {
			for (int i = 0; i < faces.size(); i++) {
				Path2D path = getFaceShape(faces.get(i));
				g2d.draw(path);
			}
		}else {
			
			//check visibility
			for(int i=0; i<faces.size(); i++){
				visibility(faces.get(i));
			}
			
			//sort visibileFaces based on
			Collections.sort((List<VisibleFace>) visibleFaces);
							
			//draw most negative z's first 
			for(int i=0; i<visibleFaces.size(); i++){
				double[][] face = visibleFaces.get(i).getFace();
				Path2D path = getFaceShape(face);
			
				//puts z value of the first vertex into the range 0-1 to make more distant faces darker 
				double shade = (face[0][z] - minZ)/(maxZ-minZ);
				System.out.println("SHade: " + shade);
				
				//g2d.setPaint(new Color((int)((face[face.length-1][0]*51)-(15*shade)),(int)(200-(30*shade)),(int)(200-(30*shade))));
				//g2d.setPaint(new Color((int)((face[face.length-1][0]*51)-(15*shade)),(int)(100-(30*shade)),(int)(100-(30*shade))));
				g2d.setPaint(new Color((int)((face[face.length-1][0]/100)*255),(int)(50-(30*shade)),(int)(100-(30*shade))));

				g2d.fill(path);
				g2d.draw(path);
			}
			
			visibleFaces.clear();

		}
	}

	
	public void visibility(double[][] f){
		
		//calculate vectors from vertices 
		double[] v1 = new double[]{f[1][x] - f[0][x], f[1][y] - f[0][y],f[1][z] -f[0][z]};
		double[] v2 = new double[]{f[2][x] - f[0][x], f[2][y] -f[0][y],f[2][z] - f[0][z]};
		
		//normalize vectors to simplify dot product calculation  
		v1 = u.normalize(v1);
		v2 = u.normalize(v2);
		
		//determine normal vector by crossing the two vectors 
		double[] normal = u.crossProd(v2, v1);
		
		//determine vector from point on face to the camera
		double[] eye = u.getVector(f[0], camera);
		eye = u.normalize(eye);
		
		double cos = u.dotProd(normal,eye);
		
		//if cos < 0 than the face is visible 
		if(cos < 0){
			visibleFaces.add(new VisibleFace(f[0][z],f));
			
			if(f[0][z] < minZ){
				minZ = f[0][z];
			}
			
			if(f[0][z] > maxZ){
				maxZ = f[0][z];
			}
		}
	}
	
	
	private Path2D getFaceShape(double[][] face) {

		Path2D path = new Path2D.Double();
		double scale = 100;
		path.moveTo(face[0][x] * scale, face[0][y] * scale);

		for (int i = 0; i < face.length - 1; i++) {
			path.lineTo(face[i][x] * scale, face[i][y] * scale);
		}

		path.closePath();
		return path;
	}

	private double[] perspective(double[] pt) {
		double e = 100;
		double xp = pt[x] / (1 - (pt[z] / e));
		double yp = pt[y] / (1 - (pt[z] / e));
		return new double[] { xp, yp };
	}

	public void rotate(String text) {

		double theta = (2 * Math.PI) / 180;

		switch (text) {
		case "XCC":
			rotateMat = r.getXRotationMatrix(theta);
			break;
		case "XC":
			rotateMat = r.getXRotationMatrix(-1 * theta);
			break;
		case "YCC":
			rotateMat = r.getYRotationMatrix(theta);
			break;
		case "YC":
			rotateMat = r.getYRotationMatrix(-1 * theta);
			break;
		case "ZCC":
			rotateMat = r.getZRotationMatrix(theta);
			break;
		case "ZC":
			rotateMat = r.getZRotationMatrix(-1 * theta);
			break;
		// case "ARBCC": rotateMat = r.getArbRotationMatrix(new double[]{xArb,
		// yArb, zArb},theta); break;
		// case "ARBC": rotateMat = r.getArbRotationMatrix(new double[]{xArb,
		// yArb, zArb},-1*theta); break;

		}

		for (int i = 0; i < faces.size(); i++) {
			transformFace(faces.get(i)); // transform faces one at a time
		}

		repaint();

	}

	private void transformFace(double[][] face) {

		for (int i = 0; i < face.length - 1; i++) {
			face[i] = u.matMult(rotateMat, face[i]);
		}
	}

}
