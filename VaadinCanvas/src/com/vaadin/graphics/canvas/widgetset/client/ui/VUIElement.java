/**
 * 
 */
package com.vaadin.graphics.canvas.widgetset.client.ui;

import com.google.gwt.canvas.dom.client.Context2d;
import com.google.gwt.event.dom.client.MouseDownEvent;
import com.google.gwt.event.dom.client.MouseDownHandler;
import com.google.gwt.event.dom.client.MouseMoveEvent;
import com.google.gwt.event.dom.client.MouseMoveHandler;
import com.google.gwt.event.dom.client.MouseOutEvent;
import com.google.gwt.event.dom.client.MouseOutHandler;
import com.google.gwt.event.dom.client.MouseOverEvent;
import com.google.gwt.event.dom.client.MouseOverHandler;
import com.google.gwt.event.dom.client.MouseUpEvent;
import com.google.gwt.event.dom.client.MouseUpHandler;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.event.shared.HasHandlers;
import com.vaadin.terminal.gwt.client.UIDL;

/**
 * @author kapil - kapil.verma@globallogic.com
 *
 */
abstract class VUIElement implements HasHandlers{
	
	private VCanvas canvas;

	private String id;
	
	private VUIElement next;
	private VUIElement prev;
	private VPoint mouseDownPoint;
	private VPoint mouseUpPoint;
	private VPoint mouseOverPoint;
	private VPoint mouseOutPoint;
	
	private boolean selected = false;
	private boolean highlighted = false;
	private boolean pressed;
	private String fillColor = "";
	private String color = "";
	private int borderWidth = -1;
	
	private HandlerManager handlerManager;
	
	public static VUIElement createFromUIDL(UIDL uidl, VCanvas canvas){
		VUIElement ele = null;
		
		String elementType = uidl.getStringAttribute("elementtype");
		
		if(elementType.equals("rect")){
			ele = new VRect(uidl);
		}
		
		ele.canvas = canvas;
		ele.initHandlers();
		
		return ele;
	}
	
	
	private void initHandlers(){
		MouseMoveHandler moveHandler = new MouseMoveHandler(){

			@Override
			public void onMouseMove(MouseMoveEvent event) {
				if(VUIElement.this.isSelected()){
					VUIElement.this.processMoveEvent(event);
				}
				VUIElement.this.mouseDownPoint = new VPoint(event.getX(), event.getY());
			}
			
		};
		
		canvas.addMouseEventHandler(moveHandler, MouseMoveEvent.getType());
		
		MouseDownHandler downHandler = new MouseDownHandler() {
			
			@Override
			public void onMouseDown(MouseDownEvent event) {
				VUIElement.this.setSelected(true);
				VUIElement.this.setMouseDownPoint(new VPoint(event.getX(), event.getY()));
			}
		};
		
		canvas.addMouseEventHandler(downHandler, MouseDownEvent.getType());
		
		MouseUpHandler upHandler = new MouseUpHandler() {
			
			@Override
			public void onMouseUp(MouseUpEvent event) {
				VUIElement.this.setSelected(false);
				VUIElement.this.setMouseUpPoint(new VPoint(event.getX(), event.getY()));
			}
		};
		
		canvas.addMouseEventHandler(upHandler, MouseUpEvent.getType());
		
		MouseOverHandler overHandler = new MouseOverHandler() {
			
			@Override
			public void onMouseOver(MouseOverEvent event) {
				VUIElement.this.setHighlighted(true);
				VUIElement.this.setMouseOverPoint(new VPoint(event.getX(), event.getY()));
			}
		};
		
		canvas.addMouseEventHandler(overHandler, MouseOverEvent.getType());
		
		MouseOutHandler outHandler = new MouseOutHandler() {
			
			@Override
			public void onMouseOut(MouseOutEvent event) {
				VUIElement.this.setHighlighted(false);
				VUIElement.this.setMouseOutPoint(new VPoint(event.getX(), event.getY()));
			}
		};
		
		canvas.addMouseEventHandler(outHandler, MouseOutEvent.getType());
	}
	
	abstract protected void processMoveEvent(MouseMoveEvent event);


	public VPoint getMouseDownPoint() {
		return mouseDownPoint;
	}


	public void setMouseDownPoint(VPoint mouseDownPoint) {
		this.mouseDownPoint = mouseDownPoint;
	}


	public VPoint getMouseUpPoint() {
		return mouseUpPoint;
	}


	public void setMouseUpPoint(VPoint mouseUpPoint) {
		this.mouseUpPoint = mouseUpPoint;
	}


	public VPoint getMouseOverPoint() {
		return mouseOverPoint;
	}


	public void setMouseOverPoint(VPoint mouseOverPoint) {
		this.mouseOverPoint = mouseOverPoint;
	}


	public VPoint getMouseOutPoint() {
		return mouseOutPoint;
	}


	public void setMouseOutPoint(VPoint mouseOutPoint) {
		this.mouseOutPoint = mouseOutPoint;
	}


	HandlerManager ensureHandlers() {
		return handlerManager == null ? handlerManager = createHandlerManager()
				: handlerManager;
	}
	
	protected HandlerManager createHandlerManager() {
		return new HandlerManager(this);
	}
	
	public VUIElement getNext() {
		return next;
	}

	public VUIElement getPrevious() {
		return prev;
	}
	
	public void setNext(VUIElement next){
		this.next = next;
	}
	
	public void setPrevious(VUIElement prev){
		this.prev = prev;
	}

	abstract public void moveTo(VPoint p);
	
	abstract public VPoint getCenter();
	
	public String getId(){
		return this.id;
	}
	
	public void setId(String id){
		this.id = id;
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

	public void addHandler(){
//		canvas.addMouseEventHandler(handler, type);
	}
	
	@Override
	public void fireEvent(GwtEvent<?> event) {
		handlerManager.fireEvent(event);
//		GwtEvent.Type<?> type = event.getAssociatedType();
//		
//		List<EventHandler> listernerList = this.handlers.get(type.getName());
//
//		if(MouseDownEvent.getType().getName().equals(type.getName())){
//			for(EventHandler listener : listernerList){
////				((MouseDownHandler)listener).onMouseDown(event);
//			}
//		}else if(MouseUpEvent.getType().getName().equals(type.getName())){
//			
//		}else if(MouseOverEvent.getType().getName().equals(type.getName())){
//			
//		}else if(MouseOutEvent.getType().getName().equals(type.getName())){
//			
//		}else if(MouseMoveEvent.getType().getName().equals(type.getName())){
//			
//		}else if(MouseWheelEvent.getType().getName().equals(type.getName())){
//			
//		}
		
	}
	
	abstract public void draw(Context2d canvas);
	
	abstract public boolean contains(VPoint p);
	
	/**
	 * @param context
	 * @param uidl
	 */
	abstract public void update(UIDL uidl);


	public void setHighlighted(boolean highlighted) {
		this.highlighted = highlighted;
	}


	public boolean isHighlighted() {
		return highlighted;
	}
	
}
