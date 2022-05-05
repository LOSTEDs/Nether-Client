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

package org.newdawn.slick.util;

public class FastTrig {
    private static double reduceSinAngle(double radians) {
        double orig = radians;
        radians %= 6.283185307179586D;
        if (Math.abs(radians) > Math.PI)
            radians -= 6.283185307179586D; 
        if (Math.abs(radians) > 1.5707963267948966D)
            radians = Math.PI - radians; 
        return radians;
    }
    
    public static double sin(double radians) {
        radians = reduceSinAngle(radians);
        if (Math.abs(radians) <= 0.7853981633974483D)
            return Math.sin(radians); 
        return Math.cos(1.5707963267948966D - radians);
    }
    
    public static double cos(double radians) {
        return sin(radians + 1.5707963267948966D);
    }
}
