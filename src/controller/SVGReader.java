package controller;

import model.Group;
import model.Line;
import model.Oval;
import model.Rect;
import model.Shape;

import java.awt.Color;
import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;

import javax.swing.text.html.StyleSheet;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.EntityResolver;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public final class SVGReader
{
	private static ArrayList<Shape> SVGShapes;
	private static ArrayList<Group> SVGGroups;
	private static int width;
	private static int height;
	
	public SVGReader(String filename){
		SVGShapes = new ArrayList<Shape>();
		SVGGroups = new ArrayList<Group>();
		readSVG(filename);
	}
	
      
    public ArrayList<Shape> returnShapes(){
    	return SVGShapes;
    }
    
    public ArrayList<Group> returnGroups(){
    	return SVGGroups;
    }
    
    public static double convertScale(String input){
    	if (input.indexOf("px") > -1){
    		String newinput = input.replace("px", "");
    		double value = Double.parseDouble(newinput);
    		return 0.75 * ((value * 254) / 72);
    	}
    	else if (input.indexOf("cm") > -1){
    		String newinput = input.replace("cm", "");
    		double value = Double.parseDouble(newinput);
    		return value * 100;
    	}
    	else if (input.indexOf("mm") > -1){
    		String newinput = input.replace("cm", "");
    		double value = Double.parseDouble(newinput);
    		return value * 10;
    	}
    	else if (input.indexOf("in") > -1){
    		String newinput = input.replace("in", "");
    		double value = Double.parseDouble(newinput);
    		return value * 254;
    	}
    	else if (input.indexOf("pt") > -1){
    		String newinput = input.replace("pt", "");
    		double value = Double.parseDouble(newinput);
    		return (value * 254) / 72;
    	}
    	else if (input.indexOf("pc") > -1){
    		String newinput = input.replace("pc", "");
    		double value = Double.parseDouble(newinput);
    		return 12 * ((value * 254) / 72);
    	}
    	else if (input.indexOf("%") > -1){
    		String newinput = input.replace("%", "");
    		double value = Double.parseDouble(newinput);
    		return value / 100;
    	}
		return Double.parseDouble(input);
    }

    public int returnWidth(){
    	return width;
    }
    
    public int returnHeight(){
    	return height;
    }
    private static void readSVG(String fileName)
    {
    	
        Document document;
        DocumentBuilder documentBuilder;
        DocumentBuilderFactory documentBuilderFactory;
        File xmlInputFile;

		try{
            xmlInputFile = new File(fileName);
            documentBuilderFactory = DocumentBuilderFactory.newInstance();
            documentBuilder = documentBuilderFactory.newDocumentBuilder();
            documentBuilder.setEntityResolver(new EntityResolver()
            {
                public InputSource resolveEntity(String publicId, String systemId)
                    throws SAXException, IOException
                {
                    return new InputSource(new StringReader(""));
                }
            });

            document = documentBuilder.parse(xmlInputFile);
            
            NodeList nodes = document.getElementsByTagName("svg");
            document.getDocumentElement().normalize();
            
            for (int i = 0; i < nodes.getLength(); i++) {
            	Node in = nodes.item(i);
            	if (in.getNodeName().equals("svg")){
            	Element elements = (Element) in;
           		String swidth = elements.getAttribute("width");
               	String sheight = elements.getAttribute("height");
               	String view = elements.getAttribute("viewBox");
               	String delim = "[ ]+";
               	String[] tokens = view.split(delim);
               	if (convertScale(swidth) <= 1){
               		width = (int) (convertScale(swidth) * Double.parseDouble(tokens[2]));
               	}
               	if (convertScale(sheight) <= 1){
               		height = (int) (convertScale(sheight) * Double.parseDouble(tokens[3]));
               	}
                else{
               		width = (int)convertScale(swidth);
                	height = (int)convertScale(sheight);
                }
               	
            	
            	XPathFactory xpathFactory = XPathFactory.newInstance();
            	XPath xpath = xpathFactory.newXPath();
            	NodeList test = (NodeList) xpath.evaluate("g", elements, XPathConstants.NODESET);
            	NodeList test1 = (NodeList) xpath.evaluate("*", elements, XPathConstants.NODESET);
               	
               	for (int h = 0; h < test.getLength(); h++){
               		Node inn = test.item(h);
               		Color stroke1 = null;
               		if (inn.getNodeName().equals("g")){
               			Group temps = new Group();
               			Element groups = (Element) inn;
               			StyleSheet st = new StyleSheet();
               			if (groups.hasAttribute("stroke")){
               				
               				if (isInteger(groups.getAttribute("stroke"))){
                    			stroke1 = new Color(Integer.parseInt(groups.getAttribute("stroke")));
                    		}
                    		else
                    			stroke1 = st.stringToColor(groups.getAttribute("stroke"));
               			}
               			NodeList gtest1 = groups.getElementsByTagName("*");
                       	for (int j = 0; j < gtest1.getLength(); j++){
                       		Node in1 = gtest1.item(j);
                       		if (in1.getNodeName().equals("rect")){
                       			Element rect = (Element) in1;
                       			int x = Integer.parseInt(rect.getAttribute("x"));
                            	int y = Integer.parseInt(rect.getAttribute("y"));
                            	int width = Integer.parseInt(rect.getAttribute("width"));
                            	int height = Integer.parseInt(rect.getAttribute("height"));
                            	Color stroke;
                            	if (rect.getAttribute("stroke").equals("none") || !rect.hasAttribute("stroke")){
                            		if (stroke1 != null){
                            			stroke = stroke1;
                            		}
                            		else
                            			stroke = Color.BLACK;
                            	}
                            	else {
                            		if (isInteger(rect.getAttribute("stroke"))){
                            			stroke = new Color(Integer.parseInt(rect.getAttribute("stroke")));
                            		}
                            		else
                            			stroke = st.stringToColor(rect.getAttribute("stroke"));
                            	}
                            	int strokewidth;
                            	if (!rect.hasAttribute("stroke-width")){
                            		strokewidth = 0;
                            	}
                            	else
                            		strokewidth = Integer.parseInt(rect.getAttribute("stroke-width"));
                            	
                            	Color fill;
                            	if (rect.getAttribute("fill").equals("none") || !rect.hasAttribute("fill")){
                            		fill = Color.WHITE;
                            	}
                            	else {
                            		if (isInteger(rect.getAttribute("fill"))){
                            			fill = new Color(Integer.parseInt(rect.getAttribute("fill")));
                            		}
                            		else
                            			fill = st.stringToColor(rect.getAttribute("fill"));
                            	}
                            	//int rx = Integer.parseInt(getTagValue7(element));
                            	
                            	Shape s = new Rect(x,y,width, height, stroke, strokewidth ,fill);
                            	temps.add(s);
                       		}
                       		else if (in1.getNodeName().equals("circle")){
                       			Element circle = (Element) in1;
                       			int cx = Integer.parseInt(circle.getAttribute("cx"));
                            	int cy = Integer.parseInt(circle.getAttribute("cy"));
                            	int r = Integer.parseInt(circle.getAttribute("r"));
                            	Color stroke;
                            	if (circle.getAttribute("stroke").equals("none") || !circle.hasAttribute("stroke")){
                            		if (stroke1 != null){
                            			stroke = stroke1;
                            		}
                            		else
                            			stroke = Color.BLACK;
                            	}
                            	else {
                            		if (isInteger(circle.getAttribute("stroke"))){
                            			stroke = new Color(Integer.parseInt(circle.getAttribute("stroke")));
                            		}
                            		else
                            			stroke = st.stringToColor(circle.getAttribute("stroke"));
                            	}
                            	int strokewidth;
                            	if (!circle.hasAttribute("stroke-width")){
                            		strokewidth = 0;
                            	}
                            	else
                            		strokewidth = Integer.parseInt(circle.getAttribute("stroke-width"));
                            	
                            	Color fill;
                            	if (circle.getAttribute("fill").equals("none") || !circle.hasAttribute("fill")){
                            		fill = Color.BLACK;
                            	}
                            	else {
                            		if (isInteger(circle.getAttribute("fill"))){
                            			fill = new Color(Integer.parseInt(circle.getAttribute("fill")));
                            		}
                            		else
                            			fill = st.stringToColor(circle.getAttribute("fill"));
                            	}
                            	Shape s = new Oval(cx,cy,r,stroke, strokewidth ,fill);
                            	temps.add(s);
                       		}
                       		else if (in1.getNodeName().equals("ellipse")){
                       			Element ellipse = (Element) in1;
                       			int cx = Integer.parseInt(ellipse.getAttribute("cx"));
                            	int cy = Integer.parseInt(ellipse.getAttribute("cy"));
                            	Color stroke;
                            	if (ellipse.getAttribute("stroke").equals("none") || !ellipse.hasAttribute("stroke")){
                            		if (stroke1 != null){
                            			stroke = stroke1;
                            		}
                            		else
                            			stroke = Color.BLACK;
                            	}
                            	else {
                            		if (isInteger(ellipse.getAttribute("stroke"))){
                            			stroke = new Color(Integer.parseInt(ellipse.getAttribute("stroke")));
                            		}
                            		else
                            			stroke = st.stringToColor(ellipse.getAttribute("stroke"));
                            	}
                            	int strokewidth;
                            	if (!ellipse.hasAttribute("stroke-width")){
                            		strokewidth = 0;
                            	}
                            	else
                            		strokewidth = Integer.parseInt(ellipse.getAttribute("stroke-width"));
                            	Color fill;
                            	if (ellipse.getAttribute("fill").equals("none") || !ellipse.hasAttribute("fill")){
                            		fill = Color.BLACK;
                            	}
                            	else {
                            		if (isInteger(ellipse.getAttribute("fill"))){
                            			fill = new Color(Integer.parseInt(ellipse.getAttribute("fill")));
                            		}
                            		else
                            			fill = st.stringToColor(ellipse.getAttribute("fill"));
                            	}
                            	int rx = Integer.parseInt(ellipse.getAttribute("rx"));
                            	int ry = Integer.parseInt(ellipse.getAttribute("ry"));
                            	Shape s = new Oval(cx,cy,stroke, strokewidth ,fill,ry,rx);
                            	temps.add(s);
                       		}
                       		else if (in1.getNodeName().equals("line")){
                       			Element line = (Element) in1;
                       			int x1 = Integer.parseInt(line.getAttribute("x1"));
                            	int y1 = Integer.parseInt(line.getAttribute("y1"));
                            	int x2 = Integer.parseInt(line.getAttribute("x2"));
                            	int y2 = Integer.parseInt(line.getAttribute("y2"));
                            	int strokewidth;
                            	if (!line.hasAttribute("stroke-width")){
                            		strokewidth = 1;
                            	}
                            	else
                            		strokewidth = Integer.parseInt(line.getAttribute("stroke-width"));
                            	
                            	Color stroke;
                            	if (line.getAttribute("fill").equals("none") || !line.hasAttribute("stroke")){
                            		if (stroke1 != null){
                            			stroke = stroke1;
                            		}
                            		else
                            			stroke = Color.BLACK;
                            	}
                            	else {
                            		if (isInteger(line.getAttribute("stroke"))){
                            			stroke = new Color(Integer.parseInt(line.getAttribute("stroke")));
                            		}
                            		else
                            			stroke = st.stringToColor(line.getAttribute("stroke"));
                            	}
                            	Shape s = new Line(x1,y1,x2,y2,strokewidth, stroke);
                            	temps.add(s);
                       		}
                       	}
                       	SVGGroups.add(temps);
               		}
               	}
               	
               	for (int j = 0; j < test1.getLength(); j++){
               		Node in1 = test1.item(j);
               		if (in1.getNodeName().equals("rect")){
               			Element rect = (Element) in1;
               			int x = Integer.parseInt(rect.getAttribute("x"));
                    	int y = Integer.parseInt(rect.getAttribute("y"));
                    	int width = Integer.parseInt(rect.getAttribute("width"));
                    	int height = Integer.parseInt(rect.getAttribute("height"));
                    	StyleSheet st = new StyleSheet();
                    	Color stroke;
                    	if (rect.getAttribute("stroke").equals("none") || !rect.hasAttribute("stroke")){
                    		stroke = Color.BLACK;
                    	}
                    	else {
                    		if (isInteger(rect.getAttribute("stroke"))){
                    			stroke = new Color(Integer.parseInt(rect.getAttribute("stroke")));
                    		}
                    		else
                    			stroke = st.stringToColor(rect.getAttribute("stroke"));
                    	}
                    	int strokewidth;
                    	if (!rect.hasAttribute("stroke-width")){
                    		strokewidth = 0;
                    	}
                    	else
                    		strokewidth = Integer.parseInt(rect.getAttribute("stroke-width"));
                    	Color fill;
                    	if (rect.getAttribute("fill").equals("none") || !rect.hasAttribute("fill")){
                    		fill = Color.WHITE;
                    	}
                    	else {
                    		if (isInteger(rect.getAttribute("fill"))){
                    			fill = new Color(Integer.parseInt(rect.getAttribute("fill")));
                    		}
                    		else
                    			fill = st.stringToColor(rect.getAttribute("fill"));
                    	}
                    	//int rx = Integer.parseInt(getTagValue7(element));
                    	
                    	Shape s = new Rect(x,y,width, height, stroke, strokewidth ,fill);
                    	SVGShapes.add(s);
               		}
               		else if (in1.getNodeName().equals("circle")){
               			Element circle = (Element) in1;
               			int cx = Integer.parseInt(circle.getAttribute("cx"));
                    	int cy = Integer.parseInt(circle.getAttribute("cy"));
                    	int r = Integer.parseInt(circle.getAttribute("r"));
                    	StyleSheet st = new StyleSheet();
                    	Color stroke;
                    	if (circle.getAttribute("stroke").equals("none") || !circle.hasAttribute("stroke")){
                    		stroke = Color.BLACK;
                    	}
                    	else {
                    		if (isInteger(circle.getAttribute("stroke"))){
                    			stroke = new Color(Integer.parseInt(circle.getAttribute("stroke")));
                    		}
                    		else
                    			stroke = st.stringToColor(circle.getAttribute("stroke"));
                    	}
                    	int strokewidth;
                    	if (!circle.hasAttribute("stroke-width")){
                    		strokewidth = 0;
                    	}
                    	else
                    		strokewidth = Integer.parseInt(circle.getAttribute("stroke-width"));
                    	
                    	Color fill;
                    	if (circle.getAttribute("fill").equals("none") || !circle.hasAttribute("fill")){
                    		fill = Color.BLACK;
                    	}
                    	else {
                    		if (isInteger(circle.getAttribute("fill"))){
                    			fill = new Color(Integer.parseInt(circle.getAttribute("fill")));
                    		}
                    		else
                    			fill = st.stringToColor(circle.getAttribute("fill"));
                    		fill = st.stringToColor(circle.getAttribute("fill"));
                    	}
                    	Shape s = new Oval(cx,cy,r,stroke, strokewidth ,fill);
                    	SVGShapes.add(s);
               		}
               		else if (in1.getNodeName().equals("ellipse")){
               			Element ellipse = (Element) in1;
               			int cx = Integer.parseInt(ellipse.getAttribute("cx"));
                    	int cy = Integer.parseInt(ellipse.getAttribute("cy"));
                    	StyleSheet st = new StyleSheet();
                    	Color stroke;
                    	if (ellipse.getAttribute("stroke").equals("none") || !ellipse.hasAttribute("stroke")){
                    		stroke = Color.BLACK;
                    	}
                    	else {
                    		if (isInteger(ellipse.getAttribute("stroke"))){
                    			stroke = new Color(Integer.parseInt(ellipse.getAttribute("stroke")));
                    		}
                    		else
                    			stroke = st.stringToColor(ellipse.getAttribute("stroke"));
                    	}
                    	int strokewidth;
                    	if (!ellipse.hasAttribute("stroke-width")){
                    		strokewidth = 0;
                    	}
                    	else
                    		strokewidth = Integer.parseInt(ellipse.getAttribute("stroke-width"));
                    	Color fill;
                    	if (ellipse.getAttribute("fill").equals("none") || !ellipse.hasAttribute("fill")){
                    		fill = Color.BLACK;
                    	}
                    	else {
                    		if (isInteger(ellipse.getAttribute("fill"))){
                    			fill = new Color(Integer.parseInt(ellipse.getAttribute("fill")));
                    		}
                    		else
                    			fill = st.stringToColor(ellipse.getAttribute("fill"));
                    	}
                    	int rx = Integer.parseInt(ellipse.getAttribute("rx"));
                    	int ry = Integer.parseInt(ellipse.getAttribute("ry"));
                    	Shape s = new Oval(cx,cy,stroke, strokewidth ,fill,ry,rx);
                    	SVGShapes.add(s);
               		}
               		else if (in1.getNodeName().equals("line")){
               			Element line = (Element) in1;
               			int x1 = Integer.parseInt(line.getAttribute("x1"));
                    	int y1 = Integer.parseInt(line.getAttribute("y1"));
                    	int x2 = Integer.parseInt(line.getAttribute("x2"));
                    	int y2 = Integer.parseInt(line.getAttribute("y2"));
                    	int strokewidth;
                    	if (!line.hasAttribute("stroke-width")){
                    		strokewidth = 1;
                    	}
                    	else
                    		strokewidth = Integer.parseInt(line.getAttribute("stroke-width"));
                    	
                    	StyleSheet st = new StyleSheet();
                    	Color stroke;
                    	if (line.getAttribute("fill").equals("none") || !line.hasAttribute("stroke")){
                    		stroke = Color.WHITE;
                    	}
                    	else {
                    		if (isInteger(line.getAttribute("stroke"))){
                    			stroke = new Color(Integer.parseInt(line.getAttribute("stroke")));
                    		}
                    		else
                    			stroke = st.stringToColor(line.getAttribute("stroke"));
                    	}
                    	Shape s = new Line(x1,y1,x2,y2,strokewidth, stroke);
                    	SVGShapes.add(s);
               		}
               	}
               	
           
            }
            }
		} catch (Exception e) {
		}
    }
    
    public static boolean isInteger( String input )  
    {  
       try  
       {  
          Integer.parseInt( input );  
          return true;  
       }  
       catch( Exception e )  
       {  
          return false;  
       }  
    }  
}
