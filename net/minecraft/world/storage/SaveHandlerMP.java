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

public class SaveHandlerMP implements ISaveHandler {
    public WorldInfo loadWorldInfo() {
        return null;
    }
    
    public void checkSessionLock() throws MinecraftException {}
    
    public IChunkLoader getChunkLoader(WorldProvider provider) {
        return null;
    }
    
    public void saveWorldInfoWithPlayer(WorldInfo worldInformation, NBTTagCompound tagCompound) {}
    
    public void saveWorldInfo(WorldInfo worldInformation) {}
    
    public IPlayerFileData getPlayerNBTManager() {
        return null;
    }
    
    public void flush() {}
    
    public File getMapFileFromName(String mapName) {
        return null;
    }
    
    public String getWorldDirectoryName() {
        return "none";
    }
    
    public File getWorldDirectory() {
        return null;
    }
}
