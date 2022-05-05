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

package net.minecraft.entity.passive;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public abstract class EntityWaterMob extends EntityLiving implements IAnimals {
    public EntityWaterMob(World worldIn) {
        super(worldIn);
    }
    
    public boolean canBreatheUnderwater() {
        return true;
    }
    
    public boolean getCanSpawnHere() {
        return true;
    }
    
    public boolean isNotColliding() {
        return this.worldObj.checkNoEntityCollision(getEntityBoundingBox(), (Entity)this);
    }
    
    public int getTalkInterval() {
        return 120;
    }
    
    protected boolean canDespawn() {
        return true;
    }
    
    protected int getExperiencePoints(EntityPlayer player) {
        return 1 + this.worldObj.rand.nextInt(3);
    }
    
    public void onEntityUpdate() {
        int i = getAir();
        super.onEntityUpdate();
        if (isEntityAlive() && !isInWater()) {
            i--;
            setAir(i);
            if (getAir() == -20) {
                setAir(0);
                attackEntityFrom(DamageSource.drown, 2.0F);
            } 
        } else {
            setAir(300);
        } 
    }
    
    public boolean isPushedByWater() {
        return false;
    }
}
