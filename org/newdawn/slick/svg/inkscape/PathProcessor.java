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
import org.newdawn.slick.geom.Path;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.geom.Transform;
import org.newdawn.slick.svg.Diagram;
import org.newdawn.slick.svg.Figure;
import org.newdawn.slick.svg.Loader;
import org.newdawn.slick.svg.NonGeometricData;
import org.newdawn.slick.svg.ParsingException;
import org.w3c.dom.Element;

public class PathProcessor implements ElementProcessor {
    private static Path processPoly(Element element, StringTokenizer tokens) throws ParsingException {
        int count = 0;
        ArrayList pts = new ArrayList();
        boolean moved = false;
        boolean reasonToBePath = false;
        Path path = null;
        while (tokens.hasMoreTokens()) {
            try {
                String nextToken = tokens.nextToken();
                if (nextToken.equals("L")) {
                    float x = Float.parseFloat(tokens.nextToken());
                    float y = Float.parseFloat(tokens.nextToken());
                    path.lineTo(x, y);
                    continue;
                } 
                if (nextToken.equals("z")) {
                    path.close();
                    continue;
                } 
                if (nextToken.equals("M")) {
                    if (!moved) {
                        moved = true;
                        float f1 = Float.parseFloat(tokens.nextToken());
                        float f2 = Float.parseFloat(tokens.nextToken());
                        path = new Path(f1, f2);
                        continue;
                    } 
                    reasonToBePath = true;
                    float x = Float.parseFloat(tokens.nextToken());
                    float y = Float.parseFloat(tokens.nextToken());
                    path.startHole(x, y);
                    continue;
                } 
                if (nextToken.equals("C")) {
                    reasonToBePath = true;
                    float cx1 = Float.parseFloat(tokens.nextToken());
                    float cy1 = Float.parseFloat(tokens.nextToken());
                    float cx2 = Float.parseFloat(tokens.nextToken());
                    float cy2 = Float.parseFloat(tokens.nextToken());
                    float x = Float.parseFloat(tokens.nextToken());
                    float y = Float.parseFloat(tokens.nextToken());
                    path.curveTo(x, y, cx1, cy1, cx2, cy2);
                } 
            } catch (NumberFormatException e) {
                throw new ParsingException(element.getAttribute("id"), "Invalid token in points list", e);
            } 
        } 
        if (!reasonToBePath)
            return null; 
        return path;
    }
    
    public void process(Loader loader, Element element, Diagram diagram, Transform t) throws ParsingException {
        Transform transform = Util.getTransform(element);
        transform = new Transform(t, transform);
        String points = element.getAttribute("points");
        if (element.getNodeName().equals("path"))
            points = element.getAttribute("d"); 
        StringTokenizer tokens = new StringTokenizer(points, ", ");
        Path path = processPoly(element, tokens);
        NonGeometricData data = Util.getNonGeometricData(element);
        if (path != null) {
            Shape shape = path.transform(transform);
            diagram.addFigure(new Figure(4, shape, data, transform));
        } 
    }
    
    public boolean handles(Element element) {
        if (element.getNodeName().equals("path") && 
            !"arc".equals(element.getAttributeNS("http://sodipodi.sourceforge.net/DTD/sodipodi-0.dtd", "type")))
            return true; 
        return false;
    }
}
