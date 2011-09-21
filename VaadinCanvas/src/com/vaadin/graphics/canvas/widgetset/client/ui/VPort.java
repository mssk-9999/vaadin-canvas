package com.vaadin.graphics.canvas.widgetset.client.ui;

import com.vaadin.terminal.gwt.client.UIDL;

public class VPort extends VArc {

	public VPort(UIDL uidl, String id, String groupId) {
		super(uidl, id, groupId);
	}

	public VPort(double radius, VPoint centre) {
		super(radius, centre, 0, 2*Math.PI, false);
	}
}
