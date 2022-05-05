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
import net.minecraft.world.World;

public class S19PacketEntityStatus implements Packet<INetHandlerPlayClient> {
    private int entityId;
    
    private byte logicOpcode;
    
    public S19PacketEntityStatus() {}
    
    public S19PacketEntityStatus(Entity entityIn, byte opCodeIn) {
        this.entityId = entityIn.getEntityId();
        this.logicOpcode = opCodeIn;
    }
    
    public void readPacketData(PacketBuffer buf) throws IOException {
        this.entityId = buf.readInt();
        this.logicOpcode = buf.readByte();
    }
    
    public void writePacketData(PacketBuffer buf) throws IOException {
        buf.writeInt(this.entityId);
        buf.writeByte(this.logicOpcode);
    }
    
    public void processPacket(INetHandlerPlayClient handler) {
        handler.handleEntityStatus(this);
    }
    
    public Entity getEntity(World worldIn) {
        return worldIn.getEntityByID(this.entityId);
    }
    
    public byte getOpCode() {
        return this.logicOpcode;
    }
}
