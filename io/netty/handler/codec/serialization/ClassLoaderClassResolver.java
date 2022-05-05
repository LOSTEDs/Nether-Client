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

class ClassLoaderClassResolver implements ClassResolver {
    private final ClassLoader classLoader;
    
    ClassLoaderClassResolver(ClassLoader classLoader) {
        this.classLoader = classLoader;
    }
    
    public Class<?> resolve(String className) throws ClassNotFoundException {
        try {
            return this.classLoader.loadClass(className);
        } catch (ClassNotFoundException ignored) {
            return Class.forName(className, false, this.classLoader);
        } 
    }
}
