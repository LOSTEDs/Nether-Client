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

package net.minecraft.client.renderer;

import net.minecraft.util.BlockPos;

public class DestroyBlockProgress {
    private final int miningPlayerEntId;
    
    private final BlockPos position;
    
    private int partialBlockProgress;
    
    private int createdAtCloudUpdateTick;
    
    public DestroyBlockProgress(int miningPlayerEntIdIn, BlockPos positionIn) {
        this.miningPlayerEntId = miningPlayerEntIdIn;
        this.position = positionIn;
    }
    
    public BlockPos getPosition() {
        return this.position;
    }
    
    public void setPartialBlockDamage(int damage) {
        if (damage > 10)
            damage = 10; 
        this.partialBlockProgress = damage;
    }
    
    public int getPartialBlockDamage() {
        return this.partialBlockProgress;
    }
    
    public void setCloudUpdateTick(int createdAtCloudUpdateTickIn) {
        this.createdAtCloudUpdateTick = createdAtCloudUpdateTickIn;
    }
    
    public int getCreationCloudUpdateTick() {
        return this.createdAtCloudUpdateTick;
    }
}
