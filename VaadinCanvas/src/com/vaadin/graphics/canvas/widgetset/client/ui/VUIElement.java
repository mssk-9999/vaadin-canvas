/**
 * 
 */
package com.vaadin.graphics.canvas.widgetset.client.ui;

import com.google.gwt.canvas.dom.client.Context2d;
//import com.vaadin.graphics.canvas.widgetset.client.event.MouseEvent;

/**
 * @author kapil - kapil.verma@globallogic.com
 *
 */
interface VUIElement {
	
	public void draw(Context2d canvas);
	
	public VUIElement getNext();
	
	public VUIElement getPrevious();
	
	public void setNext(VUIElement next);
	
	public void setPrevious(VUIElement prev);
	
	public void moveTo(VPoint p);
	
	public VPoint getCenter();
	
	public String getId();
	
	public void setId(String id);
	
	public boolean contains(VPoint p);
	
//	public void addListener(MouseEventListener listener, MouseEvent.Type eventType);
	
//	public void fireMouseEvent(MouseEvent event);
	
	public boolean isSelected();
	
	public void setSelected(boolean selected);
	
	public boolean isPressed();
	
	public void setPressed(boolean pressed);
	
	public String getColor();
	
	public void setColor(String color);
	
	public String getFillColor();
	
	public void setFillColor(String fillColor);
	
	public int getBorderWidth();
	
	public void setBorderWidth(int width);
}
