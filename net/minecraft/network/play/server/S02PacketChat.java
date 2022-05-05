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

public class S02PacketChat implements Packet<INetHandlerPlayClient> {
    private IChatComponent chatComponent;
    
    private byte type;
    
    public S02PacketChat() {}
    
    public S02PacketChat(IChatComponent component) {
        this(component, (byte)1);
    }
    
    public S02PacketChat(IChatComponent message, byte typeIn) {
        this.chatComponent = message;
        this.type = typeIn;
    }
    
    public void readPacketData(PacketBuffer buf) throws IOException {
        this.chatComponent = buf.readChatComponent();
        this.type = buf.readByte();
    }
    
    public void writePacketData(PacketBuffer buf) throws IOException {
        buf.writeChatComponent(this.chatComponent);
        buf.writeByte(this.type);
    }
    
    public void processPacket(INetHandlerPlayClient handler) {
        handler.handleChat(this);
    }
    
    public IChatComponent getChatComponent() {
        return this.chatComponent;
    }
    
    public boolean isChat() {
        return !(this.type != 1 && this.type != 2);
    }
    
    public byte getType() {
        return this.type;
    }
}
