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

import java.io.Serializable;

public abstract class Shape implements Serializable {
    protected float[] points;
    
    protected float[] center;
    
    protected float x;
    
    protected float y;
    
    protected float maxX;
    
    protected float maxY;
    
    protected float minX;
    
    protected float minY;
    
    protected float boundingCircleRadius;
    
    protected boolean pointsDirty = true;
    
    protected transient Triangulator tris;
    
    protected boolean trianglesDirty;
    
    public void setLocation(float x, float y) {
        setX(x);
        setY(y);
    }
    
    public abstract Shape transform(Transform paramTransform);
    
    protected abstract void createPoints();
    
    public float getX() {
        return this.x;
    }
    
    public void setX(float x) {
        if (x != this.x) {
            float dx = x - this.x;
            this.x = x;
            if (this.points == null || this.center == null)
                checkPoints(); 
            for (int i = 0; i < this.points.length / 2; i++)
                this.points[i * 2] = this.points[i * 2] + dx; 
            this.center[0] = this.center[0] + dx;
            x += dx;
            this.maxX += dx;
            this.minX += dx;
            this.trianglesDirty = true;
        } 
    }
    
    public void setY(float y) {
        if (y != this.y) {
            float dy = y - this.y;
            this.y = y;
            if (this.points == null || this.center == null)
                checkPoints(); 
            for (int i = 0; i < this.points.length / 2; i++)
                this.points[i * 2 + 1] = this.points[i * 2 + 1] + dy; 
            this.center[1] = this.center[1] + dy;
            y += dy;
            this.maxY += dy;
            this.minY += dy;
            this.trianglesDirty = true;
        } 
    }
    
    public float getY() {
        return this.y;
    }
    
    public Vector2f getLocation() {
        return new Vector2f(getX(), getY());
    }
    
    public void setLocation(Vector2f loc) {
        setX(loc.x);
        setY(loc.y);
    }
    
    public float getCenterX() {
        checkPoints();
        return this.center[0];
    }
    
    public void setCenterX(float centerX) {
        if (this.points == null || this.center == null)
            checkPoints(); 
        float xDiff = centerX - getCenterX();
        setX(this.x + xDiff);
    }
    
    public float getCenterY() {
        checkPoints();
        return this.center[1];
    }
    
    public void setCenterY(float centerY) {
        if (this.points == null || this.center == null)
            checkPoints(); 
        float yDiff = centerY - getCenterY();
        setY(this.y + yDiff);
    }
    
    public float getMaxX() {
        checkPoints();
        return this.maxX;
    }
    
    public float getMaxY() {
        checkPoints();
        return this.maxY;
    }
    
    public float getMinX() {
        checkPoints();
        return this.minX;
    }
    
    public float getMinY() {
        checkPoints();
        return this.minY;
    }
    
    public float getBoundingCircleRadius() {
        checkPoints();
        return this.boundingCircleRadius;
    }
    
    public float[] getCenter() {
        checkPoints();
        return this.center;
    }
    
    public float[] getPoints() {
        checkPoints();
        return this.points;
    }
    
    public int getPointCount() {
        checkPoints();
        return this.points.length / 2;
    }
    
    public float[] getPoint(int index) {
        checkPoints();
        float[] result = new float[2];
        result[0] = this.points[index * 2];
        result[1] = this.points[index * 2 + 1];
        return result;
    }
    
    public float[] getNormal(int index) {
        float[] current = getPoint(index);
        float[] prev = getPoint((index - 1 < 0) ? (getPointCount() - 1) : (index - 1));
        float[] next = getPoint((index + 1 >= getPointCount()) ? 0 : (index + 1));
        float[] t1 = getNormal(prev, current);
        float[] t2 = getNormal(current, next);
        if (index == 0 && !closed())
            return t2; 
        if (index == getPointCount() - 1 && !closed())
            return t1; 
        float tx = (t1[0] + t2[0]) / 2.0F;
        float ty = (t1[1] + t2[1]) / 2.0F;
        float len = (float)Math.sqrt((tx * tx + ty * ty));
        return new float[] { tx / len, ty / len };
    }
    
    public boolean contains(Shape other) {
        if (other.intersects(this))
            return false; 
        for (int i = 0; i < other.getPointCount(); i++) {
            float[] pt = other.getPoint(i);
            if (!contains(pt[0], pt[1]))
                return false; 
        } 
        return true;
    }
    
