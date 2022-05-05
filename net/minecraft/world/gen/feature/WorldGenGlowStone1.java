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
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

public class WorldGenGlowStone1 extends WorldGenerator {
    public boolean generate(World worldIn, Random rand, BlockPos position) {
        if (!worldIn.isAirBlock(position))
            return false; 
        if (worldIn.getBlockState(position.up()).getBlock() != Blocks.netherrack)
            return false; 
        worldIn.setBlockState(position, Blocks.glowstone.getDefaultState(), 2);
        for (int i = 0; i < 1500; i++) {
            BlockPos blockpos = position.add(rand.nextInt(8) - rand.nextInt(8), -rand.nextInt(12), rand.nextInt(8) - rand.nextInt(8));
            if (worldIn.getBlockState(blockpos).getBlock().getMaterial() == Material.air) {
                int j = 0;
                byte b;
                int k;
                EnumFacing[] arrayOfEnumFacing;
                for (k = (arrayOfEnumFacing = EnumFacing.values()).length, b = 0; b < k; ) {
                    EnumFacing enumfacing = arrayOfEnumFacing[b];
                    if (worldIn.getBlockState(blockpos.offset(enumfacing)).getBlock() == Blocks.glowstone)
                        j++; 
                    if (j > 1)
                        break; 
                    b++;
                } 
                if (j == 1)
                    worldIn.setBlockState(blockpos, Blocks.glowstone.getDefaultState(), 2); 
            } 
        } 
        return true;
    }
}
