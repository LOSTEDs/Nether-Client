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

package io.netty.channel.socket;

import io.netty.buffer.ByteBufAllocator;
import io.netty.channel.ChannelConfig;
import io.netty.channel.MessageSizeEstimator;
import io.netty.channel.RecvByteBufAllocator;

public interface ServerSocketChannelConfig extends ChannelConfig {
    int getBacklog();
    
    ServerSocketChannelConfig setBacklog(int paramInt);
    
    boolean isReuseAddress();
    
    ServerSocketChannelConfig setReuseAddress(boolean paramBoolean);
    
    int getReceiveBufferSize();
    
    ServerSocketChannelConfig setReceiveBufferSize(int paramInt);
    
    ServerSocketChannelConfig setPerformancePreferences(int paramInt1, int paramInt2, int paramInt3);
    
    ServerSocketChannelConfig setConnectTimeoutMillis(int paramInt);
    
    ServerSocketChannelConfig setMaxMessagesPerRead(int paramInt);
    
    ServerSocketChannelConfig setWriteSpinCount(int paramInt);
    
    ServerSocketChannelConfig setAllocator(ByteBufAllocator paramByteBufAllocator);
    
    ServerSocketChannelConfig setRecvByteBufAllocator(RecvByteBufAllocator paramRecvByteBufAllocator);
    
    ServerSocketChannelConfig setAutoRead(boolean paramBoolean);
    
    ServerSocketChannelConfig setMessageSizeEstimator(MessageSizeEstimator paramMessageSizeEstimator);
}
