package com.vaadin.graphics.canvas.shape;

public class Connector extends Line {
	
	private Port fromPort;
	private Port toPort;

	public Connector(Point start, Point end) {
		super(start, end);
		this.setRole(ElementRole.CONNECTOR);
	}
	
	public Connector(Port fromPort, Port toPort) {
		super(fromPort.getCenter(), toPort.getCenter());
		this.fromPort = fromPort;
		this.toPort = toPort;
		this.setRole(ElementRole.CONNECTOR);
	}

	public Port getFromPort() {
		return fromPort;
	}

	public void setFromPort(Port fromPort) {
		this.fromPort = fromPort;
		this.start = fromPort.getCenter();
	}

	public Port getToPort() {
		return toPort;
	}

	public void setToPort(Port toPort) {
		this.toPort = toPort;
		this.end = toPort.getCenter();
	}
}
