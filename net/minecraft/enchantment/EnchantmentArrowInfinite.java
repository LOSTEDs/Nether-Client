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

public class EnchantmentArrowInfinite extends Enchantment {
    public EnchantmentArrowInfinite(int enchID, ResourceLocation enchName, int enchWeight) {
        super(enchID, enchName, enchWeight, EnumEnchantmentType.BOW);
        setName("arrowInfinite");
    }
    
    public int getMinEnchantability(int enchantmentLevel) {
        return 20;
    }
    
    public int getMaxEnchantability(int enchantmentLevel) {
        return 50;
    }
    
    public int getMaxLevel() {
        return 1;
    }
}
