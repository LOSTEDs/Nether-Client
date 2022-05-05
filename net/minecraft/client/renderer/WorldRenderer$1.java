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

import com.google.common.primitives.Floats;
import java.util.Comparator;

class WorldRenderer$1 implements Comparator {
    final float[] field_181659_a;
    
    final WorldRenderer field_181660_b;
    
    WorldRenderer$1(WorldRenderer p_i46500_1_, float[] p_i46500_2_) {
        this.field_181660_b = p_i46500_1_;
        this.field_181659_a = p_i46500_2_;
    }
    
    public int compare(Integer p_compare_1_, Integer p_compare_2_) {
        return Floats.compare(this.field_181659_a[p_compare_2_.intValue()], this.field_181659_a[p_compare_1_.intValue()]);
    }
    
    public int compare(Object p_compare_1_, Object p_compare_2_) {
        return compare((Integer)p_compare_1_, (Integer)p_compare_2_);
    }
}
