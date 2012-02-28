/**
 * 
 */
package com.vaadin.graphics.canvas;

import com.vaadin.annotations.AutoGenerated;
import com.vaadin.graphics.canvas.shape.Arc;
import com.vaadin.graphics.canvas.shape.ElementGroup;
import com.vaadin.graphics.canvas.shape.Line;
import com.vaadin.graphics.canvas.shape.Node;
import com.vaadin.graphics.canvas.shape.Point;
import com.vaadin.graphics.canvas.shape.Polygon;
import com.vaadin.graphics.canvas.shape.Rect;
import com.vaadin.terminal.ThemeResource;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Embedded;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.HorizontalSplitPanel;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.VerticalSplitPanel;

/**
 * @author kapil - kapil.verma@globallogic.com
 *
 */
public class CanvasComposite extends CustomComponent {

	@AutoGenerated
	private HorizontalLayout mainLayout;
	@AutoGenerated
	private HorizontalSplitPanel horizontalSplitPanel_1;
	@AutoGenerated
	private Canvas canvas;
	@AutoGenerated
	private Panel toolPanel;
	@AutoGenerated
	private VerticalLayout verticalLayout_1;
	@AutoGenerated
	private VerticalSplitPanel verticalSplitPanel_1;
	@AutoGenerated
	private Panel propertiesPanel;
	@AutoGenerated
	private VerticalLayout verticalLayout_3;
	@AutoGenerated
	private Panel panel_1;
	@AutoGenerated
	private GridLayout palletteGrid;
	@AutoGenerated
	private Embedded imageIcon;
	@AutoGenerated
	private Embedded nodeIcon;
	@AutoGenerated
	private Embedded polyIcon;
	@AutoGenerated
	private Embedded arcIcon;
	@AutoGenerated
	private Embedded rectIcon;
	@AutoGenerated
	private Embedded lineIcon;


	/**
	 * The constructor should first build the main layout, set the
	 * composition root and then do any custom initialization.
	 *
	 * The constructor will not be automatically regenerated by the
	 * visual editor.
	 */
	public CanvasComposite() {
		buildMainLayout();
		horizontalSplitPanel_1.setSplitPosition(20, UNITS_PERCENTAGE, false);
		setCompositionRoot(mainLayout);

		initCanvas();
	}
	
	private void initCanvas(){
		Rect rect = new Rect(new Point(100, 200), new Point(200, 300));
		rect.setBorderWidth(2);
		rect.setColor("#4b6193");
		rect.setFillColor("#5caec6");
		canvas.drawUIElement(rect);
		
//		rect = new Rect(new Point(400, 200), new Point(600, 350));
//		rect.setBorderWidth(4);
//		rect.setColor("#93694b");
//		rect.setFillColor("#e5a867");
//		canvas.drawUIElement(rect);
		
		Polygon poly = new Polygon(new Point[]{new Point(123, 345), new Point(235, 463), new Point(324, 56)});
		poly.setBorderWidth(4);
		poly.setColor("#93694b");
		poly.setFillColor("#e5a867");
		canvas.drawUIElement(poly);
		
//		context.arc(pos.x, pos.y, radius,  Math.PI * 2.0, true);
//		Vector start, double startPosZ, double radius, CssColor color
//		dd(new Ball(146, 65, 0, 8, "#c41731"));

//		Arc arc = new Arc(10, new Point(146, 65), 0, Math.PI/2, false);
////		Arc arc = new Arc(10, , new Point(400, 700));
//		arc.setBorderWidth(4);
//		arc.setColor("#93694b");
//		arc.setFillColor("#e5a867");
//		canvas.drawUIElement(arc);
//		
//		arc = new Arc(20, new Point(200, 100), new Point(220, 220));
		Arc arc = new Arc(30, new Point(300, 200), 0, 2*Math.PI, false);
		arc.setBorderWidth(2);
		arc.setColor("#4b6193");
		arc.setFillColor("#5caec6");
		canvas.drawUIElement(arc);
		
		Line line = new Line(new Point(500, 200), new Point(800, 500));
		line.setColor("#4b6193");
		line.setBorderWidth(4);
		canvas.drawUIElement(line);
		
		ElementGroup group = new Node(200, 100, new Point(500, 400), "Test Node1");
		group.setColor("#4b6193");
		group.setFillColor("#5caec6");
		group.setSelectedColor("#93694b");
		group.setSelectedFillColor("#e5a867");
		group.setHighlightedColor("#4b6193");
		group.setHighlightedFillColor("#5caec6");
		canvas.drawUIElement(group);
		
		group = new Node(100, 50, new Point(800, 300), "Test Node2");
		group.setColor("#4b6193");
		group.setFillColor("#5caec6");
		group.setSelectedColor("#93694b");
		group.setSelectedFillColor("#e5a867");
		group.setHighlightedColor("#4b6193");
		group.setHighlightedFillColor("#5caec6");
		canvas.drawUIElement(group);
		
		group = new Node(150, 75, new Point(600, 200), "Test Node3");
		group.setColor("#4b6193");
		group.setFillColor("#5caec6");
		group.setSelectedColor("#93694b");
		group.setSelectedFillColor("#e5a867");
		group.setHighlightedColor("#4b6193");
		group.setHighlightedFillColor("#5caec6");
		canvas.drawUIElement(group);
		
		group = new Node(150, 100, new Point(900, 250), "Test Node4");
		group.setColor("#4b6193");
		group.setFillColor("#5caec6");
		group.setSelectedColor("#93694b");
		group.setSelectedFillColor("#e5a867");
		group.setHighlightedColor("#4b6193");
		group.setHighlightedFillColor("#5caec6");
		canvas.drawUIElement(group);
	}

