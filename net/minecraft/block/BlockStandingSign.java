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

import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public class BlockStandingSign extends BlockSign {
    public static final PropertyInteger ROTATION = PropertyInteger.create("rotation", 0, 15);
    
    public BlockStandingSign() {
        setDefaultState(this.blockState.getBaseState().withProperty((IProperty)ROTATION, Integer.valueOf(0)));
    }
    
    public void onNeighborBlockChange(World worldIn, BlockPos pos, IBlockState state, Block neighborBlock) {
        if (!worldIn.getBlockState(pos.down()).getBlock().getMaterial().isSolid()) {
            dropBlockAsItem(worldIn, pos, state, 0);
            worldIn.setBlockToAir(pos);
        } 
        super.onNeighborBlockChange(worldIn, pos, state, neighborBlock);
    }
    
    public IBlockState getStateFromMeta(int meta) {
        return getDefaultState().withProperty((IProperty)ROTATION, Integer.valueOf(meta));
    }
    
    public int getMetaFromState(IBlockState state) {
        return ((Integer)state.getValue((IProperty)ROTATION)).intValue();
    }
    
    protected BlockState createBlockState() {
        return new BlockState(this, new IProperty[] { (IProperty)ROTATION });
    }
}
