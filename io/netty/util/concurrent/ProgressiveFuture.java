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

public interface ProgressiveFuture<V> extends Future<V> {
    ProgressiveFuture<V> addListener(GenericFutureListener<? extends Future<? super V>> paramGenericFutureListener);
    
    ProgressiveFuture<V> addListeners(GenericFutureListener<? extends Future<? super V>>... paramVarArgs);
    
    ProgressiveFuture<V> removeListener(GenericFutureListener<? extends Future<? super V>> paramGenericFutureListener);
    
    ProgressiveFuture<V> removeListeners(GenericFutureListener<? extends Future<? super V>>... paramVarArgs);
    
    ProgressiveFuture<V> sync() throws InterruptedException;
    
    ProgressiveFuture<V> syncUninterruptibly();
    
    ProgressiveFuture<V> await() throws InterruptedException;
    
    ProgressiveFuture<V> awaitUninterruptibly();
}
