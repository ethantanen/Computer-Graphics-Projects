import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI extends JPanel implements ActionListener{
	
	Canvas canvas;
	
		
	public GUI(Canvas c) {
		
		setPreferredSize(new Dimension(200,600));
		setBackground(Color.DARK_GRAY);
	
	}

	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		
	}
}
