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
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public class WorldGenSand extends WorldGenerator {
    private Block block;
    
    private int radius;
    
    public WorldGenSand(Block p_i45462_1_, int p_i45462_2_) {
        this.block = p_i45462_1_;
        this.radius = p_i45462_2_;
    }
    
    public boolean generate(World worldIn, Random rand, BlockPos position) {
        if (worldIn.getBlockState(position).getBlock().getMaterial() != Material.water)
            return false; 
        int i = rand.nextInt(this.radius - 2) + 2;
        int j = 2;
        for (int k = position.getX() - i; k <= position.getX() + i; k++) {
            for (int l = position.getZ() - i; l <= position.getZ() + i; l++) {
                int i1 = k - position.getX();
                int j1 = l - position.getZ();
                if (i1 * i1 + j1 * j1 <= i * i)
                    for (int k1 = position.getY() - j; k1 <= position.getY() + j; k1++) {
                        BlockPos blockpos = new BlockPos(k, k1, l);
                        Block block = worldIn.getBlockState(blockpos).getBlock();
                        if (block == Blocks.dirt || block == Blocks.grass)
                            worldIn.setBlockState(blockpos, this.block.getDefaultState(), 2); 
                    }  
            } 
        } 
        return true;
    }
}
