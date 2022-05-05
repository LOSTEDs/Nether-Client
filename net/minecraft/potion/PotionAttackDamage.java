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

package net.minecraft.potion;

import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.util.ResourceLocation;

public class PotionAttackDamage extends Potion {
    protected PotionAttackDamage(int potionID, ResourceLocation location, boolean badEffect, int potionColor) {
        super(potionID, location, badEffect, potionColor);
    }
    
    public double getAttributeModifierAmount(int p_111183_1_, AttributeModifier modifier) {
        return (this.id == Potion.weakness.id) ? (-0.5F * (p_111183_1_ + 1)) : (1.3D * (p_111183_1_ + 1));
    }
}
