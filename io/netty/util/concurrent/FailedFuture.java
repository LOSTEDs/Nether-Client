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

import io.netty.util.internal.PlatformDependent;

public final class FailedFuture<V> extends CompleteFuture<V> {
    private final Throwable cause;
    
    public FailedFuture(EventExecutor executor, Throwable cause) {
        super(executor);
        if (cause == null)
            throw new NullPointerException("cause"); 
        this.cause = cause;
    }
    
    public Throwable cause() {
        return this.cause;
    }
    
    public boolean isSuccess() {
        return false;
    }
    
    public Future<V> sync() {
        PlatformDependent.throwException(this.cause);
        return this;
    }
    
    public Future<V> syncUninterruptibly() {
        PlatformDependent.throwException(this.cause);
        return this;
    }
    
    public V getNow() {
        return null;
    }
}
