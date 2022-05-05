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

package io.netty.handler.codec.spdy;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageCodec;
import io.netty.handler.codec.http.HttpMessage;
import io.netty.util.ReferenceCountUtil;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class SpdyHttpResponseStreamIdHandler extends MessageToMessageCodec<Object, HttpMessage> {
    private static final Integer NO_ID = Integer.valueOf(-1);
    
    private final Queue<Integer> ids = new LinkedList<Integer>();
    
    public boolean acceptInboundMessage(Object msg) throws Exception {
        return (msg instanceof HttpMessage || msg instanceof SpdyRstStreamFrame);
    }
    
    protected void encode(ChannelHandlerContext ctx, HttpMessage msg, List<Object> out) throws Exception {
        Integer id = this.ids.poll();
        if (id != null && id.intValue() != NO_ID.intValue() && !msg.headers().contains("X-SPDY-Stream-ID"))
            SpdyHttpHeaders.setStreamId(msg, id.intValue()); 
        out.add(ReferenceCountUtil.retain(msg));
    }
    
    protected void decode(ChannelHandlerContext ctx, Object msg, List<Object> out) throws Exception {
        if (msg instanceof HttpMessage) {
            boolean contains = ((HttpMessage)msg).headers().contains("X-SPDY-Stream-ID");
            if (!contains) {
                this.ids.add(NO_ID);
            } else {
                this.ids.add(Integer.valueOf(SpdyHttpHeaders.getStreamId((HttpMessage)msg)));
            } 
        } else if (msg instanceof SpdyRstStreamFrame) {
            this.ids.remove(Integer.valueOf(((SpdyRstStreamFrame)msg).streamId()));
        } 
        out.add(ReferenceCountUtil.retain(msg));
    }
}
