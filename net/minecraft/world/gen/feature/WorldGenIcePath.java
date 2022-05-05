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

package net.minecraft.world.gen.feature;

import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public class WorldGenIcePath extends WorldGenerator {
    private Block block = Blocks.packed_ice;
    
    private int basePathWidth;
    
    public WorldGenIcePath(int p_i45454_1_) {
        this.basePathWidth = p_i45454_1_;
    }
    
    public boolean generate(World worldIn, Random rand, BlockPos position) {
        while (worldIn.isAirBlock(position) && position.getY() > 2)
            position = position.down(); 
        if (worldIn.getBlockState(position).getBlock() != Blocks.snow)
            return false; 
        int i = rand.nextInt(this.basePathWidth - 2) + 2;
        int j = 1;
        for (int k = position.getX() - i; k <= position.getX() + i; k++) {
            for (int l = position.getZ() - i; l <= position.getZ() + i; l++) {
                int i1 = k - position.getX();
                int j1 = l - position.getZ();
                if (i1 * i1 + j1 * j1 <= i * i)
                    for (int k1 = position.getY() - j; k1 <= position.getY() + j; k1++) {
                        BlockPos blockpos = new BlockPos(k, k1, l);
                        Block block = worldIn.getBlockState(blockpos).getBlock();
                        if (block == Blocks.dirt || block == Blocks.snow || block == Blocks.ice)
                            worldIn.setBlockState(blockpos, this.block.getDefaultState(), 2); 
                    }  
            } 
        } 
        return true;
    }
}
