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

package net.minecraft.util;

import java.util.Random;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.item.ItemStack;

public class WeightedRandomFishable extends WeightedRandom.Item {
    private final ItemStack returnStack;
    
    private float maxDamagePercent;
    
    private boolean enchantable;
    
    public WeightedRandomFishable(ItemStack returnStackIn, int itemWeightIn) {
        super(itemWeightIn);
        this.returnStack = returnStackIn;
    }
    
    public ItemStack getItemStack(Random random) {
        ItemStack itemstack = this.returnStack.copy();
        if (this.maxDamagePercent > 0.0F) {
            int i = (int)(this.maxDamagePercent * this.returnStack.getMaxDamage());
            int j = itemstack.getMaxDamage() - random.nextInt(random.nextInt(i) + 1);
            if (j > i)
                j = i; 
            if (j < 1)
                j = 1; 
            itemstack.setItemDamage(j);
        } 
        if (this.enchantable)
            EnchantmentHelper.addRandomEnchantment(random, itemstack, 30); 
        return itemstack;
    }
    
    public WeightedRandomFishable setMaxDamagePercent(float maxDamagePercentIn) {
        this.maxDamagePercent = maxDamagePercentIn;
        return this;
    }
    
    public WeightedRandomFishable setEnchantable() {
        this.enchantable = true;
        return this;
    }
}
