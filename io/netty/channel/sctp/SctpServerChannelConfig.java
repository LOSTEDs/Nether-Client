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

public interface SctpServerChannelConfig extends ChannelConfig {
    int getBacklog();
    
    SctpServerChannelConfig setBacklog(int paramInt);
    
    int getSendBufferSize();
    
    SctpServerChannelConfig setSendBufferSize(int paramInt);
    
    int getReceiveBufferSize();
    
    SctpServerChannelConfig setReceiveBufferSize(int paramInt);
    
    SctpStandardSocketOptions.InitMaxStreams getInitMaxStreams();
    
    SctpServerChannelConfig setInitMaxStreams(SctpStandardSocketOptions.InitMaxStreams paramInitMaxStreams);
    
    SctpServerChannelConfig setMaxMessagesPerRead(int paramInt);
    
    SctpServerChannelConfig setWriteSpinCount(int paramInt);
    
    SctpServerChannelConfig setConnectTimeoutMillis(int paramInt);
    
    SctpServerChannelConfig setAllocator(ByteBufAllocator paramByteBufAllocator);
    
    SctpServerChannelConfig setRecvByteBufAllocator(RecvByteBufAllocator paramRecvByteBufAllocator);
    
    SctpServerChannelConfig setAutoRead(boolean paramBoolean);
    
    SctpServerChannelConfig setAutoClose(boolean paramBoolean);
    
    SctpServerChannelConfig setWriteBufferHighWaterMark(int paramInt);
    
    SctpServerChannelConfig setWriteBufferLowWaterMark(int paramInt);
    
    SctpServerChannelConfig setMessageSizeEstimator(MessageSizeEstimator paramMessageSizeEstimator);
}
