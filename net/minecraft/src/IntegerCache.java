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

package net.minecraft.src;

public class IntegerCache {
    private static final int CACHE_SIZE = 4096;
    
    private static final Integer[] cache = makeCache(4096);
    
    private static Integer[] makeCache(int p_makeCache_0_) {
        Integer[] ainteger = new Integer[p_makeCache_0_];
        for (int i = 0; i < p_makeCache_0_; i++)
            ainteger[i] = new Integer(i); 
        return ainteger;
    }
    
    public static Integer valueOf(int p_valueOf_0_) {
        return (p_valueOf_0_ >= 0 && p_valueOf_0_ < 4096) ? cache[p_valueOf_0_] : new Integer(p_valueOf_0_);
    }
}
