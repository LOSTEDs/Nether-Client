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
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public class BlockRail extends BlockRailBase {
    public static final PropertyEnum<BlockRailBase.EnumRailDirection> SHAPE = PropertyEnum.create("shape", BlockRailBase.EnumRailDirection.class);
    
    protected BlockRail() {
        super(false);
        setDefaultState(this.blockState.getBaseState().withProperty((IProperty)SHAPE, BlockRailBase.EnumRailDirection.NORTH_SOUTH));
    }
    
    protected void onNeighborChangedInternal(World worldIn, BlockPos pos, IBlockState state, Block neighborBlock) {
        if (neighborBlock.canProvidePower() && (new BlockRailBase.Rail(this, worldIn, pos, state)).countAdjacentRails() == 3)
            func_176564_a(worldIn, pos, state, false); 
    }
    
    public IProperty<BlockRailBase.EnumRailDirection> getShapeProperty() {
        return (IProperty<BlockRailBase.EnumRailDirection>)SHAPE;
    }
    
    public IBlockState getStateFromMeta(int meta) {
        return getDefaultState().withProperty((IProperty)SHAPE, BlockRailBase.EnumRailDirection.byMetadata(meta));
    }
    
    public int getMetaFromState(IBlockState state) {
        return ((BlockRailBase.EnumRailDirection)state.getValue((IProperty)SHAPE)).getMetadata();
    }
    
    protected BlockState createBlockState() {
        return new BlockState(this, new IProperty[] { (IProperty)SHAPE });
    }
}
