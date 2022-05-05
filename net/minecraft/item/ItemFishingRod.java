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
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityFishHook;
import net.minecraft.stats.StatList;
import net.minecraft.world.World;

public class ItemFishingRod extends Item {
    public ItemFishingRod() {
        setMaxDamage(64);
        setMaxStackSize(1);
        setCreativeTab(CreativeTabs.tabTools);
    }
    
    public boolean isFull3D() {
        return true;
    }
    
    public boolean shouldRotateAroundWhenRendering() {
        return true;
    }
    
    public ItemStack onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer playerIn) {
        if (playerIn.fishEntity != null) {
            int i = playerIn.fishEntity.handleHookRetraction();
            itemStackIn.damageItem(i, (EntityLivingBase)playerIn);
            playerIn.swingItem();
        } else {
            worldIn.playSoundAtEntity((Entity)playerIn, "random.bow", 0.5F, 0.4F / (itemRand.nextFloat() * 0.4F + 0.8F));
            if (!worldIn.isRemote)
                worldIn.spawnEntityInWorld((Entity)new EntityFishHook(worldIn, playerIn)); 
            playerIn.swingItem();
            playerIn.triggerAchievement(StatList.objectUseStats[Item.getIdFromItem(this)]);
        } 
        return itemStackIn;
    }
    
    public boolean isItemTool(ItemStack stack) {
        return super.isItemTool(stack);
    }
    
    public int getItemEnchantability() {
        return 1;
    }
}
