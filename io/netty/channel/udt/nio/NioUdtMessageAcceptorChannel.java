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

package io.netty.channel.udt.nio;

import com.barchart.udt.TypeUDT;
import com.barchart.udt.nio.SocketChannelUDT;
import io.netty.channel.Channel;
import io.netty.channel.ChannelMetadata;
import java.util.List;

public class NioUdtMessageAcceptorChannel extends NioUdtAcceptorChannel {
    private static final ChannelMetadata METADATA = new ChannelMetadata(false);
    
    public NioUdtMessageAcceptorChannel() {
        super(TypeUDT.DATAGRAM);
    }
    
    protected int doReadMessages(List<Object> buf) throws Exception {
        SocketChannelUDT channelUDT = javaChannel().accept();
        if (channelUDT == null)
            return 0; 
        buf.add(new NioUdtMessageConnectorChannel((Channel)this, channelUDT));
        return 1;
    }
    
    public ChannelMetadata metadata() {
        return METADATA;
    }
}
