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

public class RangeListInt {
    private RangeInt[] ranges = new RangeInt[0];
    
    public RangeListInt() {}
    
    public RangeListInt(RangeInt p_i91_1_) {
        addRange(p_i91_1_);
    }
    
    public void addRange(RangeInt p_addRange_1_) {
        this.ranges = (RangeInt[])Config.addObjectToArray((Object[])this.ranges, p_addRange_1_);
    }
    
    public boolean isInRange(int p_isInRange_1_) {
        for (int i = 0; i < this.ranges.length; i++) {
            RangeInt rangeint = this.ranges[i];
            if (rangeint.isInRange(p_isInRange_1_))
                return true; 
        } 
        return false;
    }
    
    public int getCountRanges() {
        return this.ranges.length;
    }
    
    public RangeInt getRange(int p_getRange_1_) {
        return this.ranges[p_getRange_1_];
    }
    
    public String toString() {
        StringBuffer stringbuffer = new StringBuffer();
        stringbuffer.append("[");
        for (int i = 0; i < this.ranges.length; i++) {
            RangeInt rangeint = this.ranges[i];
            if (i > 0)
                stringbuffer.append(", "); 
            stringbuffer.append(rangeint.toString());
        } 
        stringbuffer.append("]");
        return stringbuffer.toString();
    }
}
