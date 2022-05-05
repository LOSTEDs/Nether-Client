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

package io.netty.handler.codec.http;

import io.netty.buffer.ByteBuf;

public class HttpResponseEncoder extends HttpObjectEncoder<HttpResponse> {
    private static final byte[] CRLF = new byte[] { 13, 10 };
    
    public boolean acceptOutboundMessage(Object msg) throws Exception {
        return (super.acceptOutboundMessage(msg) && !(msg instanceof HttpRequest));
    }
    
    protected void encodeInitialLine(ByteBuf buf, HttpResponse response) throws Exception {
        response.getProtocolVersion().encode(buf);
        buf.writeByte(32);
        response.getStatus().encode(buf);
        buf.writeBytes(CRLF);
    }
}
