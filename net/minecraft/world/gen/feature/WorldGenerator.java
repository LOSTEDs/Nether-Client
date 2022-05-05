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
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public abstract class WorldGenerator {
    private final boolean doBlockNotify;
    
    public WorldGenerator() {
        this(false);
    }
    
    public WorldGenerator(boolean notify) {
        this.doBlockNotify = notify;
    }
    
    public abstract boolean generate(World paramWorld, Random paramRandom, BlockPos paramBlockPos);
    
    public void func_175904_e() {}
    
    protected void setBlockAndNotifyAdequately(World worldIn, BlockPos pos, IBlockState state) {
        if (this.doBlockNotify) {
            worldIn.setBlockState(pos, state, 3);
        } else {
            worldIn.setBlockState(pos, state, 2);
        } 
    }
}
