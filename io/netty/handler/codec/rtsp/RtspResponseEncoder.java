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

package io.netty.handler.codec.rtsp;

import io.netty.buffer.ByteBuf;
import io.netty.handler.codec.http.HttpHeaders;
import io.netty.handler.codec.http.HttpMessage;
import io.netty.handler.codec.http.HttpResponse;
import io.netty.util.CharsetUtil;

public class RtspResponseEncoder extends RtspObjectEncoder<HttpResponse> {
    private static final byte[] CRLF = new byte[] { 13, 10 };
    
    public boolean acceptOutboundMessage(Object msg) throws Exception {
        return msg instanceof io.netty.handler.codec.http.FullHttpResponse;
    }
    
    protected void encodeInitialLine(ByteBuf buf, HttpResponse response) throws Exception {
        HttpHeaders.encodeAscii(response.getProtocolVersion().toString(), buf);
        buf.writeByte(32);
        buf.writeBytes(String.valueOf(response.getStatus().code()).getBytes(CharsetUtil.US_ASCII));
        buf.writeByte(32);
        encodeAscii(String.valueOf(response.getStatus().reasonPhrase()), buf);
        buf.writeBytes(CRLF);
    }
}
