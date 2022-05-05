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
import java.net.InetAddress;
import java.net.NetworkInterface;

public interface DatagramChannelConfig extends ChannelConfig {
    int getSendBufferSize();
    
    DatagramChannelConfig setSendBufferSize(int paramInt);
    
    int getReceiveBufferSize();
    
    DatagramChannelConfig setReceiveBufferSize(int paramInt);
    
    int getTrafficClass();
    
    DatagramChannelConfig setTrafficClass(int paramInt);
    
    boolean isReuseAddress();
    
    DatagramChannelConfig setReuseAddress(boolean paramBoolean);
    
    boolean isBroadcast();
    
    DatagramChannelConfig setBroadcast(boolean paramBoolean);
    
    boolean isLoopbackModeDisabled();
    
    DatagramChannelConfig setLoopbackModeDisabled(boolean paramBoolean);
    
    int getTimeToLive();
    
    DatagramChannelConfig setTimeToLive(int paramInt);
    
    InetAddress getInterface();
    
    DatagramChannelConfig setInterface(InetAddress paramInetAddress);
    
    NetworkInterface getNetworkInterface();
    
    DatagramChannelConfig setNetworkInterface(NetworkInterface paramNetworkInterface);
    
    DatagramChannelConfig setMaxMessagesPerRead(int paramInt);
    
    DatagramChannelConfig setWriteSpinCount(int paramInt);
    
    DatagramChannelConfig setConnectTimeoutMillis(int paramInt);
    
    DatagramChannelConfig setAllocator(ByteBufAllocator paramByteBufAllocator);
    
    DatagramChannelConfig setRecvByteBufAllocator(RecvByteBufAllocator paramRecvByteBufAllocator);
    
    DatagramChannelConfig setAutoRead(boolean paramBoolean);
    
    DatagramChannelConfig setAutoClose(boolean paramBoolean);
    
    DatagramChannelConfig setMessageSizeEstimator(MessageSizeEstimator paramMessageSizeEstimator);
}
