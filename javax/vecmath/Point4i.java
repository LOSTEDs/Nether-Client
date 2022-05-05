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

public class Point4i extends Tuple4i implements Serializable {
    public Point4i(int x, int y, int z, int w) {
        super(x, y, z, w);
    }
    
    public Point4i(int[] t) {
        super(t);
    }
    
    public Point4i(Point4i t1) {
        super(t1);
    }
    
    public Point4i() {}
}