	@AutoGenerated
	private HorizontalLayout buildMainLayout() {
		// common part: create layout
		mainLayout = new HorizontalLayout();
		mainLayout.setImmediate(false);
		mainLayout.setWidth("100%");
		mainLayout.setHeight("100%");
		mainLayout.setMargin(false);
		
		// top-level component properties
		setWidth("100.0%");
		setHeight("100.0%");
		
		// horizontalSplitPanel_1
		horizontalSplitPanel_1 = buildHorizontalSplitPanel_1();
		mainLayout.addComponent(horizontalSplitPanel_1);
		mainLayout.setExpandRatio(horizontalSplitPanel_1, 1.0f);
		
		return mainLayout;
	}

	@AutoGenerated
	private HorizontalSplitPanel buildHorizontalSplitPanel_1() {
		// common part: create layout
		horizontalSplitPanel_1 = new HorizontalSplitPanel();
		horizontalSplitPanel_1.setImmediate(false);
		horizontalSplitPanel_1.setWidth("100.0%");
		horizontalSplitPanel_1.setHeight("100.0%");
		horizontalSplitPanel_1.setMargin(false);
		
		// toolPanel
		toolPanel = buildToolPanel();
		horizontalSplitPanel_1.addComponent(toolPanel);
		
		// canvas
		canvas = new Canvas();
		canvas.setImmediate(false);
		canvas.setWidth("100.0%");
		canvas.setHeight("100.0%");
		horizontalSplitPanel_1.addComponent(canvas);
		
		return horizontalSplitPanel_1;
	}

	@AutoGenerated
	private Panel buildToolPanel() {
		// common part: create layout
		toolPanel = new Panel();
		toolPanel.setImmediate(false);
		toolPanel.setWidth("100.0%");
		toolPanel.setHeight("100.0%");
		
		// verticalLayout_1
		verticalLayout_1 = buildVerticalLayout_1();
		toolPanel.setContent(verticalLayout_1);
		
		return toolPanel;
	}

	@AutoGenerated
	private VerticalLayout buildVerticalLayout_1() {
		// common part: create layout
		verticalLayout_1 = new VerticalLayout();
		verticalLayout_1.setImmediate(false);
		verticalLayout_1.setWidth("100.0%");
		verticalLayout_1.setHeight("100.0%");
		verticalLayout_1.setMargin(false);
		
		// verticalSplitPanel_1
		verticalSplitPanel_1 = buildVerticalSplitPanel_1();
		verticalLayout_1.addComponent(verticalSplitPanel_1);
		verticalLayout_1.setExpandRatio(verticalSplitPanel_1, 1.0f);
		
		return verticalLayout_1;
	}

	@AutoGenerated
	private VerticalSplitPanel buildVerticalSplitPanel_1() {
		// common part: create layout
		verticalSplitPanel_1 = new VerticalSplitPanel();
		verticalSplitPanel_1.setImmediate(false);
		verticalSplitPanel_1.setWidth("100.0%");
		verticalSplitPanel_1.setHeight("100.0%");
		verticalSplitPanel_1.setMargin(false);
		
		// panel_1
		panel_1 = buildPanel_1();
		verticalSplitPanel_1.addComponent(panel_1);
		
		// propertiesPanel
		propertiesPanel = buildPropertiesPanel();
		verticalSplitPanel_1.addComponent(propertiesPanel);
		
		return verticalSplitPanel_1;
	}

