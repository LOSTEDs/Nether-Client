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

import java.util.StringTokenizer;
import org.newdawn.slick.geom.Line;
import org.newdawn.slick.geom.Polygon;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.geom.Transform;
import org.newdawn.slick.svg.Diagram;
import org.newdawn.slick.svg.Figure;
import org.newdawn.slick.svg.Loader;
import org.newdawn.slick.svg.NonGeometricData;
import org.newdawn.slick.svg.ParsingException;
import org.w3c.dom.Element;

public class LineProcessor implements ElementProcessor {
    private static int processPoly(Polygon poly, Element element, StringTokenizer tokens) throws ParsingException {
        int count = 0;
        while (tokens.hasMoreTokens()) {
            String nextToken = tokens.nextToken();
            if (nextToken.equals("L"))
                continue; 
            if (nextToken.equals("z"))
                break; 
            if (nextToken.equals("M"))
                continue; 
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
        return count;
    }
    
    public void process(Loader loader, Element element, Diagram diagram, Transform t) throws ParsingException {
        float x1, y1, x2, y2;
        Transform transform = Util.getTransform(element);
        transform = new Transform(t, transform);
        if (element.getNodeName().equals("line")) {
            x1 = Float.parseFloat(element.getAttribute("x1"));
            x2 = Float.parseFloat(element.getAttribute("x2"));
            y1 = Float.parseFloat(element.getAttribute("y1"));
            y2 = Float.parseFloat(element.getAttribute("y2"));
        } else {
            String points = element.getAttribute("d");
            StringTokenizer tokens = new StringTokenizer(points, ", ");
            Polygon poly = new Polygon();
            if (processPoly(poly, element, tokens) == 2) {
                x1 = poly.getPoint(0)[0];
                y1 = poly.getPoint(0)[1];
                x2 = poly.getPoint(1)[0];
                y2 = poly.getPoint(1)[1];
            } else {
                return;
            } 
        } 
        float[] in = { x1, y1, x2, y2 };
        float[] out = new float[4];
        transform.transform(in, 0, out, 0, 2);
        Line line = new Line(out[0], out[1], out[2], out[3]);
        NonGeometricData data = Util.getNonGeometricData(element);
        data.addAttribute("x1", "" + x1);
        data.addAttribute("x2", "" + x2);
        data.addAttribute("y1", "" + y1);
        data.addAttribute("y2", "" + y2);
        diagram.addFigure(new Figure(2, (Shape)line, data, transform));
    }
    
    public boolean handles(Element element) {
        if (element.getNodeName().equals("line"))
            return true; 
        if (element.getNodeName().equals("path") && 
            !"arc".equals(element.getAttributeNS("http://sodipodi.sourceforge.net/DTD/sodipodi-0.dtd", "type")))
            return true; 
        return false;
    }
}
