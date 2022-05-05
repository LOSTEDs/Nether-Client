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

package net.minecraft.tileentity;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.IChatComponent;
import net.minecraft.world.IInteractionObject;
import net.minecraft.world.ILockableContainer;
import net.minecraft.world.LockCode;

public abstract class TileEntityLockable extends TileEntity implements IInteractionObject, ILockableContainer {
    private LockCode code = LockCode.EMPTY_CODE;
    
    public void readFromNBT(NBTTagCompound compound) {
        super.readFromNBT(compound);
        this.code = LockCode.fromNBT(compound);
    }
    
    public void writeToNBT(NBTTagCompound compound) {
        super.writeToNBT(compound);
        if (this.code != null)
            this.code.toNBT(compound); 
    }
    
    public boolean isLocked() {
        return (this.code != null && !this.code.isEmpty());
    }
    
    public LockCode getLockCode() {
        return this.code;
    }
    
    public void setLockCode(LockCode code) {
        this.code = code;
    }
    
    public IChatComponent getDisplayName() {
        return hasCustomName() ? (IChatComponent)new ChatComponentText(getName()) : (IChatComponent)new ChatComponentTranslation(getName(), new Object[0]);
    }
}
