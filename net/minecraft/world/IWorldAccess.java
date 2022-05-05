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

package net.minecraft.world;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.BlockPos;

public interface IWorldAccess {
    void markBlockForUpdate(BlockPos paramBlockPos);
    
    void notifyLightSet(BlockPos paramBlockPos);
    
    void markBlockRangeForRenderUpdate(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6);
    
    void playSound(String paramString, double paramDouble1, double paramDouble2, double paramDouble3, float paramFloat1, float paramFloat2);
    
    void playSoundToNearExcept(EntityPlayer paramEntityPlayer, String paramString, double paramDouble1, double paramDouble2, double paramDouble3, float paramFloat1, float paramFloat2);
    
    void spawnParticle(int paramInt, boolean paramBoolean, double paramDouble1, double paramDouble2, double paramDouble3, double paramDouble4, double paramDouble5, double paramDouble6, int... paramVarArgs);
    
    void onEntityAdded(Entity paramEntity);
    
    void onEntityRemoved(Entity paramEntity);
    
    void playRecord(String paramString, BlockPos paramBlockPos);
    
    void broadcastSound(int paramInt1, BlockPos paramBlockPos, int paramInt2);
    
    void playAuxSFX(EntityPlayer paramEntityPlayer, int paramInt1, BlockPos paramBlockPos, int paramInt2);
    
    void sendBlockBreakProgress(int paramInt1, BlockPos paramBlockPos, int paramInt2);
}
