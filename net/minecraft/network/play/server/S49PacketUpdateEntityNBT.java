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
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.INetHandler;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.INetHandlerPlayClient;
import net.minecraft.world.World;

public class S49PacketUpdateEntityNBT implements Packet<INetHandlerPlayClient> {
    private int entityId;
    
    private NBTTagCompound tagCompound;
    
    public S49PacketUpdateEntityNBT() {}
    
    public S49PacketUpdateEntityNBT(int entityIdIn, NBTTagCompound tagCompoundIn) {
        this.entityId = entityIdIn;
        this.tagCompound = tagCompoundIn;
    }
    
    public void readPacketData(PacketBuffer buf) throws IOException {
        this.entityId = buf.readVarIntFromBuffer();
        this.tagCompound = buf.readNBTTagCompoundFromBuffer();
    }
    
    public void writePacketData(PacketBuffer buf) throws IOException {
        buf.writeVarIntToBuffer(this.entityId);
        buf.writeNBTTagCompoundToBuffer(this.tagCompound);
    }
    
    public void processPacket(INetHandlerPlayClient handler) {
        handler.handleEntityNBT(this);
    }
    
    public NBTTagCompound getTagCompound() {
        return this.tagCompound;
    }
    
    public Entity getEntity(World worldIn) {
        return worldIn.getEntityByID(this.entityId);
    }
}
