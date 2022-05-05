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

package net.minecraft.entity.item;

import net.minecraft.block.Block;
import net.minecraft.block.BlockChest;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ContainerChest;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

public class EntityMinecartChest extends EntityMinecartContainer {
    public EntityMinecartChest(World worldIn) {
        super(worldIn);
    }
    
    public EntityMinecartChest(World worldIn, double p_i1715_2_, double p_i1715_4_, double p_i1715_6_) {
        super(worldIn, p_i1715_2_, p_i1715_4_, p_i1715_6_);
    }
    
    public void killMinecart(DamageSource p_94095_1_) {
        super.killMinecart(p_94095_1_);
        if (this.worldObj.getGameRules().getBoolean("doEntityDrops"))
            dropItemWithOffset(Item.getItemFromBlock((Block)Blocks.chest), 1, 0.0F); 
    }
    
    public int getSizeInventory() {
        return 27;
    }
    
    public EntityMinecart.EnumMinecartType getMinecartType() {
        return EntityMinecart.EnumMinecartType.CHEST;
    }
    
    public IBlockState getDefaultDisplayTile() {
        return Blocks.chest.getDefaultState().withProperty((IProperty)BlockChest.FACING, (Comparable)EnumFacing.NORTH);
    }
    
    public int getDefaultDisplayTileOffset() {
        return 8;
    }
    
    public String getGuiID() {
        return "minecraft:chest";
    }
    
    public Container createContainer(InventoryPlayer playerInventory, EntityPlayer playerIn) {
        return (Container)new ContainerChest((IInventory)playerInventory, (IInventory)this, playerIn);
    }
}
