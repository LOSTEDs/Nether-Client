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

package net.minecraft.entity.monster;

import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.passive.IAnimals;
import net.minecraft.world.World;

public abstract class EntityGolem extends EntityCreature implements IAnimals {
    public EntityGolem(World worldIn) {
        super(worldIn);
    }
    
    public void fall(float distance, float damageMultiplier) {}
    
    protected String getLivingSound() {
        return "none";
    }
    
    protected String getHurtSound() {
        return "none";
    }
    
    protected String getDeathSound() {
        return "none";
    }
    
    public int getTalkInterval() {
        return 120;
    }
    
    protected boolean canDespawn() {
        return false;
    }
}
