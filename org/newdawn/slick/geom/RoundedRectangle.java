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
import java.util.List;
import org.newdawn.slick.util.FastTrig;

public class RoundedRectangle extends Rectangle {
    public static final int TOP_LEFT = 1;
    
    public static final int TOP_RIGHT = 2;
    
    public static final int BOTTOM_RIGHT = 4;
    
    public static final int BOTTOM_LEFT = 8;
    
    public static final int ALL = 15;
    
    private static final int DEFAULT_SEGMENT_COUNT = 25;
    
    private float cornerRadius;
    
    private int segmentCount;
    
    private int cornerFlags;
    
    public RoundedRectangle(float x, float y, float width, float height, float cornerRadius) {
        this(x, y, width, height, cornerRadius, 25);
    }
    
    public RoundedRectangle(float x, float y, float width, float height, float cornerRadius, int segmentCount) {
        this(x, y, width, height, cornerRadius, segmentCount, 15);
    }
    
    public RoundedRectangle(float x, float y, float width, float height, float cornerRadius, int segmentCount, int cornerFlags) {
        super(x, y, width, height);
        if (cornerRadius < 0.0F)
            throw new IllegalArgumentException("corner radius must be >= 0"); 
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.cornerRadius = cornerRadius;
        this.segmentCount = segmentCount;
        this.pointsDirty = true;
        this.cornerFlags = cornerFlags;
    }
    
    public float getCornerRadius() {
        return this.cornerRadius;
    }
    
    public void setCornerRadius(float cornerRadius) {
        if (cornerRadius >= 0.0F && 
            cornerRadius != this.cornerRadius) {
            this.cornerRadius = cornerRadius;
            this.pointsDirty = true;
        } 
    }
    
    public float getHeight() {
        return this.height;
    }
    
    public void setHeight(float height) {
        if (this.height != height) {
            this.height = height;
            this.pointsDirty = true;
        } 
    }
    
    public float getWidth() {
        return this.width;
    }
    
    public void setWidth(float width) {
        if (width != this.width) {
            this.width = width;
            this.pointsDirty = true;
        } 
    }
    
    protected void createPoints() {
        this.maxX = this.x + this.width;
        this.maxY = this.y + this.height;
        this.minX = this.x;
        this.minY = this.y;
        float useWidth = this.width - 1.0F;
        float useHeight = this.height - 1.0F;
        if (this.cornerRadius == 0.0F) {
            this.points = new float[8];
            this.points[0] = this.x;
            this.points[1] = this.y;
            this.points[2] = this.x + useWidth;
            this.points[3] = this.y;
            this.points[4] = this.x + useWidth;
            this.points[5] = this.y + useHeight;
            this.points[6] = this.x;
            this.points[7] = this.y + useHeight;
        } else {
            float doubleRadius = this.cornerRadius * 2.0F;
            if (doubleRadius > useWidth) {
                doubleRadius = useWidth;
                this.cornerRadius = doubleRadius / 2.0F;
            } 
            if (doubleRadius > useHeight) {
                doubleRadius = useHeight;
                this.cornerRadius = doubleRadius / 2.0F;
            } 
            ArrayList<Float> tempPoints = new ArrayList();
            if ((this.cornerFlags & 0x1) != 0) {
                tempPoints.addAll(createPoints(this.segmentCount, this.cornerRadius, this.x + this.cornerRadius, this.y + this.cornerRadius, 180.0F, 270.0F));
            } else {
                tempPoints.add(new Float(this.x));
                tempPoints.add(new Float(this.y));
            } 
            if ((this.cornerFlags & 0x2) != 0) {
                tempPoints.addAll(createPoints(this.segmentCount, this.cornerRadius, this.x + useWidth - this.cornerRadius, this.y + this.cornerRadius, 270.0F, 360.0F));
            } else {
                tempPoints.add(new Float(this.x + useWidth));
                tempPoints.add(new Float(this.y));
            } 
            if ((this.cornerFlags & 0x4) != 0) {
                tempPoints.addAll(createPoints(this.segmentCount, this.cornerRadius, this.x + useWidth - this.cornerRadius, this.y + useHeight - this.cornerRadius, 0.0F, 90.0F));
            } else {
                tempPoints.add(new Float(this.x + useWidth));
                tempPoints.add(new Float(this.y + useHeight));
            } 
            if ((this.cornerFlags & 0x8) != 0) {
                tempPoints.addAll(createPoints(this.segmentCount, this.cornerRadius, this.x + this.cornerRadius, this.y + useHeight - this.cornerRadius, 90.0F, 180.0F));
            } else {
                tempPoints.add(new Float(this.x));
                tempPoints.add(new Float(this.y + useHeight));
            } 
            this.points = new float[tempPoints.size()];
            for (int i = 0; i < tempPoints.size(); i++)
                this.points[i] = ((Float)tempPoints.get(i)).floatValue(); 
        } 
        findCenter();
        calculateRadius();
    }
    
    private List createPoints(int numberOfSegments, float radius, float cx, float cy, float start, float end) {
        ArrayList<Float> tempPoints = new ArrayList();
        int step = 360 / numberOfSegments;
        float a;
        for (a = start; a <= end + step; a += step) {
            float ang = a;
            if (ang > end)
                ang = end; 
            float x = (float)(cx + FastTrig.cos(Math.toRadians(ang)) * radius);
            float y = (float)(cy + FastTrig.sin(Math.toRadians(ang)) * radius);
            tempPoints.add(new Float(x));
            tempPoints.add(new Float(y));
        } 
        return tempPoints;
    }
    
    public Shape transform(Transform transform) {
        checkPoints();
        Polygon resultPolygon = new Polygon();
        float[] result = new float[this.points.length];
        transform.transform(this.points, 0, result, 0, this.points.length / 2);
        resultPolygon.points = result;
        resultPolygon.findCenter();
        return resultPolygon;
    }
}
