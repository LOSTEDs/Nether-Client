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

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.stats.StatList;
import net.minecraft.world.World;

public class ItemBucketMilk extends Item {
    public ItemBucketMilk() {
        setMaxStackSize(1);
        setCreativeTab(CreativeTabs.tabMisc);
    }
    
    public ItemStack onItemUseFinish(ItemStack stack, World worldIn, EntityPlayer playerIn) {
        if (!playerIn.capabilities.isCreativeMode)
            stack.stackSize--; 
        if (!worldIn.isRemote)
            playerIn.clearActivePotions(); 
        playerIn.triggerAchievement(StatList.objectUseStats[Item.getIdFromItem(this)]);
        return (stack.stackSize <= 0) ? new ItemStack(Items.bucket) : stack;
    }
    
    public int getMaxItemUseDuration(ItemStack stack) {
        return 32;
    }
    
    public EnumAction getItemUseAction(ItemStack stack) {
        return EnumAction.DRINK;
    }
    
    public ItemStack onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer playerIn) {
        playerIn.setItemInUse(itemStackIn, getMaxItemUseDuration(itemStackIn));
        return itemStackIn;
    }
}
