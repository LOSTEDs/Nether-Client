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

public class Point3f extends Tuple3f implements Serializable {
    public Point3f(float x, float y, float z) {
        super(x, y, z);
    }
    
    public Point3f(float[] p) {
        super(p);
    }
    
    public Point3f(Point3f p1) {
        super(p1);
    }
    
    public Point3f(Point3d p1) {
        super(p1);
    }
    
    public Point3f(Tuple3f t1) {
        super(t1);
    }
    
    public Point3f(Tuple3d t1) {
        super(t1);
    }
    
    public Point3f() {}
    
    public final float distanceSquared(Point3f p1) {
        double dx = (this.x - p1.x);
        double dy = (this.y - p1.y);
        double dz = (this.z - p1.z);
        return (float)(dx * dx + dy * dy + dz * dz);
    }
    
    public final float distance(Point3f p1) {
        return (float)Math.sqrt(distanceSquared(p1));
    }
    
    public final float distanceL1(Point3f p1) {
        return Math.abs(this.x - p1.x) + Math.abs(this.y - p1.y) + Math.abs(this.z - p1.z);
    }
    
    public final float distanceLinf(Point3f p1) {
        return Math.max(Math.max(Math.abs(this.x - p1.x), Math.abs(this.y - p1.y)), Math.abs(this.z - p1.z));
    }
    
    public final void project(Point4f p1) {
        this.x = p1.x / p1.w;
        this.y = p1.y / p1.w;
        this.z = p1.z / p1.w;
    }
}
