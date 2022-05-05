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

import java.util.List;
import java.util.Random;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public abstract class BlockWoodSlab extends BlockSlab {
    public static final PropertyEnum<BlockPlanks.EnumType> VARIANT = PropertyEnum.create("variant", BlockPlanks.EnumType.class);
    
    public BlockWoodSlab() {
        super(Material.wood);
        IBlockState iblockstate = this.blockState.getBaseState();
        if (!isDouble())
            iblockstate = iblockstate.withProperty((IProperty)HALF, BlockSlab.EnumBlockHalf.BOTTOM); 
        setDefaultState(iblockstate.withProperty((IProperty)VARIANT, BlockPlanks.EnumType.OAK));
        setCreativeTab(CreativeTabs.tabBlock);
    }
    
    public MapColor getMapColor(IBlockState state) {
        return ((BlockPlanks.EnumType)state.getValue((IProperty)VARIANT)).func_181070_c();
    }
    
    public Item getItemDropped(IBlockState state, Random rand, int fortune) {
        return Item.getItemFromBlock(Blocks.wooden_slab);
    }
    
    public Item getItem(World worldIn, BlockPos pos) {
        return Item.getItemFromBlock(Blocks.wooden_slab);
    }
    
    public String getUnlocalizedName(int meta) {
        return String.valueOf(getUnlocalizedName()) + "." + BlockPlanks.EnumType.byMetadata(meta).getUnlocalizedName();
    }
    
    public IProperty<?> getVariantProperty() {
        return (IProperty<?>)VARIANT;
    }
    
    public Object getVariant(ItemStack stack) {
        return BlockPlanks.EnumType.byMetadata(stack.getMetadata() & 0x7);
    }
    
    public void getSubBlocks(Item itemIn, CreativeTabs tab, List<ItemStack> list) {
        if (itemIn != Item.getItemFromBlock(Blocks.double_wooden_slab)) {
            byte b;
            int i;
            BlockPlanks.EnumType[] arrayOfEnumType;
            for (i = (arrayOfEnumType = BlockPlanks.EnumType.values()).length, b = 0; b < i; ) {
                BlockPlanks.EnumType blockplanks$enumtype = arrayOfEnumType[b];
                list.add(new ItemStack(itemIn, 1, blockplanks$enumtype.getMetadata()));
                b++;
            } 
        } 
    }
    
    public IBlockState getStateFromMeta(int meta) {
        IBlockState iblockstate = getDefaultState().withProperty((IProperty)VARIANT, BlockPlanks.EnumType.byMetadata(meta & 0x7));
        if (!isDouble())
            iblockstate = iblockstate.withProperty((IProperty)HALF, ((meta & 0x8) == 0) ? BlockSlab.EnumBlockHalf.BOTTOM : BlockSlab.EnumBlockHalf.TOP); 
        return iblockstate;
    }
    
    public int getMetaFromState(IBlockState state) {
        int i = 0;
        i |= ((BlockPlanks.EnumType)state.getValue((IProperty)VARIANT)).getMetadata();
        if (!isDouble() && state.getValue((IProperty)HALF) == BlockSlab.EnumBlockHalf.TOP)
            i |= 0x8; 
        return i;
    }
    
    protected BlockState createBlockState() {
        return isDouble() ? new BlockState(this, new IProperty[] { (IProperty)VARIANT }) : new BlockState(this, new IProperty[] { (IProperty)HALF, (IProperty)VARIANT });
    }
    
    public int damageDropped(IBlockState state) {
        return ((BlockPlanks.EnumType)state.getValue((IProperty)VARIANT)).getMetadata();
    }
}
