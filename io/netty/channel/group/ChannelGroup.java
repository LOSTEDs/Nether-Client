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

package io.netty.channel.group;

import io.netty.channel.Channel;
import java.util.Set;

public interface ChannelGroup extends Set<Channel>, Comparable<ChannelGroup> {
    String name();
    
    ChannelGroupFuture write(Object paramObject);
    
    ChannelGroupFuture write(Object paramObject, ChannelMatcher paramChannelMatcher);
    
    ChannelGroup flush();
    
    ChannelGroup flush(ChannelMatcher paramChannelMatcher);
    
    ChannelGroupFuture writeAndFlush(Object paramObject);
    
    @Deprecated
    ChannelGroupFuture flushAndWrite(Object paramObject);
    
    ChannelGroupFuture writeAndFlush(Object paramObject, ChannelMatcher paramChannelMatcher);
    
    @Deprecated
    ChannelGroupFuture flushAndWrite(Object paramObject, ChannelMatcher paramChannelMatcher);
    
    ChannelGroupFuture disconnect();
    
    ChannelGroupFuture disconnect(ChannelMatcher paramChannelMatcher);
    
    ChannelGroupFuture close();
    
    ChannelGroupFuture close(ChannelMatcher paramChannelMatcher);
    
    @Deprecated
    ChannelGroupFuture deregister();
    
    @Deprecated
    ChannelGroupFuture deregister(ChannelMatcher paramChannelMatcher);
}
