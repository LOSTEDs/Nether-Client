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

package net.minecraft.block;

import java.util.Random;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public interface IGrowable {
    boolean canGrow(World paramWorld, BlockPos paramBlockPos, IBlockState paramIBlockState, boolean paramBoolean);
    
    boolean canUseBonemeal(World paramWorld, Random paramRandom, BlockPos paramBlockPos, IBlockState paramIBlockState);
    
    void grow(World paramWorld, Random paramRandom, BlockPos paramBlockPos, IBlockState paramIBlockState);
}
