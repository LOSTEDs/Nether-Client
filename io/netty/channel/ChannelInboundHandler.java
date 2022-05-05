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

public interface ChannelInboundHandler extends ChannelHandler {
    void channelRegistered(ChannelHandlerContext paramChannelHandlerContext) throws Exception;
    
    void channelUnregistered(ChannelHandlerContext paramChannelHandlerContext) throws Exception;
    
    void channelActive(ChannelHandlerContext paramChannelHandlerContext) throws Exception;
    
    void channelInactive(ChannelHandlerContext paramChannelHandlerContext) throws Exception;
    
    void channelRead(ChannelHandlerContext paramChannelHandlerContext, Object paramObject) throws Exception;
    
    void channelReadComplete(ChannelHandlerContext paramChannelHandlerContext) throws Exception;
    
    void userEventTriggered(ChannelHandlerContext paramChannelHandlerContext, Object paramObject) throws Exception;
    
    void channelWritabilityChanged(ChannelHandlerContext paramChannelHandlerContext) throws Exception;
    
    void exceptionCaught(ChannelHandlerContext paramChannelHandlerContext, Throwable paramThrowable) throws Exception;
}
