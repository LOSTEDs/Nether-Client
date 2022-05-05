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

package net.minecraft.inventory;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.IWorldNameable;

public interface IInventory extends IWorldNameable {
    int getSizeInventory();
    
    ItemStack getStackInSlot(int paramInt);
    
    ItemStack decrStackSize(int paramInt1, int paramInt2);
    
    ItemStack removeStackFromSlot(int paramInt);
    
    void setInventorySlotContents(int paramInt, ItemStack paramItemStack);
    
    int getInventoryStackLimit();
    
    void markDirty();
    
    boolean isUseableByPlayer(EntityPlayer paramEntityPlayer);
    
    void openInventory(EntityPlayer paramEntityPlayer);
    
    void closeInventory(EntityPlayer paramEntityPlayer);
    
    boolean isItemValidForSlot(int paramInt, ItemStack paramItemStack);
    
    int getField(int paramInt);
    
    void setField(int paramInt1, int paramInt2);
    
    int getFieldCount();
    
    void clear();
}
