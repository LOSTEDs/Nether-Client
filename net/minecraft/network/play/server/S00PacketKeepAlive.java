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

import java.io.IOException;
import net.minecraft.network.INetHandler;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.INetHandlerPlayClient;

public class S00PacketKeepAlive implements Packet<INetHandlerPlayClient> {
    private int id;
    
    public S00PacketKeepAlive() {}
    
    public S00PacketKeepAlive(int idIn) {
        this.id = idIn;
    }
    
    public void processPacket(INetHandlerPlayClient handler) {
        handler.handleKeepAlive(this);
    }
    
    public void readPacketData(PacketBuffer buf) throws IOException {
        this.id = buf.readVarIntFromBuffer();
    }
    
    public void writePacketData(PacketBuffer buf) throws IOException {
        buf.writeVarIntToBuffer(this.id);
    }
    
    public int func_149134_c() {
        return this.id;
    }
}
