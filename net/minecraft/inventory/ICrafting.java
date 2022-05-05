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

import java.util.List;
import net.minecraft.item.ItemStack;

public interface ICrafting {
    void updateCraftingInventory(Container paramContainer, List<ItemStack> paramList);
    
    void sendSlotContents(Container paramContainer, int paramInt, ItemStack paramItemStack);
    
    void sendProgressBarUpdate(Container paramContainer, int paramInt1, int paramInt2);
    
    void func_175173_a(Container paramContainer, IInventory paramIInventory);
}
