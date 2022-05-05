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

public interface ChannelFuture extends Future<Void> {
    Channel channel();
    
    ChannelFuture addListener(GenericFutureListener<? extends Future<? super Void>> paramGenericFutureListener);
    
    ChannelFuture addListeners(GenericFutureListener<? extends Future<? super Void>>... paramVarArgs);
    
    ChannelFuture removeListener(GenericFutureListener<? extends Future<? super Void>> paramGenericFutureListener);
    
    ChannelFuture removeListeners(GenericFutureListener<? extends Future<? super Void>>... paramVarArgs);
    
    ChannelFuture sync() throws InterruptedException;
    
    ChannelFuture syncUninterruptibly();
    
    ChannelFuture await() throws InterruptedException;
    
    ChannelFuture awaitUninterruptibly();
}
