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

package io.netty.util.concurrent;

import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public interface Future<V> extends Future<V> {
    boolean isSuccess();
    
    boolean isCancellable();
    
    Throwable cause();
    
    Future<V> addListener(GenericFutureListener<? extends Future<? super V>> paramGenericFutureListener);
    
    Future<V> addListeners(GenericFutureListener<? extends Future<? super V>>... paramVarArgs);
    
    Future<V> removeListener(GenericFutureListener<? extends Future<? super V>> paramGenericFutureListener);
    
    Future<V> removeListeners(GenericFutureListener<? extends Future<? super V>>... paramVarArgs);
    
    Future<V> sync() throws InterruptedException;
    
    Future<V> syncUninterruptibly();
    
    Future<V> await() throws InterruptedException;
    
    Future<V> awaitUninterruptibly();
    
    boolean await(long paramLong, TimeUnit paramTimeUnit) throws InterruptedException;
    
    boolean await(long paramLong) throws InterruptedException;
    
    boolean awaitUninterruptibly(long paramLong, TimeUnit paramTimeUnit);
    
    boolean awaitUninterruptibly(long paramLong);
    
    V getNow();
    
    boolean cancel(boolean paramBoolean);
}
