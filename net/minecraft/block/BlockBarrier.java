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

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public class BlockBarrier extends Block {
    protected BlockBarrier() {
        super(Material.barrier);
        setBlockUnbreakable();
        setResistance(6000001.0F);
        disableStats();
        this.translucent = true;
    }
    
    public int getRenderType() {
        return -1;
    }
    
    public boolean isOpaqueCube() {
        return false;
    }
    
    public float getAmbientOcclusionLightValue() {
        return 1.0F;
    }
    
    public void dropBlockAsItemWithChance(World worldIn, BlockPos pos, IBlockState state, float chance, int fortune) {}
}
