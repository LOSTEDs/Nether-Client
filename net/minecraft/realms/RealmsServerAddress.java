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

package net.minecraft.realms;

import net.minecraft.client.multiplayer.ServerAddress;

public class RealmsServerAddress {
    private final String host;
    
    private final int port;
    
    protected RealmsServerAddress(String p_i1121_1_, int p_i1121_2_) {
        this.host = p_i1121_1_;
        this.port = p_i1121_2_;
    }
    
    public String getHost() {
        return this.host;
    }
    
    public int getPort() {
        return this.port;
    }
    
    public static RealmsServerAddress parseString(String p_parseString_0_) {
        ServerAddress serveraddress = ServerAddress.func_78860_a(p_parseString_0_);
        return new RealmsServerAddress(serveraddress.getIP(), serveraddress.getPort());
    }
}
