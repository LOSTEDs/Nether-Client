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

package net.minecraft.world.gen.layer;

public class GenLayerIsland extends GenLayer {
    public GenLayerIsland(long p_i2124_1_) {
        super(p_i2124_1_);
    }
    
    public int[] getInts(int areaX, int areaY, int areaWidth, int areaHeight) {
        int[] aint = IntCache.getIntCache(areaWidth * areaHeight);
        for (int i = 0; i < areaHeight; i++) {
            for (int j = 0; j < areaWidth; j++) {
                initChunkSeed((areaX + j), (areaY + i));
                aint[j + i * areaWidth] = (nextInt(10) == 0) ? 1 : 0;
            } 
        } 
        if (areaX > -areaWidth && areaX <= 0 && areaY > -areaHeight && areaY <= 0)
            aint[-areaX + -areaY * areaWidth] = 1; 
        return aint;
    }
}
