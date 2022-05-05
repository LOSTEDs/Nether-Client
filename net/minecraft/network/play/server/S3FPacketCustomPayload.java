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

package net.minecraft.network.play.server;

import io.netty.buffer.ByteBuf;
import java.io.IOException;
import net.minecraft.network.INetHandler;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.INetHandlerPlayClient;

public class S3FPacketCustomPayload implements Packet<INetHandlerPlayClient> {
    private String channel;
    
    private PacketBuffer data;
    
    public S3FPacketCustomPayload() {}
    
    public S3FPacketCustomPayload(String channelName, PacketBuffer dataIn) {
        this.channel = channelName;
        this.data = dataIn;
        if (dataIn.writerIndex() > 1048576)
            throw new IllegalArgumentException("Payload may not be larger than 1048576 bytes"); 
    }
    
    public void readPacketData(PacketBuffer buf) throws IOException {
        this.channel = buf.readStringFromBuffer(20);
        int i = buf.readableBytes();
        if (i >= 0 && i <= 1048576) {
            this.data = new PacketBuffer(buf.readBytes(i));
        } else {
            throw new IOException("Payload may not be larger than 1048576 bytes");
        } 
    }
    
    public void writePacketData(PacketBuffer buf) throws IOException {
        buf.writeString(this.channel);
        buf.writeBytes((ByteBuf)this.data);
    }
    
    public void processPacket(INetHandlerPlayClient handler) {
        handler.handleCustomPayload(this);
    }
    
    public String getChannelName() {
        return this.channel;
    }
    
    public PacketBuffer getBufferData() {
        return this.data;
    }
}
