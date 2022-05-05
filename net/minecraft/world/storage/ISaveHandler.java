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

package net.minecraft.world.storage;

import java.io.File;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.MinecraftException;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.chunk.storage.IChunkLoader;

public interface ISaveHandler {
    WorldInfo loadWorldInfo();
    
    void checkSessionLock() throws MinecraftException;
    
    IChunkLoader getChunkLoader(WorldProvider paramWorldProvider);
    
    void saveWorldInfoWithPlayer(WorldInfo paramWorldInfo, NBTTagCompound paramNBTTagCompound);
    
    void saveWorldInfo(WorldInfo paramWorldInfo);
    
    IPlayerFileData getPlayerNBTManager();
    
    void flush();
    
    File getWorldDirectory();
    
    File getMapFileFromName(String paramString);
    
    String getWorldDirectoryName();
}
