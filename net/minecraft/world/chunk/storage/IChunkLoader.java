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

import java.io.IOException;
import net.minecraft.world.MinecraftException;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;

public interface IChunkLoader {
    Chunk loadChunk(World paramWorld, int paramInt1, int paramInt2) throws IOException;
    
    void saveChunk(World paramWorld, Chunk paramChunk) throws MinecraftException, IOException;
    
    void saveExtraChunkData(World paramWorld, Chunk paramChunk) throws IOException;
    
    void chunkTick();
    
    void saveExtraData();
}
