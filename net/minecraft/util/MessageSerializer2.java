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

package net.minecraft.util;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import net.minecraft.network.PacketBuffer;

public class MessageSerializer2 extends MessageToByteEncoder<ByteBuf> {
    protected void encode(ChannelHandlerContext p_encode_1_, ByteBuf p_encode_2_, ByteBuf p_encode_3_) throws Exception {
        int i = p_encode_2_.readableBytes();
        int j = PacketBuffer.getVarIntSize(i);
        if (j > 3)
            throw new IllegalArgumentException("unable to fit " + i + " into " + '\003'); 
        PacketBuffer packetbuffer = new PacketBuffer(p_encode_3_);
        packetbuffer.ensureWritable(j + i);
        packetbuffer.writeVarIntToBuffer(i);
        packetbuffer.writeBytes(p_encode_2_, p_encode_2_.readerIndex(), i);
    }
}
