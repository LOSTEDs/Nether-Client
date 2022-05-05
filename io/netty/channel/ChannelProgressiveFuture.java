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
import io.netty.util.concurrent.ProgressiveFuture;

public interface ChannelProgressiveFuture extends ChannelFuture, ProgressiveFuture<Void> {
    ChannelProgressiveFuture addListener(GenericFutureListener<? extends Future<? super Void>> paramGenericFutureListener);
    
    ChannelProgressiveFuture addListeners(GenericFutureListener<? extends Future<? super Void>>... paramVarArgs);
    
    ChannelProgressiveFuture removeListener(GenericFutureListener<? extends Future<? super Void>> paramGenericFutureListener);
    
    ChannelProgressiveFuture removeListeners(GenericFutureListener<? extends Future<? super Void>>... paramVarArgs);
    
    ChannelProgressiveFuture sync() throws InterruptedException;
    
    ChannelProgressiveFuture syncUninterruptibly();
    
    ChannelProgressiveFuture await() throws InterruptedException;
    
    ChannelProgressiveFuture awaitUninterruptibly();
}
