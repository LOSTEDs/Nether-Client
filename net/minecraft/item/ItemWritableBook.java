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

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.stats.StatList;
import net.minecraft.world.World;

public class ItemWritableBook extends Item {
    public ItemWritableBook() {
        setMaxStackSize(1);
    }
    
    public ItemStack onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer playerIn) {
        playerIn.displayGUIBook(itemStackIn);
        playerIn.triggerAchievement(StatList.objectUseStats[Item.getIdFromItem(this)]);
        return itemStackIn;
    }
    
    public static boolean isNBTValid(NBTTagCompound nbt) {
        if (nbt == null)
            return false; 
        if (!nbt.hasKey("pages", 9))
            return false; 
        NBTTagList nbttaglist = nbt.getTagList("pages", 8);
        for (int i = 0; i < nbttaglist.tagCount(); i++) {
            String s = nbttaglist.getStringTagAt(i);
            if (s == null)
                return false; 
            if (s.length() > 32767)
                return false; 
        } 
        return true;
    }
}
