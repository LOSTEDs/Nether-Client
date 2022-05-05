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
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraft.world.storage.MapData;

public class RecipesMapExtending extends ShapedRecipes {
    public RecipesMapExtending() {
        super(3, 3, new ItemStack[] { new ItemStack(Items.paper), new ItemStack(Items.paper), new ItemStack(Items.paper), new ItemStack(Items.paper), new ItemStack((Item)Items.filled_map, 0, 32767), new ItemStack(Items.paper), new ItemStack(Items.paper), new ItemStack(Items.paper), new ItemStack(Items.paper) }new ItemStack((Item)Items.map, 0, 0));
    }
    
    public boolean matches(InventoryCrafting inv, World worldIn) {
        if (!super.matches(inv, worldIn))
            return false; 
        ItemStack itemstack = null;
        for (int i = 0; i < inv.getSizeInventory() && itemstack == null; i++) {
            ItemStack itemstack1 = inv.getStackInSlot(i);
            if (itemstack1 != null && itemstack1.getItem() == Items.filled_map)
                itemstack = itemstack1; 
        } 
        if (itemstack == null)
            return false; 
        MapData mapdata = Items.filled_map.getMapData(itemstack, worldIn);
        return (mapdata == null) ? false : ((mapdata.scale < 4));
    }
    
    public ItemStack getCraftingResult(InventoryCrafting inv) {
        ItemStack itemstack = null;
        for (int i = 0; i < inv.getSizeInventory() && itemstack == null; i++) {
            ItemStack itemstack1 = inv.getStackInSlot(i);
            if (itemstack1 != null && itemstack1.getItem() == Items.filled_map)
                itemstack = itemstack1; 
        } 
        itemstack = itemstack.copy();
        itemstack.stackSize = 1;
        if (itemstack.getTagCompound() == null)
            itemstack.setTagCompound(new NBTTagCompound()); 
        itemstack.getTagCompound().setBoolean("map_is_scaling", true);
        return itemstack;
    }
}
