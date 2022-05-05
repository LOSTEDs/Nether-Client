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

public class C0DPacketCloseWindow implements Packet<INetHandlerPlayServer> {
    private int windowId;
    
    public C0DPacketCloseWindow() {}
    
    public C0DPacketCloseWindow(int windowId) {
        this.windowId = windowId;
    }
    
    public void processPacket(INetHandlerPlayServer handler) {
        handler.processCloseWindow(this);
    }
    
    public void readPacketData(PacketBuffer buf) throws IOException {
        this.windowId = buf.readByte();
    }
    
    public void writePacketData(PacketBuffer buf) throws IOException {
        buf.writeByte(this.windowId);
    }
}
