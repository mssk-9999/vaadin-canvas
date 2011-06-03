/**
 * 
 */
package com.vaadin.graphics.canvas.widgetset.client.ui;

import com.google.gwt.canvas.dom.client.Context2d;
//import com.vaadin.graphics.canvas.widgetset.client.event.MouseEvent;
import com.vaadin.terminal.gwt.client.UIDL;

/**
 * @author kapil - kapil.verma@globallogic.com
 *
 */
abstract class VUIElement {
	
	public static VUIElement createFromUIDL(UIDL uidl){
		VUIElement ele = null;
		
		return ele;
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
	
//	abstract public void fireMouseEvent(MouseEvent event);
	
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
	abstract public void update(Context2d context, UIDL uidl);
}
