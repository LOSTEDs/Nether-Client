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

import com.sun.nio.sctp.Association;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelPromise;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.util.Set;

public interface SctpChannel extends Channel {
    SctpServerChannel parent();
    
    Association association();
    
    InetSocketAddress localAddress();
    
    Set<InetSocketAddress> allLocalAddresses();
    
    SctpChannelConfig config();
    
    InetSocketAddress remoteAddress();
    
    Set<InetSocketAddress> allRemoteAddresses();
    
    ChannelFuture bindAddress(InetAddress paramInetAddress);
    
    ChannelFuture bindAddress(InetAddress paramInetAddress, ChannelPromise paramChannelPromise);
    
    ChannelFuture unbindAddress(InetAddress paramInetAddress);
    
    ChannelFuture unbindAddress(InetAddress paramInetAddress, ChannelPromise paramChannelPromise);
}
