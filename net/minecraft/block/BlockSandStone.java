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
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IStringSerializable;

public class BlockSandStone extends Block {
    public static final PropertyEnum<EnumType> TYPE = PropertyEnum.create("type", EnumType.class);
    
    public BlockSandStone() {
        super(Material.rock);
        setDefaultState(this.blockState.getBaseState().withProperty((IProperty)TYPE, EnumType.DEFAULT));
        setCreativeTab(CreativeTabs.tabBlock);
    }
    
    public int damageDropped(IBlockState state) {
        return ((EnumType)state.getValue((IProperty)TYPE)).getMetadata();
    }
    
    public void getSubBlocks(Item itemIn, CreativeTabs tab, List<ItemStack> list) {
        byte b;
        int i;
        EnumType[] arrayOfEnumType;
        for (i = (arrayOfEnumType = EnumType.values()).length, b = 0; b < i; ) {
            EnumType blocksandstone$enumtype = arrayOfEnumType[b];
            list.add(new ItemStack(itemIn, 1, blocksandstone$enumtype.getMetadata()));
            b++;
        } 
    }
    
    public MapColor getMapColor(IBlockState state) {
        return MapColor.sandColor;
    }
    
    public IBlockState getStateFromMeta(int meta) {
        return getDefaultState().withProperty((IProperty)TYPE, EnumType.byMetadata(meta));
    }
    
    public int getMetaFromState(IBlockState state) {
        return ((EnumType)state.getValue((IProperty)TYPE)).getMetadata();
    }
    
    protected BlockState createBlockState() {
        return new BlockState(this, new IProperty[] { (IProperty)TYPE });
    }
    
    public enum EnumType implements IStringSerializable {
        DEFAULT(0, "sandstone", "default"),
        CHISELED(1, "chiseled_sandstone", "chiseled"),
        SMOOTH(2, "smooth_sandstone", "smooth");
        
        private static final EnumType[] META_LOOKUP = new EnumType[(values()).length];
        
        private final int metadata;
        
        private final String name;
        
        private final String unlocalizedName;
        
        static {
            byte b;
            int i;
            EnumType[] arrayOfEnumType;
            for (i = (arrayOfEnumType = values()).length, b = 0; b < i; ) {
                EnumType blocksandstone$enumtype = arrayOfEnumType[b];
                META_LOOKUP[blocksandstone$enumtype.getMetadata()] = blocksandstone$enumtype;
                b++;
            } 
        }
        
        EnumType(int meta, String name, String unlocalizedName) {
            this.metadata = meta;
            this.name = name;
            this.unlocalizedName = unlocalizedName;
        }
        
        public int getMetadata() {
            return this.metadata;
        }
        
        public String toString() {
            return this.name;
        }
        
        public static EnumType byMetadata(int meta) {
            if (meta < 0 || meta >= META_LOOKUP.length)
                meta = 0; 
            return META_LOOKUP[meta];
        }
        
        public String getName() {
            return this.name;
        }
        
        public String getUnlocalizedName() {
            return this.unlocalizedName;
        }
    }
}