    private float[] getNormal(float[] start, float[] end) {
        float dx = start[0] - end[0];
        float dy = start[1] - end[1];
        float len = (float)Math.sqrt((dx * dx + dy * dy));
        dx /= len;
        dy /= len;
        return new float[] { -dy, dx };
    }
    
    public boolean includes(float x, float y) {
        if (this.points.length == 0)
            return false; 
        checkPoints();
        Line testLine = new Line(0.0F, 0.0F, 0.0F, 0.0F);
        Vector2f pt = new Vector2f(x, y);
        for (int i = 0; i < this.points.length; i += 2) {
            int n = i + 2;
            if (n >= this.points.length)
                n = 0; 
            testLine.set(this.points[i], this.points[i + 1], this.points[n], this.points[n + 1]);
            if (testLine.on(pt))
                return true; 
        } 
        return false;
    }
    
    public int indexOf(float x, float y) {
        for (int i = 0; i < this.points.length; i += 2) {
            if (this.points[i] == x && this.points[i + 1] == y)
                return i / 2; 
        } 
        return -1;
    }
    
    public boolean contains(float x, float y) {
        checkPoints();
        if (this.points.length == 0)
            return false; 
        boolean result = false;
        int npoints = this.points.length;
        float xold = this.points[npoints - 2];
        float yold = this.points[npoints - 1];
        for (int i = 0; i < npoints; i += 2) {
            float x1, y1, x2, y2, xnew = this.points[i];
            float ynew = this.points[i + 1];
            if (xnew > xold) {
                x1 = xold;
                x2 = xnew;
                y1 = yold;
                y2 = ynew;
            } else {
                x1 = xnew;
                x2 = xold;
                y1 = ynew;
                y2 = yold;
            } 
            if (((xnew < x) ? true : false) == ((x <= xold) ? true : false) && (y - y1) * (x2 - x1) < (y2 - y1) * (x - x1))
                result = !result; 
            xold = xnew;
            yold = ynew;
        } 
        return result;
    }
    
    public boolean intersects(Shape shape) {
        checkPoints();
        boolean result = false;
        float[] points = getPoints();
        float[] thatPoints = shape.getPoints();
        int length = points.length;
        int thatLength = thatPoints.length;
        if (!closed())
            length -= 2; 
        if (!shape.closed())
            thatLength -= 2; 
        for (int i = 0; i < length; i += 2) {
            int iNext = i + 2;
            if (iNext >= points.length)
                iNext = 0; 
            for (int j = 0; j < thatLength; j += 2) {
                int jNext = j + 2;
                if (jNext >= thatPoints.length)
                    jNext = 0; 
                double unknownA = ((points[iNext] - points[i]) * (thatPoints[j + 1] - points[i + 1]) - ((points[iNext + 1] - points[i + 1]) * (thatPoints[j] - points[i]))) / ((points[iNext + 1] - points[i + 1]) * (thatPoints[jNext] - thatPoints[j]) - (points[iNext] - points[i]) * (thatPoints[jNext + 1] - thatPoints[j + 1]));
                double unknownB = ((thatPoints[jNext] - thatPoints[j]) * (thatPoints[j + 1] - points[i + 1]) - ((thatPoints[jNext + 1] - thatPoints[j + 1]) * (thatPoints[j] - points[i]))) / ((points[iNext + 1] - points[i + 1]) * (thatPoints[jNext] - thatPoints[j]) - (points[iNext] - points[i]) * (thatPoints[jNext + 1] - thatPoints[j + 1]));
                if (unknownA >= 0.0D && unknownA <= 1.0D && unknownB >= 0.0D && unknownB <= 1.0D) {
                    result = true;
                    break;
                } 
            } 
            if (result)
                break; 
        } 
        return result;
    }
    
    public boolean hasVertex(float x, float y) {
        if (this.points.length == 0)
            return false; 
        checkPoints();
        for (int i = 0; i < this.points.length; i += 2) {
            if (this.points[i] == x && this.points[i + 1] == y)
                return true; 
        } 
        return false;
    }
    
