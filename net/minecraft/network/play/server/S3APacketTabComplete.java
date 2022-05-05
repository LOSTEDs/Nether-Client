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

public class S3APacketTabComplete implements Packet<INetHandlerPlayClient> {
    private String[] matches;
    
    public S3APacketTabComplete() {}
    
    public S3APacketTabComplete(String[] matchesIn) {
        this.matches = matchesIn;
    }
    
    public void readPacketData(PacketBuffer buf) throws IOException {
        this.matches = new String[buf.readVarIntFromBuffer()];
        for (int i = 0; i < this.matches.length; i++)
            this.matches[i] = buf.readStringFromBuffer(32767); 
    }
    
    public void writePacketData(PacketBuffer buf) throws IOException {
        buf.writeVarIntToBuffer(this.matches.length);
        byte b;
        int i;
        String[] arrayOfString;
        for (i = (arrayOfString = this.matches).length, b = 0; b < i; ) {
            String s = arrayOfString[b];
            buf.writeString(s);
            b++;
        } 
    }
    
    public void processPacket(INetHandlerPlayClient handler) {
        handler.handleTabComplete(this);
    }
    
    public String[] func_149630_c() {
        return this.matches;
    }
}
