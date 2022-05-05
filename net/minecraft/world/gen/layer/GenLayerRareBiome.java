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

import net.minecraft.world.biome.BiomeGenBase;

public class GenLayerRareBiome extends GenLayer {
    public GenLayerRareBiome(long p_i45478_1_, GenLayer p_i45478_3_) {
        super(p_i45478_1_);
        this.parent = p_i45478_3_;
    }
    
    public int[] getInts(int areaX, int areaY, int areaWidth, int areaHeight) {
        int[] aint = this.parent.getInts(areaX - 1, areaY - 1, areaWidth + 2, areaHeight + 2);
        int[] aint1 = IntCache.getIntCache(areaWidth * areaHeight);
        for (int i = 0; i < areaHeight; i++) {
            for (int j = 0; j < areaWidth; j++) {
                initChunkSeed((j + areaX), (i + areaY));
                int k = aint[j + 1 + (i + 1) * (areaWidth + 2)];
                if (nextInt(57) == 0) {
                    if (k == BiomeGenBase.plains.biomeID) {
                        aint1[j + i * areaWidth] = BiomeGenBase.plains.biomeID + 128;
                    } else {
                        aint1[j + i * areaWidth] = k;
                    } 
                } else {
                    aint1[j + i * areaWidth] = k;
                } 
            } 
        } 
        return aint1;
    }
}
