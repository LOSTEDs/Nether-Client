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
import net.minecraft.util.AxisAlignedBB;

public class EntityAIHurtByTarget extends EntityAITarget {
    private boolean entityCallsForHelp;
    
    private int revengeTimerOld;
    
    private final Class[] targetClasses;
    
    public EntityAIHurtByTarget(EntityCreature creatureIn, boolean entityCallsForHelpIn, Class... targetClassesIn) {
        super(creatureIn, false);
        this.entityCallsForHelp = entityCallsForHelpIn;
        this.targetClasses = targetClassesIn;
        setMutexBits(1);
    }
    
    public boolean shouldExecute() {
        int i = this.taskOwner.getRevengeTimer();
        return (i != this.revengeTimerOld && isSuitableTarget(this.taskOwner.getAITarget(), false));
    }
    
    public void startExecuting() {
        this.taskOwner.setAttackTarget(this.taskOwner.getAITarget());
        this.revengeTimerOld = this.taskOwner.getRevengeTimer();
        if (this.entityCallsForHelp) {
            double d0 = getTargetDistance();
            for (EntityCreature entitycreature : this.taskOwner.worldObj.getEntitiesWithinAABB(this.taskOwner.getClass(), (new AxisAlignedBB(this.taskOwner.posX, this.taskOwner.posY, this.taskOwner.posZ, this.taskOwner.posX + 1.0D, this.taskOwner.posY + 1.0D, this.taskOwner.posZ + 1.0D)).expand(d0, 10.0D, d0))) {
                if (this.taskOwner != entitycreature && entitycreature.getAttackTarget() == null && !entitycreature.isOnSameTeam(this.taskOwner.getAITarget())) {
                    boolean flag = false;
                    byte b;
                    int i;
                    Class[] arrayOfClass;
                    for (i = (arrayOfClass = this.targetClasses).length, b = 0; b < i; ) {
                        Class<?> oclass = arrayOfClass[b];
                        if (entitycreature.getClass() == oclass) {
                            flag = true;
                            break;
                        } 
                        b++;
                    } 
                    if (!flag)
                        setEntityAttackTarget(entitycreature, this.taskOwner.getAITarget()); 
                } 
            } 
        } 
        super.startExecuting();
    }
    
    protected void setEntityAttackTarget(EntityCreature creatureIn, EntityLivingBase entityLivingBaseIn) {
        creatureIn.setAttackTarget(entityLivingBaseIn);
    }
}
