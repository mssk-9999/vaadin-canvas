/**
 * 
 */
package com.vaadin.graphics.canvas.widgetset.client.ui;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gwt.canvas.dom.client.Context2d;
import com.google.gwt.event.dom.client.MouseMoveEvent;
import com.google.gwt.event.shared.EventHandler;
import com.vaadin.terminal.gwt.client.UIDL;

/**
 * @author kapil - kapil.verma@globallogic.com
 *
 */
public class VPolygon extends VUIElement {

	private VPoint[] vertices;

	EventHandler handler;
//	
	private Map<String, List<EventHandler>> handlers = new HashMap<String, List<EventHandler>>();
//	
	
	public VPolygon(UIDL uidl){
		this.setId(uidl.getStringAttribute("elementid"));
		this.setGroupId(uidl.getStringAttribute("groupid"));
		update(uidl);
	}
	
	public VPolygon(VPoint[] vertices){
		this.vertices = vertices;
		
	}
	
	/*public interface MouseEventListener{
		public void onMouseEvent(MouseEvent event);
	}*/
	
	public VPolygon(UIDL uidl, String id, String groupId) {
		this.setId(id);
		this.setGroupId(groupId);
		update(uidl);
	}

	/* (non-Javadoc)
	 * @see com.workflow.ivr.web.model.VUIElement#draw()
	 */
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
		context.moveTo(vertices[0].getX(), vertices[0].getY());
		int i=1;
		for(;i<vertices.length ; i++){
			context.lineTo(vertices[i].getX(), vertices[i].getY());
		}
		
		context.closePath();
		context.stroke();
		
		if(getFillColor().length() > 0){
			context.fill();
		}
		
		context.restore();
	}

	/* (non-Javadoc)
	 * @see com.workflow.ivr.web.model.VUIElement#getCenterX()
	 */
	public VPoint getCenter() {
		VPoint sum = new VPoint(0, 0);
		for(int i = 0; i < vertices.length; i++){
			sum = VPoint.add(sum, vertices[i]);
		}
		return VPoint.mult(sum, 1/vertices.length);
	}

	/* (non-Javadoc)
	 * @see com.workflow.ivr.web.model.VUIElement#moveTo(double, double)
	 */
	public void moveTo(VPoint p) {
		
	}
	
	/* (non-Javadoc)
	 * @see com.ui.model.VUIElement#contains(double, double)
	 */
	public boolean contains(VPoint p) {
		return VUIElement.pointInPolygon(vertices, p);
	}
	/* (non-Javadoc)
	 * @see com.ui.model.VUIElement#addListener(com.vaadin.ui.Component.Listener)
	 */
//	public void addListener(MouseEventListener listener, MouseEvent.Type eventType) {
//		
//	}
	/* (non-Javadoc)
	 * @see com.ui.model.VUIElement#fireMouseEvent(com.vaadin.event.MouseEvents)
	 */
//	public void fireEvent(MouseEvent<EventHandler> event) {
//		
//	}


	/* (non-Javadoc)
	 * @see com.vaadin.graphics.canvas.widgetset.client.ui.VUIElement#update(com.google.gwt.canvas.dom.client.Context2d, com.vaadin.terminal.gwt.client.UIDL)
	 */
	@Override
	public void update(UIDL uidl) {
		String prefix = getPrefix();
		
		String strokecolor = uidl.getStringAttribute(prefix + "strokecolor");
		int strokewidth = uidl.getIntAttribute(prefix + "strokewidth");
		int numOfVertices = uidl.getIntAttribute(prefix + "numberofvertices");
		String fillStyleColor = uidl.getStringAttribute(prefix + "fillstyle");
		
		this.vertices = new VPoint[numOfVertices];
		for(int i=0; i< numOfVertices; i++){
			this.vertices[i] = new VPoint(uidl.getDoubleAttribute(prefix + "x" + i), uidl.getDoubleAttribute(prefix + "y" + i));
		}
		
		setColor(strokecolor);
		setBorderWidth(strokewidth);
		setFillColor(fillStyleColor);
	}
	
	protected void processMoveEvent(MouseMoveEvent event){
		double deltaX = event.getClientX() - this.getMouseDownPoint().getX();
		double deltaY = event.getClientY() - this.getMouseDownPoint().getY();
		
		for(VPoint p: vertices){
			p.add(deltaX, deltaY);
		}
	}

}
