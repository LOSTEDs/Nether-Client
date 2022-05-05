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
import io.netty.channel.ChannelException;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.Map;

public class ChannelGroupException extends ChannelException implements Iterable<Map.Entry<Channel, Throwable>> {
    private static final long serialVersionUID = -4093064295562629453L;
    
    private final Collection<Map.Entry<Channel, Throwable>> failed;
    
    public ChannelGroupException(Collection<Map.Entry<Channel, Throwable>> causes) {
        if (causes == null)
            throw new NullPointerException("causes"); 
        if (causes.isEmpty())
            throw new IllegalArgumentException("causes must be non empty"); 
        this.failed = Collections.unmodifiableCollection(causes);
    }
    
    public Iterator<Map.Entry<Channel, Throwable>> iterator() {
        return this.failed.iterator();
    }
}
