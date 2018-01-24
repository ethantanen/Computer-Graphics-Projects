import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI extends JPanel implements ActionListener{
	
	Canvas canvas;
	JTextField x,y,z;//text fields for arbitrary rotations 
	JButton[] rotations;//jbutton array for rotation button
	String[] rotation_names; //names of rotation buttons
	JRadioButton[] fractals;//jbutton array for types of fractals
	String[] fractal_names;//names of fractal buttons
	JLabel depth_label,rotation_label;//labels for clarity
	JTextField depth;//textfield for entering recursion depth 
	JPanel p1,p2;//p1 hold components for rotations, p2 holds components for choosing fractal
	ButtonGroup fractal_buttons;//button groups wont allow two radio buttons to be picked at once 
		
	public GUI(Canvas c) {
		
		setPreferredSize(new Dimension(200,600));
		setBackground(Color.DARK_GRAY);

		canvas = c;
		
		//instantiate array of button names and array of buttons 
		rotation_names = new String[]{"XCC", "XC", "YCC", "YC", "ZCC","ZC"};
		rotations = new JButton[rotation_names.length];
		
		//Instantiate array of radio button names and array of radio buttons 
		fractal_names = new String[]{"Tetrahedron","Cube","Octahedron","Icosahedron","Cubeoctahedron","Menger Sponge"};
		fractals = new  JRadioButton[fractal_names.length];
		
		//Instantiate label and text field for entering recursion depth
		depth_label = new JLabel("Enter Depth");
		depth = new JTextField("2",5);
		
		//Instantiate label for rotation buttons
		rotation_label = new JLabel("Rotate Fractal");
		
		//instantiate button group to prevent multiple boxes to be selected at once 
		fractal_buttons = new ButtonGroup();
		
		//instantiate panel for rotation buttons and one for fractal button s
		p1 = new JPanel();
		p2 = new JPanel();
		
		//set up both jpanels 
		p1.setPreferredSize(new Dimension(200,200));
		p1.setBackground(Color.DARK_GRAY);
		
		p2.setPreferredSize(new Dimension(200,140));
		p2.setBackground(Color.DARK_GRAY);
		p2.setLayout(new BoxLayout(p2,BoxLayout.Y_AXIS));
		
		/*
		x = new JTextField("X arb",5);
		y = new JTextField("Y arb",5);
		z = new JTextField("Z arb",5);
		*/
		
		//add rotation buttons to jpanel, adds action listener to each button
		for(int i=0; i<rotation_names.length; i++){
			rotations[i] = new JButton(rotation_names[i]);
			rotations[i].addActionListener(this);
			p1.add(rotations[i]);
		}
		
		//add radiobuttons to jpanel, add action listner to buttons, add buttons to buttongorup
		for(int i=0; i<fractal_names.length; i++){
			fractals[i] = new JRadioButton(fractal_names[i]);
			fractals[i].addActionListener(this);
			fractal_buttons.add(fractals[i]);
			p2.add(fractals[i]);
		}
		
		//add componenets to jpanel 
		add(rotation_label);
		
		add(p1);
		add(p2);
		
		add(depth_label);
		add(depth);
		
		
		/*
		add(x);
		add(y);
		add(z);
		*/
	}

	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		String button = e.getActionCommand();//gets the selected buttons value 
		int d = Integer.parseInt(depth.getText());//Retrieve value from depth field
		
		canvas.change(button,d);//change image either by rotating or switching fractals 
		
		
		
	}
}
