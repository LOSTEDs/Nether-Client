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

public class Circle extends Ellipse {
    public float radius;
    
    public strictfp Circle(float centerPointX, float centerPointY, float radius) {
        this(centerPointX, centerPointY, radius, 50);
    }
    
    public strictfp Circle(float centerPointX, float centerPointY, float radius, int segmentCount) {
        super(centerPointX, centerPointY, radius, radius, segmentCount);
        this.x = centerPointX - radius;
        this.y = centerPointY - radius;
        this.radius = radius;
        this.boundingCircleRadius = radius;
    }
    
    public strictfp float getCenterX() {
        return getX() + this.radius;
    }
    
    public strictfp float getCenterY() {
        return getY() + this.radius;
    }
    
    public strictfp float[] getCenter() {
        return new float[] { getCenterX(), getCenterY() };
    }
    
    public strictfp void setRadius(float radius) {
        if (radius != this.radius) {
            this.pointsDirty = true;
            this.radius = radius;
            setRadii(radius, radius);
        } 
    }
    
    public strictfp float getRadius() {
        return this.radius;
    }
    
    public strictfp boolean intersects(Shape shape) {
        if (shape instanceof Circle) {
            Circle other = (Circle)shape;
            float totalRad2 = getRadius() + other.getRadius();
            if (Math.abs(other.getCenterX() - getCenterX()) > totalRad2)
                return false; 
            if (Math.abs(other.getCenterY() - getCenterY()) > totalRad2)
                return false; 
            totalRad2 *= totalRad2;
            float dx = Math.abs(other.getCenterX() - getCenterX());
            float dy = Math.abs(other.getCenterY() - getCenterY());
            return (totalRad2 >= dx * dx + dy * dy);
        } 
        if (shape instanceof Rectangle)
            return intersects((Rectangle)shape); 
        return super.intersects(shape);
    }
    
    public strictfp boolean contains(float x, float y) {
        float xDelta = x - getCenterX(), yDelta = y - getCenterY();
        return (xDelta * xDelta + yDelta * yDelta < getRadius() * getRadius());
    }
    
    private strictfp boolean contains(Line line) {
        return (contains(line.getX1(), line.getY1()) && contains(line.getX2(), line.getY2()));
    }
    
    protected strictfp void findCenter() {
        this.center = new float[2];
        this.center[0] = this.x + this.radius;
        this.center[1] = this.y + this.radius;
    }
    
    protected strictfp void calculateRadius() {
        this.boundingCircleRadius = this.radius;
    }
    
    private strictfp boolean intersects(Rectangle other) {
        Rectangle box = other;
        Circle circle = this;
        if (box.contains(this.x + this.radius, this.y + this.radius))
            return true; 
        float x1 = box.getX();
        float y1 = box.getY();
        float x2 = box.getX() + box.getWidth();
        float y2 = box.getY() + box.getHeight();
        Line[] lines = new Line[4];
        lines[0] = new Line(x1, y1, x2, y1);
        lines[1] = new Line(x2, y1, x2, y2);
        lines[2] = new Line(x2, y2, x1, y2);
        lines[3] = new Line(x1, y2, x1, y1);
        float r2 = circle.getRadius() * circle.getRadius();
        Vector2f pos = new Vector2f(circle.getCenterX(), circle.getCenterY());
        for (int i = 0; i < 4; i++) {
            float dis = lines[i].distanceSquared(pos);
            if (dis < r2)
                return true; 
        } 
        return false;
    }
    
    private strictfp boolean intersects(Line other) {
        Vector2f closest, lineSegmentStart = new Vector2f(other.getX1(), other.getY1());
        Vector2f lineSegmentEnd = new Vector2f(other.getX2(), other.getY2());
        Vector2f circleCenter = new Vector2f(getCenterX(), getCenterY());
        Vector2f segv = lineSegmentEnd.copy().sub(lineSegmentStart);
        Vector2f ptv = circleCenter.copy().sub(lineSegmentStart);
        float segvLength = segv.length();
        float projvl = ptv.dot(segv) / segvLength;
        if (projvl < 0.0F) {
            closest = lineSegmentStart;
        } else if (projvl > segvLength) {
            closest = lineSegmentEnd;
        } else {
            Vector2f projv = segv.copy().scale(projvl / segvLength);
            closest = lineSegmentStart.copy().add(projv);
        } 
        boolean intersects = (circleCenter.copy().sub(closest).lengthSquared() <= getRadius() * getRadius());
        return intersects;
    }
}
