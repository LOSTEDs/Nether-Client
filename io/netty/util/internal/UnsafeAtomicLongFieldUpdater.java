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

package io.netty.util.internal;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.concurrent.atomic.AtomicLongFieldUpdater;
import sun.misc.Unsafe;

final class UnsafeAtomicLongFieldUpdater<T> extends AtomicLongFieldUpdater<T> {
    private final long offset;
    
    private final Unsafe unsafe;
    
    UnsafeAtomicLongFieldUpdater(Unsafe unsafe, Class<?> tClass, String fieldName) throws NoSuchFieldException {
        Field field = tClass.getDeclaredField(fieldName);
        if (!Modifier.isVolatile(field.getModifiers()))
            throw new IllegalArgumentException("Must be volatile"); 
        this.unsafe = unsafe;
        this.offset = unsafe.objectFieldOffset(field);
    }
    
    public boolean compareAndSet(T obj, long expect, long update) {
        return this.unsafe.compareAndSwapLong(obj, this.offset, expect, update);
    }
    
    public boolean weakCompareAndSet(T obj, long expect, long update) {
        return this.unsafe.compareAndSwapLong(obj, this.offset, expect, update);
    }
    
    public void set(T obj, long newValue) {
        this.unsafe.putLongVolatile(obj, this.offset, newValue);
    }
    
    public void lazySet(T obj, long newValue) {
        this.unsafe.putOrderedLong(obj, this.offset, newValue);
    }
    
    public long get(T obj) {
        return this.unsafe.getLongVolatile(obj, this.offset);
    }
}
