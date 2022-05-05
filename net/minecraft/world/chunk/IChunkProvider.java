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

package net.minecraft.world.chunk;

import java.util.List;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.util.BlockPos;
import net.minecraft.util.IProgressUpdate;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;

public interface IChunkProvider {
    boolean chunkExists(int paramInt1, int paramInt2);
    
    Chunk provideChunk(int paramInt1, int paramInt2);
    
    Chunk provideChunk(BlockPos paramBlockPos);
    
    void populate(IChunkProvider paramIChunkProvider, int paramInt1, int paramInt2);
    
    boolean func_177460_a(IChunkProvider paramIChunkProvider, Chunk paramChunk, int paramInt1, int paramInt2);
    
    boolean saveChunks(boolean paramBoolean, IProgressUpdate paramIProgressUpdate);
    
    boolean unloadQueuedChunks();
    
    boolean canSave();
    
    String makeString();
    
    List<BiomeGenBase.SpawnListEntry> getPossibleCreatures(EnumCreatureType paramEnumCreatureType, BlockPos paramBlockPos);
    
    BlockPos getStrongholdGen(World paramWorld, String paramString, BlockPos paramBlockPos);
    
    int getLoadedChunkCount();
    
    void recreateStructures(Chunk paramChunk, int paramInt1, int paramInt2);
    
    void saveExtraData();
}
