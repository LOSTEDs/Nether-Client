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

package net.minecraft.util;

public class MouseFilter {
    private float field_76336_a;
    
    private float field_76334_b;
    
    private float field_76335_c;
    
    public float smooth(float p_76333_1_, float p_76333_2_) {
        this.field_76336_a += p_76333_1_;
        p_76333_1_ = (this.field_76336_a - this.field_76334_b) * p_76333_2_;
        this.field_76335_c += (p_76333_1_ - this.field_76335_c) * 0.5F;
        if ((p_76333_1_ > 0.0F && p_76333_1_ > this.field_76335_c) || (p_76333_1_ < 0.0F && p_76333_1_ < this.field_76335_c))
            p_76333_1_ = this.field_76335_c; 
        this.field_76334_b += p_76333_1_;
        return p_76333_1_;
    }
    
    public void reset() {
        this.field_76336_a = 0.0F;
        this.field_76334_b = 0.0F;
        this.field_76335_c = 0.0F;
    }
}
