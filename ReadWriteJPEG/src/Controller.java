import java.awt.BorderLayout;
import java.awt.Container;
import java.net.MalformedURLException;
import java.net.URL;

import javax.swing.JFrame;

public class Controller {

	public static void main(String[] args){
		JFrame frame = new JFrame();
		frame.setTitle("Read Write JPEG");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		URL url = null;
		try {
            //replace with url to the cat image, using the File constructor did not work 
			url = new URL("file:///Users/ethantanen/Desktop/ComGraph/workspace/ReadWriteJPEG/src/cat.jpg");
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Image img = new Image(url);
		Canvas canvas = new Canvas();
		canvas.setImage(img.getBufferedImage());
		
		Container c = frame.getContentPane();
		c.setLayout(new BorderLayout());
		c.add(canvas, BorderLayout.CENTER);
		
		frame.pack();
		frame.setVisible(true);
	}
}
