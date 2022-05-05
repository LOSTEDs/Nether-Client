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

package net.minecraft.world.gen;

import java.util.Random;
import net.minecraft.world.World;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.chunk.IChunkProvider;

public class MapGenBase {
    protected int range = 8;
    
    protected Random rand = new Random();
    
    protected World worldObj;
    
    public void generate(IChunkProvider chunkProviderIn, World worldIn, int x, int z, ChunkPrimer chunkPrimerIn) {
        int i = this.range;
        this.worldObj = worldIn;
        this.rand.setSeed(worldIn.getSeed());
        long j = this.rand.nextLong();
        long k = this.rand.nextLong();
        for (int l = x - i; l <= x + i; l++) {
            for (int i1 = z - i; i1 <= z + i; i1++) {
                long j1 = l * j;
                long k1 = i1 * k;
                this.rand.setSeed(j1 ^ k1 ^ worldIn.getSeed());
                recursiveGenerate(worldIn, l, i1, x, z, chunkPrimerIn);
            } 
        } 
    }
    
    protected void recursiveGenerate(World worldIn, int chunkX, int chunkZ, int p_180701_4_, int p_180701_5_, ChunkPrimer chunkPrimerIn) {}
}
