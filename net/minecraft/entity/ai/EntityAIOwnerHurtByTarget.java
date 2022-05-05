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

public class EntityAIOwnerHurtByTarget extends EntityAITarget {
    EntityTameable theDefendingTameable;
    
    EntityLivingBase theOwnerAttacker;
    
    private int field_142051_e;
    
    public EntityAIOwnerHurtByTarget(EntityTameable theDefendingTameableIn) {
        super((EntityCreature)theDefendingTameableIn, false);
        this.theDefendingTameable = theDefendingTameableIn;
        setMutexBits(1);
    }
    
    public boolean shouldExecute() {
        if (!this.theDefendingTameable.isTamed())
            return false; 
        EntityLivingBase entitylivingbase = this.theDefendingTameable.getOwner();
        if (entitylivingbase == null)
            return false; 
        this.theOwnerAttacker = entitylivingbase.getAITarget();
        int i = entitylivingbase.getRevengeTimer();
        return (i != this.field_142051_e && isSuitableTarget(this.theOwnerAttacker, false) && this.theDefendingTameable.shouldAttackEntity(this.theOwnerAttacker, entitylivingbase));
    }
    
    public void startExecuting() {
        this.taskOwner.setAttackTarget(this.theOwnerAttacker);
        EntityLivingBase entitylivingbase = this.theDefendingTameable.getOwner();
        if (entitylivingbase != null)
            this.field_142051_e = entitylivingbase.getRevengeTimer(); 
        super.startExecuting();
    }
}
