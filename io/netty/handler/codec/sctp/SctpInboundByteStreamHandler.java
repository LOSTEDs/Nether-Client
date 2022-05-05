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

package io.netty.handler.codec.sctp;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.sctp.SctpMessage;
import io.netty.handler.codec.CodecException;
import io.netty.handler.codec.MessageToMessageDecoder;
import java.util.List;

public class SctpInboundByteStreamHandler extends MessageToMessageDecoder<SctpMessage> {
    private final int protocolIdentifier;
    
    private final int streamIdentifier;
    
    public SctpInboundByteStreamHandler(int protocolIdentifier, int streamIdentifier) {
        this.protocolIdentifier = protocolIdentifier;
        this.streamIdentifier = streamIdentifier;
    }
    
    public final boolean acceptInboundMessage(Object msg) throws Exception {
        if (super.acceptInboundMessage(msg))
            return acceptInboundMessage((SctpMessage)msg); 
        return false;
    }
    
    protected boolean acceptInboundMessage(SctpMessage msg) {
        return (msg.protocolIdentifier() == this.protocolIdentifier && msg.streamIdentifier() == this.streamIdentifier);
    }
    
    protected void decode(ChannelHandlerContext ctx, SctpMessage msg, List<Object> out) throws Exception {
        if (!msg.isComplete())
            throw new CodecException(String.format("Received SctpMessage is not complete, please add %s in the pipeline before this handler", new Object[] { SctpMessageCompletionHandler.class.getSimpleName() })); 
        out.add(msg.content().retain());
    }
}
