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
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.stream.ChunkedInput;

public class HttpChunkedInput implements ChunkedInput<HttpContent> {
    private final ChunkedInput<ByteBuf> input;
    
    private final LastHttpContent lastHttpContent;
    
    private boolean sentLastChunk;
    
    public HttpChunkedInput(ChunkedInput<ByteBuf> input) {
        this.input = input;
        this.lastHttpContent = LastHttpContent.EMPTY_LAST_CONTENT;
    }
    
    public HttpChunkedInput(ChunkedInput<ByteBuf> input, LastHttpContent lastHttpContent) {
        this.input = input;
        this.lastHttpContent = lastHttpContent;
    }
    
    public boolean isEndOfInput() throws Exception {
        if (this.input.isEndOfInput())
            return this.sentLastChunk; 
        return false;
    }
    
    public void close() throws Exception {
        this.input.close();
    }
    
    public HttpContent readChunk(ChannelHandlerContext ctx) throws Exception {
        if (this.input.isEndOfInput()) {
            if (this.sentLastChunk)
                return null; 
            this.sentLastChunk = true;
            return this.lastHttpContent;
        } 
        ByteBuf buf = (ByteBuf)this.input.readChunk(ctx);
        return new DefaultHttpContent(buf);
    }
}
