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

package net.minecraft.world;

public class ColorizerFoliage {
    private static int[] foliageBuffer = new int[65536];
    
    public static void setFoliageBiomeColorizer(int[] p_77467_0_) {
        foliageBuffer = p_77467_0_;
    }
    
    public static int getFoliageColor(double p_77470_0_, double p_77470_2_) {
        p_77470_2_ *= p_77470_0_;
        int i = (int)((1.0D - p_77470_0_) * 255.0D);
        int j = (int)((1.0D - p_77470_2_) * 255.0D);
        return foliageBuffer[j << 8 | i];
    }
    
    public static int getFoliageColorPine() {
        return 6396257;
    }
    
    public static int getFoliageColorBirch() {
        return 8431445;
    }
    
    public static int getFoliageColorBasic() {
        return 4764952;
    }
}
