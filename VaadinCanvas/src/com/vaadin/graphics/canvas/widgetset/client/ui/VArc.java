package com.vaadin.graphics.canvas.widgetset.client.ui;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gwt.canvas.dom.client.Context2d;
import com.google.gwt.event.dom.client.MouseMoveEvent;
import com.google.gwt.event.shared.EventHandler;
import com.vaadin.terminal.gwt.client.UIDL;

public class VArc extends VUIElement {

	private VPoint start;
	private VPoint end;
	private double radius;
	
	private VPoint centre;
	private double startAngle;
	private double endAngle;
	private boolean anticlockwise;
	
	EventHandler handler;
//	
	private Map<String, List<EventHandler>> handlers = new HashMap<String, List<EventHandler>>();
	
	public VArc(UIDL uidl) {
		update(uidl);
		this.setId(uidl.getStringAttribute("elementid"));
	}
	
	public VArc(double radius, VPoint start, VPoint end) {
		super();
		this.start = start;
		this.end = end;
		this.radius = radius;
	}
	
	public VArc(double radius, VPoint centre, double startAngle, double endAngle,
			boolean anticlockwise) {
		super();
		this.radius = radius;
		this.centre = centre;
		this.startAngle = startAngle;
		this.endAngle = endAngle;
		this.anticlockwise = anticlockwise;
	}

	@Override
	public VPoint getCenter() {
		// TODO Auto-generated method stub
		return null;
	}

	public void setCentre(VPoint centre){
		this.centre = centre;
	}
	
	public void setStartAngle(double startAngle){
		this.startAngle = startAngle;
	}
	
	public void setEndAngle(double endAngle){
		this.endAngle = endAngle;
	}

	public VPoint getStart() {
		return start;
	}

	public void setStart(VPoint start) {
		this.start = start;
	}

	public VPoint getEnd() {
		return end;
	}

	public void setEnd(VPoint end) {
		this.end = end;
	}

	public double getRadius() {
		return radius;
	}

	public void setRadius(double radius) {
		this.radius = radius;
	}

	public boolean isAnticlockwise() {
		return anticlockwise;
	}

	public void setAnticlockwise(boolean anticlockwise) {
		this.anticlockwise = anticlockwise;
	}

	public VPoint getCentre() {
		return centre;
	}

	public double getStartAngle() {
		return startAngle;
	}

	public double getEndAngle() {
		return endAngle;
	}
	
	@Override
	protected void processMoveEvent(MouseMoveEvent event) {
		double deltaX = event.getClientX() - this.getMouseDownPoint().getX();
		double deltaY = event.getClientY() - this.getMouseDownPoint().getY();

		if(this.centre != null){
			this.centre.add(deltaX, deltaY);
		}else{
			this.start.add(deltaX, deltaY);
			this.end.add(deltaX, deltaY);
		}
	}

	@Override
	public void moveTo(VPoint p) {
		// TODO Auto-generated method stub

	}

	@Override
	public void draw(Context2d context) {
		context.save();

		if(getColor() != null && getColor().length() > 0){
			context.setStrokeStyle(getColor());
		}
		if(getBorderWidth() > 0){
			context.setLineWidth(getBorderWidth());
		}
		if(getFillColor() != null && getFillColor().length() > 0){
			context.setFillStyle(getFillColor());
		}
		context.beginPath();
		if(centre != null){
			context.arc(centre.getX(), centre.getY(), radius, startAngle, endAngle, anticlockwise);
		}else{
			context.arcTo(start.getX(), start.getY(), end.getX(), end.getY(), radius);
		}
		
		if(getFillColor().length() > 0){
			context.closePath();
			context.fill();
		}
		context.restore();
	}

	@Override
	public boolean contains(VPoint p) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void update(UIDL uidl) {
		String strokecolor = uidl.getStringAttribute("strokecolor");
		int strokewidth = uidl.getIntAttribute("strokewidth");
		double radius = uidl.getDoubleAttribute("radius");
		boolean hascenter = uidl.getBooleanAttribute("hascenter");
		String fillStyleColor = uidl.getStringAttribute("fillstyle");
		
		VPoint centre = null;
		
		if(hascenter){
			double centreX = uidl.getDoubleAttribute("centrex");
			double centreY = uidl.getDoubleAttribute("centrey");
			double startAngle = uidl.getDoubleAttribute("startangle");
			double endAngle = uidl.getDoubleAttribute("endangle");
			boolean anticlockwise = uidl.getBooleanAttribute("anticlockwise");
			
			centre = new VPoint(centreX, centreY);
			setStartAngle(startAngle);
			setEndAngle(endAngle);
			setAnticlockwise(anticlockwise);
		}else{
			double startX = uidl.getDoubleAttribute("startx");
			double startY = uidl.getDoubleAttribute("starty");
			double endX = uidl.getDoubleAttribute("endx");
			double endY = uidl.getDoubleAttribute("endy");
			
			setStart(new VPoint(startX, startY));
			setEnd(new VPoint(endX, endY));
		}
		
		setCentre(centre);
		setColor(strokecolor);
		setBorderWidth(strokewidth);
		setRadius(radius);
		setFillColor(fillStyleColor);
	}

}
