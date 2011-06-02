/**
 * 
 */
package com.vaadin.graphics.canvas.widgetset.client.event;

import com.vaadin.graphics.canvas.widgetset.client.Point;
import com.vaadin.graphics.canvas.widgetset.client.UIElement;


/**
 * @author kapil - kapil.verma@globallogic.com
 *
 */
public class MouseDownEvent extends MouseEvent {

	private Point p;
	
	/**
	 * @param source
	 */
	public MouseDownEvent(UIElement source, Point p) {
		super(source);
		this.type = MouseEvent.Type.DOWN;
		this.p = p;
	}

	public Point getPoint() {
		return this.p;
	}

}
