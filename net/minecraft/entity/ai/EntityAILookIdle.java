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

public class EntityAILookIdle extends EntityAIBase {
    private EntityLiving idleEntity;
    
    private double lookX;
    
    private double lookZ;
    
    private int idleTime;
    
    public EntityAILookIdle(EntityLiving entitylivingIn) {
        this.idleEntity = entitylivingIn;
        setMutexBits(3);
    }
    
    public boolean shouldExecute() {
        return (this.idleEntity.getRNG().nextFloat() < 0.02F);
    }
    
    public boolean continueExecuting() {
        return (this.idleTime >= 0);
    }
    
    public void startExecuting() {
        double d0 = 6.283185307179586D * this.idleEntity.getRNG().nextDouble();
        this.lookX = Math.cos(d0);
        this.lookZ = Math.sin(d0);
        this.idleTime = 20 + this.idleEntity.getRNG().nextInt(20);
    }
    
    public void updateTask() {
        this.idleTime--;
        this.idleEntity.getLookHelper().setLookPosition(this.idleEntity.posX + this.lookX, this.idleEntity.posY + this.idleEntity.getEyeHeight(), this.idleEntity.posZ + this.lookZ, 10.0F, this.idleEntity.getVerticalFaceSpeed());
    }
}
