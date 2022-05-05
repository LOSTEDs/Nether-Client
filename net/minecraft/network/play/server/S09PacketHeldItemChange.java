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

public class S09PacketHeldItemChange implements Packet<INetHandlerPlayClient> {
    private int heldItemHotbarIndex;
    
    public S09PacketHeldItemChange() {}
    
    public S09PacketHeldItemChange(int hotbarIndexIn) {
        this.heldItemHotbarIndex = hotbarIndexIn;
    }
    
    public void readPacketData(PacketBuffer buf) throws IOException {
        this.heldItemHotbarIndex = buf.readByte();
    }
    
    public void writePacketData(PacketBuffer buf) throws IOException {
        buf.writeByte(this.heldItemHotbarIndex);
    }
    
    public void processPacket(INetHandlerPlayClient handler) {
        handler.handleHeldItemChange(this);
    }
    
    public int getHeldItemHotbarIndex() {
        return this.heldItemHotbarIndex;
    }
}
