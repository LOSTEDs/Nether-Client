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

package net.minecraft.network.play.client;

import java.io.IOException;
import net.minecraft.network.INetHandler;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.INetHandlerPlayServer;

public class C00PacketKeepAlive implements Packet<INetHandlerPlayServer> {
    private int key;
    
    public C00PacketKeepAlive() {}
    
    public C00PacketKeepAlive(int key) {
        this.key = key;
    }
    
    public void processPacket(INetHandlerPlayServer handler) {
        handler.processKeepAlive(this);
    }
    
    public void readPacketData(PacketBuffer buf) throws IOException {
        this.key = buf.readVarIntFromBuffer();
    }
    
    public void writePacketData(PacketBuffer buf) throws IOException {
        buf.writeVarIntToBuffer(this.key);
    }
    
    public int getKey() {
        return this.key;
    }
}
