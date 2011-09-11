package com.vaadin.graphics.canvas.widgetset.client.ui;

import com.google.gwt.canvas.dom.client.Context2d;
import com.google.gwt.canvas.dom.client.Context2d.TextAlign;
import com.google.gwt.event.dom.client.MouseMoveEvent;
import com.vaadin.terminal.gwt.client.UIDL;

public class VText extends VUIElement {
	
	private String text;
	private VPoint point;
	private double maxWidth;
	private String alignment;
	
	public VText(UIDL uidl) {
		this.setId(uidl.getStringAttribute("elementid"));
		this.setGroupId(uidl.getStringAttribute("groupid"));
		update(uidl);
	}
	
	public VText(UIDL uidl, VCanvas canvas) {
		this.setId(uidl.getStringAttribute("elementid"));
		this.setGroupId(uidl.getStringAttribute("groupid"));
		this.canvas = canvas;
		update(uidl);
	}

	public VText(UIDL uidl, String id, String groupId) {
		this.setId(id);
		this.setGroupId(groupId);
		update(uidl);
	}

	@Override
	protected void processMoveEvent(MouseMoveEvent event) {
		double deltaX = event.getClientX() - this.getMouseDownPoint().getX();
		double deltaY = event.getClientY() - this.getMouseDownPoint().getY();
		this.point.add(deltaX, deltaY);
	}

	@Override
	public void moveTo(VPoint p) {
		this.point = p;
	}

	@Override
	public VPoint getCenter() {
		return null;
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
		
		if(alignment != null && alignment.length() > 0){
			if(alignment.equals("LEFT")){
				context.setTextAlign(TextAlign.LEFT);
			}else if(alignment.equals("RIGHT")){
				context.setTextAlign(TextAlign.RIGHT);
			}else if(alignment.equals("START")){
				context.setTextAlign(TextAlign.START);
			}else if(alignment.equals("END")){
				context.setTextAlign(TextAlign.END);
			}else if(alignment.equals("CENTER")){
				context.setTextAlign(TextAlign.CENTER);
			}
		}
		
		if(maxWidth > 0){
			context.strokeText(text, point.getX(), point.getY(), maxWidth);
		}else{
			context.strokeText(text, point.getX(), point.getY());
		}
		context.closePath();
		
		if(getFillColor() != null && getFillColor().length() > 0){
			if(maxWidth > 0){
				context.fillText(text, point.getX(), point.getY(), maxWidth);
			}else{
				context.fillText(text, point.getX(), point.getY());
			}
		}
		
		context.restore();
		
	}

	@Override
	public boolean contains(VPoint p) {
		return false;
	}

	@Override
	public void update(UIDL uidl) {
		String prefix = getPrefix();
		
		String strokecolor = uidl.getStringAttribute(prefix + "strokecolor");
		int strokewidth = uidl.getIntAttribute(prefix + "strokewidth");
		String fillStyleColor = uidl.getStringAttribute(prefix + "fillstyle");
		String selectedColor = uidl.getStringAttribute(prefix + "selectedcolor");
		String selectedFillColor = uidl.getStringAttribute(prefix + "selectedfillcolor");
		String highlightedColor = uidl.getStringAttribute(prefix + "highlightedcolor");
		String highlightedFillColor = uidl.getStringAttribute(prefix + "highlightedfillcolor");
		String text = uidl.getStringAttribute(prefix + "text");
		alignment = uidl.getStringAttribute(prefix + "alignment");
		double x = uidl.getDoubleAttribute(prefix + "x");
		double y = uidl.getDoubleAttribute(prefix + "y");
		double maxWidth = uidl.getDoubleAttribute(prefix + "maxwidth");
		
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
		
		setPoint(new VPoint(x, y));
		setText(text);
		setColor(strokecolor);
		setBorderWidth(strokewidth);
		setFillColor(fillStyleColor);
		setMaxWidth(maxWidth);
	}

	public void setMaxWidth(double maxWidth) {
		this.maxWidth = maxWidth;
	}

	public void setPoint(VPoint point) {
		this.point = point;
	}

	public void setText(String text) {
		this.text = text;
	}
	
	public String getText() {
		return this.text;
	}

	@Override
	public void moveBy(VPoint delta) {
		this.point = VPoint.add(this.point, delta);
	}

}
