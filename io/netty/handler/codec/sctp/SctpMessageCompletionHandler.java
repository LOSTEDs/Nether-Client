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

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.sctp.SctpMessage;
import io.netty.handler.codec.MessageToMessageDecoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SctpMessageCompletionHandler extends MessageToMessageDecoder<SctpMessage> {
    private final Map<Integer, ByteBuf> fragments = new HashMap<Integer, ByteBuf>();
    
    protected void decode(ChannelHandlerContext ctx, SctpMessage msg, List<Object> out) throws Exception {
        ByteBuf frag, byteBuf = msg.content();
        int protocolIdentifier = msg.protocolIdentifier();
        int streamIdentifier = msg.streamIdentifier();
        boolean isComplete = msg.isComplete();
        if (this.fragments.containsKey(Integer.valueOf(streamIdentifier))) {
            frag = this.fragments.remove(Integer.valueOf(streamIdentifier));
        } else {
            frag = Unpooled.EMPTY_BUFFER;
        } 
        if (isComplete && !frag.isReadable()) {
            out.add(msg);
        } else if (!isComplete && frag.isReadable()) {
            this.fragments.put(Integer.valueOf(streamIdentifier), Unpooled.wrappedBuffer(new ByteBuf[] { frag, byteBuf }));
        } else if (isComplete && frag.isReadable()) {
            this.fragments.remove(Integer.valueOf(streamIdentifier));
            SctpMessage assembledMsg = new SctpMessage(protocolIdentifier, streamIdentifier, Unpooled.wrappedBuffer(new ByteBuf[] { frag, byteBuf }));
            out.add(assembledMsg);
        } else {
            this.fragments.put(Integer.valueOf(streamIdentifier), byteBuf);
        } 
        byteBuf.retain();
    }
}
