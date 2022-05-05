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

import net.minecraft.entity.EntityLiving;
import net.minecraft.pathfinding.PathNavigateGround;

public class EntityAISwimming extends EntityAIBase {
    private EntityLiving theEntity;
    
    public EntityAISwimming(EntityLiving entitylivingIn) {
        this.theEntity = entitylivingIn;
        setMutexBits(4);
        ((PathNavigateGround)entitylivingIn.getNavigator()).setCanSwim(true);
    }
    
    public boolean shouldExecute() {
        return !(!this.theEntity.isInWater() && !this.theEntity.isInLava());
    }
    
    public void updateTask() {
        if (this.theEntity.getRNG().nextFloat() < 0.8F)
            this.theEntity.getJumpHelper().setJumping(); 
    }
}
