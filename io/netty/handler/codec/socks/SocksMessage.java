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

package io.netty.handler.codec.socks;

import io.netty.buffer.ByteBuf;

public abstract class SocksMessage {
    private final SocksMessageType type;
    
    private final SocksProtocolVersion protocolVersion = SocksProtocolVersion.SOCKS5;
    
    protected SocksMessage(SocksMessageType type) {
        if (type == null)
            throw new NullPointerException("type"); 
        this.type = type;
    }
    
    public SocksMessageType type() {
        return this.type;
    }
    
    public SocksProtocolVersion protocolVersion() {
        return this.protocolVersion;
    }
    
    @Deprecated
    public abstract void encodeAsByteBuf(ByteBuf paramByteBuf);
}
