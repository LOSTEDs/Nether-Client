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
import io.netty.buffer.Unpooled;
import io.netty.util.ReferenceCounted;

public class PongWebSocketFrame extends WebSocketFrame {
    public PongWebSocketFrame() {
        super(Unpooled.buffer(0));
    }
    
    public PongWebSocketFrame(ByteBuf binaryData) {
        super(binaryData);
    }
    
    public PongWebSocketFrame(boolean finalFragment, int rsv, ByteBuf binaryData) {
        super(finalFragment, rsv, binaryData);
    }
    
    public PongWebSocketFrame copy() {
        return new PongWebSocketFrame(isFinalFragment(), rsv(), content().copy());
    }
    
    public PongWebSocketFrame duplicate() {
        return new PongWebSocketFrame(isFinalFragment(), rsv(), content().duplicate());
    }
    
    public PongWebSocketFrame retain() {
        super.retain();
        return this;
    }
    
    public PongWebSocketFrame retain(int increment) {
        super.retain(increment);
        return this;
    }
}
