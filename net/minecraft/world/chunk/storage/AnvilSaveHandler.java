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

package net.minecraft.world.chunk.storage;

import java.io.File;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.storage.SaveHandler;
import net.minecraft.world.storage.ThreadedFileIOBase;
import net.minecraft.world.storage.WorldInfo;

public class AnvilSaveHandler extends SaveHandler {
    public AnvilSaveHandler(File savesDirectory, String p_i2142_2_, boolean storePlayerdata) {
        super(savesDirectory, p_i2142_2_, storePlayerdata);
    }
    
    public IChunkLoader getChunkLoader(WorldProvider provider) {
        File file1 = getWorldDirectory();
        if (provider instanceof net.minecraft.world.WorldProviderHell) {
            File file3 = new File(file1, "DIM-1");
            file3.mkdirs();
            return new AnvilChunkLoader(file3);
        } 
        if (provider instanceof net.minecraft.world.WorldProviderEnd) {
            File file2 = new File(file1, "DIM1");
            file2.mkdirs();
            return new AnvilChunkLoader(file2);
        } 
        return new AnvilChunkLoader(file1);
    }
    
    public void saveWorldInfoWithPlayer(WorldInfo worldInformation, NBTTagCompound tagCompound) {
        worldInformation.setSaveVersion(19133);
        super.saveWorldInfoWithPlayer(worldInformation, tagCompound);
    }
    
    public void flush() {
        try {
            ThreadedFileIOBase.getThreadedIOInstance().waitForFinish();
        } catch (InterruptedException interruptedexception) {
            interruptedexception.printStackTrace();
        } 
        RegionFileCache.clearRegionFileReferences();
    }
}
