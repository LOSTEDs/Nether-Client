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

public interface ChannelFutureListener extends GenericFutureListener<ChannelFuture> {
    public static final ChannelFutureListener CLOSE = new ChannelFutureListener() {
            public void operationComplete(ChannelFuture future) {
                future.channel().close();
            }
        };
    
    public static final ChannelFutureListener CLOSE_ON_FAILURE = new ChannelFutureListener() {
            public void operationComplete(ChannelFuture future) {
                if (!future.isSuccess())
                    future.channel().close(); 
            }
        };
    
    public static final ChannelFutureListener FIRE_EXCEPTION_ON_FAILURE = new ChannelFutureListener() {
            public void operationComplete(ChannelFuture future) {
                if (!future.isSuccess())
                    future.channel().pipeline().fireExceptionCaught(future.cause()); 
            }
        };
}
