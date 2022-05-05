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

package net.minecraft.client.network;

import net.minecraft.network.INetHandler;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.handshake.INetHandlerHandshakeServer;
import net.minecraft.network.handshake.client.C00Handshake;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.NetHandlerLoginServer;
import net.minecraft.util.IChatComponent;

public class NetHandlerHandshakeMemory implements INetHandlerHandshakeServer {
    private final MinecraftServer mcServer;
    
    private final NetworkManager networkManager;
    
    public NetHandlerHandshakeMemory(MinecraftServer p_i45287_1_, NetworkManager p_i45287_2_) {
        this.mcServer = p_i45287_1_;
        this.networkManager = p_i45287_2_;
    }
    
    public void processHandshake(C00Handshake packetIn) {
        this.networkManager.setConnectionState(packetIn.getRequestedState());
        this.networkManager.setNetHandler((INetHandler)new NetHandlerLoginServer(this.mcServer, this.networkManager));
    }
    
    public void onDisconnect(IChatComponent reason) {}
}
