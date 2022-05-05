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

package net.minecraft.client.renderer;

import java.util.ArrayDeque;
import java.util.Arrays;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.src.Config;
import net.minecraft.src.DynamicLights;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.Vec3i;
import net.minecraft.world.ChunkCache;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;

public class RegionRenderCache extends ChunkCache {
    private static final IBlockState DEFAULT_STATE = Blocks.air.getDefaultState();
    
    private final BlockPos position;
    
    private int[] combinedLights;
    
    private IBlockState[] blockStates;
    
    private static final String __OBFID = "CL_00002565";
    
    private static ArrayDeque<int[]> cacheLights = (ArrayDeque)new ArrayDeque<>();
    
    private static ArrayDeque<IBlockState[]> cacheStates = (ArrayDeque)new ArrayDeque<>();
    
    private static int maxCacheSize = Config.limit(Runtime.getRuntime().availableProcessors(), 1, 32);
    
    public RegionRenderCache(World worldIn, BlockPos posFromIn, BlockPos posToIn, int subIn) {
        super(worldIn, posFromIn, posToIn, subIn);
        this.position = posFromIn.subtract(new Vec3i(subIn, subIn, subIn));
        boolean flag = true;
        this.combinedLights = allocateLights(8000);
        Arrays.fill(this.combinedLights, -1);
        this.blockStates = allocateStates(8000);
    }
    
    public TileEntity getTileEntity(BlockPos pos) {
        int i = (pos.getX() >> 4) - this.chunkX;
        int j = (pos.getZ() >> 4) - this.chunkZ;
        if (i >= 0 && i < this.chunkArray.length) {
            Chunk[] achunk = this.chunkArray[i];
            if (j >= 0 && j < achunk.length) {
                Chunk chunk = achunk[j];
                return (chunk == null) ? null : chunk.getTileEntity(pos, Chunk.EnumCreateEntityType.QUEUED);
            } 
            return null;
        } 
        return null;
    }
    
    public int getCombinedLight(BlockPos pos, int lightValue) {
        int i = getPositionIndex(pos);
        if (i >= 0 && i < this.combinedLights.length) {
            int j = this.combinedLights[i];
            if (j == -1) {
                j = getCombinedLightRaw(pos, lightValue);
                this.combinedLights[i] = j;
            } 
            return j;
        } 
        return getCombinedLightRaw(pos, lightValue);
    }
    
    private int getCombinedLightRaw(BlockPos p_getCombinedLightRaw_1_, int p_getCombinedLightRaw_2_) {
        int i = super.getCombinedLight(p_getCombinedLightRaw_1_, p_getCombinedLightRaw_2_);
        if (Config.isDynamicLights() && !getBlockState(p_getCombinedLightRaw_1_).getBlock().isOpaqueCube())
            i = DynamicLights.getCombinedLight(p_getCombinedLightRaw_1_, i); 
        return i;
    }
    
    public IBlockState getBlockState(BlockPos pos) {
        int i = getPositionIndex(pos);
        if (i >= 0 && i < this.blockStates.length) {
            IBlockState iblockstate = this.blockStates[i];
            if (iblockstate == null) {
                iblockstate = getBlockStateRaw(pos);
                this.blockStates[i] = iblockstate;
            } 
            return iblockstate;
        } 
        return getBlockStateRaw(pos);
    }
    
    private IBlockState getBlockStateRaw(BlockPos pos) {
        return super.getBlockState(pos);
    }
    
    private int getPositionIndex(BlockPos p_175630_1_) {
        int i = p_175630_1_.getX() - this.position.getX();
        int j = p_175630_1_.getY() - this.position.getY();
        int k = p_175630_1_.getZ() - this.position.getZ();
        return (i >= 0 && j >= 0 && k >= 0 && i < 20 && j < 20 && k < 20) ? (i * 400 + k * 20 + j) : -1;
    }
    
    public void freeBuffers() {
        freeLights(this.combinedLights);
        freeStates(this.blockStates);
    }
    
    private static int[] allocateLights(int p_allocateLights_0_) {
        synchronized (cacheLights) {
            int[] aint = cacheLights.pollLast();
            if (aint == null || aint.length < p_allocateLights_0_)
                aint = new int[p_allocateLights_0_]; 
            return aint;
        } 
    }
    
    public static void freeLights(int[] p_freeLights_0_) {
        synchronized (cacheLights) {
            if (cacheLights.size() < maxCacheSize)
                cacheLights.add(p_freeLights_0_); 
        } 
    }
    
    private static IBlockState[] allocateStates(int p_allocateStates_0_) {
        synchronized (cacheStates) {
            IBlockState[] aiblockstate = cacheStates.pollLast();
            if (aiblockstate != null && aiblockstate.length >= p_allocateStates_0_) {
                Arrays.fill((Object[])aiblockstate, (Object)null);
            } else {
                aiblockstate = new IBlockState[p_allocateStates_0_];
            } 
            return aiblockstate;
        } 
    }
    
    public static void freeStates(IBlockState[] p_freeStates_0_) {
        synchronized (cacheStates) {
            if (cacheStates.size() < maxCacheSize)
                cacheStates.add(p_freeStates_0_); 
        } 
    }
}
