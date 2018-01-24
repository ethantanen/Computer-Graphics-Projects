import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI extends JPanel implements ActionListener{
	
	Canvas canvas;
	JTextField x,y,z;
	JButton[] buttons;
	String[] button_names; 
		
	public GUI(Canvas c) {
		
		setPreferredSize(new Dimension(200,600));
		setBackground(Color.DARK_GRAY);

		canvas = c;
		
		//instantiate and add buttons to screen 
		button_names = new String[]{"Move Left", "Move Right", "Move Up", "Move Down"};
		buttons = new JButton[button_names.length];
		
		//instantiate and add buttons to gui
		for(int i=0; i<button_names.length; i++){
			buttons[i] = new JButton(button_names[i]);
			buttons[i].addActionListener(this);
			add(buttons[i]);
		}	
	}

	
	@Override
	public void actionPerformed(ActionEvent e) {
		String button = ((JButton)e.getSource()).getText();
		canvas.moveLight(button);	
	}
}
