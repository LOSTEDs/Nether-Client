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
import net.minecraft.item.ItemStack;
import net.minecraft.network.INetHandler;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.INetHandlerPlayServer;

public class C10PacketCreativeInventoryAction implements Packet<INetHandlerPlayServer> {
    private int slotId;
    
    private ItemStack stack;
    
    public C10PacketCreativeInventoryAction() {}
    
    public C10PacketCreativeInventoryAction(int slotIdIn, ItemStack stackIn) {
        this.slotId = slotIdIn;
        this.stack = (stackIn != null) ? stackIn.copy() : null;
    }
    
    public void processPacket(INetHandlerPlayServer handler) {
        handler.processCreativeInventoryAction(this);
    }
    
    public void readPacketData(PacketBuffer buf) throws IOException {
        this.slotId = buf.readShort();
        this.stack = buf.readItemStackFromBuffer();
    }
    
    public void writePacketData(PacketBuffer buf) throws IOException {
        buf.writeShort(this.slotId);
        buf.writeItemStackToBuffer(this.stack);
    }
    
    public int getSlotId() {
        return this.slotId;
    }
    
    public ItemStack getStack() {
        return this.stack;
    }
}
