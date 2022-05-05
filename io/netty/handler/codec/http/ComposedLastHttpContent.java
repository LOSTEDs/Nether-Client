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
import io.netty.buffer.ByteBufHolder;
import io.netty.buffer.Unpooled;
import io.netty.handler.codec.DecoderResult;
import io.netty.util.ReferenceCounted;

final class ComposedLastHttpContent implements LastHttpContent {
    private final HttpHeaders trailingHeaders;
    
    private DecoderResult result;
    
    ComposedLastHttpContent(HttpHeaders trailingHeaders) {
        this.trailingHeaders = trailingHeaders;
    }
    
    public HttpHeaders trailingHeaders() {
        return this.trailingHeaders;
    }
    
    public LastHttpContent copy() {
        LastHttpContent content = new DefaultLastHttpContent(Unpooled.EMPTY_BUFFER);
        content.trailingHeaders().set(trailingHeaders());
        return content;
    }
    
    public LastHttpContent retain(int increment) {
        return this;
    }
    
    public LastHttpContent retain() {
        return this;
    }
    
    public HttpContent duplicate() {
        return copy();
    }
    
    public ByteBuf content() {
        return Unpooled.EMPTY_BUFFER;
    }
    
    public DecoderResult getDecoderResult() {
        return this.result;
    }
    
    public void setDecoderResult(DecoderResult result) {
        this.result = result;
    }
    
    public int refCnt() {
        return 1;
    }
    
    public boolean release() {
        return false;
    }
    
    public boolean release(int decrement) {
        return false;
    }
}
