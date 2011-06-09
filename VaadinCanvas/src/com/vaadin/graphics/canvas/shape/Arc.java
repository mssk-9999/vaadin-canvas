/**
 * 
 */
package com.vaadin.graphics.canvas.shape;

import java.util.HashMap;
import java.util.Map;

import com.vaadin.graphics.event.MouseEvent;
import com.vaadin.graphics.event.MouseEvent.Type;
import com.vaadin.graphics.event.listener.MouseEventListener;

/**
 * @author kapil - kapil.verma@globallogic.com
 *
 */
public class Arc extends UIElement {
	
	private Point start;
	private Point end;
	private double radius;
	
	private Point centre;
	private double startAngle;
	private double endAngle;
	private boolean anticlockwise;

	
	
	
	public Arc(double radius, Point start, Point end) {
		super();
		this.start = start;
		this.end = end;
		this.radius = radius;
	}
	

	public Arc(double radius, Point centre, double startAngle, double endAngle,
			boolean anticlockwise) {
		super();
		this.radius = radius;
		this.centre = centre;
		this.startAngle = startAngle;
		this.endAngle = endAngle;
		this.anticlockwise = anticlockwise;
	}



	/* (non-Javadoc)
	 * @see com.vaadin.graphics.canvas.shape.UIElement#getDrawInstructions()
	 */
	@Override
	public Map<String, Object> getDrawInstructions() {
		Map<String, Object> arguments = new HashMap<String, Object>();
		
		arguments.put("elementid", getId());
		arguments.put("strokecolor", getColor());
		arguments.put("strokewidth", getBorderWidth());
		arguments.put("radius", getRadius());
		arguments.put("hascenter", this.centre != null);
		
		if(this.centre != null){
			arguments.put("centrex", centre.getX());
			arguments.put("centrey", centre.getY());
			arguments.put("startangle", startAngle);
			arguments.put("endangle", endAngle);
			arguments.put("anticlockwise", anticlockwise);
		}else{
			arguments.put("startx", getStart().getX());
			arguments.put("starty", getStart().getY());
			arguments.put("endx", getEnd().getX());
			arguments.put("endy", getEnd().getY());
		}
		
		arguments.put("fillstyle", getFillColor());
		arguments.put("elementtype", "arc");
		arguments.put("command", "draw");
		
		return arguments;
	}

	public Point getStart() {
		return start;
	}


	public void setStart(Point start) {
		this.start = start;
	}


	public Point getEnd() {
		return end;
	}


	public void setEnd(Point end) {
		this.end = end;
	}


	public double getRadius() {
		return radius;
	}


	public void setRadius(double radius) {
		this.radius = radius;
	}


	public Point getCentre() {
		return centre;
	}


	public void setCentre(Point centre) {
		this.centre = centre;
	}


	public double getStartAngle() {
		return startAngle;
	}


	public void setStartAngle(double startAngle) {
		this.startAngle = startAngle;
	}


	public double getEndAngle() {
		return endAngle;
	}


	public void setEndAngle(double endAngle) {
		this.endAngle = endAngle;
	}


	public boolean isAnticlockwise() {
		return anticlockwise;
	}


	public void setAnticlockwise(boolean anticlockwise) {
		this.anticlockwise = anticlockwise;
	}


	/* (non-Javadoc)
	 * @see com.vaadin.graphics.canvas.shape.UIElement#moveTo(com.vaadin.graphics.canvas.shape.Point)
	 */
	@Override
	public void moveTo(Point p) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see com.vaadin.graphics.canvas.shape.UIElement#getCenter()
	 */
	@Override
	public Point getCenter() {
		// TODO Auto-generated method stub
		if(this.centre != null){
			return this.centre;
		}
		return null;
	}

	/* (non-Javadoc)
	 * @see com.vaadin.graphics.canvas.shape.UIElement#contains(com.vaadin.graphics.canvas.shape.Point)
	 */
	@Override
	public boolean contains(Point p) {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see com.vaadin.graphics.canvas.shape.UIElement#addListener(com.vaadin.graphics.event.listener.MouseEventListener, com.vaadin.graphics.event.MouseEvent.Type)
	 */
	@Override
	public void addListener(MouseEventListener listener, Type eventType) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see com.vaadin.graphics.canvas.shape.UIElement#fireMouseEvent(com.vaadin.graphics.event.MouseEvent)
	 */
	@Override
	public void fireMouseEvent(MouseEvent event) {
		// TODO Auto-generated method stub

	}

}
