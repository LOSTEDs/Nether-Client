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

package net.minecraft.entity.ai.attributes;

import java.util.Collection;
import java.util.UUID;

public interface IAttributeInstance {
    IAttribute getAttribute();
    
    double getBaseValue();
    
    void setBaseValue(double paramDouble);
    
    Collection<AttributeModifier> getModifiersByOperation(int paramInt);
    
    Collection<AttributeModifier> func_111122_c();
    
    boolean hasModifier(AttributeModifier paramAttributeModifier);
    
    AttributeModifier getModifier(UUID paramUUID);
    
    void applyModifier(AttributeModifier paramAttributeModifier);
    
    void removeModifier(AttributeModifier paramAttributeModifier);
    
    void removeAllModifiers();
    
    double getAttributeValue();
}
