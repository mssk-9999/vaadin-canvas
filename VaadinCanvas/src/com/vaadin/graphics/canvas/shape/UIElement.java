/**
 * 
 */
package com.vaadin.graphics.canvas.shape;

import java.util.Map;

import com.vaadin.graphics.canvas.widgetset.client.ui.VCanvas;
import com.vaadin.graphics.event.MouseEvent;
import com.vaadin.graphics.event.listener.MouseEventListener;

/**
 * @author kapil - kapil.verma@globallogic.com
 *
 */
public abstract class UIElement {
	
	private VCanvas canvas;

	private String id;
	
	private UIElement next;
	private UIElement prev;
	private Point mouseDownPoint;
	private Point mouseUpPoint;
	private Point mouseOverPoint;
	private Point mouseOutPoint;
	private boolean selected = false;
	private boolean highlighted = false;
	private boolean pressed;
	private String fillColor = "";
	private String color = "";
	private int borderWidth = -1;
	
	abstract public Map<String, Object> getDrawInstructions();
	
	abstract public void moveTo(Point p);
	
	abstract public Point getCenter();
	
	abstract public boolean contains(Point p);
	
	abstract public void addListener(MouseEventListener listener, MouseEvent.Type eventType);
	
	abstract public void fireMouseEvent(MouseEvent event);
	
	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		if(id != null)
			this.id = id;
	}
	
	/**
	 * @return the id
	 */
	public String getId() {
		return id;
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
		if(fillColor != null)
			this.fillColor = fillColor;
	}
	
	public String getFillColor(){
		return this.fillColor;
	}
	
	public void setColor(String color){
		if(color != null)
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
	
	public UIElement getNext() {
		return next;
	}

	public UIElement getPrevious() {
		return prev;
	}
	
	public void setNext(UIElement next){
		this.next = next;
	}
	
	public void setPrevious(UIElement prev){
		this.prev = prev;
	}
	
	public static boolean pointInPolygon(Point[] vertices, Point p) {

		int i, j=vertices.length-1 ;
		boolean  oddNodes=false;

		for (i=0; i<vertices.length; i++) {
			if (vertices[i].getY()<p.getY() && vertices[j].getY()>=p.getY()
					||  vertices[j].getY()<p.getY() && vertices[i].getY()>=p.getY()) {
				if (vertices[i].getX()+(p.getY()-vertices[i].getY())/(vertices[j].getY()-vertices[i].getY())*(vertices[j].getX()-vertices[i].getX())<p.getX()) {
					oddNodes=!oddNodes;
				}
			}
			j=i;
		}

		return oddNodes;
	}
}
