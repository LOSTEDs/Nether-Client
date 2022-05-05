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

public class ColorizerGrass {
    private static int[] grassBuffer = new int[65536];
    
    public static void setGrassBiomeColorizer(int[] p_77479_0_) {
        grassBuffer = p_77479_0_;
    }
    
    public static int getGrassColor(double p_77480_0_, double p_77480_2_) {
        p_77480_2_ *= p_77480_0_;
        int i = (int)((1.0D - p_77480_0_) * 255.0D);
        int j = (int)((1.0D - p_77480_2_) * 255.0D);
        int k = j << 8 | i;
        return (k > grassBuffer.length) ? -65281 : grassBuffer[k];
    }
}
