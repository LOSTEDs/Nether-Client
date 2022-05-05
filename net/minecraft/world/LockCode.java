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

package net.minecraft.world;

import net.minecraft.nbt.NBTTagCompound;

public class LockCode {
    public static final LockCode EMPTY_CODE = new LockCode("");
    
    private final String lock;
    
    public LockCode(String code) {
        this.lock = code;
    }
    
    public boolean isEmpty() {
        return !(this.lock != null && !this.lock.isEmpty());
    }
    
    public String getLock() {
        return this.lock;
    }
    
    public void toNBT(NBTTagCompound nbt) {
        nbt.setString("Lock", this.lock);
    }
    
    public static LockCode fromNBT(NBTTagCompound nbt) {
        if (nbt.hasKey("Lock", 8)) {
            String s = nbt.getString("Lock");
            return new LockCode(s);
        } 
        return EMPTY_CODE;
    }
}
