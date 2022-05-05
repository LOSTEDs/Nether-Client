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
import io.netty.util.ReferenceCounted;

public class DefaultFullHttpRequest extends DefaultHttpRequest implements FullHttpRequest {
    private final ByteBuf content;
    
    private final HttpHeaders trailingHeader;
    
    private final boolean validateHeaders;
    
    public DefaultFullHttpRequest(HttpVersion httpVersion, HttpMethod method, String uri) {
        this(httpVersion, method, uri, Unpooled.buffer(0));
    }
    
    public DefaultFullHttpRequest(HttpVersion httpVersion, HttpMethod method, String uri, ByteBuf content) {
        this(httpVersion, method, uri, content, true);
    }
    
    public DefaultFullHttpRequest(HttpVersion httpVersion, HttpMethod method, String uri, ByteBuf content, boolean validateHeaders) {
        super(httpVersion, method, uri, validateHeaders);
        if (content == null)
            throw new NullPointerException("content"); 
        this.content = content;
        this.trailingHeader = new DefaultHttpHeaders(validateHeaders);
        this.validateHeaders = validateHeaders;
    }
    
    public HttpHeaders trailingHeaders() {
        return this.trailingHeader;
    }
    
    public ByteBuf content() {
        return this.content;
    }
    
    public int refCnt() {
        return this.content.refCnt();
    }
    
    public FullHttpRequest retain() {
        this.content.retain();
        return this;
    }
    
    public FullHttpRequest retain(int increment) {
        this.content.retain(increment);
        return this;
    }
    
    public boolean release() {
        return this.content.release();
    }
    
    public boolean release(int decrement) {
        return this.content.release(decrement);
    }
    
    public FullHttpRequest setProtocolVersion(HttpVersion version) {
        super.setProtocolVersion(version);
        return this;
    }
    
    public FullHttpRequest setMethod(HttpMethod method) {
        super.setMethod(method);
        return this;
    }
    
    public FullHttpRequest setUri(String uri) {
        super.setUri(uri);
        return this;
    }
    
    public FullHttpRequest copy() {
        DefaultFullHttpRequest copy = new DefaultFullHttpRequest(getProtocolVersion(), getMethod(), getUri(), content().copy(), this.validateHeaders);
        copy.headers().set(headers());
        copy.trailingHeaders().set(trailingHeaders());
        return copy;
    }
    
    public FullHttpRequest duplicate() {
        DefaultFullHttpRequest duplicate = new DefaultFullHttpRequest(getProtocolVersion(), getMethod(), getUri(), content().duplicate(), this.validateHeaders);
        duplicate.headers().set(headers());
        duplicate.trailingHeaders().set(trailingHeaders());
        return duplicate;
    }
}
