import javax.swing.*;
import java.awt.*;
import java.awt.geom.*;

public class ColorPanel extends JPanel {

	Graphics2D g2d;

	//the seven pieces of the diagram
	Area chunk_r, chunk_g, chunk_b;
	Area slice_rg, slice_gb, slice_br;
	Area sliver_rgb;
	
	//vars to hold pixel values 
	int r, g, b;

	//
	public ColorPanel(int radius) {

		setPreferredSize(new Dimension(300, 300));
		setBackground(Color.lightGray);

		//three circles to construct the diagram pieces 
		Ellipse2D circle_r = new Ellipse2D.Double(50, 100, radius, radius);
		Ellipse2D circle_g = new Ellipse2D.Double(100, 100, radius, radius);
		Ellipse2D circle_b = new Ellipse2D.Double(75, 50, radius, radius);

		//Piece object to create the seven pieces 
		Piece p = new Piece();

		//three pieces that form the bulk of circle 
		chunk_r = p.getChunk(circle_r, circle_g, circle_b);
		chunk_g = p.getChunk(circle_g, circle_r, circle_b);
		chunk_b = p.getChunk(circle_b, circle_r, circle_g);

		//three pieces that form the overlap between circles 
		slice_rg = p.getSlice(circle_r, circle_g);
		slice_gb = p.getSlice(circle_b, circle_g);
		slice_br = p.getSlice(circle_r, circle_b);

		//one piece to represent the center of the diagram
		sliver_rgb = p.getSliver(circle_r, circle_g, circle_b);

	}

	//colors the seven pieces with the appropriate  values 
	public void paintComponent(Graphics x) {

		super.paintComponent(x);

		g2d = (Graphics2D) x;
		
		paintArea(chunk_r, new Color(r, 0, 0));
		paintArea(chunk_g, new Color(0, g, 0));
		paintArea(chunk_b, new Color(0, 0, b));

		paintArea(slice_rg, new Color(r, g, 0));
		paintArea(slice_gb, new Color(0, g, b));
		paintArea(slice_br, new Color(r, 0, b));

		paintArea(sliver_rgb, new Color(r, g, b));

	}

	//puts the area object into the g2d pane 
	private void paintArea(Area area, Color color) {
		g2d.setPaint(color);
		g2d.fill(area);
		g2d.draw(area);
	}

}