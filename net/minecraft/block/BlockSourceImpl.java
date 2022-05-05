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

import net.minecraft.block.state.IBlockState;
import net.minecraft.dispenser.IBlockSource;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public class BlockSourceImpl implements IBlockSource {
    private final World worldObj;
    
    private final BlockPos pos;
    
    public BlockSourceImpl(World worldIn, BlockPos posIn) {
        this.worldObj = worldIn;
        this.pos = posIn;
    }
    
    public World getWorld() {
        return this.worldObj;
    }
    
    public double getX() {
        return this.pos.getX() + 0.5D;
    }
    
    public double getY() {
        return this.pos.getY() + 0.5D;
    }
    
    public double getZ() {
        return this.pos.getZ() + 0.5D;
    }
    
    public BlockPos getBlockPos() {
        return this.pos;
    }
    
    public int getBlockMetadata() {
        IBlockState iblockstate = this.worldObj.getBlockState(this.pos);
        return iblockstate.getBlock().getMetaFromState(iblockstate);
    }
    
    public <T extends net.minecraft.tileentity.TileEntity> T getBlockTileEntity() {
        return (T)this.worldObj.getTileEntity(this.pos);
    }
}
