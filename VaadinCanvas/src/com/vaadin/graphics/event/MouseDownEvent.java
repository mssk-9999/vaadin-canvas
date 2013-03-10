/**
 * 
 */
package com.vaadin.graphics.event;

import com.vaadin.graphics.canvas.shape.Point;
import com.vaadin.graphics.canvas.shape.UIElement;


/**
 * @author kapil - kapildverma@gmail.com
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
