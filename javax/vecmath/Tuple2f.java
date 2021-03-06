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

public abstract class Tuple2f implements Serializable {
    public float x;
    
    public float y;
    
    public Tuple2f(float x, float y) {
        this.x = x;
        this.y = y;
    }
    
    public Tuple2f(float[] t) {
        this.x = t[0];
        this.y = t[1];
    }
    
    public Tuple2f(Tuple2f t1) {
        this.x = t1.x;
        this.y = t1.y;
    }
    
    public Tuple2f(Tuple2d t1) {
        this.x = (float)t1.x;
        this.y = (float)t1.y;
    }
    
    public Tuple2f() {
        this.x = 0.0F;
        this.y = 0.0F;
    }
    
    public final void set(float x, float y) {
        this.x = x;
        this.y = y;
    }
    
    public final void set(float[] t) {
        this.x = t[0];
        this.y = t[1];
    }
    
    public final void set(Tuple2f t1) {
        this.x = t1.x;
        this.y = t1.y;
    }
    
    public final void set(Tuple2d t1) {
        this.x = (float)t1.x;
        this.y = (float)t1.y;
    }
    
    public final void get(float[] t) {
        t[0] = this.x;
        t[1] = this.y;
    }
    
    public final void add(Tuple2f t1, Tuple2f t2) {
        t1.x += t2.x;
        t1.y += t2.y;
    }
    
    public final void add(Tuple2f t1) {
        this.x += t1.x;
        this.y += t1.y;
    }
    
    public final void sub(Tuple2f t1, Tuple2f t2) {
        t1.x -= t2.x;
        t1.y -= t2.y;
    }
    
    public final void sub(Tuple2f t1) {
        this.x -= t1.x;
        this.y -= t1.y;
    }
    
    public final void negate(Tuple2f t1) {
        this.x = -t1.x;
        this.y = -t1.y;
    }
    
    public final void negate() {
        this.x = -this.x;
        this.y = -this.y;
    }
    
    public final void scale(float s, Tuple2f t1) {
        this.x = s * t1.x;
        this.y = s * t1.y;
    }
    
    public final void scale(float s) {
        this.x *= s;
        this.y *= s;
    }
    
    public final void scaleAdd(float s, Tuple2f t1, Tuple2f t2) {
        this.x = s * t1.x + t2.x;
        this.y = s * t1.y + t2.y;
    }
    
    public final void scaleAdd(float s, Tuple2f t1) {
        this.x = s * this.x + t1.x;
        this.y = s * this.y + t1.y;
    }
    
    public int hashCode() {
        int xbits = Float.floatToIntBits(this.x);
        int ybits = Float.floatToIntBits(this.y);
        return xbits ^ ybits;
    }
    
    public boolean equals(Tuple2f t1) {
        return (t1 != null && this.x == t1.x && this.y == t1.y);
    }
    
    public boolean equals(Object o1) {
        return (o1 != null && o1 instanceof Tuple2f && equals((Tuple2f)o1));
    }
    
    public boolean epsilonEquals(Tuple2f t1, float epsilon) {
        return (Math.abs(t1.x - this.x) <= epsilon && Math.abs(t1.y - this.y) <= epsilon);
    }
    
    public String toString() {
        return "(" + this.x + ", " + this.y + ")";
    }
    
    public final void clamp(float min, float max, Tuple2f t) {
        set(t);
        clamp(min, max);
    }
    
    public final void clampMin(float min, Tuple2f t) {
        set(t);
        clampMin(min);
    }
    
    public final void clampMax(float max, Tuple2f t) {
        set(t);
        clampMax(max);
    }
    
    public final void absolute(Tuple2f t) {
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
    }
    
    public final void clampMax(float max) {
        if (this.x > max)
            this.x = max; 
        if (this.y > max)
            this.y = max; 
    }
    
    public final void absolute() {
        if (this.x < 0.0D)
            this.x = -this.x; 
        if (this.y < 0.0D)
            this.y = -this.y; 
    }
    
    public final void interpolate(Tuple2f t1, Tuple2f t2, float alpha) {
        set(t1);
        interpolate(t2, alpha);
    }
    
    public final void interpolate(Tuple2f t1, float alpha) {
        float beta = 1.0F - alpha;
        this.x = beta * this.x + alpha * t1.x;
        this.y = beta * this.y + alpha * t1.y;
    }
}
