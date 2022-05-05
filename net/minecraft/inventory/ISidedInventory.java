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

import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;

public interface ISidedInventory extends IInventory {
    int[] getSlotsForFace(EnumFacing paramEnumFacing);
    
    boolean canInsertItem(int paramInt, ItemStack paramItemStack, EnumFacing paramEnumFacing);
    
    boolean canExtractItem(int paramInt, ItemStack paramItemStack, EnumFacing paramEnumFacing);
}
