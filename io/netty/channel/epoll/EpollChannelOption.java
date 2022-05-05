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

package io.netty.channel.epoll;

import io.netty.channel.ChannelOption;

public final class EpollChannelOption<T> extends ChannelOption<T> {
    public static final ChannelOption<Boolean> TCP_CORK = valueOf("TCP_CORK");
    
    public static final ChannelOption<Integer> TCP_KEEPIDLE = valueOf("TCP_KEEPIDLE");
    
    public static final ChannelOption<Integer> TCP_KEEPINTVL = valueOf("TCP_KEEPINTVL");
    
    public static final ChannelOption<Integer> TCP_KEEPCNT = valueOf("TCP_KEEPCNT");
    
    public static final ChannelOption<Boolean> SO_REUSEPORT = valueOf("SO_REUSEPORT");
    
    private EpollChannelOption(String name) {
        super(name);
    }
}
