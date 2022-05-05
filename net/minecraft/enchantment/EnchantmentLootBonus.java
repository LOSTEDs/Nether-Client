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

import net.minecraft.util.ResourceLocation;

public class EnchantmentLootBonus extends Enchantment {
    protected EnchantmentLootBonus(int p_i45767_1_, ResourceLocation p_i45767_2_, int p_i45767_3_, EnumEnchantmentType p_i45767_4_) {
        super(p_i45767_1_, p_i45767_2_, p_i45767_3_, p_i45767_4_);
        if (p_i45767_4_ == EnumEnchantmentType.DIGGER) {
            setName("lootBonusDigger");
        } else if (p_i45767_4_ == EnumEnchantmentType.FISHING_ROD) {
            setName("lootBonusFishing");
        } else {
            setName("lootBonus");
        } 
    }
    
    public int getMinEnchantability(int enchantmentLevel) {
        return 15 + (enchantmentLevel - 1) * 9;
    }
    
    public int getMaxEnchantability(int enchantmentLevel) {
        return super.getMinEnchantability(enchantmentLevel) + 50;
    }
    
    public int getMaxLevel() {
        return 3;
    }
    
    public boolean canApplyTogether(Enchantment ench) {
        return (super.canApplyTogether(ench) && ench.effectId != silkTouch.effectId);
    }
}
