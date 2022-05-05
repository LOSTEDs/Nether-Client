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

package io.netty.channel.group;

import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;
import java.util.Iterator;

public interface ChannelGroupFuture extends Future<Void>, Iterable<ChannelFuture> {
    ChannelGroup group();
    
    ChannelFuture find(Channel paramChannel);
    
    boolean isSuccess();
    
    ChannelGroupException cause();
    
    boolean isPartialSuccess();
    
    boolean isPartialFailure();
    
    ChannelGroupFuture addListener(GenericFutureListener<? extends Future<? super Void>> paramGenericFutureListener);
    
    ChannelGroupFuture addListeners(GenericFutureListener<? extends Future<? super Void>>... paramVarArgs);
    
    ChannelGroupFuture removeListener(GenericFutureListener<? extends Future<? super Void>> paramGenericFutureListener);
    
    ChannelGroupFuture removeListeners(GenericFutureListener<? extends Future<? super Void>>... paramVarArgs);
    
    ChannelGroupFuture await() throws InterruptedException;
    
    ChannelGroupFuture awaitUninterruptibly();
    
    ChannelGroupFuture syncUninterruptibly();
    
    ChannelGroupFuture sync() throws InterruptedException;
    
    Iterator<ChannelFuture> iterator();
}
