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

public class TexCoord3f extends Tuple3f implements Serializable {
    public TexCoord3f(float x, float y, float z) {
        super(x, y, z);
    }
    
    public TexCoord3f(float[] v) {
        super(v);
    }
    
    public TexCoord3f(TexCoord3f v1) {
        super(v1);
    }
    
    public TexCoord3f() {}
}
