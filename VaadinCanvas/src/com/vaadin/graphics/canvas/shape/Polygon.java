/**
 * 
 */
package com.vaadin.graphics.canvas.shape;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.vaadin.graphics.event.MouseEvent;
import com.vaadin.graphics.event.MouseEvent.Type;
import com.vaadin.graphics.event.listener.MouseEventListener;

/**
 * @author kapil - kapil.verma@globallogic.com
 *
 */
public class Polygon extends UIElement {

	private Point[] vertices;
	
	MouseEventListener listener;
	
	private Map<MouseEvent.Type, List<MouseEventListener>> listeners = new HashMap<MouseEvent.Type, List<MouseEventListener>>();
	
	public Polygon(Point[] vertices){
		
		this.setId("");
		this.vertices = vertices;
		this.setSelected(false);
		this.setBorderWidth(-1);
		this.setFillColor("");
		this.setColor("");
		
		listener = new MouseEventListener() {
			
			Point downPoint;
			Point upPoint;
			
			public void onMouseEvent(MouseEvent event) {
				Polygon source = (Polygon)event.getSource();
				
				if(event.getType() == MouseEvent.Type.DOWN){
					downPoint = event.getPoint();
					source.setPressed(true);
				}else if(event.getType() == MouseEvent.Type.UP){
					upPoint = event.getPoint();
					source.setSelected(true);
					source.setPressed(false);
				}else if(event.getType() == MouseEvent.Type.MOVE){
					if(source.isPressed()){
						Point p = event.getPoint();
						
						Point delta = Point.sub(p, downPoint);
						
						for(Point vertex : Polygon.this.vertices){
							vertex.add(delta);
						}
						
						downPoint = p;
//						source.draw();
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
		listeners.put(Type.MOVE, moveListeners);
		
	}
	
	/* (non-Javadoc)
	 * @see com.workflow.ivr.web.model.UIElement#draw()
	 */
	public Map<String, Object> getDrawInstructions() {

		Map<String, Object> arguments = new HashMap<String, Object>();
		
		arguments.put("elementid", getId());
		arguments.put("strokecolor", getColor());
		arguments.put("strokewidth", getBorderWidth());
		arguments.put("numberofvertices", vertices.length);
		
		for(int i=0; i< vertices.length; i++){
			Point p = vertices[i];
			arguments.put("x" + i, p.getX());
			arguments.put("y" + i, p.getY());
		}
		
		arguments.put("fillstyle", getFillColor());
		arguments.put("elementtype", "polygon");
		
		arguments.put("command", "draw");
		
		return arguments;
		
	}


	/* (non-Javadoc)
	 * @see com.workflow.ivr.web.model.UIElement#getCenterX()
	 */
	public Point getCenter() {
		Point sum = new Point(0, 0);
		for(int i = 0; i < vertices.length; i++){
			sum = Point.add(sum, vertices[i]);
		}
		return Point.mult(sum, 1/vertices.length);
	}

	/* (non-Javadoc)
	 * @see com.workflow.ivr.web.model.UIElement#moveTo(double, double)
	 */
	public void moveTo(Point p) {
		
	}
	
	public void add(Point p){
		for (Point vertex : vertices){
			vertex.add(p);
		}
	}

	/* (non-Javadoc)
	 * @see com.ui.model.UIElement#contains(double, double)
	 */
	public boolean contains(Point p) {
		return UIElement.pointInPolygon(vertices, p);
	}
	/* (non-Javadoc)
	 * @see com.ui.model.UIElement#addListener(com.vaadin.ui.Component.Listener)
	 */
	public void addListener(MouseEventListener listener, MouseEvent.Type eventType) {
		
	}
	/* (non-Javadoc)
	 * @see com.ui.model.UIElement#fireMouseEvent(com.vaadin.event.MouseEvents)
	 */
	public void fireMouseEvent(MouseEvent event) {
		Type type = event.getType();
		
		List<MouseEventListener> listernerList = this.listeners.get(type);
		for(MouseEventListener listener : listernerList){
			listener.onMouseEvent(event);
		}
	}

}
