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
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.monster.EntitySilverfish;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.IStringSerializable;
import net.minecraft.world.World;

public class BlockSilverfish extends Block {
    public static final PropertyEnum<EnumType> VARIANT = PropertyEnum.create("variant", EnumType.class);
    
    public BlockSilverfish() {
        super(Material.clay);
        setDefaultState(this.blockState.getBaseState().withProperty((IProperty)VARIANT, EnumType.STONE));
        setHardness(0.0F);
        setCreativeTab(CreativeTabs.tabDecorations);
    }
    
    public int quantityDropped(Random random) {
        return 0;
    }
    
    public static boolean canContainSilverfish(IBlockState blockState) {
        Block block = blockState.getBlock();
        return !(blockState != Blocks.stone.getDefaultState().withProperty((IProperty)BlockStone.VARIANT, BlockStone.EnumType.STONE) && block != Blocks.cobblestone && block != Blocks.stonebrick);
    }
    
    protected ItemStack createStackedBlock(IBlockState state) {
        switch ((EnumType)state.getValue((IProperty)VARIANT)) {
            case COBBLESTONE:
                return new ItemStack(Blocks.cobblestone);
            case STONEBRICK:
                return new ItemStack(Blocks.stonebrick);
            case MOSSY_STONEBRICK:
                return new ItemStack(Blocks.stonebrick, 1, BlockStoneBrick.EnumType.MOSSY.getMetadata());
            case CRACKED_STONEBRICK:
                return new ItemStack(Blocks.stonebrick, 1, BlockStoneBrick.EnumType.CRACKED.getMetadata());
            case null:
                return new ItemStack(Blocks.stonebrick, 1, BlockStoneBrick.EnumType.CHISELED.getMetadata());
        } 
        return new ItemStack(Blocks.stone);
    }
    
    public void dropBlockAsItemWithChance(World worldIn, BlockPos pos, IBlockState state, float chance, int fortune) {
        if (!worldIn.isRemote && worldIn.getGameRules().getBoolean("doTileDrops")) {
            EntitySilverfish entitysilverfish = new EntitySilverfish(worldIn);
            entitysilverfish.setLocationAndAngles(pos.getX() + 0.5D, pos.getY(), pos.getZ() + 0.5D, 0.0F, 0.0F);
            worldIn.spawnEntityInWorld((Entity)entitysilverfish);
            entitysilverfish.spawnExplosionParticle();
        } 
    }
    
    public int getDamageValue(World worldIn, BlockPos pos) {
        IBlockState iblockstate = worldIn.getBlockState(pos);
        return iblockstate.getBlock().getMetaFromState(iblockstate);
    }
    
    public void getSubBlocks(Item itemIn, CreativeTabs tab, List<ItemStack> list) {
        byte b;
        int i;
        EnumType[] arrayOfEnumType;
        for (i = (arrayOfEnumType = EnumType.values()).length, b = 0; b < i; ) {
            EnumType blocksilverfish$enumtype = arrayOfEnumType[b];
            list.add(new ItemStack(itemIn, 1, blocksilverfish$enumtype.getMetadata()));
            b++;
        } 
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
        STONE(0, "stone") {
            public IBlockState getModelBlock() {
                return Blocks.stone.getDefaultState().withProperty((IProperty)BlockStone.VARIANT, BlockStone.EnumType.STONE);
            }
        },
        COBBLESTONE(1, "cobblestone", "cobble") {
            public IBlockState getModelBlock() {
                return Blocks.cobblestone.getDefaultState();
            }
        },
        STONEBRICK(2, "stone_brick", "brick") {
            public IBlockState getModelBlock() {
                return Blocks.stonebrick.getDefaultState().withProperty((IProperty)BlockStoneBrick.VARIANT, BlockStoneBrick.EnumType.DEFAULT);
            }
        },
        MOSSY_STONEBRICK(3, "mossy_brick", "mossybrick") {
            public IBlockState getModelBlock() {
                return Blocks.stonebrick.getDefaultState().withProperty((IProperty)BlockStoneBrick.VARIANT, BlockStoneBrick.EnumType.MOSSY);
            }
        },
        CRACKED_STONEBRICK(4, "cracked_brick", "crackedbrick") {
            public IBlockState getModelBlock() {
                return Blocks.stonebrick.getDefaultState().withProperty((IProperty)BlockStoneBrick.VARIANT, BlockStoneBrick.EnumType.CRACKED);
            }
        },
        CHISELED_STONEBRICK(5, "chiseled_brick", "chiseledbrick") {
            public IBlockState getModelBlock() {
                return Blocks.stonebrick.getDefaultState().withProperty((IProperty)BlockStoneBrick.VARIANT, BlockStoneBrick.EnumType.CHISELED);
            }
        };
        
        private static final EnumType[] META_LOOKUP = new EnumType[(values()).length];
        
        private final int meta;
        
        private final String name;
        
        private final String unlocalizedName;
        
        static {
            byte b;
            int i;
            EnumType[] arrayOfEnumType;
            for (i = (arrayOfEnumType = values()).length, b = 0; b < i; ) {
                EnumType blocksilverfish$enumtype = arrayOfEnumType[b];
                META_LOOKUP[blocksilverfish$enumtype.getMetadata()] = blocksilverfish$enumtype;
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
        
        public static EnumType forModelBlock(IBlockState model) {
            byte b;
            int i;
            EnumType[] arrayOfEnumType;
            for (i = (arrayOfEnumType = values()).length, b = 0; b < i; ) {
                EnumType blocksilverfish$enumtype = arrayOfEnumType[b];
                if (model == blocksilverfish$enumtype.getModelBlock())
                    return blocksilverfish$enumtype; 
                b++;
            } 
            return STONE;
        }
        
        public abstract IBlockState getModelBlock();
    }
}