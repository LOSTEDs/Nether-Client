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

import net.minecraft.util.MathHelper;

public class MathUtils {
    public static int getAverage(int[] p_getAverage_0_) {
        if (p_getAverage_0_.length <= 0)
            return 0; 
        int i = getSum(p_getAverage_0_);
        int j = i / p_getAverage_0_.length;
        return j;
    }
    
    public static int getSum(int[] p_getSum_0_) {
        if (p_getSum_0_.length <= 0)
            return 0; 
        int i = 0;
        for (int j = 0; j < p_getSum_0_.length; j++) {
            int k = p_getSum_0_[j];
            i += k;
        } 
        return i;
    }
    
    public static int roundDownToPowerOfTwo(int p_roundDownToPowerOfTwo_0_) {
        int i = MathHelper.roundUpToPowerOfTwo(p_roundDownToPowerOfTwo_0_);
        return (p_roundDownToPowerOfTwo_0_ == i) ? i : (i / 2);
    }
    
    public static boolean equalsDelta(float p_equalsDelta_0_, float p_equalsDelta_1_, float p_equalsDelta_2_) {
        return (Math.abs(p_equalsDelta_0_ - p_equalsDelta_1_) <= p_equalsDelta_2_);
    }
    
    public static float toDeg(float p_toDeg_0_) {
        return p_toDeg_0_ * 180.0F / 3.1415927F;
    }
    
    public static float toRad(float p_toRad_0_) {
        return p_toRad_0_ / 180.0F * 3.1415927F;
    }
}