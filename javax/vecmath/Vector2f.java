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

public class Vector2f extends Tuple2f implements Serializable {
    public Vector2f(float x, float y) {
        super(x, y);
    }
    
    public Vector2f(float[] v) {
        super(v);
    }
    
    public Vector2f(Vector2f v1) {
        super(v1);
    }
    
    public Vector2f(Vector2d v1) {
        super(v1);
    }
    
    public Vector2f(Tuple2f t1) {
        super(t1);
    }
    
    public Vector2f(Tuple2d t1) {
        super(t1);
    }
    
    public Vector2f() {}
    
    public final float dot(Vector2f v1) {
        return this.x * v1.x + this.y * v1.y;
    }
    
    public final float length() {
        return (float)Math.sqrt((this.x * this.x + this.y * this.y));
    }
    
    public final float lengthSquared() {
        return this.x * this.x + this.y * this.y;
    }
    
    public final void normalize() {
        double d = length();
        this.x = (float)(this.x / d);
        this.y = (float)(this.y / d);
    }
    
    public final void normalize(Vector2f v1) {
        set(v1);
        normalize();
    }
    
    public final float angle(Vector2f v1) {
        return (float)Math.abs(Math.atan2((this.x * v1.y - this.y * v1.x), dot(v1)));
    }
}
