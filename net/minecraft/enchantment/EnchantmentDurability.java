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

import java.util.Random;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public class EnchantmentDurability extends Enchantment {
    protected EnchantmentDurability(int enchID, ResourceLocation enchName, int enchWeight) {
        super(enchID, enchName, enchWeight, EnumEnchantmentType.BREAKABLE);
        setName("durability");
    }
    
    public int getMinEnchantability(int enchantmentLevel) {
        return 5 + (enchantmentLevel - 1) * 8;
    }
    
    public int getMaxEnchantability(int enchantmentLevel) {
        return super.getMinEnchantability(enchantmentLevel) + 50;
    }
    
    public int getMaxLevel() {
        return 3;
    }
    
    public boolean canApply(ItemStack stack) {
        return stack.isItemStackDamageable() ? true : super.canApply(stack);
    }
    
    public static boolean negateDamage(ItemStack p_92097_0_, int p_92097_1_, Random p_92097_2_) {
        return (p_92097_0_.getItem() instanceof net.minecraft.item.ItemArmor && p_92097_2_.nextFloat() < 0.6F) ? false : ((p_92097_2_.nextInt(p_92097_1_ + 1) > 0));
    }
}