    protected void findCenter() {
        this.center = new float[] { 0.0F, 0.0F };
        int length = this.points.length;
        for (int i = 0; i < length; i += 2) {
            this.center[0] = this.center[0] + this.points[i];
            this.center[1] = this.center[1] + this.points[i + 1];
        } 
        this.center[0] = this.center[0] / (length / 2);
        this.center[1] = this.center[1] / (length / 2);
    }
    
    protected void calculateRadius() {
        this.boundingCircleRadius = 0.0F;
        for (int i = 0; i < this.points.length; i += 2) {
            float temp = (this.points[i] - this.center[0]) * (this.points[i] - this.center[0]) + (this.points[i + 1] - this.center[1]) * (this.points[i + 1] - this.center[1]);
            this.boundingCircleRadius = (this.boundingCircleRadius > temp) ? this.boundingCircleRadius : temp;
        } 
        this.boundingCircleRadius = (float)Math.sqrt(this.boundingCircleRadius);
    }
    
    protected void calculateTriangles() {
        if (!this.trianglesDirty && this.tris != null)
            return; 
        if (this.points.length >= 6) {
            boolean clockwise = true;
            float area = 0.0F;
            int i;
            for (i = 0; i < this.points.length / 2 - 1; i++) {
                float x1 = this.points[i * 2];
                float y1 = this.points[i * 2 + 1];
                float x2 = this.points[i * 2 + 2];
                float y2 = this.points[i * 2 + 3];
                area += x1 * y2 - y1 * x2;
            } 
            area /= 2.0F;
            clockwise = (area > 0.0F);
            this.tris = new NeatTriangulator();
            for (i = 0; i < this.points.length; i += 2)
                this.tris.addPolyPoint(this.points[i], this.points[i + 1]); 
            this.tris.triangulate();
        } 
        this.trianglesDirty = false;
    }
    
    public void increaseTriangulation() {
        checkPoints();
        calculateTriangles();
        this.tris = new OverTriangulator(this.tris);
    }
    
    public Triangulator getTriangles() {
        checkPoints();
        calculateTriangles();
        return this.tris;
    }
    
    protected final void checkPoints() {
        if (this.pointsDirty) {
            createPoints();
            findCenter();
            calculateRadius();
            if (this.points.length > 0) {
                this.maxX = this.points[0];
                this.maxY = this.points[1];
                this.minX = this.points[0];
                this.minY = this.points[1];
                for (int i = 0; i < this.points.length / 2; i++) {
                    this.maxX = Math.max(this.points[i * 2], this.maxX);
                    this.maxY = Math.max(this.points[i * 2 + 1], this.maxY);
                    this.minX = Math.min(this.points[i * 2], this.minX);
                    this.minY = Math.min(this.points[i * 2 + 1], this.minY);
                } 
            } 
            this.pointsDirty = false;
            this.trianglesDirty = true;
        } 
    }
    
    public void preCache() {
        checkPoints();
        getTriangles();
    }
    
    public boolean closed() {
        return true;
    }
    
    public Shape prune() {
        Polygon result = new Polygon();
        for (int i = 0; i < getPointCount(); i++) {
            int next = (i + 1 >= getPointCount()) ? 0 : (i + 1);
            int prev = (i - 1 < 0) ? (getPointCount() - 1) : (i - 1);
            float dx1 = getPoint(i)[0] - getPoint(prev)[0];
            float dy1 = getPoint(i)[1] - getPoint(prev)[1];
            float dx2 = getPoint(next)[0] - getPoint(i)[0];
            float dy2 = getPoint(next)[1] - getPoint(i)[1];
            float len1 = (float)Math.sqrt((dx1 * dx1 + dy1 * dy1));
            float len2 = (float)Math.sqrt((dx2 * dx2 + dy2 * dy2));
            dx1 /= len1;
            dy1 /= len1;
            dx2 /= len2;
            dy2 /= len2;
            if (dx1 != dx2 || dy1 != dy2)
                result.addPoint(getPoint(i)[0], getPoint(i)[1]); 
        } 
        return result;
    }
    
    public Shape[] subtract(Shape other) {
        return (new GeomUtil()).subtract(this, other);
    }
    
    public Shape[] union(Shape other) {
        return (new GeomUtil()).union(this, other);
    }
    
    public float getWidth() {
        return this.maxX - this.minX;
    }
    
    public float getHeight() {
        return this.maxY - this.minY;
    }
}
