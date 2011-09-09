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
		// TODO Auto-generated method stub
		
	}

	public void processMouseOverEvent(MouseOverEvent event) {
		// TODO Auto-generated method stub
		
	}

	public void processMouseUpEvent(MouseUpEvent event) {
		// TODO Auto-generated method stub
		
	}

	public void processMouseDownEvent(MouseDownEvent event) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	protected void processMoveEvent(MouseMoveEvent event) {
		VUIElement mainElement = this.elements.get(this.mainElementId);
		VPoint p = new VPoint(event.getClientX() - mainElement.canvas.getAbsoluteLeft(), event.getClientY() 
				- mainElement.canvas.getAbsoluteTop());
		if(mainElement.contains(p)){
			if(this.isSelected()){
				for(String elementId : elementList){
					VUIElement elem = elements.get(elementId);
					elem.processMoveEvent(event);
				}
			}
			this.mouseDownPoint = new VPoint(event.getClientX(), event.getClientY());
		}

	}

}
