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

public class TexCoord2f extends Tuple2f implements Serializable {
    public TexCoord2f(float x, float y) {
        super(x, y);
    }
    
    public TexCoord2f(float[] v) {
        super(v);
    }
    
    public TexCoord2f(TexCoord2f v1) {
        super(v1);
    }
    
    public TexCoord2f(Tuple2f t1) {
        super(t1);
    }
    
    public TexCoord2f() {}
}
