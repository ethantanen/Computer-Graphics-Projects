import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI extends JPanel implements ActionListener{
	
	//GUI needs some notion of the canvas to call repaint 
	Canvas canvas;
	
	JButton button;
	String[] 	functions;
	JTextField a,theta; 

	public GUI(Canvas c) {
		setPreferredSize(new Dimension(200,600));
		setBackground(Color.gray);
		
		//buttons = new JButton[4];
		functions = new String[]{"cos(aθ)","aθ","a(1+cos(θ))","(e^cos(θ))+2cos(4θ)...","r"}; //list of function names 
		canvas = c;
		
		//input field for the scalar in some functions and theta 
		a = new JTextField("input a",20);
		theta = new JTextField("input theta",20);
		
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		//add components to gui
		addButtons();
		add(a);
		add(theta);

	}

	//adds buttons to gui
	private void addButtons(){
		for(int i=0; i<functions.length; i++){
			
			button = new JButton(functions[i]);
			button.addActionListener(this);
			add(button);
		}
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		
		//get the text of the button that was triggered 
		String b = ((JButton)e.getSource()).getText();
		double num = Double.parseDouble(a.getText());
		double the = Double.parseDouble(theta.getText());
		
		//set graph passing theta and the scalar 
		canvas.setGraph(b,num,the);
		
	}
}
