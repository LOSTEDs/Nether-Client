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
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;

public class EntityAITradePlayer extends EntityAIBase {
    private EntityVillager villager;
    
    public EntityAITradePlayer(EntityVillager villagerIn) {
        this.villager = villagerIn;
        setMutexBits(5);
    }
    
    public boolean shouldExecute() {
        if (!this.villager.isEntityAlive())
            return false; 
        if (this.villager.isInWater())
            return false; 
        if (!this.villager.onGround)
            return false; 
        if (this.villager.velocityChanged)
            return false; 
        EntityPlayer entityplayer = this.villager.getCustomer();
        return (entityplayer == null) ? false : ((this.villager.getDistanceSqToEntity((Entity)entityplayer) > 16.0D) ? false : (entityplayer.openContainer instanceof net.minecraft.inventory.Container));
    }
    
    public void startExecuting() {
        this.villager.getNavigator().clearPathEntity();
    }
    
    public void resetTask() {
        this.villager.setCustomer(null);
    }
}
