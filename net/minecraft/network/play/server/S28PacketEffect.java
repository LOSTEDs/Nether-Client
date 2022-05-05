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
import net.minecraft.util.BlockPos;

public class S28PacketEffect implements Packet<INetHandlerPlayClient> {
    private int soundType;
    
    private BlockPos soundPos;
    
    private int soundData;
    
    private boolean serverWide;
    
    public S28PacketEffect() {}
    
    public S28PacketEffect(int soundTypeIn, BlockPos soundPosIn, int soundDataIn, boolean serverWideIn) {
        this.soundType = soundTypeIn;
        this.soundPos = soundPosIn;
        this.soundData = soundDataIn;
        this.serverWide = serverWideIn;
    }
    
    public void readPacketData(PacketBuffer buf) throws IOException {
        this.soundType = buf.readInt();
        this.soundPos = buf.readBlockPos();
        this.soundData = buf.readInt();
        this.serverWide = buf.readBoolean();
    }
    
    public void writePacketData(PacketBuffer buf) throws IOException {
        buf.writeInt(this.soundType);
        buf.writeBlockPos(this.soundPos);
        buf.writeInt(this.soundData);
        buf.writeBoolean(this.serverWide);
    }
    
    public void processPacket(INetHandlerPlayClient handler) {
        handler.handleEffect(this);
    }
    
    public boolean isSoundServerwide() {
        return this.serverWide;
    }
    
    public int getSoundType() {
        return this.soundType;
    }
    
    public int getSoundData() {
        return this.soundData;
    }
    
    public BlockPos getSoundPos() {
        return this.soundPos;
    }
}
