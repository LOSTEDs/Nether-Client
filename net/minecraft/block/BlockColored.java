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
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class BlockColored extends Block {
    public static final PropertyEnum<EnumDyeColor> COLOR = PropertyEnum.create("color", EnumDyeColor.class);
    
    public BlockColored(Material materialIn) {
        super(materialIn);
        setDefaultState(this.blockState.getBaseState().withProperty((IProperty)COLOR, (Comparable)EnumDyeColor.WHITE));
        setCreativeTab(CreativeTabs.tabBlock);
    }
    
    public int damageDropped(IBlockState state) {
        return ((EnumDyeColor)state.getValue((IProperty)COLOR)).getMetadata();
    }
    
    public void getSubBlocks(Item itemIn, CreativeTabs tab, List<ItemStack> list) {
        byte b;
        int i;
        EnumDyeColor[] arrayOfEnumDyeColor;
        for (i = (arrayOfEnumDyeColor = EnumDyeColor.values()).length, b = 0; b < i; ) {
            EnumDyeColor enumdyecolor = arrayOfEnumDyeColor[b];
            list.add(new ItemStack(itemIn, 1, enumdyecolor.getMetadata()));
            b++;
        } 
    }
    
    public MapColor getMapColor(IBlockState state) {
        return ((EnumDyeColor)state.getValue((IProperty)COLOR)).getMapColor();
    }
    
    public IBlockState getStateFromMeta(int meta) {
        return getDefaultState().withProperty((IProperty)COLOR, (Comparable)EnumDyeColor.byMetadata(meta));
    }
    
    public int getMetaFromState(IBlockState state) {
        return ((EnumDyeColor)state.getValue((IProperty)COLOR)).getMetadata();
    }
    
    protected BlockState createBlockState() {
        return new BlockState(this, new IProperty[] { (IProperty)COLOR });
    }
}
