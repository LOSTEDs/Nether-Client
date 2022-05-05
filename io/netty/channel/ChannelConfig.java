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

import io.netty.buffer.ByteBufAllocator;
import java.util.Map;

public interface ChannelConfig {
    Map<ChannelOption<?>, Object> getOptions();
    
    boolean setOptions(Map<ChannelOption<?>, ?> paramMap);
    
    <T> T getOption(ChannelOption<T> paramChannelOption);
    
    <T> boolean setOption(ChannelOption<T> paramChannelOption, T paramT);
    
    int getConnectTimeoutMillis();
    
    ChannelConfig setConnectTimeoutMillis(int paramInt);
    
    int getMaxMessagesPerRead();
    
    ChannelConfig setMaxMessagesPerRead(int paramInt);
    
    int getWriteSpinCount();
    
    ChannelConfig setWriteSpinCount(int paramInt);
    
    ByteBufAllocator getAllocator();
    
    ChannelConfig setAllocator(ByteBufAllocator paramByteBufAllocator);
    
    RecvByteBufAllocator getRecvByteBufAllocator();
    
    ChannelConfig setRecvByteBufAllocator(RecvByteBufAllocator paramRecvByteBufAllocator);
    
    boolean isAutoRead();
    
    ChannelConfig setAutoRead(boolean paramBoolean);
    
    @Deprecated
    boolean isAutoClose();
    
    @Deprecated
    ChannelConfig setAutoClose(boolean paramBoolean);
    
    int getWriteBufferHighWaterMark();
    
    ChannelConfig setWriteBufferHighWaterMark(int paramInt);
    
    int getWriteBufferLowWaterMark();
    
    ChannelConfig setWriteBufferLowWaterMark(int paramInt);
    
    MessageSizeEstimator getMessageSizeEstimator();
    
    ChannelConfig setMessageSizeEstimator(MessageSizeEstimator paramMessageSizeEstimator);
}
