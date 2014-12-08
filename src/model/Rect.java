package model;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;



public class Rect extends Shape {
	
	//public Points[] points = new Points[8];
	
	
	public Rect(int x1, int y1, int x2, int y2) {
		this.type = "Rect";
		reshape(x1, y1, x2, y2);
		setColor(Color.RED);
		createPoints();
	}
	
	public Rect(int x, int y, int width, int height, Color stroke, int s, Color fill){
		//reshape(x1, y1, x2, y2);
		this.type = "Rect";
		reshape(x, y, width, height);
		setStrokeWidth(s);
		setStroke(stroke);
		setColor(fill);
		createPoints();
	}
	
	public void createPoints(){
		points[0] = new Points(left-5, top-5, 8, 8);
		points[1] = new Points(left+width-5, top-5, 8, 8);
		points[2] = new Points(left-5, top+height-5, 8, 8);
		points[3] = new Points(left+width-5, top+height-5, 8, 8);
		points[4] = new Points(left+(width/2)-5, top-5, 8, 8);
		points[5] = new Points(left-5, top+(height/2)-5, 8, 8);
		points[6] = new Points(left+(width/2)-5, top+height-5, 8, 8);
		points[7] = new Points(left+width-5, top+(height/2)-5, 8, 8);
		
	}
	
	
	
    public void draw(Graphics g) {
	 Graphics2D g2 = (Graphics2D) g;
	 AffineTransform orig = g2.getTransform();
	 target += (angle - target) * ease;
     g2.rotate(angle, left+(width/2), top+(height/2));
     g2.setStroke (new BasicStroke (strokewidth)); 
     g2.setColor(stroke);
     g2.drawRect(left,top,width,height);
     g2.setColor(color);
     g2.fillRect(left,top,width,height);
     g2.setTransform(orig);
     if (isSelected){
    	 createPoints();
    	 for (int i = 0; i < 8; i++){
    		 points[i].draw(g2, angle, left+(width/2), top+(height/2));
    	 }

     }
    
 }


}