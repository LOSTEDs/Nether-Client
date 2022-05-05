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

import java.util.ArrayList;
import java.util.StringTokenizer;
import org.newdawn.slick.geom.Polygon;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.geom.Transform;
import org.newdawn.slick.svg.Diagram;
import org.newdawn.slick.svg.Figure;
import org.newdawn.slick.svg.Loader;
import org.newdawn.slick.svg.NonGeometricData;
import org.newdawn.slick.svg.ParsingException;
import org.w3c.dom.Element;

public class PolygonProcessor implements ElementProcessor {
    private static int processPoly(Polygon poly, Element element, StringTokenizer tokens) throws ParsingException {
        int count = 0;
        ArrayList pts = new ArrayList();
        boolean moved = false;
        boolean closed = false;
        while (tokens.hasMoreTokens()) {
            String nextToken = tokens.nextToken();
            if (nextToken.equals("L"))
                continue; 
            if (nextToken.equals("z")) {
                closed = true;
                break;
            } 
            if (nextToken.equals("M")) {
                if (!moved) {
                    moved = true;
                    continue;
                } 
                return 0;
            } 
            if (nextToken.equals("C"))
                return 0; 
            String tokenX = nextToken;
            String tokenY = tokens.nextToken();
            try {
                float x = Float.parseFloat(tokenX);
                float y = Float.parseFloat(tokenY);
                poly.addPoint(x, y);
                count++;
            } catch (NumberFormatException e) {
                throw new ParsingException(element.getAttribute("id"), "Invalid token in points list", e);
            } 
        } 
        poly.setClosed(closed);
        return count;
    }
    
    public void process(Loader loader, Element element, Diagram diagram, Transform t) throws ParsingException {
        Transform transform = Util.getTransform(element);
        transform = new Transform(t, transform);
        String points = element.getAttribute("points");
        if (element.getNodeName().equals("path"))
            points = element.getAttribute("d"); 
        StringTokenizer tokens = new StringTokenizer(points, ", ");
        Polygon poly = new Polygon();
        int count = processPoly(poly, element, tokens);
        NonGeometricData data = Util.getNonGeometricData(element);
        if (count > 3) {
            Shape shape = poly.transform(transform);
            diagram.addFigure(new Figure(5, shape, data, transform));
        } 
    }
    
    public boolean handles(Element element) {
        if (element.getNodeName().equals("polygon"))
            return true; 
        if (element.getNodeName().equals("path") && 
            !"arc".equals(element.getAttributeNS("http://sodipodi.sourceforge.net/DTD/sodipodi-0.dtd", "type")))
            return true; 
        return false;
    }
}
