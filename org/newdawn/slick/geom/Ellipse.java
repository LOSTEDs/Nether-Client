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
import org.newdawn.slick.util.FastTrig;

public class Ellipse extends Shape {
    protected static final int DEFAULT_SEGMENT_COUNT = 50;
    
    private int segmentCount;
    
    private float radius1;
    
    private float radius2;
    
    public Ellipse(float centerPointX, float centerPointY, float radius1, float radius2) {
        this(centerPointX, centerPointY, radius1, radius2, 50);
    }
    
    public Ellipse(float centerPointX, float centerPointY, float radius1, float radius2, int segmentCount) {
        this.x = centerPointX - radius1;
        this.y = centerPointY - radius2;
        this.radius1 = radius1;
        this.radius2 = radius2;
        this.segmentCount = segmentCount;
        checkPoints();
    }
    
    public void setRadii(float radius1, float radius2) {
        setRadius1(radius1);
        setRadius2(radius2);
    }
    
    public float getRadius1() {
        return this.radius1;
    }
    
    public void setRadius1(float radius1) {
        if (radius1 != this.radius1) {
            this.radius1 = radius1;
            this.pointsDirty = true;
        } 
    }
    
    public float getRadius2() {
        return this.radius2;
    }
    
    public void setRadius2(float radius2) {
        if (radius2 != this.radius2) {
            this.radius2 = radius2;
            this.pointsDirty = true;
        } 
    }
    
    protected void createPoints() {
        ArrayList<Float> tempPoints = new ArrayList();
        this.maxX = -1.4E-45F;
        this.maxY = -1.4E-45F;
        this.minX = Float.MAX_VALUE;
        this.minY = Float.MAX_VALUE;
        float start = 0.0F;
        float end = 359.0F;
        float cx = this.x + this.radius1;
        float cy = this.y + this.radius2;
        int step = 360 / this.segmentCount;
        float a;
        for (a = start; a <= end + step; a += step) {
            float ang = a;
            if (ang > end)
                ang = end; 
            float newX = (float)(cx + FastTrig.cos(Math.toRadians(ang)) * this.radius1);
            float newY = (float)(cy + FastTrig.sin(Math.toRadians(ang)) * this.radius2);
            if (newX > this.maxX)
                this.maxX = newX; 
            if (newY > this.maxY)
                this.maxY = newY; 
            if (newX < this.minX)
                this.minX = newX; 
            if (newY < this.minY)
                this.minY = newY; 
            tempPoints.add(new Float(newX));
            tempPoints.add(new Float(newY));
        } 
        this.points = new float[tempPoints.size()];
        for (int i = 0; i < this.points.length; i++)
            this.points[i] = ((Float)tempPoints.get(i)).floatValue(); 
    }
    
    public Shape transform(Transform transform) {
        checkPoints();
        Polygon resultPolygon = new Polygon();
        float[] result = new float[this.points.length];
        transform.transform(this.points, 0, result, 0, this.points.length / 2);
        resultPolygon.points = result;
        resultPolygon.checkPoints();
        return resultPolygon;
    }
    
    protected void findCenter() {
        this.center = new float[2];
        this.center[0] = this.x + this.radius1;
        this.center[1] = this.y + this.radius2;
    }
    
    protected void calculateRadius() {
        this.boundingCircleRadius = (this.radius1 > this.radius2) ? this.radius1 : this.radius2;
    }
}
