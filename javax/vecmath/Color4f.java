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

import java.awt.Color;
import java.io.Serializable;

public class Color4f extends Tuple4f implements Serializable {
    public Color4f(float x, float y, float z, float w) {
        super(x, y, z, w);
    }
    
    public Color4f(float[] c) {
        super(c);
    }
    
    public Color4f(Color4f c1) {
        super(c1);
    }
    
    public Color4f(Tuple4d t1) {
        super(t1);
    }
    
    public Color4f(Tuple4f t1) {
        super(t1);
    }
    
    public Color4f() {}
    
    public Color4f(Color color) {
        this.x = color.getRed() / 255.0F;
        this.y = color.getGreen() / 255.0F;
        this.z = color.getBlue() / 255.0F;
        this.w = color.getAlpha() / 255.0F;
    }
    
    public final void set(Color color) {
        this.x = color.getRed() / 255.0F;
        this.y = color.getGreen() / 255.0F;
        this.z = color.getBlue() / 255.0F;
        this.w = color.getAlpha() / 255.0F;
    }
    
    public final Color get() {
        return new Color(this.x, this.y, this.z, this.w);
    }
}
