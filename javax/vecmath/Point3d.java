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

public class Point3d extends Tuple3d implements Serializable {
    public Point3d(double x, double y, double z) {
        super(x, y, z);
    }
    
    public Point3d(double[] p) {
        super(p);
    }
    
    public Point3d(Point3d p1) {
        super(p1);
    }
    
    public Point3d(Point3f p1) {
        super(p1);
    }
    
    public Point3d(Tuple3d t1) {
        super(t1);
    }
    
    public Point3d(Tuple3f t1) {
        super(t1);
    }
    
    public Point3d() {}
    
    public final double distanceSquared(Point3d p1) {
        double dx = this.x - p1.x;
        double dy = this.y - p1.y;
        double dz = this.z - p1.z;
        return dx * dx + dy * dy + dz * dz;
    }
    
    public final double distance(Point3d p1) {
        return Math.sqrt(distanceSquared(p1));
    }
    
    public final double distanceL1(Point3d p1) {
        return Math.abs(this.x - p1.x) + Math.abs(this.y - p1.y) + Math.abs(this.z - p1.z);
    }
    
    public final double distanceLinf(Point3d p1) {
        return Math.max(Math.max(Math.abs(this.x - p1.x), Math.abs(this.y - p1.y)), Math.abs(this.z - p1.z));
    }
    
    public final void project(Point4d p1) {
        this.x = p1.x / p1.w;
        this.y = p1.y / p1.w;
        this.z = p1.z / p1.w;
    }
}
