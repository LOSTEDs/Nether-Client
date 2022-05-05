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

public interface ProgressivePromise<V> extends Promise<V>, ProgressiveFuture<V> {
    ProgressivePromise<V> setProgress(long paramLong1, long paramLong2);
    
    boolean tryProgress(long paramLong1, long paramLong2);
    
    ProgressivePromise<V> setSuccess(V paramV);
    
    ProgressivePromise<V> setFailure(Throwable paramThrowable);
    
    ProgressivePromise<V> addListener(GenericFutureListener<? extends Future<? super V>> paramGenericFutureListener);
    
    ProgressivePromise<V> addListeners(GenericFutureListener<? extends Future<? super V>>... paramVarArgs);
    
    ProgressivePromise<V> removeListener(GenericFutureListener<? extends Future<? super V>> paramGenericFutureListener);
    
    ProgressivePromise<V> removeListeners(GenericFutureListener<? extends Future<? super V>>... paramVarArgs);
    
    ProgressivePromise<V> await() throws InterruptedException;
    
    ProgressivePromise<V> awaitUninterruptibly();
    
    ProgressivePromise<V> sync() throws InterruptedException;
    
    ProgressivePromise<V> syncUninterruptibly();
}
