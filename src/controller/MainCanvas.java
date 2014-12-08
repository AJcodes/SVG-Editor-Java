package controller;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.util.ArrayList;

import javax.swing.JPanel;

import model.Group;
import model.Line;
import model.Oval;
import model.Rect;
import model.Shape;

public class MainCanvas extends JPanel
implements ActionListener, MouseListener, MouseMotionListener, MouseWheelListener {


/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
public ArrayList<Shape> shapes = new ArrayList<Shape>(); 
public String shapetype;
public boolean isSelect = true;
public boolean isRotate = false;
public Shape testrect;
public double scale;
private double angle;
private double target;
public double owidth;
public double oheight;
public double width;
public double height;

public ArrayList<Group> Groups = new ArrayList<Group>();

public Group clickedGroup = new Group();

public Group selGroup = new Group();

public Shape selection = null;

public Shape clickedShape = null;  

public Shape resizeShape = null;

public Shape draggedShape = null;  

public String path;

Shape newS;

int x1;
int y1;


int prevDragX;  
int prevDragY;  
int startDrawX;
int startDrawY;

public MainCanvas(String pa, int widths, int heights) {
path = pa;
angle = 0;
target = 0;
scale = 1;
width = widths;
height = heights;
oheight = height;
owidth = width;
testrect = new Rect(0, 0, (int)width, (int)height);
testrect.setColor(Color.WHITE);
testrect.setStroke(Color.WHITE);
setBounds(0,0,(int)width, (int)height);
setPreferredSize(new Dimension((int)width,(int)height));
setBackground(Color.GRAY);
addMouseListener(this);
addMouseMotionListener(this);
addMouseWheelListener(this);
}   

public void paintComponent(Graphics g) {

super.paintComponent(g);
Graphics2D g2 = (Graphics2D) g;
g2.scale(scale, scale);
testrect.draw(g2);
g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

if (!shapes.isEmpty()){
	int top = shapes.size();
	for (int i = 0; i < top; i++) {
	Shape s = (Shape)shapes.get(i);
	s.draw(g2);
	}
}
if (!Groups.isEmpty()){
	for (int j = 0; j < Groups.size(); j++){
		Group m = Groups.get(j);
		m.repaint(g2);
	}
}
if (selection != null){
	selection.draw(g2);
}
g2.scale(1/scale, 1/scale);
	


}   

public void actionPerformed(ActionEvent evt) {

String command = evt.getActionCommand();
//if (command.equals("Clear")) {
//shapes.clear();
//repaint();
//}
if (command.equals("Select")){
	setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
	isSelect = true;
	isRotate = false;
	shapetype = "";
}
else if (command.equals("Rotate")){
	isRotate = true;
	isSelect = false;
	shapetype = "";
}
else if (command.equals("Rectangle")){
	isSelect = false;
	isRotate = false;
	setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
	shapetype = command;
}
else if (command.equals("Oval")){
	isSelect = false;
	isRotate = false;
	setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
	shapetype = command;
}
else if (command.equals("Line")){
	isSelect = false;
	isRotate = false;
	setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
	shapetype = command;
}
else if (command.equals("Bring to Front")) {
	for (int i = shapes.size() - 1; i >= 0; i--){
    	Shape s = (Shape)shapes.get(i);
    	if (s.isSelected == true){
    		shapes.remove(s);
    		shapes.add(s);
    	}
    }
}

repaint();
revalidate();
//}
} // end actionPerformed()


// -------------------- -------------------------------------- ----------------------

public void mousePressed(MouseEvent evt) {
	try{
	int x = (int) (evt.getX() / scale);  
	int y = (int) (evt.getY() / scale); 
	startDrawX = x;
	startDrawY = y;
	x1 = x;
	//boolean test = true;
	y1 = y;
//	if (!clickedGroup.isEmpty()){
//		clickedGroup.reset();
//		clickedGroup = new Group();
//		repaint();
//	}
	
	if (isRotate == true){
		x1 = x;
		y1 = y;
	}
	else if (isSelect == true){
		if (!selGroup.isEmpty()){
			selGroup.reset();
			selGroup = new Group();
			repaint();
		}
		if (clickedShape != null){
			clickedShape.isSelected = false;
			clickedShape = null;
			
			repaint();
		}
		if (resizeShape != null){
			resizeShape.reset();
			resizeShape = null;
			repaint();
		}
		setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
		if (!clickedGroup.isEmpty()){
			return;
		}
		if (draggedShape != null) {

			return;
		}
		boolean test = false;
		
		if (!Groups.isEmpty()){
			for (int i = Groups.size() - 1; i >= 0; i--){
				Group g = Groups.get(i);
				if (g.contains(x,y)){
					g.isSelect(true);
					clickedGroup = g;
					prevDragX = x;
					prevDragY = y;
					repaint();
					return;
				}
			}
		}
		//clickedShape = null;  
		for ( int i = shapes.size() - 1; i >= 0; i-- ) {  

			Shape s = (Shape)shapes.get(i);
			if (s.containsPoint(x,y)) {
				s.isSelected = true;
				clickedShape = s;
//				clickedShape.isSelected = true;
				break;
			}
			else if (s.contains(x, y)){
				resizeShape = s;
				startDrawX = x;
				startDrawY = y;
				break;
			}
			
		}
		
		
		
		if (clickedShape == null && resizeShape == null) {
			selection = new Rect(x1, y1, 0, 0);
			Color color = new Color(0, 0, 255, 128); //Red 
			selection.setColor(color);
			selection.setStrokeWidth(0.1f);
			selection.setStroke(Color.WHITE);
		}
		else if (evt.isPopupTrigger()) {
			
			//popup.show(this,x-10,y-2);
		}
		else if (evt.isShiftDown()) {
			
//			shapes.remove(clickedShape);
//			
//			shapes.add(clickedShape);
			repaint();
		}
		else {
			if (test == false){
				draggedShape = clickedShape;
				prevDragX = x;
				prevDragY = y;
			}
		}
	}
	else {
		if (!selGroup.isEmpty()){
			selGroup.reset();
			selGroup = new Group();
			repaint();
		}
		if (clickedShape != null){
			clickedShape.isSelected = false;
			clickedShape = null;
			
			repaint();
		}
		if (resizeShape != null){
			resizeShape.reset();
			resizeShape = null;
			repaint();
		}
		if (shapetype.equals("Rectangle")) {
			
			newS = new Rect(x1, y1, 0, 0);
			shapes.add(newS);
	   	     //repaint();
	    }
	    else if (shapetype.equals("Line")) {
	    	
	    	newS = new Line(x1, y1, 0, 0);
	    	shapes.add(newS);
	    	  //repaint();
	    }
	    else if (shapetype.equals("Oval")) {
	    	
	    	newS = new Oval(x1, y1, 0, 0);
	    	shapes.add(newS);
	    	  //repaint();
	    }
	}
	}catch(NullPointerException e){
		
	}
		
}

public void mouseDragged(MouseEvent evt) {
	int x = (int) (evt.getX() / scale);  
	int y = (int) (evt.getY() / scale); 
	
	if (isRotate == true){
		if (clickedShape != null){
			double ang = Math.atan2(x - clickedShape.width/2, clickedShape.height/2 - y);
			if ( ang < clickedShape.target - Math.PI ) ang = ang + (2 * Math.PI);
			if ( ang > clickedShape.target + Math.PI ) ang = ang - (2 * Math.PI);
			
			clickedShape.angle = ang;
			repaint();
		}
	}
	else if (isSelect == true){
		if (draggedShape != null) {
			setCursor(new Cursor(Cursor.MOVE_CURSOR));
			draggedShape.moveBy(x - prevDragX, y - prevDragY);
			prevDragX = x;
			prevDragY = y;
			repaint(); 
			
			//return;
		}
//		else{
//		draggedShape.moveBy(x - prevDragX, y - prevDragY);
//		prevDragX = x;
//		prevDragY = y;
//		repaint();  
//		}
		if (resizeShape != null){
			setCursor(new Cursor(Cursor.HAND_CURSOR));
			resizeShape.reSize(x, y, startDrawX, startDrawY);
			repaint();
			//return;
		}
		if (selection != null){
			selection.setSize(x-x1, y-y1);
	   	     repaint();
		}
		if (!clickedGroup.isEmpty()){
			setCursor(new Cursor(Cursor.MOVE_CURSOR));
			clickedGroup.moveBy(x - prevDragX, y - prevDragY);
			prevDragX = x;
			prevDragY = y;
			repaint();
		}
	}
	else{
		
		if (shapetype.equals("Rectangle")) {
			setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
			newS.setSize(x-x1, y-y1);
	   	     repaint();
	    }
	    else if (shapetype.equals("Line")) {
	    	setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
	    	newS.setSize(x, y);
	    	  repaint();
	    }
	    else if (shapetype.equals("Oval")) {
	    	setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
	    	newS.setSize(x-x1, y-y1);
	    	  repaint();
	    }
		//repaint();
	}
	
}

public void mouseReleased(MouseEvent evt) {
	int x = (int) (evt.getX() / scale);  
	int y = (int) (evt.getY() / scale); 
	
	try{
	if (isSelect == true){
		if (!clickedGroup.isEmpty()){
			selGroup = clickedGroup;
			clickedGroup.reset();
			clickedGroup = new Group();
		}
		setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
		if (selection != null){
			for (int i = shapes.size() - 1; i >= 0; i--){
				Shape s = (Shape)shapes.get(i);
				if (s.left >= selection.left && s.left < selection.left + selection.width && s.top >= selection.top && s.top < selection.top + selection.height){
					s.isSelected = true;
					clickedGroup.add(s);
					//repaint();
				}
				repaint();
			}
			selection = null;
			repaint();
			
		}
		if (draggedShape == null) {

			return;
		}
		
		if (evt.isPopupTrigger()) {

			//popup.show(this,x-10,y-2);
		}
		else {
				draggedShape.moveBy(x - prevDragX, y - prevDragY);
				if ( draggedShape.left >= getSize().width || draggedShape.top >= getSize().height ||
						draggedShape.left + draggedShape.width < 0 ||
						draggedShape.top + draggedShape.height < 0 ) {  
					shapes.remove(draggedShape);  
				}
				repaint();
			
		}
		draggedShape = null;  
	}
	else{
		setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
	    if (shapetype.equals("Rectangle")) {
	    	//Shape s = null;
	      // a Rectangle cannot have a zero width or height
	      if (x1==x || y1==y)
	        shapes.remove(newS);
	      //shapes.add(s);
	      repaint();
	    }
	    else if (shapetype.equals("Line")) {
	    	//Shape s = null;
	      // a Rectangle cannot have a zero width or height
	    	if (x1==x || y1==y)
		        shapes.remove(newS);
	      //shapes.add(s);
	      repaint();
	    }
	    else if (shapetype.equals("Oval")) {
	    	//Shape s = null;
	      // an Oval cannot have a zero width or height
	    	if (x1==x || y1==y)
		        shapes.remove(newS);
	      //shapes.add(s);
	      repaint();
	    }
	    
	    
	}
	}catch(NullPointerException e){
		
	}
}

public void mouseEntered(MouseEvent evt) { }   
public void mouseExited(MouseEvent evt) { }    
public void mouseMoved(MouseEvent evt) { }
public void mouseClicked(MouseEvent evt) {
	
}

@Override
public void mouseWheelMoved(MouseWheelEvent e) {
	if(e.getScrollType() == MouseWheelEvent.WHEEL_UNIT_SCROLL) {
		scale += (.1 * e.getWheelRotation());
		scale = Math.max(0.00001, scale); 
		
		width = testrect.getWidth() * scale;
		height = testrect.getHeight()* scale;
		setBounds(0,0,(int)width, (int)height);
		setPreferredSize(new Dimension((int)width,(int)height));
		repaint();
		revalidate();
		
	}
	
}


}


