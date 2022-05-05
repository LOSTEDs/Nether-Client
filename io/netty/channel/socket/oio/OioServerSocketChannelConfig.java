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

package io.netty.channel.socket.oio;

import io.netty.buffer.ByteBufAllocator;
import io.netty.channel.MessageSizeEstimator;
import io.netty.channel.RecvByteBufAllocator;
import io.netty.channel.socket.ServerSocketChannelConfig;

public interface OioServerSocketChannelConfig extends ServerSocketChannelConfig {
    OioServerSocketChannelConfig setSoTimeout(int paramInt);
    
    int getSoTimeout();
    
    OioServerSocketChannelConfig setBacklog(int paramInt);
    
    OioServerSocketChannelConfig setReuseAddress(boolean paramBoolean);
    
    OioServerSocketChannelConfig setReceiveBufferSize(int paramInt);
    
    OioServerSocketChannelConfig setPerformancePreferences(int paramInt1, int paramInt2, int paramInt3);
    
    OioServerSocketChannelConfig setConnectTimeoutMillis(int paramInt);
    
    OioServerSocketChannelConfig setMaxMessagesPerRead(int paramInt);
    
    OioServerSocketChannelConfig setWriteSpinCount(int paramInt);
    
    OioServerSocketChannelConfig setAllocator(ByteBufAllocator paramByteBufAllocator);
    
    OioServerSocketChannelConfig setRecvByteBufAllocator(RecvByteBufAllocator paramRecvByteBufAllocator);
    
    OioServerSocketChannelConfig setAutoRead(boolean paramBoolean);
    
    OioServerSocketChannelConfig setAutoClose(boolean paramBoolean);
    
    OioServerSocketChannelConfig setWriteBufferHighWaterMark(int paramInt);
    
    OioServerSocketChannelConfig setWriteBufferLowWaterMark(int paramInt);
    
    OioServerSocketChannelConfig setMessageSizeEstimator(MessageSizeEstimator paramMessageSizeEstimator);
}
