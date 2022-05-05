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

import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;
import io.netty.util.concurrent.Promise;

public interface ChannelPromise extends ChannelFuture, Promise<Void> {
    Channel channel();
    
    ChannelPromise setSuccess(Void paramVoid);
    
    ChannelPromise setSuccess();
    
    boolean trySuccess();
    
    ChannelPromise setFailure(Throwable paramThrowable);
    
    ChannelPromise addListener(GenericFutureListener<? extends Future<? super Void>> paramGenericFutureListener);
    
    ChannelPromise addListeners(GenericFutureListener<? extends Future<? super Void>>... paramVarArgs);
    
    ChannelPromise removeListener(GenericFutureListener<? extends Future<? super Void>> paramGenericFutureListener);
    
    ChannelPromise removeListeners(GenericFutureListener<? extends Future<? super Void>>... paramVarArgs);
    
    ChannelPromise sync() throws InterruptedException;
    
    ChannelPromise syncUninterruptibly();
    
    ChannelPromise await() throws InterruptedException;
    
    ChannelPromise awaitUninterruptibly();
}
