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

import io.netty.channel.sctp.SctpMessage;
import io.netty.handler.codec.CodecException;
import io.netty.handler.codec.MessageToMessageDecoder;

public abstract class SctpMessageToMessageDecoder extends MessageToMessageDecoder<SctpMessage> {
    public boolean acceptInboundMessage(Object msg) throws Exception {
        if (msg instanceof SctpMessage) {
            SctpMessage sctpMsg = (SctpMessage)msg;
            if (sctpMsg.isComplete())
                return true; 
            throw new CodecException(String.format("Received SctpMessage is not complete, please add %s in the pipeline before this handler", new Object[] { SctpMessageCompletionHandler.class.getSimpleName() }));
        } 
        return false;
    }
}
