package com.vaadin.graphics.canvas.widgetset.client.ui;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gwt.canvas.dom.client.Context2d;
import com.google.gwt.event.dom.client.MouseMoveEvent;
import com.google.gwt.event.shared.EventHandler;
import com.vaadin.terminal.gwt.client.UIDL;

public class VLine extends VUIElement {
	
	private VPoint start;

	private VPoint end;
	
	EventHandler handler;

	private Map<String, List<EventHandler>> handlers = new HashMap<String, List<EventHandler>>();
	
	public VLine(UIDL uidl){
		this.setId(uidl.getStringAttribute("elementid"));
		this.setGroupId(uidl.getStringAttribute("groupid"));
		update(uidl);
	}
	
	public VLine(UIDL uidl, String id, String groupId){
		this.setId(id);
		this.setGroupId(groupId);
		update(uidl);
	}
	
	public VLine(VPoint start, VPoint end){
		this.start = start;
		this.end = end;
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
		context.moveTo(start.getX(), start.getY());
		context.beginPath();
		context.lineTo(end.getX(), end.getY());
		context.closePath();
		
		/*if(getFillColor().length() > 0){
			context.fillRect(start.getX(), start.getY(), end.getX()-start.getX(), end.getY()-start.getY());
		}*/
		
		context.restore();
	}

	@Override
	protected void processMoveEvent(MouseMoveEvent event) {
		double deltaX = event.getClientX() - this.getMouseDownPoint().getX();
		double deltaY = event.getClientY() - this.getMouseDownPoint().getY();
		
		this.start.add(deltaX, deltaY);
		this.end.add(deltaX, deltaY);
	}

	@Override
	public void moveTo(VPoint p) {
		VPoint delta = VPoint.sub(p, getCenter());
		moveBy(delta);
	}

	@Override
	public VPoint getCenter() {
		return VPoint.mult(VPoint.add(start, end), 0.5);
	}

	@Override
	public boolean contains(VPoint p) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void update(UIDL uidl) {
		String prefix = getPrefix();
		
		String strokecolor = uidl.getStringAttribute(prefix + "strokecolor");
		int strokewidth = uidl.getIntAttribute(prefix + "strokewidth");
		double startX = uidl.getDoubleAttribute(prefix + "startx");
		double startY = uidl.getDoubleAttribute(prefix + "starty");
		double endX = uidl.getDoubleAttribute(prefix + "endx");
		double endY = uidl.getDoubleAttribute(prefix + "endy");
		String fillStyleColor = uidl.getStringAttribute(prefix + "fillstyle");
		String selectedColor = uidl.getStringAttribute(prefix + "selectedcolor");
		String selectedFillColor = uidl.getStringAttribute(prefix + "selectedfillcolor");
		String highlightedColor = uidl.getStringAttribute(prefix + "highlightedcolor");
		String highlightedFillColor = uidl.getStringAttribute(prefix + "highlightedfillcolor");
		
		this.setRole(uidl.getStringAttribute(getPrefix() + "role"));
		
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
		
		setColor(strokecolor);
		setBorderWidth(strokewidth);
		setStart(new VPoint(startX, startY));
		setEnd(new VPoint(endX, endY));
		setFillColor(fillStyleColor);
	}
	

	@Override
	public void moveBy(VPoint delta) {
		start = VPoint.add(start, delta);
		end = VPoint.add(end, delta);
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

}
