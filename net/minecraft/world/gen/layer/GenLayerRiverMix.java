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

public class GenLayerRiverMix extends GenLayer {
    private GenLayer biomePatternGeneratorChain;
    
    private GenLayer riverPatternGeneratorChain;
    
    public GenLayerRiverMix(long p_i2129_1_, GenLayer p_i2129_3_, GenLayer p_i2129_4_) {
        super(p_i2129_1_);
        this.biomePatternGeneratorChain = p_i2129_3_;
        this.riverPatternGeneratorChain = p_i2129_4_;
    }
    
    public void initWorldGenSeed(long seed) {
        this.biomePatternGeneratorChain.initWorldGenSeed(seed);
        this.riverPatternGeneratorChain.initWorldGenSeed(seed);
        super.initWorldGenSeed(seed);
    }
    
    public int[] getInts(int areaX, int areaY, int areaWidth, int areaHeight) {
        int[] aint = this.biomePatternGeneratorChain.getInts(areaX, areaY, areaWidth, areaHeight);
        int[] aint1 = this.riverPatternGeneratorChain.getInts(areaX, areaY, areaWidth, areaHeight);
        int[] aint2 = IntCache.getIntCache(areaWidth * areaHeight);
        for (int i = 0; i < areaWidth * areaHeight; i++) {
            if (aint[i] != BiomeGenBase.ocean.biomeID && aint[i] != BiomeGenBase.deepOcean.biomeID) {
                if (aint1[i] == BiomeGenBase.river.biomeID) {
                    if (aint[i] == BiomeGenBase.icePlains.biomeID) {
                        aint2[i] = BiomeGenBase.frozenRiver.biomeID;
                    } else if (aint[i] != BiomeGenBase.mushroomIsland.biomeID && aint[i] != BiomeGenBase.mushroomIslandShore.biomeID) {
                        aint2[i] = aint1[i] & 0xFF;
                    } else {
                        aint2[i] = BiomeGenBase.mushroomIslandShore.biomeID;
                    } 
                } else {
                    aint2[i] = aint[i];
                } 
            } else {
                aint2[i] = aint[i];
            } 
        } 
        return aint2;
    }
}
