package model;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

public class Group {
	
	public ArrayList<Shape> children;
	public boolean isSelected = false;
	public Color fill = null;
	public Color stroke = null;
	public float width;
	
	public Group(){
		children = new ArrayList<Shape>();
	}
	
	public Shape get(int index){
		return children.get(index);
		
	}
	
	public void isSelect(boolean tf){
		for (Shape s:children){
			s.isSelected = tf;
		}
	}
	
	public int size(){
		return children.size();
	}
	
	public boolean isEmpty(){
		if (children.isEmpty()){
			return true;
		}
		else
			return false;
	}
	
	public void repaint(Graphics g){
		for (int i = children.size() - 1; i >= 0; i--){
			children.get(i).draw(g);
		}
	}
	
	public void reset(){
		for (int i = children.size() - 1; i >= 0; i--){
			children.get(i).isSelected = false;
			//children.remove(children.get(i));
		}
	}
	
	public void add(Shape input){
		children.add(input);
	}
	
	public boolean contains(int x, int y){
		for (Shape s:children){
			if (s.containsPoint(x,y)){
				return true;
			}
		}
		return false;
	}
	
	public void moveBy(int dx, int dy){
		for (Shape s:children){
			s.moveBy(dx, dy);
		}
	}
	
	public void remove(Shape input){
		children.remove(input);
	}
	
	public void setColor(Color color){
		fill = color;
		for (Shape s:children){
			s.setColor(color);
		}
	}
	
	public void setStroke(Color color){
		stroke = color;
		for (Shape s:children){
			s.setStroke(color);
		}
	}
	
	public void setStrokeWidth(float width){
		this.width = width;
		for (Shape s:children){
			s.setStrokeWidth(width);
		}
	}
	
	

}
