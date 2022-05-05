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

public abstract class Tuple4i implements Serializable {
    public int x;
    
    public int y;
    
    public int z;
    
    public int w;
    
    public Tuple4i(int x, int y, int z, int w) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.w = w;
    }
    
    public Tuple4i(int[] t) {
        this.x = t[0];
        this.y = t[1];
        this.z = t[2];
        this.w = t[3];
    }
    
    public Tuple4i(Tuple4i t1) {
        this.x = t1.x;
        this.y = t1.y;
        this.z = t1.z;
        this.w = t1.w;
    }
    
    public Tuple4i() {
        this.x = 0;
        this.y = 0;
        this.z = 0;
        this.w = 0;
    }
    
    public final void set(int x, int y, int z, int w) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.w = w;
    }
    
    public final void set(int[] t) {
        this.x = t[0];
        this.y = t[1];
        this.z = t[2];
        this.w = t[3];
    }
    
    public final void set(Tuple4i t1) {
        this.x = t1.x;
        this.y = t1.y;
        this.z = t1.z;
        this.w = t1.w;
    }
    
    public final void get(int[] t) {
        t[0] = this.x;
        t[1] = this.y;
        t[2] = this.z;
        t[3] = this.w;
    }
    
    public final void get(Tuple4i t) {
        t.x = this.x;
        t.y = this.y;
        t.z = this.z;
        t.w = this.w;
    }
    
    public final void add(Tuple4i t1, Tuple4i t2) {
        t1.x += t2.x;
        t1.y += t2.y;
        t1.z += t2.z;
        t1.w += t2.w;
    }
    
    public final void add(Tuple4i t1) {
        this.x += t1.x;
        this.y += t1.y;
        this.z += t1.z;
        this.w += t1.w;
    }
    
    public final void sub(Tuple4i t1, Tuple4i t2) {
        t1.x -= t2.x;
        t1.y -= t2.y;
        t1.z -= t2.z;
        t1.w -= t2.w;
    }
    
    public final void sub(Tuple4i t1) {
        this.x -= t1.x;
        this.y -= t1.y;
        this.z -= t1.z;
        this.w -= t1.w;
    }
    
    public final void negate(Tuple4i t1) {
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
    
    public final void scale(int s, Tuple4i t1) {
        this.x = s * t1.x;
        this.y = s * t1.y;
        this.z = s * t1.z;
        this.w = s * t1.w;
    }
    
    public final void scale(int s) {
        this.x *= s;
        this.y *= s;
        this.z *= s;
        this.w *= s;
    }
    
    public final void scaleAdd(int s, Tuple4i t1, Tuple4i t2) {
        this.x = s * t1.x + t2.x;
        this.y = s * t1.y + t2.y;
        this.z = s * t1.z + t2.z;
        this.w = s * t1.w + t2.w;
    }
    
    public final void scaleAdd(int s, Tuple4i t1) {
        this.x = s * this.x + t1.x;
        this.y = s * this.y + t1.y;
        this.z = s * this.z + t1.z;
        this.w = s * this.w + t1.w;
    }
    
    public int hashCode() {
        return this.x ^ this.y ^ this.z ^ this.w;
    }
    
    private boolean equals(Tuple4i t1) {
        return (t1 != null && this.x == t1.x && this.y == t1.y && this.z == t1.z);
    }
    
    public boolean equals(Object o1) {
        return (o1 != null && o1 instanceof Tuple4i && equals((Tuple4i)o1));
    }
    
    public String toString() {
        return "(" + this.x + ", " + this.y + ", " + this.z + ", " + this.w + ")";
    }
    
    public final void clamp(int min, int max, Tuple4i t) {
        set(t);
        clamp(min, max);
    }
    
    public final void clampMin(int min, Tuple4i t) {
        set(t);
        clampMin(min);
    }
    
    public final void clampMax(int max, Tuple4i t) {
        set(t);
        clampMax(max);
    }
    
    public final void absolute(Tuple4i t) {
        set(t);
        absolute();
    }
    
    public final void clamp(int min, int max) {
        clampMin(min);
        clampMax(max);
    }
    
    public final void clampMin(int min) {
        if (this.x < min)
            this.x = min; 
        if (this.y < min)
            this.y = min; 
        if (this.z < min)
            this.z = min; 
        if (this.w < min)
            this.w = min; 
    }
    
    public final void clampMax(int max) {
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
}
