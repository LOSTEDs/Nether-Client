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

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.attributes.BaseAttributeMap;
import net.minecraft.util.ResourceLocation;

public class PotionAbsorption extends Potion {
    protected PotionAbsorption(int potionID, ResourceLocation location, boolean badEffect, int potionColor) {
        super(potionID, location, badEffect, potionColor);
    }
    
    public void removeAttributesModifiersFromEntity(EntityLivingBase entityLivingBaseIn, BaseAttributeMap p_111187_2_, int amplifier) {
        entityLivingBaseIn.setAbsorptionAmount(entityLivingBaseIn.getAbsorptionAmount() - (4 * (amplifier + 1)));
        super.removeAttributesModifiersFromEntity(entityLivingBaseIn, p_111187_2_, amplifier);
    }
    
    public void applyAttributesModifiersToEntity(EntityLivingBase entityLivingBaseIn, BaseAttributeMap p_111185_2_, int amplifier) {
        entityLivingBaseIn.setAbsorptionAmount(entityLivingBaseIn.getAbsorptionAmount() + (4 * (amplifier + 1)));
        super.applyAttributesModifiersToEntity(entityLivingBaseIn, p_111185_2_, amplifier);
    }
}
