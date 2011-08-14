package com.vaadin.graphics.canvas.widgetset.client.ui;

import com.google.gwt.canvas.dom.client.Context2d;
import com.google.gwt.event.dom.client.MouseMoveEvent;
import com.vaadin.terminal.gwt.client.UIDL;

public class VElementGroup extends VUIElement {

	private String[] elementList;

	@Override
	protected void processMoveEvent(MouseMoveEvent event) {
		// TODO Auto-generated method stub

	}

	@Override
	public void moveTo(VPoint p) {
		// TODO Auto-generated method stub

	}

	@Override
	public VPoint getCenter() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void draw(Context2d context) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean contains(VPoint p) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void update(UIDL uidl) {
		this.groupId = uidl.getStringAttribute("groupId");
		this.elementList = uidl.getStringArrayAttribute("elementList");
	}

}
