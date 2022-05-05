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
import net.minecraft.entity.monster.EntityIronGolem;
import net.minecraft.village.Village;

public class EntityAIDefendVillage extends EntityAITarget {
    EntityIronGolem irongolem;
    
    EntityLivingBase villageAgressorTarget;
    
    public EntityAIDefendVillage(EntityIronGolem ironGolemIn) {
        super((EntityCreature)ironGolemIn, false, true);
        this.irongolem = ironGolemIn;
        setMutexBits(1);
    }
    
    public boolean shouldExecute() {
        Village village = this.irongolem.getVillage();
        if (village == null)
            return false; 
        this.villageAgressorTarget = village.findNearestVillageAggressor((EntityLivingBase)this.irongolem);
        if (this.villageAgressorTarget instanceof net.minecraft.entity.monster.EntityCreeper)
            return false; 
        if (!isSuitableTarget(this.villageAgressorTarget, false)) {
            if (this.taskOwner.getRNG().nextInt(20) == 0) {
                this.villageAgressorTarget = (EntityLivingBase)village.getNearestTargetPlayer((EntityLivingBase)this.irongolem);
                return isSuitableTarget(this.villageAgressorTarget, false);
            } 
            return false;
        } 
        return true;
    }
    
    public void startExecuting() {
        this.irongolem.setAttackTarget(this.villageAgressorTarget);
        super.startExecuting();
    }
}
