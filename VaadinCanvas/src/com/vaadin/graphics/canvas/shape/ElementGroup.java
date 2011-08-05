package com.vaadin.graphics.canvas.shape;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.vaadin.graphics.event.MouseEvent;
import com.vaadin.graphics.event.MouseEvent.Type;
import com.vaadin.graphics.event.listener.MouseEventListener;

public class ElementGroup extends UIElement {

	List<UIElement> elements;
	List<Point> relativePositions;

	Point center;
	
	public ElementGroup(){
		elements = new ArrayList<UIElement>();
		relativePositions = new ArrayList<Point>();
	}
	
	@Override
	public Map<String, Object> getDrawInstructions() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void moveTo(Point p) {
		center = p;
		for(int i=0; i<elements.size(); i++){
			UIElement element = elements.get(i);
			element.moveTo(Point.add(center, relativePositions.get(i)));
		}
	}

	@Override
	public Point getCenter() {
		return center;
	}

	@Override
	public boolean contains(Point p) {
		for(UIElement element : elements){
			if(element.contains(p))
				return true;
		}
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
	
	public void addElement(UIElement element, Point p){
		element.add(p);
		element.add(center);
		elements.add(element);
		relativePositions.add(p);
	}

}
