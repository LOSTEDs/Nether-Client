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

public class CounterInt {
    private int startValue;
    
    private int value;
    
    public CounterInt(int p_i34_1_) {
        this.startValue = p_i34_1_;
        this.value = p_i34_1_;
    }
    
    public synchronized int nextValue() {
        int i = this.value++;
        return i;
    }
    
    public synchronized void reset() {
        this.value = this.startValue;
    }
    
    public int getValue() {
        return this.value;
    }
}
