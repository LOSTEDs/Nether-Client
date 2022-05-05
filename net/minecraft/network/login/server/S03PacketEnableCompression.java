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

package net.minecraft.network.login.server;

import java.io.IOException;
import net.minecraft.network.INetHandler;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.login.INetHandlerLoginClient;

public class S03PacketEnableCompression implements Packet<INetHandlerLoginClient> {
    private int compressionTreshold;
    
    public S03PacketEnableCompression() {}
    
    public S03PacketEnableCompression(int compressionTresholdIn) {
        this.compressionTreshold = compressionTresholdIn;
    }
    
    public void readPacketData(PacketBuffer buf) throws IOException {
        this.compressionTreshold = buf.readVarIntFromBuffer();
    }
    
    public void writePacketData(PacketBuffer buf) throws IOException {
        buf.writeVarIntToBuffer(this.compressionTreshold);
    }
    
    public void processPacket(INetHandlerLoginClient handler) {
        handler.handleEnableCompression(this);
    }
    
    public int getCompressionTreshold() {
        return this.compressionTreshold;
    }
}
