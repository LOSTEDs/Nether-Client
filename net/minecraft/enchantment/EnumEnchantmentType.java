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

package net.minecraft.enchantment;

import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;

public enum EnumEnchantmentType {
    ALL, ARMOR, ARMOR_FEET, ARMOR_LEGS, ARMOR_TORSO, ARMOR_HEAD, WEAPON, DIGGER, FISHING_ROD, BREAKABLE, BOW;
    
    public boolean canEnchantItem(Item p_77557_1_) {
        if (this == ALL)
            return true; 
        if (this == BREAKABLE && p_77557_1_.isDamageable())
            return true; 
        if (p_77557_1_ instanceof ItemArmor) {
            if (this == ARMOR)
                return true; 
            ItemArmor itemarmor = (ItemArmor)p_77557_1_;
            return (itemarmor.armorType == 0) ? ((this == ARMOR_HEAD)) : ((itemarmor.armorType == 2) ? ((this == ARMOR_LEGS)) : ((itemarmor.armorType == 1) ? ((this == ARMOR_TORSO)) : ((itemarmor.armorType == 3) ? ((this == ARMOR_FEET)) : false)));
        } 
        return (p_77557_1_ instanceof net.minecraft.item.ItemSword) ? ((this == WEAPON)) : ((p_77557_1_ instanceof net.minecraft.item.ItemTool) ? ((this == DIGGER)) : ((p_77557_1_ instanceof net.minecraft.item.ItemBow) ? ((this == BOW)) : ((p_77557_1_ instanceof net.minecraft.item.ItemFishingRod) ? ((this == FISHING_ROD)) : false)));
    }
}
