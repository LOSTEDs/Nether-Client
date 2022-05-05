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

public class S0BPacketAnimation implements Packet<INetHandlerPlayClient> {
    private int entityId;
    
    private int type;
    
    public S0BPacketAnimation() {}
    
    public S0BPacketAnimation(Entity ent, int animationType) {
        this.entityId = ent.getEntityId();
        this.type = animationType;
    }
    
    public void readPacketData(PacketBuffer buf) throws IOException {
        this.entityId = buf.readVarIntFromBuffer();
        this.type = buf.readUnsignedByte();
    }
    
    public void writePacketData(PacketBuffer buf) throws IOException {
        buf.writeVarIntToBuffer(this.entityId);
        buf.writeByte(this.type);
    }
    
    public void processPacket(INetHandlerPlayClient handler) {
        handler.handleAnimation(this);
    }
    
    public int getEntityID() {
        return this.entityId;
    }
    
    public int getAnimationType() {
        return this.type;
    }
}
