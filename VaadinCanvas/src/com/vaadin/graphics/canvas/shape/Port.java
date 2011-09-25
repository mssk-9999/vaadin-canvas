package com.vaadin.graphics.canvas.shape;


public abstract class Port extends Arc {

	public Port(double radius, Point centre) {
		super(radius, centre, 0, 2*Math.PI, false);
	}
	
}
