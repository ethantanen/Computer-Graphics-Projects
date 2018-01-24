import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Path2D;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.swing.JPanel;

public class Canvas extends JPanel {

	Graphics2D g2d;//canvas 
	final int x, y, z;//constants for easy indexing 
	Utilities u;//utility class 
	Rotator r;//linear transformation class
	Fractal fractal;//fractal object (interface)
	ArrayList<double[][]> faces;//current fractals faces
	double[][] rotateMat;//current rotation matrix
	boolean isWire;//determine if wire frame or not (not included in gui)
	ArrayList<VisibleFace> visibleFaces; //z value, face
	double[] camera;//location of camera (along z axis)
	double scale;//how much to scale polyhedra by
	double theta = (4 * Math.PI) / 180;//amount the shape will rotate per click 
	double minZ;//keep track of minimum and maximum z values for shading 
	double maxZ;

	
	/*
	 * constructor 
	 */
	public Canvas() {

		//keep track of min an max z values for faces 
		minZ = Double.MAX_VALUE;
		maxZ = Double.MIN_VALUE;
		
		//setup jpanel
		setPreferredSize(new Dimension(600, 600));
		setBackground(Color.DARK_GRAY);

		//constants for easy indexing 
		x = 0;
		y = 1;
		z = 2;
		
		//amount to scale shape by 
		scale = 250;

		//boolean to switch between wire frame and not 
		isWire = false;

		//utility classes 
		u = new Utilities();
		r = new Rotator();
		
		//blank rotation matrix
		rotateMat = new double[][]{{1,0,0},{0,1,0},{0,0,1}};
		
		//instantiate the jpanel w/ a sierpinski tetrahedron of depth 2
		fractal = new SierpinskiPolyhedra(new Tetrahedron(),2);
		faces = fractal.getFaces();
		
		//instantiate an arraylist of visible faces 
		visibleFaces = new ArrayList<>();
		
		//location of the camera
		camera = new double[]{0,0,100};

	}

	public void paintComponent(Graphics b) {

		// prepare canvas
		super.paintComponent(b);
		g2d = (Graphics2D) b;
		g2d.translate(getWidth() / 2, getHeight() / 2);
		g2d.scale(1, -1);

		//if the shape is wire frame than simply cycle through faces and draw shape 
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
			
			//sort visibileFaces based on avg z value 
			Collections.sort((List<VisibleFace>) visibleFaces);
							
			//draw most negative z's first 
			for(int i=0; i<visibleFaces.size(); i++){
				double[][] face = visibleFaces.get(i).getFace();//get current face
				Path2D path = getFaceShape(face);//draw current face
			
				//puts z value of the first vertex into the range 0-1 to make more distant faces darker 
				double shade = (visibleFaces.get(i).z - minZ)/(maxZ-minZ);
				
				//draw face and shade based on distance from viewer 
				g2d.setPaint(new Color((int)(face[face.length-1][0]*20),(int)(shade*100),(int)(shade*100)));
				g2d.fill(path);
				g2d.draw(path);
			}
			
			//clear array list of visible faces 
			visibleFaces.clear();

		}
	}

	/*
	 * calculate visibility based on the angle between a normal vector from the face and vector to the eye 
	 * 	-acute angles are visible faces
	 */
	public void visibility(double[][] f){
		
		//calculate vectors from vertices 
		double[] v1 = new double[]{f[1][x] - f[0][x], f[1][y] - f[0][y],f[1][z] -f[0][z]};
		double[] v2 = new double[]{f[2][x] - f[0][x], f[2][y] -f[0][y],f[2][z] - f[0][z]};
		
		//normalize vectors to simplify dot product calculation  
		v1 = u.normalize(v1);
		v2 = u.normalize(v2);
		
		//determine normal vector by crossing the two vectors 
		double[] normal = u.crossProd(v1, v2);
		
		//determine vector from point on face to the camera
		double[] eye = u.getVector(f[0], camera);
		eye = u.normalize(eye);
		
		double cos = u.dotProd(normal,eye);
		
		//if cos < 0 than the face is visible 
		if(cos < 0){
			
			//determine average z values for faces
			double z_sum=0;
			for(int i=0; i<f.length-1; i++){
				z_sum += f[i][z];
			}
			double z_avg = z_sum/f.length;
			
			//add the visible faces to an array list for drawing later down the line
			visibleFaces.add(new VisibleFace(z_avg,f));
			
			//update z_avg min and max variables for shading down the lien 
			if(z_avg < minZ){
				minZ = z_avg;
			}
			
			if(z_avg > maxZ){
				maxZ = z_avg;
			}
		}
	}
	
	
	/*
	 * returns a path2d shape of a particular face based on a set of coordinates 
	 */
	private Path2D getFaceShape(double[][] face) {

		Path2D path = new Path2D.Double();
		
		//move to first point 
		double[] pt = perspective(face[0]);
		path.moveTo(pt[x] * scale, pt[y] * scale);

		//cycle through remaining pts drawing lines between them
		for (int i = 1; i < face.length - 1; i++) {
			pt = perspective(face[i]);
			path.lineTo(pt[x] * scale, pt[y] * scale);
		}
		
		//close path 
		path.closePath();
		
		//return newly formed shape 
		return path;
	}

	//perspective transformation
	//-i think it looks better with the orthogonal projection
	private double[] perspective(double[] pt) {
		double xp = pt[x]; /// (1 - (pt[z] / camera[z]));
		double yp = pt[y]; /// (1 - (pt[z] / camera[z]));
		return new double[] { xp, yp };
	}

	/*
	 * method to manage manipulation of the on screen image 
	 */
	public void change(String text, int depth) {
		
		//rotation buttons update rotation matrix, fractals update polyhedra and face array 
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
			case "Tetrahedron":
				fractal = new SierpinskiPolyhedra(new Tetrahedron(),depth);
				break;
			case "Octahedron":
				fractal = new SierpinskiPolyhedra(new Octahedron(),depth);
				break;
			case "Cube":
				fractal = new SierpinskiPolyhedra(new Cube(),depth);
				break;
			case "Icosahedron":
				fractal = new SierpinskiPolyhedra(new Icosahedron(),depth);
				break;
			case "Menger Sponge":
				fractal = new MengerSponge(depth);
				break;
			case "Cubeoctahedron":
				fractal = new SierpinskiPolyhedra(new Cubeoctahedron(),depth);
		}
		
		//update faces with new face 
		faces = fractal.getFaces();

		//transform face based on current transformation matrix
		for (int i = 0; i < faces.size(); i++) {
			transformFace(faces.get(i)); 
		}
		
		//repaint canvas 
		repaint();

	}
	
	
	//transforms face using linear algebra 
	private void transformFace(double[][] face) {
		for (int i = 0; i < face.length - 1; i++) {//-1 bc last index is a color 
			face[i] = u.matMult(rotateMat, face[i]);//rotation matrix * coordinates of pt
		}
	}

}
