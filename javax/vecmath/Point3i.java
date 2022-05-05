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

public class Point3i extends Tuple3i implements Serializable {
    public Point3i(int x, int y, int z) {
        super(x, y, z);
    }
    
    public Point3i(int[] t) {
        super(t);
    }
    
    public Point3i(Point3i t1) {
        super(t1);
    }
    
    public Point3i() {}
}
