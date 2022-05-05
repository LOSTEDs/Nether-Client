/*
Decompiled By LOSTED
https://github.com/LOSTEDs
LOSTED#8754
https://www.youtube.com/watch?v=xg2M21todDU&t=55s
"...Minecraft client created by professional developers exclusively for me..." - SuchSpeed
Here is a better way to say this, "...Minecraft client skidded by some random script kittens exclusively for me"
Please SuchSpeed, don't sue me... I just dumped the source...
For Educational Purposes Only...
*/

package org.newdawn.slick.svg.inkscape;

import org.newdawn.slick.geom.Ellipse;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.geom.Transform;
import org.newdawn.slick.svg.Diagram;
import org.newdawn.slick.svg.Figure;
import org.newdawn.slick.svg.Loader;
import org.newdawn.slick.svg.NonGeometricData;
import org.newdawn.slick.svg.ParsingException;
import org.w3c.dom.Element;

public class EllipseProcessor implements ElementProcessor {
    public void process(Loader loader, Element element, Diagram diagram, Transform t) throws ParsingException {
        Transform transform = Util.getTransform(element);
        transform = new Transform(t, transform);
        float x = Util.getFloatAttribute(element, "cx");
        float y = Util.getFloatAttribute(element, "cy");
        float rx = Util.getFloatAttribute(element, "rx");
        float ry = Util.getFloatAttribute(element, "ry");
        Ellipse ellipse = new Ellipse(x, y, rx, ry);
        Shape shape = ellipse.transform(transform);
        NonGeometricData data = Util.getNonGeometricData(element);
        data.addAttribute("cx", "" + x);
        data.addAttribute("cy", "" + y);
        data.addAttribute("rx", "" + rx);
        data.addAttribute("ry", "" + ry);
        diagram.addFigure(new Figure(1, shape, data, transform));
    }
    
    public boolean handles(Element element) {
        if (element.getNodeName().equals("ellipse"))
            return true; 
        if (element.getNodeName().equals("path") && 
            "arc".equals(element.getAttributeNS("http://sodipodi.sourceforge.net/DTD/sodipodi-0.dtd", "type")))
            return true; 
        return false;
    }
}
