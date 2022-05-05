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
import net.minecraft.util.MathHelper;

public class S2CPacketSpawnGlobalEntity implements Packet<INetHandlerPlayClient> {
    private int entityId;
    
    private int x;
    
    private int y;
    
    private int z;
    
    private int type;
    
    public S2CPacketSpawnGlobalEntity() {}
    
    public S2CPacketSpawnGlobalEntity(Entity entityIn) {
        this.entityId = entityIn.getEntityId();
        this.x = MathHelper.floor_double(entityIn.posX * 32.0D);
        this.y = MathHelper.floor_double(entityIn.posY * 32.0D);
        this.z = MathHelper.floor_double(entityIn.posZ * 32.0D);
        if (entityIn instanceof net.minecraft.entity.effect.EntityLightningBolt)
            this.type = 1; 
    }
    
    public void readPacketData(PacketBuffer buf) throws IOException {
        this.entityId = buf.readVarIntFromBuffer();
        this.type = buf.readByte();
        this.x = buf.readInt();
        this.y = buf.readInt();
        this.z = buf.readInt();
    }
    
    public void writePacketData(PacketBuffer buf) throws IOException {
        buf.writeVarIntToBuffer(this.entityId);
        buf.writeByte(this.type);
        buf.writeInt(this.x);
        buf.writeInt(this.y);
        buf.writeInt(this.z);
    }
    
    public void processPacket(INetHandlerPlayClient handler) {
        handler.handleSpawnGlobalEntity(this);
    }
    
    public int func_149052_c() {
        return this.entityId;
    }
    
    public int func_149051_d() {
        return this.x;
    }
    
    public int func_149050_e() {
        return this.y;
    }
    
    public int func_149049_f() {
        return this.z;
    }
    
    public int func_149053_g() {
        return this.type;
    }
}