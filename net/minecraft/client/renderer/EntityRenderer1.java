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

import com.google.common.base.Predicate;
import net.minecraft.entity.Entity;

class EntityRenderer1 implements Predicate {
    final EntityRenderer field_90032_a;
    
    EntityRenderer1(EntityRenderer p_i1243_1_) {
        this.field_90032_a = p_i1243_1_;
    }
    
    public boolean apply(Entity p_apply_1_) {
        return p_apply_1_.canBeCollidedWith();
    }
    
    public boolean apply(Object p_apply_1_) {
        return apply((Entity)p_apply_1_);
    }
}
