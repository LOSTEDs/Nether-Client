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

public class S40PacketDisconnect implements Packet<INetHandlerPlayClient> {
    private IChatComponent reason;
    
    public S40PacketDisconnect() {}
    
    public S40PacketDisconnect(IChatComponent reasonIn) {
        this.reason = reasonIn;
    }
    
    public void readPacketData(PacketBuffer buf) throws IOException {
        this.reason = buf.readChatComponent();
    }
    
    public void writePacketData(PacketBuffer buf) throws IOException {
        buf.writeChatComponent(this.reason);
    }
    
    public void processPacket(INetHandlerPlayClient handler) {
        handler.handleDisconnect(this);
    }
    
    public IChatComponent getReason() {
        return this.reason;
    }
}
