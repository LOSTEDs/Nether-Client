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
import net.minecraft.entity.Entity;
import net.minecraft.network.INetHandler;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.INetHandlerPlayClient;

public class S1BPacketEntityAttach implements Packet<INetHandlerPlayClient> {
    private int leash;
    
    private int entityId;
    
    private int vehicleEntityId;
    
    public S1BPacketEntityAttach() {}
    
    public S1BPacketEntityAttach(int leashIn, Entity entityIn, Entity vehicle) {
        this.leash = leashIn;
        this.entityId = entityIn.getEntityId();
        this.vehicleEntityId = (vehicle != null) ? vehicle.getEntityId() : -1;
    }
    
    public void readPacketData(PacketBuffer buf) throws IOException {
        this.entityId = buf.readInt();
        this.vehicleEntityId = buf.readInt();
        this.leash = buf.readUnsignedByte();
    }
    
    public void writePacketData(PacketBuffer buf) throws IOException {
        buf.writeInt(this.entityId);
        buf.writeInt(this.vehicleEntityId);
        buf.writeByte(this.leash);
    }
    
    public void processPacket(INetHandlerPlayClient handler) {
        handler.handleEntityAttach(this);
    }
    
    public int getLeash() {
        return this.leash;
    }
    
    public int getEntityId() {
        return this.entityId;
    }
    
    public int getVehicleEntityId() {
        return this.vehicleEntityId;
    }
}
