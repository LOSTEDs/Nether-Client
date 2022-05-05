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

package net.minecraft.entity.boss;

import net.minecraft.entity.Entity;
import net.minecraft.entity.IEntityMultiPart;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;

public class EntityDragonPart extends Entity {
    public final IEntityMultiPart entityDragonObj;
    
    public final String partName;
    
    public EntityDragonPart(IEntityMultiPart parent, String partName, float base, float sizeHeight) {
        super(parent.getWorld());
        setSize(base, sizeHeight);
        this.entityDragonObj = parent;
        this.partName = partName;
    }
    
    protected void entityInit() {}
    
    protected void readEntityFromNBT(NBTTagCompound tagCompund) {}
    
    protected void writeEntityToNBT(NBTTagCompound tagCompound) {}
    
    public boolean canBeCollidedWith() {
        return true;
    }
    
    public boolean attackEntityFrom(DamageSource source, float amount) {
        return isEntityInvulnerable(source) ? false : this.entityDragonObj.attackEntityFromPart(this, source, amount);
    }
    
    public boolean isEntityEqual(Entity entityIn) {
        return !(this != entityIn && this.entityDragonObj != entityIn);
    }
}
