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

package net.minecraft.item.crafting;

import net.minecraft.init.Items;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class RecipesMapCloning implements IRecipe {
    public boolean matches(InventoryCrafting inv, World worldIn) {
        int i = 0;
        ItemStack itemstack = null;
        for (int j = 0; j < inv.getSizeInventory(); j++) {
            ItemStack itemstack1 = inv.getStackInSlot(j);
            if (itemstack1 != null)
                if (itemstack1.getItem() == Items.filled_map) {
                    if (itemstack != null)
                        return false; 
                    itemstack = itemstack1;
                } else {
                    if (itemstack1.getItem() != Items.map)
                        return false; 
                    i++;
                }  
        } 
        return (itemstack != null && i > 0);
    }
    
    public ItemStack getCraftingResult(InventoryCrafting inv) {
        int i = 0;
        ItemStack itemstack = null;
        for (int j = 0; j < inv.getSizeInventory(); j++) {
            ItemStack itemstack1 = inv.getStackInSlot(j);
            if (itemstack1 != null)
                if (itemstack1.getItem() == Items.filled_map) {
                    if (itemstack != null)
                        return null; 
                    itemstack = itemstack1;
                } else {
                    if (itemstack1.getItem() != Items.map)
                        return null; 
                    i++;
                }  
        } 
        if (itemstack != null && i >= 1) {
            ItemStack itemstack2 = new ItemStack((Item)Items.filled_map, i + 1, itemstack.getMetadata());
            if (itemstack.hasDisplayName())
                itemstack2.setStackDisplayName(itemstack.getDisplayName()); 
            return itemstack2;
        } 
        return null;
    }
    
    public int getRecipeSize() {
        return 9;
    }
    
    public ItemStack getRecipeOutput() {
        return null;
    }
    
    public ItemStack[] getRemainingItems(InventoryCrafting inv) {
        ItemStack[] aitemstack = new ItemStack[inv.getSizeInventory()];
        for (int i = 0; i < aitemstack.length; i++) {
            ItemStack itemstack = inv.getStackInSlot(i);
            if (itemstack != null && itemstack.getItem().hasContainerItem())
                aitemstack[i] = new ItemStack(itemstack.getItem().getContainerItem()); 
        } 
        return aitemstack;
    }
}
