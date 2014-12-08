package model;

import java.awt.Color;
import java.awt.Graphics;

public abstract class Shape {

    // A class representing shapes that can be displayed on a ShapeCanvas.
    // The subclasses of this class represent particular types of shapes.
    // When a shape is first constructed, it has height and width zero
    // and a default color of white.

 public int left, top;      // Position of top left corner of rectangle that bounds this shape.
 public int width, height;  // Size of the bounding rectangle.
 public double angle = 0;
 public double target = 0;
 public double ease = 0.1;
 public Color color = Color.white;  // Color of this shape.
 public Color stroke = Color.black;
 public float strokewidth = 10;
 public boolean isActive = true;
 public String type;
 public boolean isSelected = false;  // If true, a black border is drawn on the shape
 public Points[] points = new Points[8];
 public Points[] opoints = new Points[4];
 public Points[] lpoints = new Points[2];
 
 public void reshape(int left, int top, int width, int height) {
       // Set the position and size of this shape.
    this.left = left;
    this.top = top;
    this.width = width;
    this.height = height;
 }
 
 public void setSize(int width, int height) {
       // Set the size of this shape
    this.width = width;
    this.height = height;
 }
 
	public int getWidth(){
		return width;
	}
	
	public int getHeight(){
		return height;
	}
	
public void moveBy(int dx, int dy) {
        // Move the shape by dx pixels horizontally and dy pixels vertically
        // (by changing the position of the top-left corner of the shape).
    left += dx;
    top += dy;
 }

public void reSize(int dx, int dy, int firstx, int firsty){
	if (type.equals("Rect")){
		if (points[0].isSelected == true){
			reshape(dx, dy, (left - dx) + width, (top - dy) + height);
		}
		if (points[1].isSelected == true){
			reshape(left, dy, dx, (top - dy) + height);
		}
		if (points[2].isSelected == true){
			reshape(dx, top, (left - dx) + width, dy);
		}
		if (points[3].isSelected == true){
			reshape(left, top, dx, dy);
		}
		if (points[4].isSelected == true){
			reshape(left, dy, width, (top - dy) + height);
		}
		if (points[5].isSelected == true){
			reshape(dx, top, (left - dx) + width, height);
		}
		if (points[6].isSelected == true){
			reshape(left, top, width, dy);
		}
		if (points[7].isSelected == true){
			reshape(left, top, dx, height);
		}
	}
	else if (type.equals("Oval")){
		if (opoints[0].isSelected == true){
			reshape(dx, dy, (left - dx) + width, (top - dy) + height);
		}
		if (opoints[1].isSelected == true){
			reshape(left, dy, dx, (top - dy) + height);
		}
		if (opoints[2].isSelected == true){
			reshape(dx, top, (left - dx) + width, dy);
		}
		if (opoints[3].isSelected == true){
			reshape(left, top, dx, dy);
		}
	}
	else if (type.equals("Line")){
		if (lpoints[0].isSelected == true){
			reshape(dx, dy, width, height);
		}
		if (lpoints[1].isSelected == true){
			reshape(left, top, dx, dy);
		}
	}
	
}

public void setStrokeWidth(float width){
	this.strokewidth = width;
}

public void setStroke(Color stroke){
	this.stroke = stroke;
}

 public void setColor(Color color) {
        // Set the color of this shape
    this.color = color;
 }
 
 public boolean contains(int x, int y){
	 if (type.equals("Rect")){
		 for (int j = 0; j < 8; j++){
			 if (points[j].containsPoint(x, y)){
				 points[j].isSelected = true;
				 return true;
			 }
		 }
	 }
	 else if (type.equals("Oval")){
		 for (int k = 0; k < 4; k++){
			 if (opoints[k].containsPoint(x, y)){
				 opoints[k].isSelected = true;
				 return true;
			 }
		 }
	 }
	 else if (type.equals("Line")){
		 for (int l = 0; l < 2; l++){
			 if (lpoints[l].containsPoint(x, y)){
				 lpoints[l].isSelected = true;
				 return true;
			 }
		 }
	 }
	 return false;
 }
 
 public void reset(){
	 if (type.equals("Rect")){
		 for (int j = 0; j < 8; j++){
				 points[j].isSelected = false;
		 }
	 }
	 else if (type.equals("Oval")){
		 for (int j = 0; j < 4; j++){
				 opoints[j].isSelected = false;
		 }
	 }
	 else if (type.equals("Line")){
		 for (int j = 0; j < 2; j++){
				 lpoints[j].isSelected = false;
		 }
	 }
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
 
 abstract public void draw(Graphics g);  
       // Draw the shape in the graphics context g.
       // This must be overridden in any concrete subclass.



}
