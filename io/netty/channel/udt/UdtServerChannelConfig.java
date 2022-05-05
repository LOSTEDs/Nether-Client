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

package io.netty.channel.udt;

import io.netty.buffer.ByteBufAllocator;
import io.netty.channel.MessageSizeEstimator;
import io.netty.channel.RecvByteBufAllocator;

public interface UdtServerChannelConfig extends UdtChannelConfig {
    int getBacklog();
    
    UdtServerChannelConfig setBacklog(int paramInt);
    
    UdtServerChannelConfig setConnectTimeoutMillis(int paramInt);
    
    UdtServerChannelConfig setMaxMessagesPerRead(int paramInt);
    
    UdtServerChannelConfig setWriteSpinCount(int paramInt);
    
    UdtServerChannelConfig setAllocator(ByteBufAllocator paramByteBufAllocator);
    
    UdtServerChannelConfig setRecvByteBufAllocator(RecvByteBufAllocator paramRecvByteBufAllocator);
    
    UdtServerChannelConfig setAutoRead(boolean paramBoolean);
    
    UdtServerChannelConfig setAutoClose(boolean paramBoolean);
    
    UdtServerChannelConfig setProtocolReceiveBufferSize(int paramInt);
    
    UdtServerChannelConfig setProtocolSendBufferSize(int paramInt);
    
    UdtServerChannelConfig setReceiveBufferSize(int paramInt);
    
    UdtServerChannelConfig setReuseAddress(boolean paramBoolean);
    
    UdtServerChannelConfig setSendBufferSize(int paramInt);
    
    UdtServerChannelConfig setSoLinger(int paramInt);
    
    UdtServerChannelConfig setSystemReceiveBufferSize(int paramInt);
    
    UdtServerChannelConfig setSystemSendBufferSize(int paramInt);
    
    UdtServerChannelConfig setWriteBufferHighWaterMark(int paramInt);
    
    UdtServerChannelConfig setWriteBufferLowWaterMark(int paramInt);
    
    UdtServerChannelConfig setMessageSizeEstimator(MessageSizeEstimator paramMessageSizeEstimator);
}
