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

package net.minecraft.src;

public class RangeInt {
    private int min;
    
    private int max;
    
    public RangeInt(int p_i90_1_, int p_i90_2_) {
        this.min = Math.min(p_i90_1_, p_i90_2_);
        this.max = Math.max(p_i90_1_, p_i90_2_);
    }
    
    public boolean isInRange(int p_isInRange_1_) {
        return (p_isInRange_1_ < this.min) ? false : ((p_isInRange_1_ <= this.max));
    }
    
    public int getMin() {
        return this.min;
    }
    
    public int getMax() {
        return this.max;
    }
    
    public String toString() {
        return "min: " + this.min + ", max: " + this.max;
    }
}
