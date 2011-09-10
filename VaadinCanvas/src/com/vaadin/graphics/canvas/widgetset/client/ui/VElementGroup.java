package com.vaadin.graphics.canvas.widgetset.client.ui;

import java.util.HashMap;
import java.util.Map;

import com.google.gwt.canvas.dom.client.Context2d;
import com.google.gwt.event.dom.client.MouseDownEvent;
import com.google.gwt.event.dom.client.MouseMoveEvent;
import com.google.gwt.event.dom.client.MouseOutEvent;
import com.google.gwt.event.dom.client.MouseOverEvent;
import com.google.gwt.event.dom.client.MouseUpEvent;
import com.vaadin.terminal.gwt.client.UIDL;

public class VElementGroup extends VUIElement {

	private String[] elementList;
	
	private Map <String, VUIElement> elements;
	
	private String mainElementId;

	public VElementGroup(){
		super();
		elements = new HashMap<String, VUIElement>();
	}
	
	public VElementGroup(UIDL uidl, VCanvas canvas){
		elements = new HashMap<String, VUIElement>();
		this.setId(uidl.getStringAttribute("elementid"));
		this.setGroupId(uidl.getStringAttribute("groupId"));
		this.canvas = canvas;
		this.update(uidl);
	}
	
	public VElementGroup(UIDL uidl, String id, String groupId, VCanvas canvas) {
		elements = new HashMap<String, VUIElement>();
		this.setId(id);
		this.setGroupId(groupId);
		this.canvas = canvas;
		this.update(uidl);
	}

	@Override
	public void moveTo(VPoint p) {
		
	}

	@Override
	public VPoint getCenter() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void draw(Context2d context) {
		for(String elementId : elementList){
			VUIElement elem = elements.get(elementId);
			elem.draw(context);
		}
	}

	@Override
	public boolean contains(VPoint p) {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public void setHighlighted(boolean highlighted) {
		super.setHighlighted(highlighted);
		
		for(String elementId : elementList){
			elements.get(elementId).setHighlighted(highlighted);
		}
	}
	
	@Override
	public void setSelected(boolean highlighted) {
		super.setSelected(highlighted);
		
		for(String elementId : elementList){
			elements.get(elementId).setSelected(highlighted);
		}
	}

	@Override
	public void update(UIDL uidl) {
		this.elementList = uidl.getStringArrayAttribute(getPrefix() + "elementlist");
		this.mainElementId = uidl.getStringAttribute(getPrefix() + "mainelementid");
		
		for(String elementId : elementList){
			if(elements.get(elementId) != null){
				VUIElement elem = elements.get(elementId);
				elem.update(uidl);
			}else{
				VUIElement elem = VUIElement.createFromUIDL(uidl, elementId, getId(), this.canvas);
				elements.put(elementId, elem);
			}
		}
	}

	public void processMouseOutEvent(MouseOutEvent event) {
		VPoint p = new VPoint(event.getClientX() - this.canvas.getAbsoluteLeft(), event.getClientY() - this.canvas.getAbsoluteTop());
		VUIElement mainElement = this.elements.get(this.mainElementId);
		if(mainElement.contains(p)){
			this.setHighlighted(false);
			this.setMouseOutPoint(new VPoint(event.getClientX(), event.getClientY()));
		}
		
	}

	public void processMouseOverEvent(MouseOverEvent event) {
		VPoint p = new VPoint(event.getClientX() - this.canvas.getAbsoluteLeft(), event.getClientY() - this.canvas.getAbsoluteTop());
		VUIElement mainElement = this.elements.get(this.mainElementId);
		if(mainElement.contains(p)){
			this.setHighlighted(true);
			this.setMouseOverPoint(new VPoint(event.getClientX(), event.getClientY()));
		}
		
	}

	public void processMouseUpEvent(MouseUpEvent event) {
		VPoint p = new VPoint(event.getClientX() - this.canvas.getAbsoluteLeft(), event.getClientY() - this.canvas.getAbsoluteTop());
		VUIElement mainElement = this.elements.get(this.mainElementId);
		if(mainElement.contains(p)){
			this.setSelected(false);
			this.setMouseUpPoint(new VPoint(event.getClientX(), event.getClientY()));
		}
		
	}

	public void processMouseDownEvent(MouseDownEvent event) {
		VPoint p = new VPoint(event.getClientX() - this.canvas.getAbsoluteLeft(), event.getClientY() - this.canvas.getAbsoluteTop());
		VUIElement mainElement = this.elements.get(this.mainElementId);
		if(mainElement.contains(p)){
			this.setSelected(true);
			this.setMouseDownPoint(new VPoint(event.getClientX(), event.getClientY()));
		}
		
	}
	
	public void moveBy(VPoint delta){
		for(String elementId : elementList){
			VUIElement elem = elements.get(elementId);
			elem.moveBy(delta);
		}
	}
	
	@Override
	protected void processMoveEvent(MouseMoveEvent event) {
		VUIElement mainElement = this.elements.get(this.mainElementId);
		VPoint p = new VPoint(event.getClientX() - mainElement.canvas.getAbsoluteLeft(), event.getClientY() 
				- mainElement.canvas.getAbsoluteTop());
		if(mainElement.contains(p)){
			double deltaX = event.getClientX() - this.getMouseDownPoint().getX();
			double deltaY = event.getClientY() - this.getMouseDownPoint().getY();
			VPoint delta = new VPoint(deltaX, deltaY);
			
			if(this.isSelected()){
				moveBy(delta);
			}
			this.mouseDownPoint = new VPoint(event.getClientX(), event.getClientY());
		}

	}
	
	public String getSelectedColor() {
		return super.getSelectedColor();
	}

	public void setSelectedColor(String selectedColor) {
		super.setSelectedColor(selectedColor);
		for(String elementId : elementList){
			VUIElement elem = elements.get(elementId);
			elem.setSelectedColor(selectedColor);
		}
	}

	public String getSelectedFillColor() {
		return super.getSelectedFillColor();
	}

	public void setSelectedFillColor(String selectedFillColor) {
		super.setSelectedFillColor(selectedFillColor);
		for(String elementId : elementList){
			VUIElement elem = elements.get(elementId);
			elem.setSelectedFillColor(selectedFillColor);
		}
	}

	public String getHighlightedColor() {
		return super.getHighlightedColor();
	}

	public void setHighlightedColor(String highlightedColor) {
		super.setHighlightedFillColor(highlightedColor);
		for(String elementId : elementList){
			VUIElement elem = elements.get(elementId);
			elem.setHighlightedColor(highlightedColor);
		}
	}

	public String getHighlightedFillColor() {
		return super.getHighlightedFillColor();
	}

	public void setHighlightedFillColor(String highlightedFillColor) {
		super.setHighlightedFillColor(highlightedFillColor);
		for(String elementId : elementList){
			VUIElement elem = elements.get(elementId);
			elem.setHighlightedFillColor(highlightedFillColor);
		}
	}

}
