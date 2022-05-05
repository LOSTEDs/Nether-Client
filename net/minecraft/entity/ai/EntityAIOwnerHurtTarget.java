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

import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntityTameable;

public class EntityAIOwnerHurtTarget extends EntityAITarget {
    EntityTameable theEntityTameable;
    
    EntityLivingBase theTarget;
    
    private int field_142050_e;
    
    public EntityAIOwnerHurtTarget(EntityTameable theEntityTameableIn) {
        super((EntityCreature)theEntityTameableIn, false);
        this.theEntityTameable = theEntityTameableIn;
        setMutexBits(1);
    }
    
    public boolean shouldExecute() {
        if (!this.theEntityTameable.isTamed())
            return false; 
        EntityLivingBase entitylivingbase = this.theEntityTameable.getOwner();
        if (entitylivingbase == null)
            return false; 
        this.theTarget = entitylivingbase.getLastAttacker();
        int i = entitylivingbase.getLastAttackerTime();
        return (i != this.field_142050_e && isSuitableTarget(this.theTarget, false) && this.theEntityTameable.shouldAttackEntity(this.theTarget, entitylivingbase));
    }
    
    public void startExecuting() {
        this.taskOwner.setAttackTarget(this.theTarget);
        EntityLivingBase entitylivingbase = this.theEntityTameable.getOwner();
        if (entitylivingbase != null)
            this.field_142050_e = entitylivingbase.getLastAttackerTime(); 
        super.startExecuting();
    }
}
