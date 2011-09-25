package com.vaadin.graphics.canvas.widgetset.client.ui;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.event.dom.client.MouseDownEvent;
import com.google.gwt.event.dom.client.MouseMoveEvent;
import com.google.gwt.event.dom.client.MouseUpEvent;
import com.vaadin.terminal.gwt.client.UIDL;

public class VPort extends VArc {
	
	List<VConnector> inConnectors = new ArrayList<VConnector>();
	List<VConnector> outConnectors = new ArrayList<VConnector>();

	public VPort(UIDL uidl) {
		super(uidl);
	}
	
	public VPort(UIDL uidl, String id, String groupId) {
		super(uidl, id, groupId);
	}

	public VPort(double radius, VPoint centre) {
		super(radius, centre, 0, 2*Math.PI, false);
	}
	

	@Override
	protected void initiateConnectionEvent(MouseDownEvent event) {
		VPoint start = getCentre();
		VPoint end = new VPoint(event.getClientX() - 
				this.canvas.getAbsoluteLeft(), event.getClientY() - this.canvas.getAbsoluteTop());
		
		VConnector connector = new VConnector(start, end);
		connector.setColor(this.getColor());
		connector.setBorderWidth(this.getBorderWidth());
		connector.setHighlightedColor(this.getHighlightedColor());
		connector.setRole("CONNECTOR");
		this.outConnectors.add(connector);
		this.canvas.addChild(connector);
	}
	
	@Override
	protected void highlightConnectionEvent(MouseMoveEvent event) {
		for(VConnector connector: outConnectors){
			connector.setHighlighted(true);
		}
	}
	
	@Override
	protected void lowlightConnectionEvent(MouseMoveEvent event){
		for(VConnector connector: outConnectors){
			connector.setHighlighted(false);
		}
	}
	
	@Override
	protected void finalizeConnectionEvent(MouseUpEvent event) {
		
	}
	
	@Override
	protected void updateConnectorEvent(MouseMoveEvent event) {
		
	}
}
