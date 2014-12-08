package model;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;


public class Oval extends Shape {
	
	public Oval(int x1, int y1, int x2, int y2){
		this.type = "Oval";
		reshape(x1, y1, x2, y2);
		setColor(Color.BLUE);
		createPoints();
	}
	
	public Oval(int cx, int cy, int r, Color stroke, int s, Color fill){
		//reshape(x1, y1, x2, y2);
		this.type = "Oval";
		reshape(cx-r, cy-r, r*2, r*2);
		setStrokeWidth(s);
		setStroke(stroke);
		setColor(fill);
		createPoints();
	}
	
	public Oval(int cx, int cy, Color stroke, int s, Color fill, int ry, int rx){
		//reshape(x1, y1, x2, y2);
		this.type = "Oval";
		reshape(cx-rx, cy-ry, rx*2, ry*2);
		setStrokeWidth(s);
		setStroke(stroke);
		setColor(fill);
		createPoints();
	}
	
	public void createPoints(){
		opoints[0] = new Points(left-5, top-5, 8, 8);
		opoints[1] = new Points(left+width-5, top-5, 8, 8);
		opoints[2] = new Points(left-5, top+height-5, 8,8);
		opoints[3] = new Points(left+width-5, top+height-5, 8, 8);
		
	}
	
	
	
public void draw(Graphics g) {
	Graphics2D g2 = (Graphics2D) g;
	AffineTransform orig = g2.getTransform();
	target += (angle - target) * ease;
    g2.rotate(angle, left+(width/2), top+(height/2));
	g2.setStroke (new BasicStroke (strokewidth)); 
	g2.setColor(stroke);
    g2.drawOval(left,top,width,height);
    g2.setColor(color);
    g2.fillOval(left,top,width,height);
    g2.setTransform(orig);
    if (isSelected){
    	createPoints();
    	for (int i = 0; i < 4; i++){
   		 opoints[i].draw(g2, angle, left+(width/2), top+(height/2));
   	 	}
    	
//   	   g2.setStroke(new ControlPointsStroke(strokewidth));
//   	   g2.setColor(Color.black);
//   	   g2.drawOval(left,top,width,height);
    }
}
public boolean containsPoint(int x, int y) {
      // Check whether (x,y) is inside this oval, using the
      // mathematical equation of an ellipse.
   double rx = width/2.0;   // horizontal radius of ellipse
   double ry = height/2.0;  // vertical radius of ellipse 
   double cx = left + rx;   // x-coord of center of ellipse
   double cy = top + ry;    // y-coord of center of ellipse
   if ( (ry*(x-cx))*(ry*(x-cx)) + (rx*(y-cy))*(rx*(y-cy)) <= rx*rx*ry*ry )
      return true;
   else
     return false;
}

}
