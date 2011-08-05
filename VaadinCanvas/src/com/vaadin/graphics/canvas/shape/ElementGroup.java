package com.vaadin.graphics.canvas.shape;

import java.util.List;
import java.util.Map;

import com.vaadin.graphics.event.MouseEvent;
import com.vaadin.graphics.event.MouseEvent.Type;
import com.vaadin.graphics.event.listener.MouseEventListener;

public class ElementGroup extends UIElement {

	List<UIElement> elements;

	Point point;
	
	@Override
	public Map<String, Object> getDrawInstructions() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void moveTo(Point p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Point getCenter() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean contains(Point p) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void addListener(MouseEventListener listener, Type eventType) {
		// TODO Auto-generated method stub

	}

	@Override
	public void fireMouseEvent(MouseEvent event) {
		// TODO Auto-generated method stub

	}
	
	public void add(Point p){
		for(UIElement element : elements){
			element.add(p);
		}
	}
	
	public void addElement(UIElement element){
		element.add(point);
		elements.add(element);
	}

}
