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

public abstract class Tuple2d implements Serializable {
    public double x;
    
    public double y;
    
    public Tuple2d(double x, double y) {
        this.x = x;
        this.y = y;
    }
    
    public Tuple2d(double[] t) {
        this.x = t[0];
        this.y = t[1];
    }
    
    public Tuple2d(Tuple2d t1) {
        this.x = t1.x;
        this.y = t1.y;
    }
    
    public Tuple2d(Tuple2f t1) {
        this.x = t1.x;
        this.y = t1.y;
    }
    
    public Tuple2d() {
        this.x = 0.0D;
        this.y = 0.0D;
    }
    
    public final void set(double x, double y) {
        this.x = x;
        this.y = y;
    }
    
    public final void set(double[] t) {
        this.x = t[0];
        this.y = t[1];
    }
    
    public final void set(Tuple2d t1) {
        this.x = t1.x;
        this.y = t1.y;
    }
    
    public final void set(Tuple2f t1) {
        this.x = t1.x;
        this.y = t1.y;
    }
    
    public final void get(double[] t) {
        t[0] = this.x;
        t[1] = this.y;
    }
    
    public final void add(Tuple2d t1, Tuple2d t2) {
        t1.x += t2.x;
        t1.y += t2.y;
    }
    
    public final void add(Tuple2d t1) {
        this.x += t1.x;
        this.y += t1.y;
    }
    
    public final void sub(Tuple2d t1, Tuple2d t2) {
        t1.x -= t2.x;
        t1.y -= t2.y;
    }
    
    public final void sub(Tuple2d t1) {
        this.x -= t1.x;
        this.y -= t1.y;
    }
    
    public final void negate(Tuple2d t1) {
        this.x = -t1.x;
        this.y = -t1.y;
    }
    
    public final void negate() {
        this.x = -this.x;
        this.y = -this.y;
    }
    
    public final void scale(double s, Tuple2d t1) {
        this.x = s * t1.x;
        this.y = s * t1.y;
    }
    
    public final void scale(double s) {
        this.x *= s;
        this.y *= s;
    }
    
    public final void scaleAdd(double s, Tuple2d t1, Tuple2d t2) {
        this.x = s * t1.x + t2.x;
        this.y = s * t1.y + t2.y;
    }
    
    public final void scaleAdd(double s, Tuple2d t1) {
        this.x = s * this.x + t1.x;
        this.y = s * this.y + t1.y;
    }
    
    public int hashCode() {
        long xbits = Double.doubleToLongBits(this.x);
        long ybits = Double.doubleToLongBits(this.y);
        return (int)(xbits ^ xbits >> 32L ^ ybits ^ ybits >> 32L);
    }
    
    public boolean equals(Tuple2d t1) {
        return (t1 != null && this.x == t1.x && this.y == t1.y);
    }
    
    public boolean equals(Object o1) {
        return (o1 != null && o1 instanceof Tuple2d && equals((Tuple2d)o1));
    }
    
    public boolean epsilonEquals(Tuple2d t1, double epsilon) {
        return (Math.abs(t1.x - this.x) <= epsilon && Math.abs(t1.y - this.y) <= epsilon);
    }
    
    public String toString() {
        return "(" + this.x + ", " + this.y + ")";
    }
    
    public final void clamp(double min, double max, Tuple2d t) {
        set(t);
        clamp(min, max);
    }
    
    public final void clampMin(double min, Tuple2d t) {
        set(t);
        clampMin(min);
    }
    
    public final void clampMax(double max, Tuple2d t) {
        set(t);
        clampMax(max);
    }
    
    public final void absolute(Tuple2d t) {
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
    }
    
    public final void clampMax(double max) {
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
    
    public final void interpolate(Tuple2d t1, Tuple2d t2, double alpha) {
        set(t1);
        interpolate(t2, alpha);
    }
    
    public final void interpolate(Tuple2d t1, double alpha) {
        double beta = 1.0D - alpha;
        this.x = beta * this.x + alpha * t1.x;
        this.y = beta * this.y + alpha * t1.y;
    }
}
