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

public class WorldGenDeadBush extends WorldGenerator {
    public boolean generate(World worldIn, Random rand, BlockPos position) {
        Block block;
        while (((block = worldIn.getBlockState(position).getBlock()).getMaterial() == Material.air || block.getMaterial() == Material.leaves) && position.getY() > 0)
            position = position.down(); 
        for (int i = 0; i < 4; i++) {
            BlockPos blockpos = position.add(rand.nextInt(8) - rand.nextInt(8), rand.nextInt(4) - rand.nextInt(4), rand.nextInt(8) - rand.nextInt(8));
            if (worldIn.isAirBlock(blockpos) && Blocks.deadbush.canBlockStay(worldIn, blockpos, Blocks.deadbush.getDefaultState()))
                worldIn.setBlockState(blockpos, Blocks.deadbush.getDefaultState(), 2); 
        } 
        return true;
    }
}