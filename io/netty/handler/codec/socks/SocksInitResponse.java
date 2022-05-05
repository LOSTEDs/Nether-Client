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

public final class SocksInitResponse extends SocksResponse {
    private final SocksAuthScheme authScheme;
    
    public SocksInitResponse(SocksAuthScheme authScheme) {
        super(SocksResponseType.INIT);
        if (authScheme == null)
            throw new NullPointerException("authScheme"); 
        this.authScheme = authScheme;
    }
    
    public SocksAuthScheme authScheme() {
        return this.authScheme;
    }
    
    public void encodeAsByteBuf(ByteBuf byteBuf) {
        byteBuf.writeByte(protocolVersion().byteValue());
        byteBuf.writeByte(this.authScheme.byteValue());
    }
}
