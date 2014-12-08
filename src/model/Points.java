package model;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;

public class Points{

public int left, top;      // Position of top left corner of rectangle that bounds this shape.
public int width, height;  // Size of the bounding rectangle.
public Color color = Color.black;  // Color of this shape.
public boolean isSelected = false;  // If true, a black border is drawn on the shape
	
	public Points(int x1, int y1, int x2, int y2) {
		reshape(x1, y1, x2, y2);
	}
	
	public void reshape(int left, int top, int width, int height) {
	      // Set the position and size of this shape.
	   this.left = left;
	   this.top = top;
	   this.width = width;
	   this.height = height;
	}
	
	 public boolean containsPoint(int x, int y) {
	       // Check whether the shape contains the point (x,y).
	       // By default, this just checks whether (x,y) is inside the
	       // rectangle that bounds the shape.  This method should be
	       // overridden by a subclass if the default behavior is not
	       // appropriate for the subclass.
	    if (x >= left && x < left+width && y >= top && y < top+height)
	       return true;
	    else
	       return false;
	 }
	
	 public void moveBy(int dx, int dy) {
	        // Move the shape by dx pixels horizontally and dy pixels vertically
	        // (by changing the position of the top-left corner of the shape).
	    left += dx;
	    top += dy;
	 }

	public void draw(Graphics g, double angle, int x, int y) {
		Graphics2D g2 = (Graphics2D) g;
		AffineTransform orig = g2.getTransform();
		g2.rotate(angle, x, y);
        g2.setColor(Color.BLACK);
	    g2.drawRect(left,top,width,height);
	    g2.setTransform(orig);
	}
}
