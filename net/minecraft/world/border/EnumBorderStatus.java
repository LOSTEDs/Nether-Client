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

package net.minecraft.world.border;

public enum EnumBorderStatus {
    GROWING(4259712),
    SHRINKING(16724016),
    STATIONARY(2138367);
    
    private final int id;
    
    EnumBorderStatus(int id) {
        this.id = id;
    }
    
    public int getID() {
        return this.id;
    }
}
