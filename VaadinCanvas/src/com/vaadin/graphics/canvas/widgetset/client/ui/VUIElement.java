/**
 * 
 */
package com.vaadin.graphics.canvas.widgetset.client.ui;

import com.google.gwt.canvas.dom.client.Context2d;
import com.google.gwt.event.dom.client.MouseDownEvent;
import com.google.gwt.event.dom.client.MouseDownHandler;
import com.google.gwt.event.dom.client.MouseEvent;
import com.google.gwt.event.dom.client.MouseMoveEvent;
import com.google.gwt.event.dom.client.MouseMoveHandler;
import com.google.gwt.event.dom.client.MouseOutEvent;
import com.google.gwt.event.dom.client.MouseOutHandler;
import com.google.gwt.event.dom.client.MouseOverEvent;
import com.google.gwt.event.dom.client.MouseOverHandler;
import com.google.gwt.event.dom.client.MouseUpEvent;
import com.google.gwt.event.dom.client.MouseUpHandler;
import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.HasHandlers;
import com.vaadin.terminal.gwt.client.UIDL;

/**
 * @author kapil - kapil.verma@globallogic.com
 *
 */
abstract class VUIElement implements HasHandlers{
	
	private VCanvas canvas;
	
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
				// TODO Auto-generated method stub
			}
			
		};
		
		canvas.addMouseEventHandler(moveHandler, MouseMoveEvent.getType());
		
		MouseDownHandler downHandler = new MouseDownHandler() {
			
			@Override
			public void onMouseDown(MouseDownEvent event) {
				// TODO Auto-generated method stub
			}
		};
		
		canvas.addMouseEventHandler(downHandler, MouseDownEvent.getType());
		
		MouseUpHandler upHandler = new MouseUpHandler() {
			
			@Override
			public void onMouseUp(MouseUpEvent event) {
				// TODO Auto-generated method stub
				
			}
		};
		
		canvas.addMouseEventHandler(upHandler, MouseUpEvent.getType());
		
		MouseOverHandler overHandler = new MouseOverHandler() {
			
			@Override
			public void onMouseOver(MouseOverEvent event) {
				// TODO Auto-generated method stub
				
			}
		};
		
		canvas.addMouseEventHandler(overHandler, MouseOverEvent.getType());
		
		MouseOutHandler outHandler = new MouseOutHandler() {
			
			@Override
			public void onMouseOut(MouseOutEvent event) {
				// TODO Auto-generated method stub
				
			}
		};
		
		canvas.addMouseEventHandler(outHandler, MouseOutEvent.getType());
	}
	
	abstract public void draw(Context2d canvas);
	
	abstract public VUIElement getNext();
	
	abstract public VUIElement getPrevious();
	
	abstract public void setNext(VUIElement next);
	
	abstract public void setPrevious(VUIElement prev);
	
	abstract public void moveTo(VPoint p);
	
	abstract public VPoint getCenter();
	
	abstract public String getId();
	
	abstract public void setId(String id);
	
	abstract public boolean contains(VPoint p);
	
//	abstract public void addListener(MouseEventListener listener, MouseEvent.Type eventType);
	
//	abstract public void fireMouseEvent(MouseEvent<EventHandler> event);
	
	abstract public boolean isSelected();
	
	abstract public void setSelected(boolean selected);
	
	abstract public boolean isPressed();
	
	abstract public void setPressed(boolean pressed);
	
	abstract public String getColor();
	
	abstract public void setColor(String color);
	
	abstract public String getFillColor();
	
	abstract public void setFillColor(String fillColor);
	
	abstract public int getBorderWidth();
	
	abstract public void setBorderWidth(int width);

	/**
	 * @param context
	 * @param uidl
	 */
	abstract public void update(UIDL uidl);
	
	public void addHandler(){
//		canvas.addMouseEventHandler(handler, type);
	}
}
