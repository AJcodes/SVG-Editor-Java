package controller;

import model.Group;
import model.Shape;

import java.awt.Color;
import java.io.File;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public final class SVGWriter
{
	private static ArrayList<Shape> SVGShapes;
	private static ArrayList<Group> SVGGroups;
	private static double width;
	private static double height;
	private static String path;
	
	public SVGWriter(String pathl, double wid, double heig, ArrayList<Shape> in, ArrayList<Group> gin){
		path = pathl;
		SVGShapes = in;
		SVGGroups = gin;
		width = wid;
		height = heig;
		writeSVG();
	}
	
      
    private static void writeSVG()
    {
    	try{
    		DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
    		DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
    		
    		Document doc = docBuilder.newDocument();
    		Element rootElement = doc.createElement("svg");
    		doc.appendChild(rootElement);
    		
    		Attr attr = doc.createAttribute("width");
    		attr.setValue(Integer.toString((int)width));
    		rootElement.setAttributeNode(attr);
    		
    		Attr attr2 = doc.createAttribute("height");
    		attr2.setValue(Integer.toString((int)height));
    		rootElement.setAttributeNode(attr2);
    		
    		Attr attr3 = doc.createAttribute("viewBox");
    		attr3.setValue("0 0 " + Integer.toString((int)width) + " " + Integer.toString((int)height));
    		rootElement.setAttributeNode(attr3);
    		
    		for (int i = 0; i < SVGShapes.size(); i++){
    			Shape s = (Shape)SVGShapes.get(i);
    			if (s.type.equals("Rect")){
    				Element rect = doc.createElement("rect");
    				Attr attrect1 = doc.createAttribute("x");
    	    		attrect1.setValue(Integer.toString(s.left));
    	    		rect.setAttributeNode(attrect1);
    	    		Attr attrect2 = doc.createAttribute("y");
    	    		attrect2.setValue(Integer.toString(s.top));
    	    		rect.setAttributeNode(attrect2);
    	    		Attr attrect3 = doc.createAttribute("width");
    	    		attrect3.setValue(Integer.toString(s.width));
    	    		rect.setAttributeNode(attrect3);
    	    		Attr attrect4 = doc.createAttribute("height");
    	    		attrect4.setValue(Integer.toString(s.height));
    	    		rect.setAttributeNode(attrect4);
    	    		if (s.color == Color.WHITE){
    	    			Attr attrect5 = doc.createAttribute("fill");
    	    			attrect5.setValue("none");
        	    		rect.setAttributeNode(attrect5);
    	    		}
    	    		else{
    	    			Attr attrect5 = doc.createAttribute("fill");
    	    			attrect5.setValue(Integer.toString(s.color.getRGB()));
        	    		rect.setAttributeNode(attrect5);
    	    		}
    	    		if (s.stroke == Color.white){
    	    			Attr attrect6 = doc.createAttribute("stroke");
    	    			attrect6.setValue("none");
        	    		rect.setAttributeNode(attrect6);
    	    		}
    	    		else{
    	    			Attr attrect6 = doc.createAttribute("stroke");
    	    			attrect6.setValue(Integer.toString(s.stroke.getRGB()));
        	    		rect.setAttributeNode(attrect6);
    	    		}
    	    		Attr attrect7 = doc.createAttribute("stroke-width");
    	    		attrect7.setValue(Integer.toString((int)s.strokewidth));
    	    		rect.setAttributeNode(attrect7);
    				rootElement.appendChild(rect);
    			}
    			else if (s.type.equals("Line")){
    				Element line = doc.createElement("line");
    				Attr attrect1 = doc.createAttribute("x1");
    				attrect1.setValue(Integer.toString(s.left));
    	    		line.setAttributeNode(attrect1);
    	    		Attr attrect2 = doc.createAttribute("y1");
    	    		attrect2.setValue(Integer.toString(s.top));
    	    		line.setAttributeNode(attrect2);
    	    		Attr attrect3 = doc.createAttribute("x2");
    	    		attrect3.setValue(Integer.toString(s.width));
    	    		line.setAttributeNode(attrect3);
    	    		Attr attrect4 = doc.createAttribute("y2");
    	    		attrect4.setValue(Integer.toString(s.height));
    	    		line.setAttributeNode(attrect4);
    	    		if (s.stroke == Color.WHITE){
    	    			Attr attrect5 = doc.createAttribute("stroke");
    	    			attrect5.setValue("none");
        	    		line.setAttributeNode(attrect5);
    	    		}
    	    		else{
    	    			Attr attrect5 = doc.createAttribute("stroke");
    	    			attrect5.setValue(Integer.toString(s.stroke.getRGB()));
        	    		line.setAttributeNode(attrect5);
    	    		}
    	    		Attr attrect7 = doc.createAttribute("stroke-width");
    	    		attrect7.setValue(Integer.toString((int)s.strokewidth));
    	    		line.setAttributeNode(attrect7);
    				rootElement.appendChild(line);
    			}
    			else if (s.type.equals("Oval")){
    				if (s.width == s.height){
	    				Element line = doc.createElement("circle");
	    				Attr attrect1 = doc.createAttribute("cx");
	    				attrect1.setValue(Integer.toString(s.left+(s.width/2)));
	    	    		line.setAttributeNode(attrect1);
	    	    		Attr attrect2 = doc.createAttribute("cy");
	    	    		attrect2.setValue(Integer.toString(s.top+(s.height/2)));
	    	    		line.setAttributeNode(attrect2);
    	    			Attr attrect3 = doc.createAttribute("r");
    	    			attrect3.setValue(Integer.toString(s.width/2));
        	    		line.setAttributeNode(attrect3);
	    	    		if (s.color == Color.WHITE){
	    	    			Attr attrect5 = doc.createAttribute("fill");
	    	    			attrect5.setValue("none");
	        	    		line.setAttributeNode(attrect5);
	    	    		}
	    	    		else{
	    	    			Attr attrect5 = doc.createAttribute("fill");
	    	    			attrect5.setValue(Integer.toString(s.color.getRGB()));
	        	    		line.setAttributeNode(attrect5);
	    	    		}
	    	    		if (s.stroke == Color.white){
	    	    			Attr attrect6 = doc.createAttribute("stroke");
	    	    			attrect6.setValue("none");
	        	    		line.setAttributeNode(attrect6);
	    	    		}
	    	    		else{
	    	    			Attr attrect6 = doc.createAttribute("stroke");
	    	    			attrect6.setValue(Integer.toString(s.stroke.getRGB()));
	        	    		line.setAttributeNode(attrect6);
	    	    		}
	    	    		Attr attrect7 = doc.createAttribute("stroke-width");
	    	    		attrect7.setValue(Integer.toString((int)s.strokewidth));
	    	    		line.setAttributeNode(attrect7);
	    				rootElement.appendChild(line);
	    				}
    				else{
    					Element line = doc.createElement("ellipse");
	    				Attr attrect1 = doc.createAttribute("cx");
	    				attrect1.setValue(Integer.toString(s.left+(s.width/2)));
	    	    		line.setAttributeNode(attrect1);
	    	    		Attr attrect2 = doc.createAttribute("cy");
	    	    		attrect2.setValue(Integer.toString(s.top+(s.height/2)));
	    	    		line.setAttributeNode(attrect2);
    	    			Attr attrect3 = doc.createAttribute("rx");
	    	    		attrect3.setValue(Integer.toString(s.width/2));
	        	    	line.setAttributeNode(attrect3);
	        	    	Attr attrect4 = doc.createAttribute("ry");
	        	    	attrect4.setValue(Integer.toString(s.height/2));
	        	    	line.setAttributeNode(attrect4);
	    	    		if (s.color == Color.WHITE){
	    	    			Attr attrect5 = doc.createAttribute("fill");
	    	    			attrect5.setValue("none");
	        	    		line.setAttributeNode(attrect5);
	    	    		}
	    	    		else{
	    	    			Attr attrect5 = doc.createAttribute("fill");
	    	    			attrect5.setValue(Integer.toString(s.color.getRGB()));
	        	    		line.setAttributeNode(attrect5);
	    	    		}
	    	    		if (s.stroke == Color.white){
	    	    			Attr attrect6 = doc.createAttribute("stroke");
	    	    			attrect6.setValue("none");
	        	    		line.setAttributeNode(attrect6);
	    	    		}
	    	    		else{
	    	    			Attr attrect6 = doc.createAttribute("stroke");
	    	    			attrect6.setValue(Integer.toString(s.stroke.getRGB()));
	        	    		line.setAttributeNode(attrect6);
	    	    		}
	    	    		Attr attrect7 = doc.createAttribute("stroke-width");
	    	    		attrect7.setValue(Integer.toString((int)s.strokewidth));
	    	    		line.setAttributeNode(attrect7);
	    				rootElement.appendChild(line);
	    				}
    				}
    			}
    		
    		if (!SVGGroups.isEmpty()){
    			for (int i = 0; i < SVGGroups.size(); i++){
        			Group g = (Group)SVGGroups.get(i);
	    			Element group = doc.createElement("g");
	    			rootElement.appendChild(group);
	    			for (int j = 0; j < g.children.size(); j++){
	    				Shape s = (Shape) g.children.get(j);
	    				if (s.type.equals("Rect")){
	        				Element rect = doc.createElement("rect");
	        				Attr attrect1 = doc.createAttribute("x");
	        	    		attrect1.setValue(Integer.toString(s.left));
	        	    		rect.setAttributeNode(attrect1);
	        	    		Attr attrect2 = doc.createAttribute("y");
	        	    		attrect2.setValue(Integer.toString(s.top));
	        	    		rect.setAttributeNode(attrect2);
	        	    		Attr attrect3 = doc.createAttribute("width");
	        	    		attrect3.setValue(Integer.toString(s.width));
	        	    		rect.setAttributeNode(attrect3);
	        	    		Attr attrect4 = doc.createAttribute("height");
	        	    		attrect4.setValue(Integer.toString(s.height));
	        	    		rect.setAttributeNode(attrect4);
	        	    		if (s.color == Color.WHITE){
	        	    			Attr attrect5 = doc.createAttribute("fill");
	        	    			attrect5.setValue("none");
	            	    		rect.setAttributeNode(attrect5);
	        	    		}
	        	    		else{
	        	    			Attr attrect5 = doc.createAttribute("fill");
	        	    			attrect5.setValue(Integer.toString(s.color.getRGB()));
	            	    		rect.setAttributeNode(attrect5);
	        	    		}
	        	    		if (s.stroke == Color.white){
	        	    			Attr attrect6 = doc.createAttribute("stroke");
	        	    			attrect6.setValue("none");
	            	    		rect.setAttributeNode(attrect6);
	        	    		}
	        	    		else{
	        	    			Attr attrect6 = doc.createAttribute("stroke");
	        	    			attrect6.setValue(Integer.toString(s.stroke.getRGB()));
	            	    		rect.setAttributeNode(attrect6);
	        	    		}
	        	    		Attr attrect7 = doc.createAttribute("stroke-width");
	        	    		attrect7.setValue(Integer.toString((int)s.strokewidth));
	        	    		rect.setAttributeNode(attrect7);
	        				group.appendChild(rect);
	        			}
	        			else if (s.type.equals("Line")){
	        				Element line = doc.createElement("line");
	        				Attr attrect1 = doc.createAttribute("x1");
	        				attrect1.setValue(Integer.toString(s.left));
	        	    		line.setAttributeNode(attrect1);
	        	    		Attr attrect2 = doc.createAttribute("y1");
	        	    		attrect2.setValue(Integer.toString(s.top));
	        	    		line.setAttributeNode(attrect2);
	        	    		Attr attrect3 = doc.createAttribute("x2");
	        	    		attrect3.setValue(Integer.toString(s.width));
	        	    		line.setAttributeNode(attrect3);
	        	    		Attr attrect4 = doc.createAttribute("y2");
	        	    		attrect4.setValue(Integer.toString(s.height));
	        	    		line.setAttributeNode(attrect4);
	        	    		if (s.stroke == Color.WHITE){
	        	    			Attr attrect5 = doc.createAttribute("stroke");
	        	    			attrect5.setValue("none");
	            	    		line.setAttributeNode(attrect5);
	        	    		}
	        	    		else{
	        	    			Attr attrect5 = doc.createAttribute("stroke");
	        	    			attrect5.setValue(Integer.toString(s.stroke.getRGB()));
	            	    		line.setAttributeNode(attrect5);
	        	    		}
	        	    		Attr attrect7 = doc.createAttribute("stroke-width");
	        	    		attrect7.setValue(Integer.toString((int)s.strokewidth));
	        	    		line.setAttributeNode(attrect7);
	        				group.appendChild(line);
	        			}
	        			else if (s.type.equals("Oval")){
	        				if (s.width == s.height){
	    	    				Element line = doc.createElement("circle");
	    	    				Attr attrect1 = doc.createAttribute("cx");
	    	    				attrect1.setValue(Integer.toString(s.left+(s.width/2)));
	    	    	    		line.setAttributeNode(attrect1);
	    	    	    		Attr attrect2 = doc.createAttribute("cy");
	    	    	    		attrect2.setValue(Integer.toString(s.top+(s.height/2)));
	    	    	    		line.setAttributeNode(attrect2);
	        	    			Attr attrect3 = doc.createAttribute("r");
	        	    			attrect3.setValue(Integer.toString(s.width/2));
	            	    		line.setAttributeNode(attrect3);
	    	    	    		if (s.color == Color.WHITE){
	    	    	    			Attr attrect5 = doc.createAttribute("fill");
	    	    	    			attrect5.setValue("none");
	    	        	    		line.setAttributeNode(attrect5);
	    	    	    		}
	    	    	    		else{
	    	    	    			Attr attrect5 = doc.createAttribute("fill");
	    	    	    			attrect5.setValue(Integer.toString(s.color.getRGB()));
	    	        	    		line.setAttributeNode(attrect5);
	    	    	    		}
	    	    	    		if (s.stroke == Color.white){
	    	    	    			Attr attrect6 = doc.createAttribute("stroke");
	    	    	    			attrect6.setValue("none");
	    	        	    		line.setAttributeNode(attrect6);
	    	    	    		}
	    	    	    		else{
	    	    	    			Attr attrect6 = doc.createAttribute("stroke");
	    	    	    			attrect6.setValue(Integer.toString(s.stroke.getRGB()));
	    	        	    		line.setAttributeNode(attrect6);
	    	    	    		}
	    	    	    		Attr attrect7 = doc.createAttribute("stroke-width");
	    	    	    		attrect7.setValue(Integer.toString((int)s.strokewidth));
	    	    	    		line.setAttributeNode(attrect7);
	    	    				group.appendChild(line);
	    	    				}
	        				else{
	        					Element line = doc.createElement("ellipse");
	    	    				Attr attrect1 = doc.createAttribute("cx");
	    	    				attrect1.setValue(Integer.toString(s.left+(s.width/2)));
	    	    	    		line.setAttributeNode(attrect1);
	    	    	    		Attr attrect2 = doc.createAttribute("cy");
	    	    	    		attrect2.setValue(Integer.toString(s.top+(s.height/2)));
	    	    	    		line.setAttributeNode(attrect2);
	        	    			Attr attrect3 = doc.createAttribute("rx");
	    	    	    		attrect3.setValue(Integer.toString(s.width/2));
	    	        	    	line.setAttributeNode(attrect3);
	    	        	    	Attr attrect4 = doc.createAttribute("ry");
	    	        	    	attrect4.setValue(Integer.toString(s.height/2));
	    	        	    	line.setAttributeNode(attrect4);
	    	    	    		if (s.color == Color.WHITE){
	    	    	    			Attr attrect5 = doc.createAttribute("fill");
	    	    	    			attrect5.setValue("none");
	    	        	    		line.setAttributeNode(attrect5);
	    	    	    		}
	    	    	    		else{
	    	    	    			Attr attrect5 = doc.createAttribute("fill");
	    	    	    			attrect5.setValue(Integer.toString(s.color.getRGB()));
	    	        	    		line.setAttributeNode(attrect5);
	    	    	    		}
	    	    	    		if (s.stroke == Color.white){
	    	    	    			Attr attrect6 = doc.createAttribute("stroke");
	    	    	    			attrect6.setValue("none");
	    	        	    		line.setAttributeNode(attrect6);
	    	    	    		}
	    	    	    		else{
	    	    	    			Attr attrect6 = doc.createAttribute("stroke");
	    	    	    			attrect6.setValue(Integer.toString(s.stroke.getRGB()));
	    	        	    		line.setAttributeNode(attrect6);
	    	    	    		}
	    	    	    		Attr attrect7 = doc.createAttribute("stroke-width");
	    	    	    		attrect7.setValue(Integer.toString((int)s.strokewidth));
	    	    	    		line.setAttributeNode(attrect7);
	    	    				group.appendChild(line);
	    	    				}
	        				}
	    			}
    			}
    		}
    		
    		
    		TransformerFactory transformerFactory = TransformerFactory.newInstance();
    		Transformer transformer = transformerFactory.newTransformer();
    		DOMSource source = new DOMSource(doc);
    		StreamResult result = new StreamResult(new File(path));
    		
    		transformer.transform(source, result);
    		
	    } catch (ParserConfigurationException pce) {
			pce.printStackTrace();
		} 
    	catch (TransformerException tfe) {
			tfe.printStackTrace();
		  }
        
    }
}
