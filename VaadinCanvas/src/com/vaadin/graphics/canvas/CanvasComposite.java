/**
 * 
 */
package com.vaadin.graphics.canvas;

import com.vaadin.annotations.AutoGenerated;
import com.vaadin.graphics.canvas.shape.Arc;
import com.vaadin.graphics.canvas.shape.Point;
import com.vaadin.graphics.canvas.shape.Polygon;
import com.vaadin.graphics.canvas.shape.Rect;
import com.vaadin.ui.AbsoluteLayout;
import com.vaadin.ui.CustomComponent;

/**
 * @author kapil - kapil.verma@globallogic.com
 *
 */
public class CanvasComposite extends CustomComponent {

	@AutoGenerated
	private AbsoluteLayout mainLayout;
	@AutoGenerated
	private Canvas canvas;

	/*- VaadinEditorProperties={"grid":"RegularGrid,20","showGrid":true,"snapToGrid":true,"snapToObject":true,"movingGuides":false,"snappingDistance":10} */

	/**
	 * The constructor should first build the main layout, set the
	 * composition root and then do any custom initialization.
	 *
	 * The constructor will not be automatically regenerated by the
	 * visual editor.
	 */
	public CanvasComposite() {
		buildMainLayout();
		setCompositionRoot(mainLayout);

		initCanvas();
	}
	
	private void initCanvas(){
		Rect rect = new Rect(new Point(100, 200), new Point(200, 300));
		rect.setBorderWidth(2);
		rect.setColor("#4b6193");
		rect.setFillColor("#5caec6");
		canvas.drawUIElement(rect);
		
		rect = new Rect(new Point(400, 200), new Point(600, 350));
		rect.setBorderWidth(4);
		rect.setColor("#93694b");
		rect.setFillColor("#e5a867");
		canvas.drawUIElement(rect);
		
		Polygon poly = new Polygon(new Point[]{new Point(123, 345), new Point(235, 463), new Point(324, 56)});
		poly.setBorderWidth(4);
		poly.setColor("#93694b");
		poly.setFillColor("#e5a867");
		canvas.drawUIElement(poly);
		
//		context.arc(pos.x, pos.y, radius,  Math.PI * 2.0, true);
//		Vector start, double startPosZ, double radius, CssColor color
//		dd(new Ball(146, 65, 0, 8, "#c41731"));

		Arc arc = new Arc(10, new Point(146, 65), 0, Math.PI * 2.0, true);
//		Arc arc = new Arc(10, , new Point(400, 700));
		arc.setBorderWidth(4);
		arc.setColor("#93694b");
		arc.setFillColor("#e5a867");
		canvas.drawUIElement(arc);
	}

	@AutoGenerated
	private AbsoluteLayout buildMainLayout() {
		// common part: create layout
		mainLayout = new AbsoluteLayout();
		mainLayout.setImmediate(false);
		mainLayout.setWidth("100%");
		mainLayout.setHeight("100%");
		mainLayout.setMargin(false);
		
		// top-level component properties
		setWidth("100.0%");
		setHeight("100.0%");
		
		// canvas
		canvas = new Canvas();
		canvas.setImmediate(false);
		canvas.setWidth("100.0%");
		canvas.setHeight("100.0%");
		mainLayout.addComponent(canvas, "top:0.0px;left:0.0px;");
		
		return mainLayout;
	}

}
