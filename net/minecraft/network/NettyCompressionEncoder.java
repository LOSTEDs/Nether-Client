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

package net.minecraft.network;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import java.util.zip.Deflater;

public class NettyCompressionEncoder extends MessageToByteEncoder<ByteBuf> {
    private final byte[] buffer = new byte[8192];
    
    private final Deflater deflater;
    
    private int treshold;
    
    public NettyCompressionEncoder(int treshold) {
        this.treshold = treshold;
        this.deflater = new Deflater();
    }
    
    protected void encode(ChannelHandlerContext p_encode_1_, ByteBuf p_encode_2_, ByteBuf p_encode_3_) throws Exception {
        int i = p_encode_2_.readableBytes();
        PacketBuffer packetbuffer = new PacketBuffer(p_encode_3_);
        if (i < this.treshold) {
            packetbuffer.writeVarIntToBuffer(0);
            packetbuffer.writeBytes(p_encode_2_);
        } else {
            byte[] abyte = new byte[i];
            p_encode_2_.readBytes(abyte);
            packetbuffer.writeVarIntToBuffer(abyte.length);
            this.deflater.setInput(abyte, 0, i);
            this.deflater.finish();
            while (!this.deflater.finished()) {
                int j = this.deflater.deflate(this.buffer);
                packetbuffer.writeBytes(this.buffer, 0, j);
            } 
            this.deflater.reset();
        } 
    }
    
    public void setCompressionTreshold(int treshold) {
        this.treshold = treshold;
    }
}
