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

package net.minecraft.block.properties;

import com.google.common.collect.ImmutableSet;
import java.util.Collection;

public class PropertyBool extends PropertyHelper<Boolean> {
    private final ImmutableSet<Boolean> allowedValues = ImmutableSet.of(Boolean.valueOf(true), Boolean.valueOf(false));
    
    protected PropertyBool(String name) {
        super(name, Boolean.class);
    }
    
    public Collection<Boolean> getAllowedValues() {
        return (Collection<Boolean>)this.allowedValues;
    }
    
    public static PropertyBool create(String name) {
        return new PropertyBool(name);
    }
    
    public String getName(Boolean value) {
        return value.toString();
    }
}
