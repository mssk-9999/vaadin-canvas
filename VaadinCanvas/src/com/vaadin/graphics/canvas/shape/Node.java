package com.vaadin.graphics.canvas.shape;

import java.util.Map;

public class Node extends ElementGroup {
	public static double connectorRadius = 5;
	
	public Node(double width, double height){
		super();
		this.center = new Point(width/2, height/2);
		Rect rect = new Rect(new Point(0, 0), new Point(width, height));
		Arc inConnector = new Arc(Node.connectorRadius, new Point(0, height/2), 0, 2*Math.PI, false);
		Arc outConnector = new Arc(Node.connectorRadius, new Point(width, height/2), 0, 2*Math.PI, false);
		this.elements.add(rect);
		this.elements.add(inConnector);
		this.elements.add(outConnector);
		this.relativePositions.add(new Point(width/2, height/2));
		this.relativePositions.add(new Point(0, height/2));
		this.relativePositions.add(new Point(width, height/2));
	}

	@Override
	public Map<String, Object> getDrawInstructions() {
		return null;
	}

}
