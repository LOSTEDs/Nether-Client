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

package javax.vecmath;

import java.io.Serializable;

public class Point2f extends Tuple2f implements Serializable {
    public Point2f(float x, float y) {
        super(x, y);
    }
    
    public Point2f(float[] p) {
        super(p);
    }
    
    public Point2f(Point2f p1) {
        super(p1);
    }
    
    public Point2f(Point2d p1) {
        super(p1);
    }
    
    public Point2f(Tuple2f t1) {
        super(t1);
    }
    
    public Point2f(Tuple2d t1) {
        super(t1);
    }
    
    public Point2f() {}
    
    public final float distanceSquared(Point2f p1) {
        double dx = (this.x - p1.x);
        double dy = (this.y - p1.y);
        return (float)(dx * dx + dy * dy);
    }
    
    public final float distance(Point2f p1) {
        return (float)Math.sqrt(distanceSquared(p1));
    }
    
    public final float distanceL1(Point2f p1) {
        return Math.abs(this.x - p1.x) + Math.abs(this.y - p1.y);
    }
    
    public final float distanceLinf(Point2f p1) {
        return Math.max(Math.abs(this.x - p1.x), Math.abs(this.y - p1.y));
    }
}
