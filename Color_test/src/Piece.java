import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;

public class Piece{
	
   public Piece(){
   }
   
   /*
    * creates the halfmoon shaped piece of the diagram
    * -the first circle is the circle from which chunks are removed
    * 
    * @param c1,c2,c3 the circles that form the diagram
    */
   Area getChunk(Ellipse2D c1_, Ellipse2D c2_, Ellipse2D c3_){
	   
	   Area c1 = new Area(c1_);
	   Area c2 = new Area(c2_);
	   Area c3 = new Area(c3_);
	   
      c1.subtract(c2);   
      c1.subtract(c3);
      return  c1;    
   } 
   
   /*
    * creates the lens shaped piece where two circles overlap
    * -the first circle is the circle from which chunks are removed
    * 
    * @param c1,c2 the circles that form the diagram
    */
   Area getSlice(Ellipse2D c1_, Ellipse2D c2_){
	   
	   Area c1 = new Area(c1_);
	   Area c2 = new Area(c2_);
	   
	   c1.intersect(c2);   
	   return  c1;    
	} 
   
   /*
    * creates the triangle piece in the center o the diagram 
    * -the first circle is the circle from which chunks are removed
    * 
    * @param c1,c2,c3 the circles that form the diagram
    */
   Area getSliver(Ellipse2D c1_, Ellipse2D c2_, Ellipse2D c3_){
	   
	   Area c1 = new Area(c1_);
	   Area c2 = new Area(c2_);
	   Area c3 = new Area(c3_);
	   
       c1.intersect(c2);
       c1.intersect(c3);
       return c1;
  }   
}