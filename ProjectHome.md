Canvas widget based on vaadin and GWT. Supports simple shapes (rectangle, arc, circle etc) and events.

Sample application using this canvas is available at http://veldev.com:8090/VaadinCanvas.

---

Sept 20, 2011 - Updated the demo application to show the following changes:

  1. Added type enumeration to the elements:
    * OPORT - Output port
    * IPORT - Input port
    * IOPORT - Input/Output port
    * CONNECTOR - Connector element
    * ELEMENT - Normal UI element
  1. Smoother drag effect. Removed defect with the drag.
  1. Added Line UI Element.
  1. Implementation of Drag support for Circle (Arc with angle 0 to 2\*Math.PI)
  1. Implementation of Drag for Line
  1. Highlighting of PORT elements (Click on the right port (small circle) of the Test Node and see the color change only for the port).

Upcoming changes:
  1. Functionality to add CONNECTOR from OPORT/IOPORT to IPORT/IOPORT using drag and drop.


---

Sept 1st, 2011 - This version shows the functionality of Element Group, Text Element and Mouse Click events.