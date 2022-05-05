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

package org.newdawn.slick.fills;

import org.newdawn.slick.Color;
import org.newdawn.slick.ShapeFill;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.geom.Vector2f;

public class GradientFill implements ShapeFill {
    private Vector2f none = new Vector2f(0.0F, 0.0F);
    
    private Vector2f start;
    
    private Vector2f end;
    
    private Color startCol;
    
    private Color endCol;
    
    private boolean local = false;
    
    public GradientFill(float sx, float sy, Color startCol, float ex, float ey, Color endCol) {
        this(sx, sy, startCol, ex, ey, endCol, false);
    }
    
    public GradientFill(float sx, float sy, Color startCol, float ex, float ey, Color endCol, boolean local) {
        this(new Vector2f(sx, sy), startCol, new Vector2f(ex, ey), endCol, local);
    }
    
    public GradientFill(Vector2f start, Color startCol, Vector2f end, Color endCol, boolean local) {
        this.start = new Vector2f(start);
        this.end = new Vector2f(end);
        this.startCol = new Color(startCol);
        this.endCol = new Color(endCol);
        this.local = local;
    }
    
    public GradientFill getInvertedCopy() {
        return new GradientFill(this.start, this.endCol, this.end, this.startCol, this.local);
    }
    
    public void setLocal(boolean local) {
        this.local = local;
    }
    
    public Vector2f getStart() {
        return this.start;
    }
    
    public Vector2f getEnd() {
        return this.end;
    }
    
    public Color getStartColor() {
        return this.startCol;
    }
    
    public Color getEndColor() {
        return this.endCol;
    }
    
    public void setStart(float x, float y) {
        setStart(new Vector2f(x, y));
    }
    
    public void setStart(Vector2f start) {
        this.start = new Vector2f(start);
    }
    
    public void setEnd(float x, float y) {
        setEnd(new Vector2f(x, y));
    }
    
    public void setEnd(Vector2f end) {
        this.end = new Vector2f(end);
    }
    
    public void setStartColor(Color color) {
        this.startCol = new Color(color);
    }
    
    public void setEndColor(Color color) {
        this.endCol = new Color(color);
    }
    
    public Color colorAt(Shape shape, float x, float y) {
        if (this.local)
            return colorAt(x - shape.getCenterX(), y - shape.getCenterY()); 
        return colorAt(x, y);
    }
    
    public Color colorAt(float x, float y) {
        float dx1 = this.end.getX() - this.start.getX();
        float dy1 = this.end.getY() - this.start.getY();
        float dx2 = -dy1;
        float dy2 = dx1;
        float denom = dy2 * dx1 - dx2 * dy1;
        if (denom == 0.0F)
            return Color.black; 
        float ua = dx2 * (this.start.getY() - y) - dy2 * (this.start.getX() - x);
        ua /= denom;
        float ub = dx1 * (this.start.getY() - y) - dy1 * (this.start.getX() - x);
        ub /= denom;
        float u = ua;
        if (u < 0.0F)
            u = 0.0F; 
        if (u > 1.0F)
            u = 1.0F; 
        float v = 1.0F - u;
        Color col = new Color(1, 1, 1, 1);
        col.r = u * this.endCol.r + v * this.startCol.r;
        col.b = u * this.endCol.b + v * this.startCol.b;
        col.g = u * this.endCol.g + v * this.startCol.g;
        col.a = u * this.endCol.a + v * this.startCol.a;
        return col;
    }
    
    public Vector2f getOffsetAt(Shape shape, float x, float y) {
        return this.none;
    }
}
