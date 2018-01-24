import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI extends JPanel implements ActionListener{
	
	//GUI needs some notion of the canvas to call repaint 
	Canvas canvas;
	
	//JElements for functionality 
	JLabel message1,message2,message3;
	JButton face,eye,nose,ear,brow,mouth;
	JButton left,right,up,down,rotate_l,rotate_r,scale_up,scale_down;
	JButton erase;
	
	public GUI(Canvas c){
		
		setPreferredSize(new Dimension(200,600));
		setBackground(Color.gray);
		
		canvas = c;
		
		message1 = new JLabel("Choose a facial feature");
		message2 = new JLabel("Move your object");
		message3 = new JLabel("Erase Canvas");
		
		//will create loop to complete this next step in next iteration using an array of button names 
		face = new JButton("Face");
		face.addActionListener(this);
		eye = new JButton("Eye");
		eye.addActionListener(this);
		nose = new JButton("Nose");
		nose.addActionListener(this);
		ear = new JButton("Ear");
		ear.addActionListener(this);
		brow = new JButton("Brow");
		brow.addActionListener(this);
		mouth = new JButton("Mouth");
		mouth.addActionListener(this);
		
		left = new JButton("Left");
		left.addActionListener(this);
		right = new JButton("Right");
		right.addActionListener(this);
		up = new JButton("Up");
		up.addActionListener(this);
		down = new JButton("Down");
		down.addActionListener(this);
		rotate_l = new JButton("Rotate Left");
		rotate_l.addActionListener(this);
		rotate_r = new JButton("Rotate Right");
		rotate_r.addActionListener(this);
		scale_up = new JButton("Scale Up");
		scale_up.addActionListener(this);
		scale_down = new JButton("Scale Down");
		scale_down.addActionListener(this);
		
		erase = new JButton("Erase");
		erase.addActionListener(this);
		super.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
		
		add(message1);
		add(face);
		add(eye);
		add(nose);
		add(ear);
		add(brow);
		add(mouth);
		add(new JLabel("			"));
		add(message2);
		add(left);
		add(right);
		add(up);
		add(down);
		add(rotate_l);
		add(rotate_r);
		add(scale_up);
		add(scale_down);
		add(new JLabel("    "));
		add(message3);
		add(erase);
		
	}
	

	@Override
	public void actionPerformed(ActionEvent e) {
		
		//get the text of the button that was triggered 
		String b = ((JButton)e.getSource()).getText();
		
		//if the button is a move feature call the function to move the feature on the canvas object 
		if(b.equals("Left") || b.equals("Right") || b.equals("Up") || b.equals("Down") || b.equals("Rotate Left") || b.equals("Rotate Right") || b.equals("Scale Up") || b.equals("Scale Down")){	
			
			//display error message if a user attempts to move a feature before selecting a feature 
			if(canvas.isFeature()){
				canvas.moveFeature(b);	
			}else{
				JOptionPane.showMessageDialog(null, "Choose Feature Before Moving Feature");
			}	
		}else if(b.equals("Erase")){
			
				canvas.erase();
		}else{
			
			//if a feature is chosen, request a color 
			Color c = JColorChooser.showDialog(null, "Choose Color", null);
			
			//default to gray if a color is not chosen 
			if(c != null){
				canvas.setFeature(b,c);
			}else{
				canvas.setFeature(b,Color.blue);
			}
		}
	}
}
