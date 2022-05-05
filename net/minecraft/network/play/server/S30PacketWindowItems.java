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
import java.util.List;
import net.minecraft.item.ItemStack;
import net.minecraft.network.INetHandler;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.INetHandlerPlayClient;

public class S30PacketWindowItems implements Packet<INetHandlerPlayClient> {
    private int windowId;
    
    private ItemStack[] itemStacks;
    
    public S30PacketWindowItems() {}
    
    public S30PacketWindowItems(int windowIdIn, List<ItemStack> p_i45186_2_) {
        this.windowId = windowIdIn;
        this.itemStacks = new ItemStack[p_i45186_2_.size()];
        for (int i = 0; i < this.itemStacks.length; i++) {
            ItemStack itemstack = p_i45186_2_.get(i);
            this.itemStacks[i] = (itemstack == null) ? null : itemstack.copy();
        } 
    }
    
    public void readPacketData(PacketBuffer buf) throws IOException {
        this.windowId = buf.readUnsignedByte();
        int i = buf.readShort();
        this.itemStacks = new ItemStack[i];
        for (int j = 0; j < i; j++)
            this.itemStacks[j] = buf.readItemStackFromBuffer(); 
    }
    
    public void writePacketData(PacketBuffer buf) throws IOException {
        buf.writeByte(this.windowId);
        buf.writeShort(this.itemStacks.length);
        byte b;
        int i;
        ItemStack[] arrayOfItemStack;
        for (i = (arrayOfItemStack = this.itemStacks).length, b = 0; b < i; ) {
            ItemStack itemstack = arrayOfItemStack[b];
            buf.writeItemStackToBuffer(itemstack);
            b++;
        } 
    }
    
    public void processPacket(INetHandlerPlayClient handler) {
        handler.handleWindowItems(this);
    }
    
    public int func_148911_c() {
        return this.windowId;
    }
    
    public ItemStack[] getItemStacks() {
        return this.itemStacks;
    }
}
