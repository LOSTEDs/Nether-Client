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
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntityTameable;

public class EntityAISit extends EntityAIBase {
    private EntityTameable theEntity;
    
    private boolean isSitting;
    
    public EntityAISit(EntityTameable entityIn) {
        this.theEntity = entityIn;
        setMutexBits(5);
    }
    
    public boolean shouldExecute() {
        if (!this.theEntity.isTamed())
            return false; 
        if (this.theEntity.isInWater())
            return false; 
        if (!this.theEntity.onGround)
            return false; 
        EntityLivingBase entitylivingbase = this.theEntity.getOwner();
        return (entitylivingbase == null) ? true : ((this.theEntity.getDistanceSqToEntity((Entity)entitylivingbase) < 144.0D && entitylivingbase.getAITarget() != null) ? false : this.isSitting);
    }
    
    public void startExecuting() {
        this.theEntity.getNavigator().clearPathEntity();
        this.theEntity.setSitting(true);
    }
    
    public void resetTask() {
        this.theEntity.setSitting(false);
    }
    
    public void setSitting(boolean sitting) {
        this.isSitting = sitting;
    }
}
