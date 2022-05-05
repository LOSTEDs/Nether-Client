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
import net.minecraft.util.BlockPos;
import org.apache.commons.lang3.StringUtils;

public class C14PacketTabComplete implements Packet<INetHandlerPlayServer> {
    private String message;
    
    private BlockPos targetBlock;
    
    public C14PacketTabComplete() {}
    
    public C14PacketTabComplete(String msg) {
        this(msg, null);
    }
    
    public C14PacketTabComplete(String msg, BlockPos target) {
        this.message = msg;
        this.targetBlock = target;
    }
    
    public void readPacketData(PacketBuffer buf) throws IOException {
        this.message = buf.readStringFromBuffer(32767);
        boolean flag = buf.readBoolean();
        if (flag)
            this.targetBlock = buf.readBlockPos(); 
    }
    
    public void writePacketData(PacketBuffer buf) throws IOException {
        buf.writeString(StringUtils.substring(this.message, 0, 32767));
        boolean flag = (this.targetBlock != null);
        buf.writeBoolean(flag);
        if (flag)
            buf.writeBlockPos(this.targetBlock); 
    }
    
    public void processPacket(INetHandlerPlayServer handler) {
        handler.processTabComplete(this);
    }
    
    public String getMessage() {
        return this.message;
    }
    
    public BlockPos getTargetBlock() {
        return this.targetBlock;
    }
}
