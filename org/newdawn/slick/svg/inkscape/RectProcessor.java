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

import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.geom.Transform;
import org.newdawn.slick.svg.Diagram;
import org.newdawn.slick.svg.Figure;
import org.newdawn.slick.svg.Loader;
import org.newdawn.slick.svg.NonGeometricData;
import org.newdawn.slick.svg.ParsingException;
import org.w3c.dom.Element;

public class RectProcessor implements ElementProcessor {
    public void process(Loader loader, Element element, Diagram diagram, Transform t) throws ParsingException {
        Transform transform = Util.getTransform(element);
        transform = new Transform(t, transform);
        float width = Float.parseFloat(element.getAttribute("width"));
        float height = Float.parseFloat(element.getAttribute("height"));
        float x = Float.parseFloat(element.getAttribute("x"));
        float y = Float.parseFloat(element.getAttribute("y"));
        Rectangle rect = new Rectangle(x, y, width + 1.0F, height + 1.0F);
        Shape shape = rect.transform(transform);
        NonGeometricData data = Util.getNonGeometricData(element);
        data.addAttribute("width", "" + width);
        data.addAttribute("height", "" + height);
        data.addAttribute("x", "" + x);
        data.addAttribute("y", "" + y);
        diagram.addFigure(new Figure(3, shape, data, transform));
    }
    
    public boolean handles(Element element) {
        if (element.getNodeName().equals("rect"))
            return true; 
        return false;
    }
}
