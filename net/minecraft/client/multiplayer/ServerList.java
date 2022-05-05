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

package net.minecraft.client.multiplayer;

import com.google.common.collect.Lists;
import java.io.File;
import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.nbt.CompressedStreamTools;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ServerList {
    private static final Logger logger = LogManager.getLogger();
    
    private final Minecraft mc;
    
    private final List<ServerData> servers = Lists.newArrayList();
    
    public ServerList(Minecraft mcIn) {
        this.mc = mcIn;
        loadServerList();
    }
    
    public void loadServerList() {
        try {
            this.servers.clear();
            NBTTagCompound nbttagcompound = CompressedStreamTools.read(new File(this.mc.mcDataDir, "servers.dat"));
            if (nbttagcompound == null)
                return; 
            NBTTagList nbttaglist = nbttagcompound.getTagList("servers", 10);
            for (int i = 0; i < nbttaglist.tagCount(); i++)
                this.servers.add(ServerData.getServerDataFromNBTCompound(nbttaglist.getCompoundTagAt(i))); 
        } catch (Exception exception) {
            logger.error("Couldn't load server list", exception);
        } 
    }
    
    public void saveServerList() {
        try {
            NBTTagList nbttaglist = new NBTTagList();
            for (ServerData serverdata : this.servers)
                nbttaglist.appendTag((NBTBase)serverdata.getNBTCompound()); 
            NBTTagCompound nbttagcompound = new NBTTagCompound();
            nbttagcompound.setTag("servers", (NBTBase)nbttaglist);
            CompressedStreamTools.safeWrite(nbttagcompound, new File(this.mc.mcDataDir, "servers.dat"));
        } catch (Exception exception) {
            logger.error("Couldn't save server list", exception);
        } 
    }
    
    public ServerData getServerData(int p_78850_1_) {
        return this.servers.get(p_78850_1_);
    }
    
    public void removeServerData(int p_78851_1_) {
        this.servers.remove(p_78851_1_);
    }
    
    public void addServerData(ServerData p_78849_1_) {
        this.servers.add(p_78849_1_);
    }
    
    public int countServers() {
        return this.servers.size();
    }
    
    public void swapServers(int p_78857_1_, int p_78857_2_) {
        ServerData serverdata = getServerData(p_78857_1_);
        this.servers.set(p_78857_1_, getServerData(p_78857_2_));
        this.servers.set(p_78857_2_, serverdata);
        saveServerList();
    }
    
    public void func_147413_a(int p_147413_1_, ServerData p_147413_2_) {
        this.servers.set(p_147413_1_, p_147413_2_);
    }
    
    public static void func_147414_b(ServerData p_147414_0_) {
        ServerList serverlist = new ServerList(Minecraft.getMinecraft());
        serverlist.loadServerList();
        for (int i = 0; i < serverlist.countServers(); i++) {
            ServerData serverdata = serverlist.getServerData(i);
            if (serverdata.serverName.equals(p_147414_0_.serverName) && serverdata.serverIP.equals(p_147414_0_.serverIP)) {
                serverlist.func_147413_a(i, p_147414_0_);
                break;
            } 
        } 
        serverlist.saveServerList();
    }
}