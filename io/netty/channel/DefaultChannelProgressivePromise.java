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

package io.netty.channel;

import io.netty.util.concurrent.DefaultProgressivePromise;
import io.netty.util.concurrent.EventExecutor;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;
import io.netty.util.concurrent.ProgressiveFuture;
import io.netty.util.concurrent.ProgressivePromise;
import io.netty.util.concurrent.Promise;

public class DefaultChannelProgressivePromise extends DefaultProgressivePromise<Void> implements ChannelProgressivePromise, ChannelFlushPromiseNotifier.FlushCheckpoint {
    private final Channel channel;
    
    private long checkpoint;
    
    public DefaultChannelProgressivePromise(Channel channel) {
        this.channel = channel;
    }
    
    public DefaultChannelProgressivePromise(Channel channel, EventExecutor executor) {
        super(executor);
        this.channel = channel;
    }
    
    protected EventExecutor executor() {
        EventExecutor e = super.executor();
        if (e == null)
            return channel().eventLoop(); 
        return e;
    }
    
    public Channel channel() {
        return this.channel;
    }
    
    public ChannelProgressivePromise setSuccess() {
        return setSuccess((Void)null);
    }
    
    public ChannelProgressivePromise setSuccess(Void result) {
        super.setSuccess(result);
        return this;
    }
    
    public boolean trySuccess() {
        return trySuccess(null);
    }
    
    public ChannelProgressivePromise setFailure(Throwable cause) {
        super.setFailure(cause);
        return this;
    }
    
    public ChannelProgressivePromise setProgress(long progress, long total) {
        super.setProgress(progress, total);
        return this;
    }
    
    public ChannelProgressivePromise addListener(GenericFutureListener<? extends Future<? super Void>> listener) {
        super.addListener(listener);
        return this;
    }
    
    public ChannelProgressivePromise addListeners(GenericFutureListener<? extends Future<? super Void>>... listeners) {
        super.addListeners((GenericFutureListener[])listeners);
        return this;
    }
    
    public ChannelProgressivePromise removeListener(GenericFutureListener<? extends Future<? super Void>> listener) {
        super.removeListener(listener);
        return this;
    }
    
    public ChannelProgressivePromise removeListeners(GenericFutureListener<? extends Future<? super Void>>... listeners) {
        super.removeListeners((GenericFutureListener[])listeners);
        return this;
    }
    
    public ChannelProgressivePromise sync() throws InterruptedException {
        super.sync();
        return this;
    }
    
    public ChannelProgressivePromise syncUninterruptibly() {
        super.syncUninterruptibly();
        return this;
    }
    
    public ChannelProgressivePromise await() throws InterruptedException {
        super.await();
        return this;
    }
    
    public ChannelProgressivePromise awaitUninterruptibly() {
        super.awaitUninterruptibly();
        return this;
    }
    
    public long flushCheckpoint() {
        return this.checkpoint;
    }
    
    public void flushCheckpoint(long checkpoint) {
        this.checkpoint = checkpoint;
    }
    
    public ChannelProgressivePromise promise() {
        return this;
    }
    
    protected void checkDeadLock() {
        if (channel().isRegistered())
            super.checkDeadLock(); 
    }
}
