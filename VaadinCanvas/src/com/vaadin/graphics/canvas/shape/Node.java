package com.vaadin.graphics.canvas.shape;


public class Node extends ElementGroup {
	public static double connectorRadius = 5;
	
	public Node(double width, double height, Point center){
		super();
		this.center = center;
		Rect rect = new Rect(new Point(- width/2, - height/2), new Point(width/2, height/2));
		Arc inConnector = new Arc(Node.connectorRadius, new Point(0, 0), 0, 2*Math.PI, false);
		Arc outConnector = new Arc(Node.connectorRadius, new Point(0, 0), 0, 2*Math.PI, false);
		addElement(rect, new Point(0, 0));
		addElement(inConnector, new Point(-width/2, 0));
		addElement(outConnector, new Point(width/2, 0));
		this.relativePositions.add(new Point(center));
		this.relativePositions.add(new Point(0, height/2));
		this.relativePositions.add(new Point(width, height/2));
	}

}
