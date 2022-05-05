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

import java.net.SocketAddress;

public class ChannelDuplexHandler extends ChannelInboundHandlerAdapter implements ChannelOutboundHandler {
    public void bind(ChannelHandlerContext ctx, SocketAddress localAddress, ChannelPromise future) throws Exception {
        ctx.bind(localAddress, future);
    }
    
    public void connect(ChannelHandlerContext ctx, SocketAddress remoteAddress, SocketAddress localAddress, ChannelPromise future) throws Exception {
        ctx.connect(remoteAddress, localAddress, future);
    }
    
    public void disconnect(ChannelHandlerContext ctx, ChannelPromise future) throws Exception {
        ctx.disconnect(future);
    }
    
    public void close(ChannelHandlerContext ctx, ChannelPromise future) throws Exception {
        ctx.close(future);
    }
    
    public void deregister(ChannelHandlerContext ctx, ChannelPromise future) throws Exception {
        ctx.deregister(future);
    }
    
    public void read(ChannelHandlerContext ctx) throws Exception {
        ctx.read();
    }
    
    public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
        ctx.write(msg, promise);
    }
    
    public void flush(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
    }
}
