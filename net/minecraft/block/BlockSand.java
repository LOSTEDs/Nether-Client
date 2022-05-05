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
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IStringSerializable;

public class BlockSand extends BlockFalling {
    public static final PropertyEnum<EnumType> VARIANT = PropertyEnum.create("variant", EnumType.class);
    
    public BlockSand() {
        setDefaultState(this.blockState.getBaseState().withProperty((IProperty)VARIANT, EnumType.SAND));
    }
    
    public int damageDropped(IBlockState state) {
        return ((EnumType)state.getValue((IProperty)VARIANT)).getMetadata();
    }
    
    public void getSubBlocks(Item itemIn, CreativeTabs tab, List<ItemStack> list) {
        byte b;
        int i;
        EnumType[] arrayOfEnumType;
        for (i = (arrayOfEnumType = EnumType.values()).length, b = 0; b < i; ) {
            EnumType blocksand$enumtype = arrayOfEnumType[b];
            list.add(new ItemStack(itemIn, 1, blocksand$enumtype.getMetadata()));
            b++;
        } 
    }
    
    public MapColor getMapColor(IBlockState state) {
        return ((EnumType)state.getValue((IProperty)VARIANT)).getMapColor();
    }
    
    public IBlockState getStateFromMeta(int meta) {
        return getDefaultState().withProperty((IProperty)VARIANT, EnumType.byMetadata(meta));
    }
    
    public int getMetaFromState(IBlockState state) {
        return ((EnumType)state.getValue((IProperty)VARIANT)).getMetadata();
    }
    
    protected BlockState createBlockState() {
        return new BlockState(this, new IProperty[] { (IProperty)VARIANT });
    }
    
    public enum EnumType implements IStringSerializable {
        SAND(0, "sand", "default", (String)MapColor.sandColor),
        RED_SAND(1, "red_sand", "red", (String)MapColor.adobeColor);
        
        private static final EnumType[] META_LOOKUP = new EnumType[(values()).length];
        
        private final int meta;
        
        private final String name;
        
        private final MapColor mapColor;
        
        private final String unlocalizedName;
        
        static {
            byte b;
            int i;
            EnumType[] arrayOfEnumType;
            for (i = (arrayOfEnumType = values()).length, b = 0; b < i; ) {
                EnumType blocksand$enumtype = arrayOfEnumType[b];
                META_LOOKUP[blocksand$enumtype.getMetadata()] = blocksand$enumtype;
                b++;
            } 
        }
        
        EnumType(int meta, String name, String unlocalizedName, MapColor mapColor) {
            this.meta = meta;
            this.name = name;
            this.mapColor = mapColor;
            this.unlocalizedName = unlocalizedName;
        }
        
        public int getMetadata() {
            return this.meta;
        }
        
        public String toString() {
            return this.name;
        }
        
        public MapColor getMapColor() {
            return this.mapColor;
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
