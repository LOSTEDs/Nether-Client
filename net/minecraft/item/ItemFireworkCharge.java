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

import java.util.List;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagIntArray;
import net.minecraft.util.StatCollector;

public class ItemFireworkCharge extends Item {
    public int getColorFromItemStack(ItemStack stack, int renderPass) {
        if (renderPass != 1)
            return super.getColorFromItemStack(stack, renderPass); 
        NBTBase nbtbase = getExplosionTag(stack, "Colors");
        if (!(nbtbase instanceof NBTTagIntArray))
            return 9079434; 
        NBTTagIntArray nbttagintarray = (NBTTagIntArray)nbtbase;
        int[] aint = nbttagintarray.getIntArray();
        if (aint.length == 1)
            return aint[0]; 
        int i = 0;
        int j = 0;
        int k = 0;
        byte b;
        int m, arrayOfInt1[];
        for (m = (arrayOfInt1 = aint).length, b = 0; b < m; ) {
            int l = arrayOfInt1[b];
            i += (l & 0xFF0000) >> 16;
            j += (l & 0xFF00) >> 8;
            k += (l & 0xFF) >> 0;
            b++;
        } 
        i /= aint.length;
        j /= aint.length;
        k /= aint.length;
        return i << 16 | j << 8 | k;
    }
    
    public static NBTBase getExplosionTag(ItemStack stack, String key) {
        if (stack.hasTagCompound()) {
            NBTTagCompound nbttagcompound = stack.getTagCompound().getCompoundTag("Explosion");
            if (nbttagcompound != null)
                return nbttagcompound.getTag(key); 
        } 
        return null;
    }
    
    public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced) {
        if (stack.hasTagCompound()) {
            NBTTagCompound nbttagcompound = stack.getTagCompound().getCompoundTag("Explosion");
            if (nbttagcompound != null)
                addExplosionInfo(nbttagcompound, tooltip); 
        } 
    }
    
    public static void addExplosionInfo(NBTTagCompound nbt, List<String> tooltip) {
        byte b0 = nbt.getByte("Type");
        if (b0 >= 0 && b0 <= 4) {
            tooltip.add(StatCollector.translateToLocal("item.fireworksCharge.type." + b0).trim());
        } else {
            tooltip.add(StatCollector.translateToLocal("item.fireworksCharge.type").trim());
        } 
        int[] aint = nbt.getIntArray("Colors");
        if (aint.length > 0) {
            boolean flag = true;
            String s = "";
            byte b;
            int i, arrayOfInt[];
            for (i = (arrayOfInt = aint).length, b = 0; b < i; ) {
                int k = arrayOfInt[b];
                if (!flag)
                    s = String.valueOf(s) + ", "; 
                flag = false;
                boolean flag1 = false;
                for (int j = 0; j < ItemDye.dyeColors.length; j++) {
                    if (k == ItemDye.dyeColors[j]) {
                        flag1 = true;
                        s = String.valueOf(s) + StatCollector.translateToLocal("item.fireworksCharge." + EnumDyeColor.byDyeDamage(j).getUnlocalizedName());
                        break;
                    } 
                } 
                if (!flag1)
                    s = String.valueOf(s) + StatCollector.translateToLocal("item.fireworksCharge.customColor"); 
                b++;
            } 
            tooltip.add(s);
        } 
        int[] aint1 = nbt.getIntArray("FadeColors");
        if (aint1.length > 0) {
            boolean flag2 = true;
            String s1 = String.valueOf(StatCollector.translateToLocal("item.fireworksCharge.fadeTo")) + " ";
            byte b;
            int i, arrayOfInt[];
            for (i = (arrayOfInt = aint1).length, b = 0; b < i; ) {
                int l = arrayOfInt[b];
                if (!flag2)
                    s1 = String.valueOf(s1) + ", "; 
                flag2 = false;
                boolean flag5 = false;
                for (int k = 0; k < 16; k++) {
                    if (l == ItemDye.dyeColors[k]) {
                        flag5 = true;
                        s1 = String.valueOf(s1) + StatCollector.translateToLocal("item.fireworksCharge." + EnumDyeColor.byDyeDamage(k).getUnlocalizedName());
                        break;
                    } 
                } 
                if (!flag5)
                    s1 = String.valueOf(s1) + StatCollector.translateToLocal("item.fireworksCharge.customColor"); 
                b++;
            } 
            tooltip.add(s1);
        } 
        boolean flag3 = nbt.getBoolean("Trail");
        if (flag3)
            tooltip.add(StatCollector.translateToLocal("item.fireworksCharge.trail")); 
        boolean flag4 = nbt.getBoolean("Flicker");
        if (flag4)
            tooltip.add(StatCollector.translateToLocal("item.fireworksCharge.flicker")); 
    }
}
