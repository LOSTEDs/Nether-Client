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

package io.netty.handler.codec.serialization;

import java.util.Map;

class CachingClassResolver implements ClassResolver {
    private final Map<String, Class<?>> classCache;
    
    private final ClassResolver delegate;
    
    CachingClassResolver(ClassResolver delegate, Map<String, Class<?>> classCache) {
        this.delegate = delegate;
        this.classCache = classCache;
    }
    
    public Class<?> resolve(String className) throws ClassNotFoundException {
        Class<?> clazz = this.classCache.get(className);
        if (clazz != null)
            return clazz; 
        clazz = this.delegate.resolve(className);
        this.classCache.put(className, clazz);
        return clazz;
    }
}
