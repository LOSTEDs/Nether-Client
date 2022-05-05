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

package io.netty.channel.local;

import io.netty.channel.Channel;
import io.netty.channel.ChannelException;
import io.netty.util.internal.PlatformDependent;
import io.netty.util.internal.StringUtil;
import java.net.SocketAddress;
import java.util.concurrent.ConcurrentMap;

final class LocalChannelRegistry {
    private static final ConcurrentMap<LocalAddress, Channel> boundChannels = PlatformDependent.newConcurrentHashMap();
    
    static LocalAddress register(Channel channel, LocalAddress oldLocalAddress, SocketAddress localAddress) {
        if (oldLocalAddress != null)
            throw new ChannelException("already bound"); 
        if (!(localAddress instanceof LocalAddress))
            throw new ChannelException("unsupported address type: " + StringUtil.simpleClassName(localAddress)); 
        LocalAddress addr = (LocalAddress)localAddress;
        if (LocalAddress.ANY.equals(addr))
            addr = new LocalAddress(channel); 
        Channel boundChannel = boundChannels.putIfAbsent(addr, channel);
        if (boundChannel != null)
            throw new ChannelException("address already in use by: " + boundChannel); 
        return addr;
    }
    
    static Channel get(SocketAddress localAddress) {
        return boundChannels.get(localAddress);
    }
    
    static void unregister(LocalAddress localAddress) {
        boundChannels.remove(localAddress);
    }
}