	@AutoGenerated
	private Panel buildPanel_1() {
		// common part: create layout
		panel_1 = new Panel();
		panel_1.setCaption("Pallette");
		panel_1.setImmediate(false);
		panel_1.setWidth("100.0%");
		panel_1.setHeight("100.0%");
		
		// palletteGrid
		palletteGrid = buildPalletteGrid();
		panel_1.setContent(palletteGrid);
		
		return panel_1;
	}

	@AutoGenerated
	private GridLayout buildPalletteGrid() {
		// common part: create layout
		palletteGrid = new GridLayout();
		palletteGrid.setImmediate(false);
		palletteGrid.setWidth("100.0%");
		palletteGrid.setHeight("100.0%");
		palletteGrid.setMargin(false);
		palletteGrid.setColumns(2);
		palletteGrid.setRows(3);
		
		// lineIcon
		lineIcon = new Embedded();
		lineIcon.setImmediate(false);
		lineIcon.setWidth("-1px");
		lineIcon.setHeight("-1px");
		lineIcon.setSource(new ThemeResource("img/component/embedded_icon.png"));
		lineIcon.setType(1);
		lineIcon.setMimeType("image/png");
		palletteGrid.addComponent(lineIcon, 0, 0);
		
		// rectIcon
		rectIcon = new Embedded();
		rectIcon.setImmediate(false);
		rectIcon.setWidth("-1px");
		rectIcon.setHeight("-1px");
		rectIcon.setSource(new ThemeResource("img/component/embedded_icon.png"));
		rectIcon.setType(1);
		rectIcon.setMimeType("image/png");
		palletteGrid.addComponent(rectIcon, 1, 0);
		
		// arcIcon
		arcIcon = new Embedded();
		arcIcon.setImmediate(false);
		arcIcon.setWidth("-1px");
		arcIcon.setHeight("-1px");
		arcIcon.setSource(new ThemeResource("img/component/embedded_icon.png"));
		arcIcon.setType(1);
		arcIcon.setMimeType("image/png");
		palletteGrid.addComponent(arcIcon, 0, 1);
		
		// polyIcon
		polyIcon = new Embedded();
		polyIcon.setImmediate(false);
		polyIcon.setWidth("-1px");
		polyIcon.setHeight("-1px");
		polyIcon.setSource(new ThemeResource("img/component/embedded_icon.png"));
		polyIcon.setType(1);
		polyIcon.setMimeType("image/png");
		palletteGrid.addComponent(polyIcon, 1, 1);
		
		// nodeIcon
		nodeIcon = new Embedded();
		nodeIcon.setImmediate(false);
		nodeIcon.setWidth("-1px");
		nodeIcon.setHeight("-1px");
		nodeIcon.setSource(new ThemeResource("img/component/embedded_icon.png"));
		nodeIcon.setType(1);
		nodeIcon.setMimeType("image/png");
		palletteGrid.addComponent(nodeIcon, 0, 2);
		
		// imageIcon
		imageIcon = new Embedded();
		imageIcon.setImmediate(false);
		imageIcon.setWidth("-1px");
		imageIcon.setHeight("-1px");
		imageIcon
				.setSource(new ThemeResource("img/component/embedded_icon.png"));
		imageIcon.setType(1);
		imageIcon.setMimeType("image/png");
		palletteGrid.addComponent(imageIcon, 1, 2);
		
		return palletteGrid;
	}

	@AutoGenerated
	private Panel buildPropertiesPanel() {
		// common part: create layout
		propertiesPanel = new Panel();
		propertiesPanel.setCaption("Properties");
		propertiesPanel.setImmediate(false);
		propertiesPanel.setWidth("100.0%");
		propertiesPanel.setHeight("100.0%");
		
		// verticalLayout_3
		verticalLayout_3 = new VerticalLayout();
		verticalLayout_3.setImmediate(false);
		verticalLayout_3.setWidth("100.0%");
		verticalLayout_3.setHeight("100.0%");
		verticalLayout_3.setMargin(false);
		propertiesPanel.setContent(verticalLayout_3);
		
		return propertiesPanel;
	}

}
