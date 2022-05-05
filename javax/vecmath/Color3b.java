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

public class Color3b extends Tuple3b implements Serializable {
    public Color3b(byte c1, byte c2, byte c3) {
        super(c1, c2, c3);
    }
    
    public Color3b(byte[] c) {
        super(c);
    }
    
    public Color3b(Color3b c1) {
        super(c1);
    }
    
    public Color3b(Tuple3b t1) {
        super(t1);
    }
    
    public Color3b() {}
    
    public Color3b(Color color) {
        this.x = (byte)color.getRed();
        this.y = (byte)color.getGreen();
        this.z = (byte)color.getBlue();
    }
    
    public final void set(Color color) {
        this.x = (byte)color.getRed();
        this.y = (byte)color.getGreen();
        this.z = (byte)color.getBlue();
    }
    
    public final Color get() {
        return new Color(this.x, this.y, this.z);
    }
}
