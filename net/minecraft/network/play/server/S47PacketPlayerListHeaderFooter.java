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
import net.minecraft.util.IChatComponent;

public class S47PacketPlayerListHeaderFooter implements Packet<INetHandlerPlayClient> {
    private IChatComponent header;
    
    private IChatComponent footer;
    
    public S47PacketPlayerListHeaderFooter() {}
    
    public S47PacketPlayerListHeaderFooter(IChatComponent headerIn) {
        this.header = headerIn;
    }
    
    public void readPacketData(PacketBuffer buf) throws IOException {
        this.header = buf.readChatComponent();
        this.footer = buf.readChatComponent();
    }
    
    public void writePacketData(PacketBuffer buf) throws IOException {
        buf.writeChatComponent(this.header);
        buf.writeChatComponent(this.footer);
    }
    
    public void processPacket(INetHandlerPlayClient handler) {
        handler.handlePlayerListHeaderFooter(this);
    }
    
    public IChatComponent getHeader() {
        return this.header;
    }
    
    public IChatComponent getFooter() {
        return this.footer;
    }
}
