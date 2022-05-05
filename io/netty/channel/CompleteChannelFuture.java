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

import io.netty.util.concurrent.CompleteFuture;
import io.netty.util.concurrent.EventExecutor;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;

abstract class CompleteChannelFuture extends CompleteFuture<Void> implements ChannelFuture {
    private final Channel channel;
    
    protected CompleteChannelFuture(Channel channel, EventExecutor executor) {
        super(executor);
        if (channel == null)
            throw new NullPointerException("channel"); 
        this.channel = channel;
    }
    
    protected EventExecutor executor() {
        EventExecutor e = super.executor();
        if (e == null)
            return channel().eventLoop(); 
        return e;
    }
    
    public ChannelFuture addListener(GenericFutureListener<? extends Future<? super Void>> listener) {
        super.addListener(listener);
        return this;
    }
    
    public ChannelFuture addListeners(GenericFutureListener<? extends Future<? super Void>>... listeners) {
        super.addListeners((GenericFutureListener[])listeners);
        return this;
    }
    
    public ChannelFuture removeListener(GenericFutureListener<? extends Future<? super Void>> listener) {
        super.removeListener(listener);
        return this;
    }
    
    public ChannelFuture removeListeners(GenericFutureListener<? extends Future<? super Void>>... listeners) {
        super.removeListeners((GenericFutureListener[])listeners);
        return this;
    }
    
    public ChannelFuture syncUninterruptibly() {
        return this;
    }
    
    public ChannelFuture sync() throws InterruptedException {
        return this;
    }
    
    public ChannelFuture await() throws InterruptedException {
        return this;
    }
    
    public ChannelFuture awaitUninterruptibly() {
        return this;
    }
    
    public Channel channel() {
        return this.channel;
    }
    
    public Void getNow() {
        return null;
    }
}
