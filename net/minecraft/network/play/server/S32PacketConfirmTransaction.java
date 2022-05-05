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

public class S32PacketConfirmTransaction implements Packet<INetHandlerPlayClient> {
    private int windowId;
    
    private short actionNumber;
    
    private boolean field_148893_c;
    
    public S32PacketConfirmTransaction() {}
    
    public S32PacketConfirmTransaction(int windowIdIn, short actionNumberIn, boolean p_i45182_3_) {
        this.windowId = windowIdIn;
        this.actionNumber = actionNumberIn;
        this.field_148893_c = p_i45182_3_;
    }
    
    public void processPacket(INetHandlerPlayClient handler) {
        handler.handleConfirmTransaction(this);
    }
    
    public void readPacketData(PacketBuffer buf) throws IOException {
        this.windowId = buf.readUnsignedByte();
        this.actionNumber = buf.readShort();
        this.field_148893_c = buf.readBoolean();
    }
    
    public void writePacketData(PacketBuffer buf) throws IOException {
        buf.writeByte(this.windowId);
        buf.writeShort(this.actionNumber);
        buf.writeBoolean(this.field_148893_c);
    }
    
    public int getWindowId() {
        return this.windowId;
    }
    
    public short getActionNumber() {
        return this.actionNumber;
    }
    
    public boolean func_148888_e() {
        return this.field_148893_c;
    }
}
