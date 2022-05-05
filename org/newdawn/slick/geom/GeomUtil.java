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

package org.newdawn.slick.geom;

import java.util.ArrayList;

public class GeomUtil {
    public float EPSILON = 1.0E-4F;
    
    public float EDGE_SCALE = 1.0F;
    
    public int MAX_POINTS = 10000;
    
    public GeomUtilListener listener;
    
    public Shape[] subtract(Shape target, Shape missing) {
        target = target.transform(new Transform());
        missing = missing.transform(new Transform());
        int count = 0;
        for (int i = 0; i < target.getPointCount(); i++) {
            if (missing.contains(target.getPoint(i)[0], target.getPoint(i)[1]))
                count++; 
        } 
        if (count == target.getPointCount())
            return new Shape[0]; 
        if (!target.intersects(missing))
            return new Shape[] { target }; 
        int found = 0;
        int j;
        for (j = 0; j < missing.getPointCount(); j++) {
            if (target.contains(missing.getPoint(j)[0], missing.getPoint(j)[1]) && 
                !onPath(target, missing.getPoint(j)[0], missing.getPoint(j)[1]))
                found++; 
        } 
        for (j = 0; j < target.getPointCount(); j++) {
            if (missing.contains(target.getPoint(j)[0], target.getPoint(j)[1]) && 
                !onPath(missing, target.getPoint(j)[0], target.getPoint(j)[1]))
                found++; 
        } 
        if (found < 1)
            return new Shape[] { target }; 
        return combine(target, missing, true);
    }
    
    private boolean onPath(Shape path, float x, float y) {
        for (int i = 0; i < path.getPointCount() + 1; i++) {
            int n = rationalPoint(path, i + 1);
            Line line = getLine(path, rationalPoint(path, i), n);
            if (line.distance(new Vector2f(x, y)) < this.EPSILON * 100.0F)
                return true; 
        } 
        return false;
    }
    
    public void setListener(GeomUtilListener listener) {
        this.listener = listener;
    }
    
    public Shape[] union(Shape target, Shape other) {
        target = target.transform(new Transform());
        other = other.transform(new Transform());
        if (!target.intersects(other))
            return new Shape[] { target, other }; 
        boolean touches = false;
        int buttCount = 0;
        int i;
        for (i = 0; i < target.getPointCount(); i++) {
            if (other.contains(target.getPoint(i)[0], target.getPoint(i)[1]) && 
                !other.hasVertex(target.getPoint(i)[0], target.getPoint(i)[1])) {
                touches = true;
                break;
            } 
            if (other.hasVertex(target.getPoint(i)[0], target.getPoint(i)[1]))
                buttCount++; 
        } 
        for (i = 0; i < other.getPointCount(); i++) {
            if (target.contains(other.getPoint(i)[0], other.getPoint(i)[1]) && 
                !target.hasVertex(other.getPoint(i)[0], other.getPoint(i)[1])) {
                touches = true;
                break;
            } 
        } 
        if (!touches && buttCount < 2)
            return new Shape[] { target, other }; 
        return combine(target, other, false);
    }
    
    private Shape[] combine(Shape target, Shape other, boolean subtract) {
        if (subtract) {
            ArrayList<Shape> shapes = new ArrayList();
            ArrayList<Vector2f> used = new ArrayList();
            int j;
            for (j = 0; j < target.getPointCount(); j++) {
                float[] point = target.getPoint(j);
                if (other.contains(point[0], point[1])) {
                    used.add(new Vector2f(point[0], point[1]));
                    if (this.listener != null)
                        this.listener.pointExcluded(point[0], point[1]); 
                } 
            } 
            for (j = 0; j < target.getPointCount(); j++) {
                float[] point = target.getPoint(j);
                Vector2f pt = new Vector2f(point[0], point[1]);
                if (!used.contains(pt)) {
                    Shape result = combineSingle(target, other, true, j);
                    shapes.add(result);
                    for (int k = 0; k < result.getPointCount(); k++) {
                        float[] kpoint = result.getPoint(k);
                        Vector2f kpt = new Vector2f(kpoint[0], kpoint[1]);
                        used.add(kpt);
                    } 
                } 
            } 
            return shapes.<Shape>toArray(new Shape[0]);
        } 
        for (int i = 0; i < target.getPointCount(); i++) {
            if (!other.contains(target.getPoint(i)[0], target.getPoint(i)[1]) && 
                !other.hasVertex(target.getPoint(i)[0], target.getPoint(i)[1])) {
                Shape shape = combineSingle(target, other, false, i);
                return new Shape[] { shape };
            } 
        } 
        return new Shape[] { other };
    }
    
