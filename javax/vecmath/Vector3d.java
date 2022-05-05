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

public class Vector3d extends Tuple3d implements Serializable {
    public Vector3d(double x, double y, double z) {
        super(x, y, z);
    }
    
    public Vector3d(double[] v) {
        super(v);
    }
    
    public Vector3d(Vector3f v1) {
        super(v1);
    }
    
    public Vector3d(Vector3d v1) {
        super(v1);
    }
    
    public Vector3d(Tuple3d t1) {
        super(t1);
    }
    
    public Vector3d(Tuple3f t1) {
        super(t1);
    }
    
    public Vector3d() {}
    
    public final void cross(Vector3d v1, Vector3d v2) {
        set(v1.y * v2.z - v1.z * v2.y, v1.z * v2.x - v1.x * v2.z, v1.x * v2.y - v1.y * v2.x);
    }
    
    public final void normalize(Vector3d v1) {
        set(v1);
        normalize();
    }
    
    public final void normalize() {
        double d = length();
        this.x /= d;
        this.y /= d;
        this.z /= d;
    }
    
    public final double dot(Vector3d v1) {
        return this.x * v1.x + this.y * v1.y + this.z * v1.z;
    }
    
    public final double lengthSquared() {
        return this.x * this.x + this.y * this.y + this.z * this.z;
    }
    
    public final double length() {
        return Math.sqrt(lengthSquared());
    }
    
    public final double angle(Vector3d v1) {
        double xx = this.y * v1.z - this.z * v1.y;
        double yy = this.z * v1.x - this.x * v1.z;
        double zz = this.x * v1.y - this.y * v1.x;
        double cross = Math.sqrt(xx * xx + yy * yy + zz * zz);
        return Math.abs(Math.atan2(cross, dot(v1)));
    }
}
