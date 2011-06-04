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
import com.google.gwt.event.dom.client.MouseDownHandler;
import com.google.gwt.event.dom.client.MouseEvent;
import com.google.gwt.event.dom.client.MouseMoveEvent;
import com.google.gwt.event.dom.client.MouseOutEvent;
import com.google.gwt.event.dom.client.MouseOverEvent;
import com.google.gwt.event.dom.client.MouseUpEvent;
import com.google.gwt.event.dom.client.MouseWheelEvent;
import com.google.gwt.event.shared.EventHandler;
import com.vaadin.terminal.gwt.client.UIDL;

/**
 * @author kapil - kapil.verma@globallogic.com
 *
 */
class VRect extends VUIElement {

	private String id;
	private VPoint start;

	private VPoint end;
	
	private VUIElement next;
	private VUIElement prev;
	
	private boolean selected = false;
	private boolean pressed;
	private String fillColor = "";
	private String color = "";
	private int borderWidth = -1;
	
	EventHandler handler;
//	
	private Map<String, List<EventHandler>> handlers = new HashMap<String, List<EventHandler>>();
//	
	
	public VRect(UIDL uidl){
		update(uidl);
		this.id = uidl.getStringAttribute("elementid");
		initializeHandlers();
	}
	
	public VRect(VPoint start, VPoint end){
		this.start = start;
		this.end = end;
		
		initializeHandlers();
	}
	
	private void initializeHandlers(){
		/*listener = new MouseEventListener() {
		
		VPoint downPoint;
		VPoint upPoint;
		
		public void onMouseEvent(MouseEvent event) {
			Rect source = (Rect)event.getSource();
			
			if(event.getType() == MouseEvent.Type.DOWN){
				downPoint = event.getPoint();
				source.setPressed(true);
			}else if(event.getType() == MouseEvent.Type.UP){
				upPoint = event.getPoint();
				source.setSelected(true);
				source.setPressed(false);
			}else if(event.getType() == MouseEvent.Type.MOVE){
				if(source.isPressed()){
					VPoint p = event.getPoint();
					
					VPoint delta = VPoint.sub(p, downPoint);
					
					source.start.add(delta);
					
					source.end.add(delta);
					
					downPoint = p;
//					source.draw();
				}
			}else{
				System.err.println("Unknown event type: " + event.getType());
			}
		}
	};
	
	List<MouseEventListener> upListeners = new ArrayList<MouseEventListener>();
	upListeners.add(listener);
	listeners.put(Type.UP, upListeners);
	
	List<MouseEventListener> downListeners = new ArrayList<MouseEventListener>();
	downListeners.add(listener);
	listeners.put(Type.DOWN, downListeners);
	
	List<MouseEventListener> moveListeners = new ArrayList<MouseEventListener>();
	moveListeners.add(listener);
	listeners.put(Type.MOVE, moveListeners);*/
	}
	
	/*public interface MouseEventListener{
		public void onMouseEvent(MouseEvent event);
	}*/
	
	/* (non-Javadoc)
	 * @see com.workflow.ivr.web.model.VUIElement#draw()
	 */
	public void draw(Context2d context) {
		context.save();

		if(color != null && color.length() > 0){
			context.setStrokeStyle(color);
		}
		if(borderWidth > 0){
			context.setLineWidth(borderWidth);
		}
		if(fillColor != null && fillColor.length() > 0){
			context.setFillStyle(fillColor);
		}
		context.beginPath();
		context.strokeRect(start.getX(), start.getY(), end.getX()-start.getX(), end.getY()-start.getY());
		context.closePath();
		
		if(fillColor.length() > 0){
			context.fillRect(start.getX(), start.getY(), end.getX()-start.getX(), end.getY()-start.getY());
		}
		
		context.restore();
	}

	/* (non-Javadoc)
	 * @see com.workflow.ivr.web.model.VUIElement#getNext()
	 */
	public VUIElement getNext() {
		return next;
	}

	/* (non-Javadoc)
	 * @see com.workflow.ivr.web.model.VUIElement#getPrevious()
	 */
	public VUIElement getPrevious() {
		return prev;
	}
	
	public void setNext(VUIElement next){
		this.next = next;
	}
	
	public void setPrevious(VUIElement prev){
		this.prev = prev;
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
	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}
	
	/**
	 * @return the id
	 */
	public String getId() {
		return id;
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
	public void fireMouseEvent(MouseEvent<EventHandler> event) {
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

	public boolean isSelected() {
		return this.selected;
	}
	
	public void setSelected(boolean selected) {
		this.selected = selected;
	}
	
	public boolean isPressed() {
		return this.pressed;
	}
	
	public void setPressed(boolean pressed) {
		this.pressed = pressed;
	}
	
	public void setFillColor(String fillColor){
		this.fillColor = fillColor;
	}
	
	public String getFillColor(){
		return this.fillColor;
	}
	
	public void setColor(String color){
		this.color = color;
	}
	
	public String getColor(){
		return this.color;
	}

	/**
	 * @param borderWidth the borderWidth to set
	 */
	public void setBorderWidth(int borderWidth) {
		this.borderWidth = borderWidth;
	}

	/**
	 * @return the borderWidth
	 */
	public int getBorderWidth() {
		return borderWidth;
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

}
