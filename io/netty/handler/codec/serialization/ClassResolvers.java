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

import io.netty.util.internal.PlatformDependent;
import java.lang.ref.Reference;
import java.util.HashMap;

public final class ClassResolvers {
    public static ClassResolver cacheDisabled(ClassLoader classLoader) {
        return new ClassLoaderClassResolver(defaultClassLoader(classLoader));
    }
    
    public static ClassResolver weakCachingResolver(ClassLoader classLoader) {
        return new CachingClassResolver(new ClassLoaderClassResolver(defaultClassLoader(classLoader)), new WeakReferenceMap<String, Class<?>>(new HashMap<String, Reference<Class<?>>>()));
    }
    
    public static ClassResolver softCachingResolver(ClassLoader classLoader) {
        return new CachingClassResolver(new ClassLoaderClassResolver(defaultClassLoader(classLoader)), new SoftReferenceMap<String, Class<?>>(new HashMap<String, Reference<Class<?>>>()));
    }
    
    public static ClassResolver weakCachingConcurrentResolver(ClassLoader classLoader) {
        return new CachingClassResolver(new ClassLoaderClassResolver(defaultClassLoader(classLoader)), new WeakReferenceMap<String, Class<?>>(PlatformDependent.newConcurrentHashMap()));
    }
    
    public static ClassResolver softCachingConcurrentResolver(ClassLoader classLoader) {
        return new CachingClassResolver(new ClassLoaderClassResolver(defaultClassLoader(classLoader)), new SoftReferenceMap<String, Class<?>>(PlatformDependent.newConcurrentHashMap()));
    }
    
    static ClassLoader defaultClassLoader(ClassLoader classLoader) {
        if (classLoader != null)
            return classLoader; 
        ClassLoader contextClassLoader = PlatformDependent.getContextClassLoader();
        if (contextClassLoader != null)
            return contextClassLoader; 
        return PlatformDependent.getClassLoader(ClassResolvers.class);
    }
}
