import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class GUI extends JPanel implements ActionListener{
	
	//JElements for functionality 
	JButton inputButton,single; 
	JTextField lambda,numSquares,width; 
	JLabel l,n,w; 
	
	Canvas canvas; 
	
	/*
	 * Constructor 
	 * 
	 * @param the canvas object on which the spirals are drawn 
	 */
	public GUI(Canvas canvas_){
		
		setPreferredSize(new Dimension(600,600));
		setBackground(Color.gray);
		
		//instantiate all the j elements and add a listener to the button and add elements to panel 
		inputButton = new JButton("update image");
		inputButton.addActionListener(this);
		
		single = new JButton("Single Box");
		single.addActionListener(this);
		
		lambda = new JTextField(".9",20);
		numSquares = new JTextField("10",20);
		width = new JTextField("10",20);
	
		l = new JLabel("Lamda");
		n = new JLabel("Box Depth");
		w = new JLabel("Grid Width");
		
		canvas = canvas_;
	
		//super.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
		
		add(l);
		add(lambda);
		add(n);
		add(numSquares);
		add(w);
		add(width);	
		add(inputButton);
		add(single);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource() == inputButton){
			//when button is clicked update fields in canvas object and repaint the canvas 
			canvas.lam = Double.parseDouble(lambda.getText());
			canvas.num = Integer.parseInt(numSquares.getText());
			canvas.wid = Integer.parseInt(width.getText());
		}else if(e.getSource() == single){
			//when single is clicked update fields with appropriate values 
			canvas.lam = Double.parseDouble(lambda.getText());
			canvas.num = Integer.parseInt(numSquares.getText());
			canvas.wid = 1;
		}
		canvas.repaint();	
	}
}
