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

package io.netty.util;

import io.netty.util.internal.PlatformDependent;
import java.util.concurrent.ConcurrentMap;

public final class AttributeKey<T> extends UniqueName {
    private static final ConcurrentMap<String, Boolean> names = PlatformDependent.newConcurrentHashMap();
    
    public static <T> AttributeKey<T> valueOf(String name) {
        return new AttributeKey<T>(name);
    }
    
    @Deprecated
    public AttributeKey(String name) {
        super(names, name, new Object[0]);
    }
}
