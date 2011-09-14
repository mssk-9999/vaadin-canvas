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
	
	private String role;
	protected VCanvas canvas;

	private String id;
	protected String groupId = "";
	
	private VUIElement next;
	private VUIElement prev;
	VPoint mouseDownPoint;
	VPoint mouseUpPoint;
	VPoint mouseOverPoint;
	VPoint mouseOutPoint;
	
	private boolean selected = false;
	private boolean highlighted = false;
	private boolean pressed;
	private String color = "";
	private String fillColor = "";
	private String selectedColor = "";
	private String selectedFillColor = "";
	private String highlightedColor = "";
	private String highlightedFillColor = "";
	private int borderWidth = -1;
	
	private HandlerManager handlerManager;
	
	public static VUIElement createFromUIDL(UIDL uidl, VCanvas canvas){
		VUIElement ele = null;
		
		String id = uidl.getStringAttribute("elementid");
		
		String elementType = uidl.getStringAttribute(id + ".elementtype");
		if(elementType.equals("rect")){
			ele = new VRect(uidl);
		}else if(elementType.equals("polygon")){
			ele = new VPolygon(uidl);
		}else if(elementType.equals("arc")){
			ele = new VArc(uidl);
		}else if(elementType.equals("group")){
			ele = new VElementGroup(uidl, canvas);
		}else if(elementType.equals("text")){
			ele = new VText(uidl, canvas);
		}
		
		ele.canvas = canvas;
		ele.initHandlers();
		
		return ele;
	}
	
	public static VUIElement createFromUIDL(UIDL uidl, String id, String groupId, VCanvas canvas){
		VUIElement ele = null;
		
		String elementType = uidl.getStringAttribute(id + ".elementtype");
		if(elementType.equals("rect")){
			ele = new VRect(uidl, id, groupId);
		}else if(elementType.equals("polygon")){
			ele = new VPolygon(uidl, id, groupId);
		}else if(elementType.equals("arc")){
			ele = new VArc(uidl, id, groupId);
		}else if(elementType.equals("text")){
			ele = new VText(uidl, id, groupId);
		}else if(elementType.equals("line")){
			ele = new VLine(uidl, id, groupId);
		}else if(elementType.equals("group")){
			ele = new VElementGroup(uidl, id, groupId, canvas);
		}
		
		ele.canvas = canvas;
		ele.initGroupHandlers();
		
		return ele;
	}
	
	private void initGroupHandlers(){
		MouseMoveHandler moveHandler = new MouseMoveHandler(){

			@Override
			public void onMouseMove(MouseMoveEvent event) {
				VUIElement.this.canvas.getChild(groupId).processMoveEvent(event);
				
				/*VPoint p = new VPoint(event.getClientX() - VUIElement.this.canvas.getAbsoluteLeft(), event.getClientY() - VUIElement.this.canvas.getAbsoluteTop());
				if(VUIElement.this.contains(p)){
					if(VUIElement.this.isSelected()){
						VUIElement.this.processMoveEvent(event);
					}
					VUIElement.this.mouseDownPoint = new VPoint(event.getClientX(), event.getClientY());
				}*/
			}
			
		};
		
		canvas.addMouseEventHandler(moveHandler, MouseMoveEvent.getType());
		
		MouseDownHandler downHandler = new MouseDownHandler() {
			
			@Override
			public void onMouseDown(MouseDownEvent event) {
				((VElementGroup)VUIElement.this.canvas.getChild(groupId)).processMouseDownEvent(event);
				/*VPoint p = new VPoint(event.getClientX() - VUIElement.this.canvas.getAbsoluteLeft(), event.getClientY() - VUIElement.this.canvas.getAbsoluteTop());
				if(VUIElement.this.contains(p)){
					VUIElement.this.setSelected(true);
					VUIElement.this.setMouseDownPoint(new VPoint(event.getClientX(), event.getClientY()));
				}*/
			}
		};
		
		canvas.addMouseEventHandler(downHandler, MouseDownEvent.getType());
		
		MouseUpHandler upHandler = new MouseUpHandler() {
			
			@Override
			public void onMouseUp(MouseUpEvent event) {
				((VElementGroup)VUIElement.this.canvas.getChild(groupId)).processMouseUpEvent(event);
				/*VPoint p = new VPoint(event.getClientX() - VUIElement.this.canvas.getAbsoluteLeft(), event.getClientY() - VUIElement.this.canvas.getAbsoluteTop());
				if(VUIElement.this.contains(p)){
					VUIElement.this.setSelected(false);
					VUIElement.this.setMouseUpPoint(new VPoint(event.getClientX(), event.getClientY()));
				}*/
			}
		};
		
		canvas.addMouseEventHandler(upHandler, MouseUpEvent.getType());
		
		MouseOverHandler overHandler = new MouseOverHandler() {
			
			@Override
			public void onMouseOver(MouseOverEvent event) {
				((VElementGroup)VUIElement.this.canvas.getChild(groupId)).processMouseOverEvent(event);
				/*VPoint p = new VPoint(event.getClientX() - VUIElement.this.canvas.getAbsoluteLeft(), event.getClientY() - VUIElement.this.canvas.getAbsoluteTop());
				if(VUIElement.this.contains(p)){
					VUIElement.this.setHighlighted(true);
					VUIElement.this.setMouseOverPoint(new VPoint(event.getClientX(), event.getClientY()));
				}*/
			}
		};
		
		canvas.addMouseEventHandler(overHandler, MouseOverEvent.getType());
		
		MouseOutHandler outHandler = new MouseOutHandler() {
			
			@Override
			public void onMouseOut(MouseOutEvent event) {
				((VElementGroup)VUIElement.this.canvas.getChild(groupId)).processMouseOutEvent(event);
				/*VPoint p = new VPoint(event.getClientX() - VUIElement.this.canvas.getAbsoluteLeft(), event.getClientY() - VUIElement.this.canvas.getAbsoluteTop());
				if(VUIElement.this.contains(p)){
					VUIElement.this.setHighlighted(false);
					VUIElement.this.setMouseOutPoint(new VPoint(event.getClientX(), event.getClientY()));
				}*/
			}
		};
		
		canvas.addMouseEventHandler(outHandler, MouseOutEvent.getType());
	}
	
	private void initHandlers(){
		MouseMoveHandler moveHandler = new MouseMoveHandler(){

			@Override
			public void onMouseMove(MouseMoveEvent event) {
				VPoint p = new VPoint(event.getClientX() - VUIElement.this.canvas.getAbsoluteLeft(), event.getClientY() - VUIElement.this.canvas.getAbsoluteTop());
				if(VUIElement.this.contains(p)){
					if(VUIElement.this.isSelected()){
						VUIElement.this.processMoveEvent(event);
					}
					VUIElement.this.mouseDownPoint = new VPoint(event.getClientX(), event.getClientY());
				}
			}
			
		};
		
		canvas.addMouseEventHandler(moveHandler, MouseMoveEvent.getType());
		
		MouseDownHandler downHandler = new MouseDownHandler() {
			
			@Override
			public void onMouseDown(MouseDownEvent event) {
				VPoint p = new VPoint(event.getClientX() - VUIElement.this.canvas.getAbsoluteLeft(), event.getClientY() - VUIElement.this.canvas.getAbsoluteTop());
				if(VUIElement.this.contains(p)){
					VUIElement.this.setSelected(true);
					VUIElement.this.setMouseDownPoint(new VPoint(event.getClientX(), event.getClientY()));
				}
			}
		};
		
		canvas.addMouseEventHandler(downHandler, MouseDownEvent.getType());
		
		MouseUpHandler upHandler = new MouseUpHandler() {
			
			@Override
			public void onMouseUp(MouseUpEvent event) {
				VPoint p = new VPoint(event.getClientX() - VUIElement.this.canvas.getAbsoluteLeft(), event.getClientY() - VUIElement.this.canvas.getAbsoluteTop());
				if(VUIElement.this.contains(p)){
					VUIElement.this.setSelected(false);
					VUIElement.this.setMouseUpPoint(new VPoint(event.getClientX(), event.getClientY()));
				}
			}
		};
		
		canvas.addMouseEventHandler(upHandler, MouseUpEvent.getType());
		
		MouseOverHandler overHandler = new MouseOverHandler() {
			
			@Override
			public void onMouseOver(MouseOverEvent event) {
				VPoint p = new VPoint(event.getClientX() - VUIElement.this.canvas.getAbsoluteLeft(), event.getClientY() - VUIElement.this.canvas.getAbsoluteTop());
				if(VUIElement.this.contains(p)){
					VUIElement.this.setHighlighted(true);
					VUIElement.this.setMouseOverPoint(new VPoint(event.getClientX(), event.getClientY()));
				}
			}
		};
		
		canvas.addMouseEventHandler(overHandler, MouseOverEvent.getType());
		
		MouseOutHandler outHandler = new MouseOutHandler() {
			
			@Override
			public void onMouseOut(MouseOutEvent event) {
				VPoint p = new VPoint(event.getClientX() - VUIElement.this.canvas.getAbsoluteLeft(), event.getClientY() - VUIElement.this.canvas.getAbsoluteTop());
				if(VUIElement.this.contains(p)){
					VUIElement.this.setHighlighted(false);
					VUIElement.this.setMouseOutPoint(new VPoint(event.getClientX(), event.getClientY()));
				}
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

	public VCanvas getCanvas() {
		return canvas;
	}

	public void setCanvas(VCanvas canvas) {
		this.canvas = canvas;
	}

	public VUIElement getPrev() {
		return prev;
	}

	public void setPrev(VUIElement prev) {
		this.prev = prev;
	}

	public String getSelectedColor() {
		return selectedColor;
	}

	public void setSelectedColor(String selectedColor) {
		this.selectedColor = selectedColor;
	}

	public String getSelectedFillColor() {
		return selectedFillColor;
	}

	public void setSelectedFillColor(String selectedFillColor) {
		this.selectedFillColor = selectedFillColor;
	}

	public String getHighlightedColor() {
		return highlightedColor;
	}

	public void setHighlightedColor(String highlightedColor) {
		this.highlightedColor = highlightedColor;
	}

	public String getHighlightedFillColor() {
		return highlightedFillColor;
	}

	public void setHighlightedFillColor(String highlightedFillColor) {
		this.highlightedFillColor = highlightedFillColor;
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
	
	abstract public void draw(Context2d context);
	
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
	
	public static boolean pointInPolygon(VPoint[] vertices, VPoint p) {

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


	/**
	 * @param groupId the groupId to set
	 */
	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}


	/**
	 * @return the groupId
	 */
	public String getGroupId() {
		return groupId;
	}
	
	public String getPrefix(){

		String prefix = "";
		if(id.length() != 0){
			prefix = id + ".";
		}
/*		
		if(groupId.length() != 0){
			prefix = groupId + ".";
		}
*/		return prefix;
	}

	abstract public void moveBy(VPoint delta);

	/**
	 * @return the role
	 */
	public String getRole() {
		return role;
	}

	/**
	 * @param role the role to set
	 */
	public void setRole(String role) {
		this.role = role;
	}
}
