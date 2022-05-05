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

public abstract class WorldSavedData {
    public final String mapName;
    
    private boolean dirty;
    
    public WorldSavedData(String name) {
        this.mapName = name;
    }
    
    public abstract void readFromNBT(NBTTagCompound paramNBTTagCompound);
    
    public abstract void writeToNBT(NBTTagCompound paramNBTTagCompound);
    
    public void markDirty() {
        setDirty(true);
    }
    
    public void setDirty(boolean isDirty) {
        this.dirty = isDirty;
    }
    
    public boolean isDirty() {
        return this.dirty;
    }
}
