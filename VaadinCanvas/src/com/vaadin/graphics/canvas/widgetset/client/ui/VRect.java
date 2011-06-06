/**
 * 
 */
package com.vaadin.graphics.canvas.widgetset.client.ui;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gwt.canvas.dom.client.Context2d;
import com.google.gwt.event.dom.client.DomEvent;
import com.google.gwt.event.dom.client.MouseDownEvent;
import com.google.gwt.event.dom.client.MouseEvent;
import com.google.gwt.event.dom.client.MouseMoveEvent;
import com.google.gwt.event.dom.client.MouseOutEvent;
import com.google.gwt.event.dom.client.MouseOverEvent;
import com.google.gwt.event.dom.client.MouseUpEvent;
import com.google.gwt.event.dom.client.MouseWheelEvent;
import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;
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
	public void fireEvent(MouseEvent<EventHandler> event) {
		DomEvent.Type<EventHandler> type = event.getAssociatedType();
		
		List<EventHandler> listernerList = this.handlers.get(type.getName());

		if(MouseDownEvent.getType().getName().equals(type.getName())){
			for(EventHandler listener : listernerList){
//				((MouseDownHandler)listener).onMouseDown(event);
			}
		}else if(MouseUpEvent.getType().getName().equals(type.getName())){
			
		}else if(MouseOverEvent.getType().getName().equals(type.getName())){
			
		}else if(MouseOutEvent.getType().getName().equals(type.getName())){
			
		}else if(MouseMoveEvent.getType().getName().equals(type.getName())){
			
		}else if(MouseWheelEvent.getType().getName().equals(type.getName())){
			
		}
		
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

	/* (non-Javadoc)
	 * @see com.vaadin.graphics.canvas.widgetset.client.ui.VUIElement#update(com.google.gwt.canvas.dom.client.Context2d, com.vaadin.terminal.gwt.client.UIDL)
	 */
	@Override
	public void update(UIDL uidl) {
		String strokecolor = uidl.getStringAttribute("strokecolor");
		int strokewidth = uidl.getIntAttribute("strokewidth");
		double startX = uidl.getDoubleAttribute("startx");
		double startY = uidl.getDoubleAttribute("starty");
		double endX = uidl.getDoubleAttribute("endx");
		double endY = uidl.getDoubleAttribute("endy");
		String fillStyleColor = uidl.getStringAttribute("fillstyle");
		
		setColor(strokecolor);
		setBorderWidth(strokewidth);
		setStart(new VPoint(startX, startY));
		setEnd(new VPoint(endX, endY));
		setFillColor(fillStyleColor);
	}

	@Override
	public void fireEvent(GwtEvent<?> event) {
		
	}

}
