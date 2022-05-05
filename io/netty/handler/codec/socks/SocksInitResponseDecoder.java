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
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ReplayingDecoder;
import java.util.List;

public class SocksInitResponseDecoder extends ReplayingDecoder<SocksInitResponseDecoder.State> {
    private static final String name = "SOCKS_INIT_RESPONSE_DECODER";
    
    private SocksProtocolVersion version;
    
    private SocksAuthScheme authScheme;
    
    @Deprecated
    public static String getName() {
        return "SOCKS_INIT_RESPONSE_DECODER";
    }
    
    private SocksResponse msg = SocksCommonUtils.UNKNOWN_SOCKS_RESPONSE;
    
    public SocksInitResponseDecoder() {
        super(State.CHECK_PROTOCOL_VERSION);
    }
    
    protected void decode(ChannelHandlerContext ctx, ByteBuf byteBuf, List<Object> out) throws Exception {
        switch ((State)state()) {
            case CHECK_PROTOCOL_VERSION:
                this.version = SocksProtocolVersion.valueOf(byteBuf.readByte());
                if (this.version != SocksProtocolVersion.SOCKS5)
                    break; 
                checkpoint(State.READ_PREFFERED_AUTH_TYPE);
            case READ_PREFFERED_AUTH_TYPE:
                this.authScheme = SocksAuthScheme.valueOf(byteBuf.readByte());
                this.msg = new SocksInitResponse(this.authScheme);
                break;
        } 
        ctx.pipeline().remove((ChannelHandler)this);
        out.add(this.msg);
    }
    
    enum State {
        CHECK_PROTOCOL_VERSION, READ_PREFFERED_AUTH_TYPE;
    }
}
