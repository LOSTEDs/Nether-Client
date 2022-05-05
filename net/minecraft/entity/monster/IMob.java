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

import com.google.common.base.Predicate;
import net.minecraft.entity.Entity;
import net.minecraft.entity.passive.IAnimals;

public interface IMob extends IAnimals {
    public static final Predicate<Entity> mobSelector = new Predicate<Entity>() {
            public boolean apply(Entity p_apply_1_) {
                return p_apply_1_ instanceof IMob;
            }
        };
    
    public static final Predicate<Entity> VISIBLE_MOB_SELECTOR = new Predicate<Entity>() {
            public boolean apply(Entity p_apply_1_) {
                return (p_apply_1_ instanceof IMob && !p_apply_1_.isInvisible());
            }
        };
}
