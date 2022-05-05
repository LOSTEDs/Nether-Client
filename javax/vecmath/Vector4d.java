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

public class Vector4d extends Tuple4d implements Serializable {
    public Vector4d(double x, double y, double z, double w) {
        super(x, y, z, w);
    }
    
    public Vector4d(double[] v) {
        super(v);
    }
    
    public Vector4d(Vector4f v1) {
        super(v1);
    }
    
    public Vector4d(Vector4d v1) {
        super(v1);
    }
    
    public Vector4d(Tuple4d t1) {
        super(t1);
    }
    
    public Vector4d(Tuple4f t1) {
        super(t1);
    }
    
    public Vector4d() {}
    
    public Vector4d(Tuple3d t1) {
        super(t1.x, t1.y, t1.z, 0.0D);
    }
    
    public final void set(Tuple3d t1) {
        set(t1.x, t1.y, t1.z, 0.0D);
    }
    
    public final double lengthSquared() {
        return this.x * this.x + this.y * this.y + this.z * this.z + this.w * this.w;
    }
    
    public final double length() {
        return Math.sqrt(lengthSquared());
    }
    
    public final double dot(Vector4d v1) {
        return this.x * v1.x + this.y * v1.y + this.z * v1.z + this.w * v1.w;
    }
    
    public final void normalize(Vector4d v1) {
        set(v1);
        normalize();
    }
    
    public final void normalize() {
        double d = length();
        this.x /= d;
        this.y /= d;
        this.z /= d;
        this.w /= d;
    }
    
    public final double angle(Vector4d v1) {
        double d = dot(v1);
        double v1_length = v1.length();
        double v_length = length();
        return Math.acos(d / v1_length / v_length);
    }
}
