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

public class C16PacketClientStatus implements Packet<INetHandlerPlayServer> {
    private EnumState status;
    
    public C16PacketClientStatus() {}
    
    public C16PacketClientStatus(EnumState statusIn) {
        this.status = statusIn;
    }
    
    public void readPacketData(PacketBuffer buf) throws IOException {
        this.status = (EnumState)buf.readEnumValue(EnumState.class);
    }
    
    public void writePacketData(PacketBuffer buf) throws IOException {
        buf.writeEnumValue(this.status);
    }
    
    public void processPacket(INetHandlerPlayServer handler) {
        handler.processClientStatus(this);
    }
    
    public EnumState getStatus() {
        return this.status;
    }
    
    public enum EnumState {
        PERFORM_RESPAWN, REQUEST_STATS, OPEN_INVENTORY_ACHIEVEMENT;
    }
}
