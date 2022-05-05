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

public class Point extends Shape {
    public Point(float x, float y) {
        this.x = x;
        this.y = y;
        checkPoints();
    }
    
    public Shape transform(Transform transform) {
        float[] result = new float[this.points.length];
        transform.transform(this.points, 0, result, 0, this.points.length / 2);
        return new Point(this.points[0], this.points[1]);
    }
    
    protected void createPoints() {
        this.points = new float[2];
        this.points[0] = getX();
        this.points[1] = getY();
        this.maxX = this.x;
        this.maxY = this.y;
        this.minX = this.x;
        this.minY = this.y;
        findCenter();
        calculateRadius();
    }
    
    protected void findCenter() {
        this.center = new float[2];
        this.center[0] = this.points[0];
        this.center[1] = this.points[1];
    }
    
    protected void calculateRadius() {
        this.boundingCircleRadius = 0.0F;
    }
}
