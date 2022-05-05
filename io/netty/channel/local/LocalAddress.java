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
import java.net.SocketAddress;

public final class LocalAddress extends SocketAddress implements Comparable<LocalAddress> {
    private static final long serialVersionUID = 4644331421130916435L;
    
    public static final LocalAddress ANY = new LocalAddress("ANY");
    
    private final String id;
    
    private final String strVal;
    
    LocalAddress(Channel channel) {
        StringBuilder buf = new StringBuilder(16);
        buf.append("local:E");
        buf.append(Long.toHexString(channel.hashCode() & 0xFFFFFFFFL | 0x100000000L));
        buf.setCharAt(7, ':');
        this.id = buf.substring(6);
        this.strVal = buf.toString();
    }
    
    public LocalAddress(String id) {
        if (id == null)
            throw new NullPointerException("id"); 
        id = id.trim().toLowerCase();
        if (id.isEmpty())
            throw new IllegalArgumentException("empty id"); 
        this.id = id;
        this.strVal = "local:" + id;
    }
    
    public String id() {
        return this.id;
    }
    
    public int hashCode() {
        return this.id.hashCode();
    }
    
    public boolean equals(Object o) {
        if (!(o instanceof LocalAddress))
            return false; 
        return this.id.equals(((LocalAddress)o).id);
    }
    
    public int compareTo(LocalAddress o) {
        return this.id.compareTo(o.id);
    }
    
    public String toString() {
        return this.strVal;
    }
}
