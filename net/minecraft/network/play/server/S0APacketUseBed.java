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
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.INetHandler;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.INetHandlerPlayClient;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public class S0APacketUseBed implements Packet<INetHandlerPlayClient> {
    private int playerID;
    
    private BlockPos bedPos;
    
    public S0APacketUseBed() {}
    
    public S0APacketUseBed(EntityPlayer player, BlockPos bedPosIn) {
        this.playerID = player.getEntityId();
        this.bedPos = bedPosIn;
    }
    
    public void readPacketData(PacketBuffer buf) throws IOException {
        this.playerID = buf.readVarIntFromBuffer();
        this.bedPos = buf.readBlockPos();
    }
    
    public void writePacketData(PacketBuffer buf) throws IOException {
        buf.writeVarIntToBuffer(this.playerID);
        buf.writeBlockPos(this.bedPos);
    }
    
    public void processPacket(INetHandlerPlayClient handler) {
        handler.handleUseBed(this);
    }
    
    public EntityPlayer getPlayer(World worldIn) {
        return (EntityPlayer)worldIn.getEntityByID(this.playerID);
    }
    
    public BlockPos getBedPosition() {
        return this.bedPos;
    }
}
