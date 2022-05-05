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

public interface Promise<V> extends Future<V> {
    Promise<V> setSuccess(V paramV);
    
    boolean trySuccess(V paramV);
    
    Promise<V> setFailure(Throwable paramThrowable);
    
    boolean tryFailure(Throwable paramThrowable);
    
    boolean setUncancellable();
    
    Promise<V> addListener(GenericFutureListener<? extends Future<? super V>> paramGenericFutureListener);
    
    Promise<V> addListeners(GenericFutureListener<? extends Future<? super V>>... paramVarArgs);
    
    Promise<V> removeListener(GenericFutureListener<? extends Future<? super V>> paramGenericFutureListener);
    
    Promise<V> removeListeners(GenericFutureListener<? extends Future<? super V>>... paramVarArgs);
    
    Promise<V> await() throws InterruptedException;
    
    Promise<V> awaitUninterruptibly();
    
    Promise<V> sync() throws InterruptedException;
    
    Promise<V> syncUninterruptibly();
}
