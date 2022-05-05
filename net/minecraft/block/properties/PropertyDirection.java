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

import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import com.google.common.collect.Collections2;
import com.google.common.collect.Lists;
import java.util.Collection;
import net.minecraft.util.EnumFacing;

public class PropertyDirection extends PropertyEnum<EnumFacing> {
    protected PropertyDirection(String name, Collection<EnumFacing> values) {
        super(name, EnumFacing.class, values);
    }
    
    public static PropertyDirection create(String name) {
        return create(name, Predicates.alwaysTrue());
    }
    
    public static PropertyDirection create(String name, Predicate<EnumFacing> filter) {
        return create(name, Collections2.filter(Lists.newArrayList((Object[])EnumFacing.values()), filter));
    }
    
    public static PropertyDirection create(String name, Collection<EnumFacing> values) {
        return new PropertyDirection(name, values);
    }
}
