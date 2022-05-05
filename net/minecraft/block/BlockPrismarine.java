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
import net.minecraft.util.StatCollector;

public class BlockPrismarine extends Block {
    public static final PropertyEnum<EnumType> VARIANT = PropertyEnum.create("variant", EnumType.class);
    
    public static final int ROUGH_META = EnumType.ROUGH.getMetadata();
    
    public static final int BRICKS_META = EnumType.BRICKS.getMetadata();
    
    public static final int DARK_META = EnumType.DARK.getMetadata();
    
    public BlockPrismarine() {
        super(Material.rock);
        setDefaultState(this.blockState.getBaseState().withProperty((IProperty)VARIANT, EnumType.ROUGH));
        setCreativeTab(CreativeTabs.tabBlock);
    }
    
    public String getLocalizedName() {
        return StatCollector.translateToLocal(String.valueOf(getUnlocalizedName()) + "." + EnumType.ROUGH.getUnlocalizedName() + ".name");
    }
    
    public MapColor getMapColor(IBlockState state) {
        return (state.getValue((IProperty)VARIANT) == EnumType.ROUGH) ? MapColor.cyanColor : MapColor.diamondColor;
    }
    
    public int damageDropped(IBlockState state) {
        return ((EnumType)state.getValue((IProperty)VARIANT)).getMetadata();
    }
    
    public int getMetaFromState(IBlockState state) {
        return ((EnumType)state.getValue((IProperty)VARIANT)).getMetadata();
    }
    
    protected BlockState createBlockState() {
        return new BlockState(this, new IProperty[] { (IProperty)VARIANT });
    }
    
    public IBlockState getStateFromMeta(int meta) {
        return getDefaultState().withProperty((IProperty)VARIANT, EnumType.byMetadata(meta));
    }
    
    public void getSubBlocks(Item itemIn, CreativeTabs tab, List<ItemStack> list) {
        list.add(new ItemStack(itemIn, 1, ROUGH_META));
        list.add(new ItemStack(itemIn, 1, BRICKS_META));
        list.add(new ItemStack(itemIn, 1, DARK_META));
    }
    
    public enum EnumType implements IStringSerializable {
        ROUGH(0, "prismarine", "rough"),
        BRICKS(1, "prismarine_bricks", "bricks"),
        DARK(2, "dark_prismarine", "dark");
        
        private static final EnumType[] META_LOOKUP = new EnumType[(values()).length];
        
        private final int meta;
        
        private final String name;
        
        private final String unlocalizedName;
        
        static {
            byte b;
            int i;
            EnumType[] arrayOfEnumType;
            for (i = (arrayOfEnumType = values()).length, b = 0; b < i; ) {
                EnumType blockprismarine$enumtype = arrayOfEnumType[b];
                META_LOOKUP[blockprismarine$enumtype.getMetadata()] = blockprismarine$enumtype;
                b++;
            } 
        }
        
        EnumType(int meta, String name, String unlocalizedName) {
            this.meta = meta;
            this.name = name;
            this.unlocalizedName = unlocalizedName;
        }
        
        public int getMetadata() {
            return this.meta;
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
