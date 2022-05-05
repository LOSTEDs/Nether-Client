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

import io.netty.util.Recycler;
import io.netty.util.ReferenceCountUtil;
import io.netty.util.concurrent.Promise;

public final class PendingWrite {
    private static final Recycler<PendingWrite> RECYCLER = new Recycler<PendingWrite>() {
            protected PendingWrite newObject(Recycler.Handle handle) {
                return new PendingWrite(handle);
            }
        };
    
    private final Recycler.Handle handle;
    
    private Object msg;
    
    private Promise<Void> promise;
    
    public static PendingWrite newInstance(Object msg, Promise<Void> promise) {
        PendingWrite pending = (PendingWrite)RECYCLER.get();
        pending.msg = msg;
        pending.promise = promise;
        return pending;
    }
    
    private PendingWrite(Recycler.Handle handle) {
        this.handle = handle;
    }
    
    public boolean recycle() {
        this.msg = null;
        this.promise = null;
        return RECYCLER.recycle(this, this.handle);
    }
    
    public boolean failAndRecycle(Throwable cause) {
        ReferenceCountUtil.release(this.msg);
        if (this.promise != null)
            this.promise.setFailure(cause); 
        return recycle();
    }
    
    public boolean successAndRecycle() {
        if (this.promise != null)
            this.promise.setSuccess(null); 
        return recycle();
    }
    
    public Object msg() {
        return this.msg;
    }
    
    public Promise<Void> promise() {
        return this.promise;
    }
    
    public Promise<Void> recycleAndGet() {
        Promise<Void> promise = this.promise;
        recycle();
        return promise;
    }
}
