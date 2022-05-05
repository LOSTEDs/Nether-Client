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
import net.minecraft.entity.passive.EntityPig;
import net.minecraft.entity.player.EntityPlayer;

public class ItemSaddle extends Item {
    public ItemSaddle() {
        this.maxStackSize = 1;
        setCreativeTab(CreativeTabs.tabTransport);
    }
    
    public boolean itemInteractionForEntity(ItemStack stack, EntityPlayer playerIn, EntityLivingBase target) {
        if (target instanceof EntityPig) {
            EntityPig entitypig = (EntityPig)target;
            if (!entitypig.getSaddled() && !entitypig.isChild()) {
                entitypig.setSaddled(true);
                entitypig.worldObj.playSoundAtEntity((Entity)entitypig, "mob.horse.leather", 0.5F, 1.0F);
                stack.stackSize--;
            } 
            return true;
        } 
        return false;
    }
    
    public boolean hitEntity(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker) {
        itemInteractionForEntity(stack, (EntityPlayer)null, target);
        return true;
    }
}
