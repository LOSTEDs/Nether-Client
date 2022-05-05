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

public class EntityAIOpenDoor extends EntityAIDoorInteract {
    boolean closeDoor;
    
    int closeDoorTemporisation;
    
    public EntityAIOpenDoor(EntityLiving entitylivingIn, boolean shouldClose) {
        super(entitylivingIn);
        this.theEntity = entitylivingIn;
        this.closeDoor = shouldClose;
    }
    
    public boolean continueExecuting() {
        return (this.closeDoor && this.closeDoorTemporisation > 0 && super.continueExecuting());
    }
    
    public void startExecuting() {
        this.closeDoorTemporisation = 20;
        this.doorBlock.toggleDoor(this.theEntity.worldObj, this.doorPosition, true);
    }
    
    public void resetTask() {
        if (this.closeDoor)
            this.doorBlock.toggleDoor(this.theEntity.worldObj, this.doorPosition, false); 
    }
    
    public void updateTask() {
        this.closeDoorTemporisation--;
        super.updateTask();
    }
}
