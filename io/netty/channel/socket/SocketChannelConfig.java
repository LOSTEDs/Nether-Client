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

public interface SocketChannelConfig extends ChannelConfig {
    boolean isTcpNoDelay();
    
    SocketChannelConfig setTcpNoDelay(boolean paramBoolean);
    
    int getSoLinger();
    
    SocketChannelConfig setSoLinger(int paramInt);
    
    int getSendBufferSize();
    
    SocketChannelConfig setSendBufferSize(int paramInt);
    
    int getReceiveBufferSize();
    
    SocketChannelConfig setReceiveBufferSize(int paramInt);
    
    boolean isKeepAlive();
    
    SocketChannelConfig setKeepAlive(boolean paramBoolean);
    
    int getTrafficClass();
    
    SocketChannelConfig setTrafficClass(int paramInt);
    
    boolean isReuseAddress();
    
    SocketChannelConfig setReuseAddress(boolean paramBoolean);
    
    SocketChannelConfig setPerformancePreferences(int paramInt1, int paramInt2, int paramInt3);
    
    boolean isAllowHalfClosure();
    
    SocketChannelConfig setAllowHalfClosure(boolean paramBoolean);
    
    SocketChannelConfig setConnectTimeoutMillis(int paramInt);
    
    SocketChannelConfig setMaxMessagesPerRead(int paramInt);
    
    SocketChannelConfig setWriteSpinCount(int paramInt);
    
    SocketChannelConfig setAllocator(ByteBufAllocator paramByteBufAllocator);
    
    SocketChannelConfig setRecvByteBufAllocator(RecvByteBufAllocator paramRecvByteBufAllocator);
    
    SocketChannelConfig setAutoRead(boolean paramBoolean);
    
    SocketChannelConfig setAutoClose(boolean paramBoolean);
    
    SocketChannelConfig setMessageSizeEstimator(MessageSizeEstimator paramMessageSizeEstimator);
}
