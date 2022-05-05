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

package net.minecraft.item;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

public class ItemRedstone extends Item {
    public ItemRedstone() {
        setCreativeTab(CreativeTabs.tabRedstone);
    }
    
    public boolean onItemUse(ItemStack stack, EntityPlayer playerIn, World worldIn, BlockPos pos, EnumFacing side, float hitX, float hitY, float hitZ) {
        boolean flag = worldIn.getBlockState(pos).getBlock().isReplaceable(worldIn, pos);
        BlockPos blockpos = flag ? pos : pos.offset(side);
        if (!playerIn.canPlayerEdit(blockpos, side, stack))
            return false; 
        Block block = worldIn.getBlockState(blockpos).getBlock();
        if (!worldIn.canBlockBePlaced(block, blockpos, false, side, null, stack))
            return false; 
        if (Blocks.redstone_wire.canPlaceBlockAt(worldIn, blockpos)) {
            stack.stackSize--;
            worldIn.setBlockState(blockpos, Blocks.redstone_wire.getDefaultState());
            return true;
        } 
        return false;
    }
}
