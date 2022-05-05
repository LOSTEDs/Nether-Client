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
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;

public class ItemNameTag extends Item {
    public ItemNameTag() {
        setCreativeTab(CreativeTabs.tabTools);
    }
    
    public boolean itemInteractionForEntity(ItemStack stack, EntityPlayer playerIn, EntityLivingBase target) {
        if (!stack.hasDisplayName())
            return false; 
        if (target instanceof EntityLiving) {
            EntityLiving entityliving = (EntityLiving)target;
            entityliving.setCustomNameTag(stack.getDisplayName());
            entityliving.enablePersistence();
            stack.stackSize--;
            return true;
        } 
        return super.itemInteractionForEntity(stack, playerIn, target);
    }
}
