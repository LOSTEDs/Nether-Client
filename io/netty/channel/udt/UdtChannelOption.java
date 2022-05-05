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

package io.netty.channel.udt;

import io.netty.channel.ChannelOption;

public final class UdtChannelOption<T> extends ChannelOption<T> {
    public static final UdtChannelOption<Integer> PROTOCOL_RECEIVE_BUFFER_SIZE = new UdtChannelOption("PROTOCOL_RECEIVE_BUFFER_SIZE");
    
    public static final UdtChannelOption<Integer> PROTOCOL_SEND_BUFFER_SIZE = new UdtChannelOption("PROTOCOL_SEND_BUFFER_SIZE");
    
    public static final UdtChannelOption<Integer> SYSTEM_RECEIVE_BUFFER_SIZE = new UdtChannelOption("SYSTEM_RECEIVE_BUFFER_SIZE");
    
    public static final UdtChannelOption<Integer> SYSTEM_SEND_BUFFER_SIZE = new UdtChannelOption("SYSTEM_SEND_BUFFER_SIZE");
    
    private UdtChannelOption(String name) {
        super(name);
    }
}
