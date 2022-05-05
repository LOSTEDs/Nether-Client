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

package net.minecraft.item;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.stats.StatList;
import net.minecraft.world.World;
import net.minecraft.world.WorldSavedData;
import net.minecraft.world.storage.MapData;

public class ItemEmptyMap extends ItemMapBase {
    protected ItemEmptyMap() {
        setCreativeTab(CreativeTabs.tabMisc);
    }
    
    public ItemStack onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer playerIn) {
        ItemStack itemstack = new ItemStack(Items.filled_map, 1, worldIn.getUniqueDataId("map"));
        String s = "map_" + itemstack.getMetadata();
        MapData mapdata = new MapData(s);
        worldIn.setItemData(s, (WorldSavedData)mapdata);
        mapdata.scale = 0;
        mapdata.calculateMapCenter(playerIn.posX, playerIn.posZ, mapdata.scale);
        mapdata.dimension = (byte)worldIn.provider.getDimensionId();
        mapdata.markDirty();
        itemStackIn.stackSize--;
        if (itemStackIn.stackSize <= 0)
            return itemstack; 
        if (!playerIn.inventory.addItemStackToInventory(itemstack.copy()))
            playerIn.dropPlayerItemWithRandomChoice(itemstack, false); 
        playerIn.triggerAchievement(StatList.objectUseStats[Item.getIdFromItem(this)]);
        return itemStackIn;
    }
}
