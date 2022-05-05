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

public interface ChannelOutboundHandler extends ChannelHandler {
    void bind(ChannelHandlerContext paramChannelHandlerContext, SocketAddress paramSocketAddress, ChannelPromise paramChannelPromise) throws Exception;
    
    void connect(ChannelHandlerContext paramChannelHandlerContext, SocketAddress paramSocketAddress1, SocketAddress paramSocketAddress2, ChannelPromise paramChannelPromise) throws Exception;
    
    void disconnect(ChannelHandlerContext paramChannelHandlerContext, ChannelPromise paramChannelPromise) throws Exception;
    
    void close(ChannelHandlerContext paramChannelHandlerContext, ChannelPromise paramChannelPromise) throws Exception;
    
    void deregister(ChannelHandlerContext paramChannelHandlerContext, ChannelPromise paramChannelPromise) throws Exception;
    
    void read(ChannelHandlerContext paramChannelHandlerContext) throws Exception;
    
    void write(ChannelHandlerContext paramChannelHandlerContext, Object paramObject, ChannelPromise paramChannelPromise) throws Exception;
    
    void flush(ChannelHandlerContext paramChannelHandlerContext) throws Exception;
}
