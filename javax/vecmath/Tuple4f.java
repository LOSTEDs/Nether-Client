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

public abstract class Tuple4f implements Serializable {
    public float x;
    
    public float y;
    
    public float z;
    
    public float w;
    
    public Tuple4f(float x, float y, float z, float w) {
        set(x, y, z, w);
    }
    
    public Tuple4f(float[] t) {
        set(t);
    }
    
    public Tuple4f(Tuple4f t1) {
        set(t1);
    }
    
    public Tuple4f(Tuple4d t1) {
        set(t1);
    }
    
    public Tuple4f() {
        this.x = 0.0F;
        this.y = 0.0F;
        this.z = 0.0F;
        this.w = 0.0F;
    }
    
    public final void set(float x, float y, float z, float w) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.w = w;
    }
    
    public final void set(float[] t) {
        this.x = t[0];
        this.y = t[1];
        this.z = t[2];
        this.w = t[3];
    }
    
    public final void set(Tuple4f t1) {
        this.x = t1.x;
        this.y = t1.y;
        this.z = t1.z;
        this.w = t1.w;
    }
    
    public final void set(Tuple4d t1) {
        this.x = (float)t1.x;
        this.y = (float)t1.y;
        this.z = (float)t1.z;
        this.w = (float)t1.w;
    }
    
    public final void get(float[] t) {
        t[0] = this.x;
        t[1] = this.y;
        t[2] = this.z;
        t[3] = this.w;
    }
    
    public final void get(Tuple4f t) {
        t.x = this.x;
        t.y = this.y;
        t.z = this.z;
        t.w = this.w;
    }
    
    public final void add(Tuple4f t1, Tuple4f t2) {
        t1.x += t2.x;
        t1.y += t2.y;
        t1.z += t2.z;
        t1.w += t2.w;
    }
    
    public final void add(Tuple4f t1) {
        this.x += t1.x;
        this.y += t1.y;
        this.z += t1.z;
        this.w += t1.w;
    }
    
    public final void sub(Tuple4f t1, Tuple4f t2) {
        t1.x -= t2.x;
        t1.y -= t2.y;
        t1.z -= t2.z;
        t1.w -= t2.w;
    }
    
    public final void sub(Tuple4f t1) {
        this.x -= t1.x;
        this.y -= t1.y;
        this.z -= t1.z;
        this.w -= t1.w;
    }
    
    public final void negate(Tuple4f t1) {
        this.x = -t1.x;
        this.y = -t1.y;
        this.z = -t1.z;
        this.w = -t1.w;
    }
    
    public final void negate() {
        this.x = -this.x;
        this.y = -this.y;
        this.z = -this.z;
        this.w = -this.w;
    }
    
    public final void scale(float s, Tuple4f t1) {
        this.x = s * t1.x;
        this.y = s * t1.y;
        this.z = s * t1.z;
        this.w = s * t1.w;
    }
    
    public final void scale(float s) {
        this.x *= s;
        this.y *= s;
        this.z *= s;
        this.w *= s;
    }
    
    public final void scaleAdd(float s, Tuple4f t1, Tuple4f t2) {
        this.x = s * t1.x + t2.x;
        this.y = s * t1.y + t2.y;
        this.z = s * t1.z + t2.z;
        this.w = s * t1.w + t2.w;
    }
    
    public final void scaleAdd(float s, Tuple4f t1) {
        this.x = s * this.x + t1.x;
        this.y = s * this.y + t1.y;
        this.z = s * this.z + t1.z;
        this.w = s * this.z + t1.w;
    }
    
    public int hashCode() {
        return Float.floatToIntBits(this.x) ^ Float.floatToIntBits(this.y) ^ Float.floatToIntBits(this.z) ^ Float.floatToIntBits(this.w);
    }
    
    public boolean equals(Tuple4f t1) {
        return (t1 != null && this.x == t1.x && this.y == t1.y && this.z == t1.z && this.w == t1.w);
    }
    
    public boolean epsilonEquals(Tuple4f t1, float epsilon) {
        return (Math.abs(t1.x - this.x) <= epsilon && Math.abs(t1.y - this.y) <= epsilon && Math.abs(t1.z - this.z) <= epsilon && Math.abs(t1.w - this.w) <= epsilon);
    }
    
    public String toString() {
        return "(" + this.x + ", " + this.y + ", " + this.z + ", " + this.w + ")";
    }
    
    public final void clamp(float min, float max, Tuple4f t) {
        set(t);
        clamp(min, max);
    }
    
    public final void clampMin(float min, Tuple4f t) {
        set(t);
        clampMin(min);
    }
    
    public final void clampMax(float max, Tuple4f t) {
        set(t);
        clampMax(max);
    }
    
    public final void absolute(Tuple4f t) {
        set(t);
        absolute();
    }
    
    public final void clamp(float min, float max) {
        clampMin(min);
        clampMax(max);
    }
    
    public final void clampMin(float min) {
        if (this.x < min)
            this.x = min; 
        if (this.y < min)
            this.y = min; 
        if (this.z < min)
            this.z = min; 
        if (this.w < min)
            this.w = min; 
    }
    
    public final void clampMax(float max) {
        if (this.x > max)
            this.x = max; 
        if (this.y > max)
            this.y = max; 
        if (this.z > max)
            this.z = max; 
        if (this.w > max)
            this.w = max; 
    }
    
    public final void absolute() {
        if (this.x < 0.0D)
            this.x = -this.x; 
        if (this.y < 0.0D)
            this.y = -this.y; 
        if (this.z < 0.0D)
            this.z = -this.z; 
        if (this.w < 0.0D)
            this.w = -this.w; 
    }
    
    public final void interpolate(Tuple4f t1, Tuple4f t2, float alpha) {
        set(t1);
        interpolate(t2, alpha);
    }
    
    public final void interpolate(Tuple4f t1, float alpha) {
        float beta = 1.0F - alpha;
        this.x = beta * this.x + alpha * t1.x;
        this.y = beta * this.y + alpha * t1.y;
        this.z = beta * this.z + alpha * t1.z;
        this.w = beta * this.w + alpha * t1.w;
    }
}
