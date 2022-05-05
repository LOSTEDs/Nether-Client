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

package net.minecraft.entity.ai;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;

public class EntityAILookAtTradePlayer extends EntityAIWatchClosest {
    private final EntityVillager theMerchant;
    
    public EntityAILookAtTradePlayer(EntityVillager theMerchantIn) {
        super((EntityLiving)theMerchantIn, (Class)EntityPlayer.class, 8.0F);
        this.theMerchant = theMerchantIn;
    }
    
    public boolean shouldExecute() {
        if (this.theMerchant.isTrading()) {
            this.closestEntity = (Entity)this.theMerchant.getCustomer();
            return true;
        } 
        return false;
    }
}
