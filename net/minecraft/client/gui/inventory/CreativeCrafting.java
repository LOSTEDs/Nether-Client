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

package net.minecraft.client.gui.inventory;

import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;

public class CreativeCrafting implements ICrafting {
    private final Minecraft mc;
    
    public CreativeCrafting(Minecraft mc) {
        this.mc = mc;
    }
    
    public void updateCraftingInventory(Container containerToSend, List<ItemStack> itemsList) {}
    
    public void sendSlotContents(Container containerToSend, int slotInd, ItemStack stack) {
        this.mc.playerController.sendSlotPacket(stack, slotInd);
    }
    
    public void sendProgressBarUpdate(Container containerIn, int varToUpdate, int newValue) {}
    
    public void func_175173_a(Container p_175173_1_, IInventory p_175173_2_) {}
}
