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

package io.netty.channel.sctp;

import com.sun.nio.sctp.SctpStandardSocketOptions;
import io.netty.buffer.ByteBufAllocator;
import io.netty.channel.ChannelConfig;
import io.netty.channel.MessageSizeEstimator;
import io.netty.channel.RecvByteBufAllocator;

public interface SctpChannelConfig extends ChannelConfig {
    boolean isSctpNoDelay();
    
    SctpChannelConfig setSctpNoDelay(boolean paramBoolean);
    
    int getSendBufferSize();
    
    SctpChannelConfig setSendBufferSize(int paramInt);
    
    int getReceiveBufferSize();
    
    SctpChannelConfig setReceiveBufferSize(int paramInt);
    
    SctpStandardSocketOptions.InitMaxStreams getInitMaxStreams();
    
    SctpChannelConfig setInitMaxStreams(SctpStandardSocketOptions.InitMaxStreams paramInitMaxStreams);
    
    SctpChannelConfig setConnectTimeoutMillis(int paramInt);
    
    SctpChannelConfig setMaxMessagesPerRead(int paramInt);
    
    SctpChannelConfig setWriteSpinCount(int paramInt);
    
    SctpChannelConfig setAllocator(ByteBufAllocator paramByteBufAllocator);
    
    SctpChannelConfig setRecvByteBufAllocator(RecvByteBufAllocator paramRecvByteBufAllocator);
    
    SctpChannelConfig setAutoRead(boolean paramBoolean);
    
    SctpChannelConfig setAutoClose(boolean paramBoolean);
    
    SctpChannelConfig setWriteBufferHighWaterMark(int paramInt);
    
    SctpChannelConfig setWriteBufferLowWaterMark(int paramInt);
    
    SctpChannelConfig setMessageSizeEstimator(MessageSizeEstimator paramMessageSizeEstimator);
}
