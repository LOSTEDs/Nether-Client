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
import io.netty.util.ReferenceCounted;
import io.netty.util.internal.StringUtil;

public class DefaultHttpContent extends DefaultHttpObject implements HttpContent {
    private final ByteBuf content;
    
    public DefaultHttpContent(ByteBuf content) {
        if (content == null)
            throw new NullPointerException("content"); 
        this.content = content;
    }
    
    public ByteBuf content() {
        return this.content;
    }
    
    public HttpContent copy() {
        return new DefaultHttpContent(this.content.copy());
    }
    
    public HttpContent duplicate() {
        return new DefaultHttpContent(this.content.duplicate());
    }
    
    public int refCnt() {
        return this.content.refCnt();
    }
    
    public HttpContent retain() {
        this.content.retain();
        return this;
    }
    
    public HttpContent retain(int increment) {
        this.content.retain(increment);
        return this;
    }
    
    public boolean release() {
        return this.content.release();
    }
    
    public boolean release(int decrement) {
        return this.content.release(decrement);
    }
    
    public String toString() {
        return StringUtil.simpleClassName(this) + "(data: " + content() + ", decoderResult: " + getDecoderResult() + ')';
    }
}
