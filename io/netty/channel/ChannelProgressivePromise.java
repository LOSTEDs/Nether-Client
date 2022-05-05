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
import io.netty.util.concurrent.ProgressivePromise;

public interface ChannelProgressivePromise extends ProgressivePromise<Void>, ChannelProgressiveFuture, ChannelPromise {
    ChannelProgressivePromise addListener(GenericFutureListener<? extends Future<? super Void>> paramGenericFutureListener);
    
    ChannelProgressivePromise addListeners(GenericFutureListener<? extends Future<? super Void>>... paramVarArgs);
    
    ChannelProgressivePromise removeListener(GenericFutureListener<? extends Future<? super Void>> paramGenericFutureListener);
    
    ChannelProgressivePromise removeListeners(GenericFutureListener<? extends Future<? super Void>>... paramVarArgs);
    
    ChannelProgressivePromise sync() throws InterruptedException;
    
    ChannelProgressivePromise syncUninterruptibly();
    
    ChannelProgressivePromise await() throws InterruptedException;
    
    ChannelProgressivePromise awaitUninterruptibly();
    
    ChannelProgressivePromise setSuccess(Void paramVoid);
    
    ChannelProgressivePromise setSuccess();
    
    ChannelProgressivePromise setFailure(Throwable paramThrowable);
    
    ChannelProgressivePromise setProgress(long paramLong1, long paramLong2);
}
