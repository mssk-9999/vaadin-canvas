package com.vaadin.graphics.canvas.widgetset.client.ui;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gwt.canvas.dom.client.Context2d;
import com.google.gwt.event.dom.client.MouseDownEvent;
import com.google.gwt.event.dom.client.MouseMoveEvent;
import com.google.gwt.event.dom.client.MouseUpEvent;
import com.google.gwt.event.shared.EventHandler;
import com.vaadin.graphics.canvas.shape.Point;
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
		this.setId(uidl.getStringAttribute("elementid"));
		this.setGroupId(uidl.getStringAttribute("groupid"));
		update(uidl);
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

	public VArc(UIDL uidl, String id, String groupId) {
		this.setId(id);
		this.setGroupId(groupId);
		update(uidl);
	}

	@Override
	public VPoint getCenter() {
		return centre;
	}

	public void setCentre(VPoint centre){
		this.centre = centre;
		setChanged(true);
	}
	
	public void setStartAngle(double startAngle){
		this.startAngle = startAngle;
		setChanged(true);
	}
	
	public void setEndAngle(double endAngle){
		this.endAngle = endAngle;
		setChanged(true);
	}

	public VPoint getStart() {
		return start;
	}

	public void setStart(VPoint start) {
		this.start = start;
		setChanged(true);
	}

	public VPoint getEnd() {
		return end;
	}

	public void setEnd(VPoint end) {
		this.end = end;
		setChanged(true);
	}

	public double getRadius() {
		return radius;
	}

	public void setRadius(double radius) {
		this.radius = radius;
		setChanged(true);
	}

	public boolean isAnticlockwise() {
		return anticlockwise;
	}

	public void setAnticlockwise(boolean anticlockwise) {
		this.anticlockwise = anticlockwise;
		setChanged(true);
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
		setChanged(true);
	}

	@Override
	public void moveTo(VPoint p) {
		// TODO Auto-generated method stub
		setChanged(true);
	}

	@Override
	public void draw(Context2d context) {
		context.save();

		if(this.isSelected()){
			context.setStrokeStyle(getSelectedColor());
		}else if(this.isHighlighted()){
			context.setStrokeStyle(getHighlightedColor());
		}else if(getColor() != null && getColor().length() > 0){
			context.setStrokeStyle(getColor());
		}
		if(getBorderWidth() > 0){
			context.setLineWidth(getBorderWidth());
		}
		
		if(this.isSelected()){
			context.setFillStyle(getSelectedFillColor());
		}else if(this.isHighlighted()){
			context.setFillStyle(getHighlightedFillColor());
		}else if(getFillColor() != null && getFillColor().length() > 0){
			context.setFillStyle(getFillColor());
		}
		context.beginPath();
		if(centre != null){
			context.arc(centre.getX(), centre.getY(), radius, startAngle, endAngle, anticlockwise);
		}else{
			context.arcTo(start.getX(), start.getY(), end.getX(), end.getY(), radius);
		}
		context.stroke();
		
		if(getFillColor().length() > 0){
			context.closePath();
			context.fill();
		}
		context.restore();
		setChanged(false);
	}

	@Override
	public boolean contains(VPoint p) {
		if(Math.abs(endAngle - startAngle) == 2*Math.PI){
			if(this.centre != null){
				double distanceSqr = (p.getX() - this.centre.getX())*(p.getX() - this.centre.getX())
						+ (p.getY() - this.centre.getY())*(p.getY() - this.centre.getY());
				return distanceSqr <= this.radius * this.radius;
			}
		}
		return false;
	}

	@Override
	public void update(UIDL uidl) {
		String prefix = getPrefix();
		
		String strokecolor = uidl.getStringAttribute(prefix + "strokecolor");
		int strokewidth = uidl.getIntAttribute(prefix + "strokewidth");
		double radius = uidl.getDoubleAttribute(prefix + "radius");
		boolean hascenter = uidl.getBooleanAttribute(prefix + "hascenter");
		String fillStyleColor = uidl.getStringAttribute(prefix + "fillstyle");
		String selectedColor = uidl.getStringAttribute(prefix + "selectedcolor");
		String selectedFillColor = uidl.getStringAttribute(prefix + "selectedfillcolor");
		String highlightedColor = uidl.getStringAttribute(prefix + "highlightedcolor");
		String highlightedFillColor = uidl.getStringAttribute(prefix + "highlightedfillcolor");
		this.setRole(uidl.getStringAttribute(getPrefix() + "role"));
		
		VPoint centre = null;
		
		if(hascenter){
			double centreX = uidl.getDoubleAttribute(prefix + "centrex");
			double centreY = uidl.getDoubleAttribute(prefix + "centrey");
			double startAngle = uidl.getDoubleAttribute(prefix + "startangle");
			double endAngle = uidl.getDoubleAttribute(prefix + "endangle");
			boolean anticlockwise = uidl.getBooleanAttribute(prefix + "anticlockwise");
			
			centre = new VPoint(centreX, centreY);
			setStartAngle(startAngle);
			setEndAngle(endAngle);
			setAnticlockwise(anticlockwise);
		}else{
			double startX = uidl.getDoubleAttribute(prefix + "startx");
			double startY = uidl.getDoubleAttribute(prefix + "starty");
			double endX = uidl.getDoubleAttribute(prefix + "endx");
			double endY = uidl.getDoubleAttribute(prefix + "endy");
			
			setStart(new VPoint(startX, startY));
			setEnd(new VPoint(endX, endY));
		}
		
		if(selectedColor.length() > 0){
			this.setSelectedColor(selectedColor);
		}
		
		if(selectedFillColor.length() > 0){
			this.setSelectedFillColor(selectedFillColor);
		}
		
		if(highlightedColor.length() > 0){
			this.setHighlightedColor(highlightedColor);
		}
		
		if(highlightedFillColor.length() > 0){
			this.setHighlightedFillColor(highlightedFillColor);
		}
		
		setCentre(centre);
		setColor(strokecolor);
		setBorderWidth(strokewidth);
		setRadius(radius);
		setFillColor(fillStyleColor);
		setChanged(true);
	}

	@Override
	public void moveBy(VPoint delta) {
		if(this.centre != null){
			centre = VPoint.add(centre, delta);
		}else{
			start = VPoint.add(start, delta);
			end = VPoint.add(end, delta);
		}
		setChanged(true);
	}
	
}
