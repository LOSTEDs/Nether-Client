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

public class Vector4f extends Tuple4f implements Serializable {
    public Vector4f(float x, float y, float z, float w) {
        super(x, y, z, w);
    }
    
    public Vector4f(float[] v) {
        super(v);
    }
    
    public Vector4f(Vector4f v1) {
        super(v1);
    }
    
    public Vector4f(Vector4d v1) {
        super(v1);
    }
    
    public Vector4f(Tuple4d t1) {
        super(t1);
    }
    
    public Vector4f(Tuple4f t1) {
        super(t1);
    }
    
    public Vector4f() {}
    
    public Vector4f(Tuple3f t1) {
        super(t1.x, t1.y, t1.z, 0.0F);
    }
    
    public final void set(Tuple3f t1) {
        set(t1.x, t1.y, t1.z, 0.0F);
    }
    
    public final float lengthSquared() {
        return this.x * this.x + this.y * this.y + this.z * this.z + this.w * this.w;
    }
    
    public final float length() {
        return (float)Math.sqrt(lengthSquared());
    }
    
    public final float dot(Vector4f v1) {
        return this.x * v1.x + this.y * v1.y + this.z * v1.z + this.w * v1.w;
    }
    
    public final void normalize(Vector4d v1) {
        set(v1);
        normalize();
    }
    
    public final void normalize() {
        double d = length();
        this.x = (float)(this.x / d);
        this.y = (float)(this.y / d);
        this.z = (float)(this.z / d);
        this.w = (float)(this.w / d);
    }
    
    public final float angle(Vector4f v1) {
        double d = dot(v1);
        double v1_length = v1.length();
        double v_length = length();
        return (float)Math.acos(d / v1_length / v_length);
    }
}