    private Shape combineSingle(Shape target, Shape missing, boolean subtract, int start) {
        Shape current = target;
        Shape other = missing;
        int point = start;
        int dir = 1;
        Polygon poly = new Polygon();
        boolean first = true;
        int loop = 0;
        float px = current.getPoint(point)[0];
        float py = current.getPoint(point)[1];
        while (!poly.hasVertex(px, py) || first || current != target) {
            first = false;
            loop++;
            if (loop > this.MAX_POINTS)
                break; 
            poly.addPoint(px, py);
            if (this.listener != null)
                this.listener.pointUsed(px, py); 
            Line line = getLine(current, px, py, rationalPoint(current, point + dir));
            HitResult hit = intersect(other, line);
            if (hit != null) {
                Line hitLine = hit.line;
                Vector2f pt = hit.pt;
                px = pt.x;
                py = pt.y;
                if (this.listener != null)
                    this.listener.pointIntersected(px, py); 
                if (other.hasVertex(px, py)) {
                    point = other.indexOf(pt.x, pt.y);
                    dir = 1;
                    px = pt.x;
                    py = pt.y;
                    Shape shape = current;
                    current = other;
                    other = shape;
                    continue;
                } 
                float dx = hitLine.getDX() / hitLine.length();
                float dy = hitLine.getDY() / hitLine.length();
                dx *= this.EDGE_SCALE;
                dy *= this.EDGE_SCALE;
                if (current.contains(pt.x + dx, pt.y + dy)) {
                    if (subtract) {
                        if (current == missing) {
                            point = hit.p2;
                            dir = -1;
                        } else {
                            point = hit.p1;
                            dir = 1;
                        } 
                    } else if (current == target) {
                        point = hit.p2;
                        dir = -1;
                    } else {
                        point = hit.p2;
                        dir = -1;
                    } 
                    Shape shape = current;
                    current = other;
                    other = shape;
                    continue;
                } 
                if (current.contains(pt.x - dx, pt.y - dy)) {
                    if (subtract) {
                        if (current == target) {
                            point = hit.p2;
                            dir = -1;
                        } else {
                            point = hit.p1;
                            dir = 1;
                        } 
                    } else if (current == missing) {
                        point = hit.p1;
                        dir = 1;
                    } else {
                        point = hit.p1;
                        dir = 1;
                    } 
                    Shape shape = current;
                    current = other;
                    other = shape;
                    continue;
                } 
                if (subtract)
                    break; 
                point = hit.p1;
                dir = 1;
                Shape temp = current;
                current = other;
                other = temp;
                point = rationalPoint(current, point + dir);
                px = current.getPoint(point)[0];
                py = current.getPoint(point)[1];
                continue;
            } 
            point = rationalPoint(current, point + dir);
            px = current.getPoint(point)[0];
            py = current.getPoint(point)[1];
        } 
        poly.addPoint(px, py);
        if (this.listener != null)
            this.listener.pointUsed(px, py); 
        return poly;
    }
    
    public HitResult intersect(Shape shape, Line line) {
        float distance = Float.MAX_VALUE;
        HitResult hit = null;
        for (int i = 0; i < shape.getPointCount(); i++) {
            int next = rationalPoint(shape, i + 1);
            Line local = getLine(shape, i, next);
            Vector2f pt = line.intersect(local, true);
            if (pt != null) {
                float newDis = pt.distance(line.getStart());
                if (newDis < distance && newDis > this.EPSILON) {
                    hit = new HitResult();
                    hit.pt = pt;
                    hit.line = local;
                    hit.p1 = i;
                    hit.p2 = next;
                    distance = newDis;
                } 
            } 
        } 
        return hit;
    }
    
    public static int rationalPoint(Shape shape, int p) {
        while (p < 0)
            p += shape.getPointCount(); 
        while (p >= shape.getPointCount())
            p -= shape.getPointCount(); 
        return p;
    }
    
    public Line getLine(Shape shape, int s, int e) {
        float[] start = shape.getPoint(s);
        float[] end = shape.getPoint(e);
        Line line = new Line(start[0], start[1], end[0], end[1]);
        return line;
    }
    
    public Line getLine(Shape shape, float sx, float sy, int e) {
        float[] end = shape.getPoint(e);
        Line line = new Line(sx, sy, end[0], end[1]);
        return line;
    }
    
    public class HitResult {
        public Line line;
        
        public int p1;
        
        public int p2;
        
        public Vector2f pt;
    }
}
