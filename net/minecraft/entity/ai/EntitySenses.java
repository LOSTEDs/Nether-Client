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

import com.google.common.collect.Lists;
import java.util.List;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;

public class EntitySenses {
    EntityLiving entityObj;
    
    List<Entity> seenEntities = Lists.newArrayList();
    
    List<Entity> unseenEntities = Lists.newArrayList();
    
    public EntitySenses(EntityLiving entityObjIn) {
        this.entityObj = entityObjIn;
    }
    
    public void clearSensingCache() {
        this.seenEntities.clear();
        this.unseenEntities.clear();
    }
    
    public boolean canSee(Entity entityIn) {
        if (this.seenEntities.contains(entityIn))
            return true; 
        if (this.unseenEntities.contains(entityIn))
            return false; 
        this.entityObj.worldObj.theProfiler.startSection("canSee");
        boolean flag = this.entityObj.canEntityBeSeen(entityIn);
        this.entityObj.worldObj.theProfiler.endSection();
        if (flag) {
            this.seenEntities.add(entityIn);
        } else {
            this.unseenEntities.add(entityIn);
        } 
        return flag;
    }
}
