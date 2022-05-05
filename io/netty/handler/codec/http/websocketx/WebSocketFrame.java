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

package io.netty.handler.codec.http.websocketx;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufHolder;
import io.netty.buffer.DefaultByteBufHolder;
import io.netty.util.ReferenceCounted;
import io.netty.util.internal.StringUtil;

public abstract class WebSocketFrame extends DefaultByteBufHolder {
    private final boolean finalFragment;
    
    private final int rsv;
    
    protected WebSocketFrame(ByteBuf binaryData) {
        this(true, 0, binaryData);
    }
    
    protected WebSocketFrame(boolean finalFragment, int rsv, ByteBuf binaryData) {
        super(binaryData);
        this.finalFragment = finalFragment;
        this.rsv = rsv;
    }
    
    public boolean isFinalFragment() {
        return this.finalFragment;
    }
    
    public int rsv() {
        return this.rsv;
    }
    
    public String toString() {
        return StringUtil.simpleClassName(this) + "(data: " + content().toString() + ')';
    }
    
    public WebSocketFrame retain() {
        super.retain();
        return this;
    }
    
    public WebSocketFrame retain(int increment) {
        super.retain(increment);
        return this;
    }
    
    public abstract WebSocketFrame copy();
    
    public abstract WebSocketFrame duplicate();
}
