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

public abstract class Tuple4d implements Serializable {
    public double x;
    
    public double y;
    
    public double z;
    
    public double w;
    
    public Tuple4d(double x, double y, double z, double w) {
        set(x, y, z, w);
    }
    
    public Tuple4d(double[] t) {
        set(t);
    }
    
    public Tuple4d(Tuple4d t1) {
        set(t1);
    }
    
    public Tuple4d(Tuple4f t1) {
        set(t1);
    }
    
    public Tuple4d() {
        this.x = 0.0D;
        this.y = 0.0D;
        this.z = 0.0D;
        this.w = 0.0D;
    }
    
    public final void set(double x, double y, double z, double w) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.w = w;
    }
    
    public final void set(double[] t) {
        this.x = t[0];
        this.y = t[1];
        this.z = t[2];
        this.w = t[3];
    }
    
    public final void set(Tuple4d t1) {
        this.x = t1.x;
        this.y = t1.y;
        this.z = t1.z;
        this.w = t1.w;
    }
    
    public final void set(Tuple4f t1) {
        this.x = t1.x;
        this.y = t1.y;
        this.z = t1.z;
        this.w = t1.w;
    }
    
    public final void get(double[] t) {
        t[0] = this.x;
        t[1] = this.y;
        t[2] = this.z;
        t[3] = this.w;
    }
    
    public final void get(Tuple4d t) {
        t.x = this.x;
        t.y = this.y;
        t.z = this.z;
        t.w = this.w;
    }
    
    public final void add(Tuple4d t1, Tuple4d t2) {
        t1.x += t2.x;
        t1.y += t2.y;
        t1.z += t2.z;
        t1.w += t2.w;
    }
    
    public final void add(Tuple4d t1) {
        this.x += t1.x;
        this.y += t1.y;
        this.z += t1.z;
        this.w += t1.w;
    }
    
    public final void sub(Tuple4d t1, Tuple4d t2) {
        t1.x -= t2.x;
        t1.y -= t2.y;
        t1.z -= t2.z;
        t1.w -= t2.w;
    }
    
    public final void sub(Tuple4d t1) {
        this.x -= t1.x;
        this.y -= t1.y;
        this.z -= t1.z;
        this.w -= t1.w;
    }
    
    public final void negate(Tuple4d t1) {
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
    
    public final void scale(double s, Tuple4d t1) {
        this.x = s * t1.x;
        this.y = s * t1.y;
        this.z = s * t1.z;
        this.w = s * t1.w;
    }
    
    public final void scale(double s) {
        this.x *= s;
        this.y *= s;
        this.z *= s;
        this.w *= s;
    }
    
    public final void scaleAdd(double s, Tuple4d t1, Tuple4d t2) {
        this.x = s * t1.x + t2.x;
        this.y = s * t1.y + t2.y;
        this.z = s * t1.z + t2.z;
        this.w = s * t1.w + t2.w;
    }
    
    public final void scaleAdd(double s, Tuple4d t1) {
        this.x = s * this.x + t1.x;
        this.y = s * this.y + t1.y;
        this.z = s * this.z + t1.z;
        this.w = s * this.z + t1.w;
    }
    
    public int hashCode() {
        long xbits = Double.doubleToLongBits(this.x);
        long ybits = Double.doubleToLongBits(this.y);
        long zbits = Double.doubleToLongBits(this.z);
        long wbits = Double.doubleToLongBits(this.w);
        return (int)(xbits ^ xbits >> 32L ^ ybits ^ ybits >> 32L ^ zbits ^ zbits >> 32L ^ wbits ^ wbits >> 32L);
    }
    
    public boolean equals(Tuple4d t1) {
        return (t1 != null && this.x == t1.x && this.y == t1.y && this.z == t1.z && this.w == t1.w);
    }
    
    public boolean epsilonEquals(Tuple4d t1, double epsilon) {
        return (Math.abs(t1.x - this.x) <= epsilon && Math.abs(t1.y - this.y) <= epsilon && Math.abs(t1.z - this.z) <= epsilon && Math.abs(t1.w - this.w) <= epsilon);
    }
    
    public String toString() {
        return "(" + this.x + ", " + this.y + ", " + this.z + ", " + this.w + ")";
    }
    
    public final void clamp(double min, double max, Tuple4d t) {
        set(t);
        clamp(min, max);
    }
    
    public final void clampMin(double min, Tuple4d t) {
        set(t);
        clampMin(min);
    }
    
    public final void clampMax(double max, Tuple4d t) {
        set(t);
        clampMax(max);
    }
    
    public final void absolute(Tuple4d t) {
        set(t);
        absolute();
    }
    
    public final void clamp(double min, double max) {
        clampMin(min);
        clampMax(max);
    }
    
    public final void clampMin(double min) {
        if (this.x < min)
            this.x = min; 
        if (this.y < min)
            this.y = min; 
        if (this.z < min)
            this.z = min; 
        if (this.w < min)
            this.w = min; 
    }
    
    public final void clampMax(double max) {
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
    
    public final void interpolate(Tuple4d t1, Tuple4d t2, double alpha) {
        set(t1);
        interpolate(t2, alpha);
    }
    
    public final void interpolate(Tuple4d t1, double alpha) {
        double beta = 1.0D - alpha;
        this.x = beta * this.x + alpha * t1.x;
        this.y = beta * this.y + alpha * t1.y;
        this.z = beta * this.z + alpha * t1.z;
        this.w = beta * this.w + alpha * t1.w;
    }
}
