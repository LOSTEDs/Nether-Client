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

package net.minecraft.util;

public class IntegerCache {
    private static final Integer[] field_181757_a = new Integer[65535];
    
    public static Integer func_181756_a(int p_181756_0_) {
        return (p_181756_0_ > 0 && p_181756_0_ < field_181757_a.length) ? field_181757_a[p_181756_0_] : Integer.valueOf(p_181756_0_);
    }
    
    static {
        int i = 0;
        for (int j = field_181757_a.length; i < j; i++)
            field_181757_a[i] = Integer.valueOf(i); 
    }
}
