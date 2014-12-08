package model;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;


public class Line extends Shape {
	
	public Line(int x1, int y1, int x2, int y2){
		this.type = "Line";
		reshape(x1, y1, x2, y2);
		setStroke(Color.GREEN);
		createPoints();
	}
	
	public Line(int x1, int y1, int x2, int y2, int swidth, Color color){
		this.type = "Line";
		reshape(x1, y1, x2, y2);
		strokewidth = swidth;
		setStroke(color);
		createPoints();
	}
	
	public void createPoints(){
		lpoints[0] = new Points(left-5, top-5, 8, 8);
		lpoints[1] = new Points(width-5, height-5, 8, 8);
		
	}
	
	public void draw(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		AffineTransform orig = g2.getTransform();
		target += (angle - target) * ease;
	    g2.rotate(angle, (left+width)/2, (top+height)/2);
		g2.setStroke (new BasicStroke (strokewidth)); 
	    g2.setColor(stroke);
	    g2.drawLine(left,top,width,height);
	    g2.setTransform(orig);
	    if (isSelected){
	    	createPoints();
	    	for (int i = 0; i < 2; i++){
	   		 lpoints[i].draw(g2, angle, (left+width)/2, (top+height)/2);
	   	 	}
	    	
//	    	   g2.setStroke(new ControlPointsStroke(strokewidth));
//	    	   g2.setColor(Color.black);
//	    	   g2.drawLine(left,top,width,height);
	     }
	 }
	
	public boolean containsPoint(int x, int y) {
	       // Check whether the shape contains the point (x,y).
	       // By default, this just checks whether (x,y) is inside the
	       // rectangle that bounds the shape.  This method should be
	       // overridden by a subclass if the default behavior is not
	       // appropriate for the subclass.
		if (width > left && height > top){
		    if (x >= left && x < width && y >= top && y < height)
		       return true;
	    }
		else if (width < left && height > top){
			if (x <= left && x > width && y >= top && y < height)
			       return true;
		}
		else if (width < left && height < top){
			if (x <= left && x > width && y <= top && y > height)
			       return true;
		}
		else if (width > left && height < top){
			if (x >= left && x < width && y <= top && y > height)
			       return true;
		}
		return false;
	 }
	
	public void moveBy(int dx, int dy) {
    left += dx;
    top += dy;
    width += dx;
    height += dy;
	}


	  
}