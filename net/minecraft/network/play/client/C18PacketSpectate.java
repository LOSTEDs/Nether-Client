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
import java.util.UUID;
import net.minecraft.entity.Entity;
import net.minecraft.network.INetHandler;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.INetHandlerPlayServer;
import net.minecraft.world.WorldServer;

public class C18PacketSpectate implements Packet<INetHandlerPlayServer> {
    private UUID id;
    
    public C18PacketSpectate() {}
    
    public C18PacketSpectate(UUID id) {
        this.id = id;
    }
    
    public void readPacketData(PacketBuffer buf) throws IOException {
        this.id = buf.readUuid();
    }
    
    public void writePacketData(PacketBuffer buf) throws IOException {
        buf.writeUuid(this.id);
    }
    
    public void processPacket(INetHandlerPlayServer handler) {
        handler.handleSpectate(this);
    }
    
    public Entity getEntity(WorldServer worldIn) {
        return worldIn.getEntityFromUuid(this.id);
    }
}
