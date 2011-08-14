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
class VRect extends VUIElement {

	private VPoint start;

	private VPoint end;
	
	EventHandler handler;
//	
	private Map<String, List<EventHandler>> handlers = new HashMap<String, List<EventHandler>>();
//	
	
	public VRect(UIDL uidl){
		update(uidl);
		this.setId(uidl.getStringAttribute("elementid"));
	}
	
	public VRect(VPoint start, VPoint end){
		this.start = start;
		this.end = end;
		
	}
	
	/*public interface MouseEventListener{
		public void onMouseEvent(MouseEvent event);
	}*/
	
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
		context.strokeRect(start.getX(), start.getY(), end.getX()-start.getX(), end.getY()-start.getY());
		context.closePath();
		
		if(getFillColor().length() > 0){
			context.fillRect(start.getX(), start.getY(), end.getX()-start.getX(), end.getY()-start.getY());
		}
		
		context.restore();
	}

	/* (non-Javadoc)
	 * @see com.workflow.ivr.web.model.VUIElement#getCenterX()
	 */
	public VPoint getCenter() {
		return VPoint.mult(VPoint.add(start, end), 0.5);
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
		return start.getX() <= p.getX() && p.getX() <= end.getX() && start.getY() <= p.getY() && p.getY() <= end.getY();
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

	/* (non-Javadoc)
	 * @see com.vaadin.graphics.canvas.widgetset.client.ui.VUIElement#update(com.google.gwt.canvas.dom.client.Context2d, com.vaadin.terminal.gwt.client.UIDL)
	 */
	@Override
	public void update(UIDL uidl) {
		String prefix = "";
		if(groupId.length() != 0){
			prefix = groupId + ".";
		}
		
		String strokecolor = uidl.getStringAttribute(prefix + "strokecolor");
		int strokewidth = uidl.getIntAttribute(prefix + "strokewidth");
		double startX = uidl.getDoubleAttribute(prefix + "startx");
		double startY = uidl.getDoubleAttribute(prefix + "starty");
		double endX = uidl.getDoubleAttribute(prefix + "endx");
		double endY = uidl.getDoubleAttribute(prefix + "endy");
		String fillStyleColor = uidl.getStringAttribute(prefix + "fillstyle");
		
		setColor(strokecolor);
		setBorderWidth(strokewidth);
		setStart(new VPoint(startX, startY));
		setEnd(new VPoint(endX, endY));
		setFillColor(fillStyleColor);
	}
	
	protected void processMoveEvent(MouseMoveEvent event){
		double deltaX = event.getClientX() - this.getMouseDownPoint().getX();
		double deltaY = event.getClientY() - this.getMouseDownPoint().getY();
		
		this.start.add(deltaX, deltaY);
		this.end.add(deltaX, deltaY);
	}

}
