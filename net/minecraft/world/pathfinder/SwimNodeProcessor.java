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

package net.minecraft.world.pathfinder;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.pathfinding.PathPoint;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;

public class SwimNodeProcessor extends NodeProcessor {
    public void initProcessor(IBlockAccess iblockaccessIn, Entity entityIn) {
        super.initProcessor(iblockaccessIn, entityIn);
    }
    
    public void postProcess() {
        super.postProcess();
    }
    
    public PathPoint getPathPointTo(Entity entityIn) {
        return openPoint(MathHelper.floor_double((entityIn.getEntityBoundingBox()).minX), MathHelper.floor_double((entityIn.getEntityBoundingBox()).minY + 0.5D), MathHelper.floor_double((entityIn.getEntityBoundingBox()).minZ));
    }
    
    public PathPoint getPathPointToCoords(Entity entityIn, double x, double y, double target) {
        return openPoint(MathHelper.floor_double(x - (entityIn.width / 2.0F)), MathHelper.floor_double(y + 0.5D), MathHelper.floor_double(target - (entityIn.width / 2.0F)));
    }
    
    public int findPathOptions(PathPoint[] pathOptions, Entity entityIn, PathPoint currentPoint, PathPoint targetPoint, float maxDistance) {
        int i = 0;
        byte b;
        int j;
        EnumFacing[] arrayOfEnumFacing;
        for (j = (arrayOfEnumFacing = EnumFacing.values()).length, b = 0; b < j; ) {
            EnumFacing enumfacing = arrayOfEnumFacing[b];
            PathPoint pathpoint = getSafePoint(entityIn, currentPoint.xCoord + enumfacing.getFrontOffsetX(), currentPoint.yCoord + enumfacing.getFrontOffsetY(), currentPoint.zCoord + enumfacing.getFrontOffsetZ());
            if (pathpoint != null && !pathpoint.visited && pathpoint.distanceTo(targetPoint) < maxDistance)
                pathOptions[i++] = pathpoint; 
            b++;
        } 
        return i;
    }
    
    private PathPoint getSafePoint(Entity entityIn, int x, int y, int z) {
        int i = func_176186_b(entityIn, x, y, z);
        return (i == -1) ? openPoint(x, y, z) : null;
    }
    
    private int func_176186_b(Entity entityIn, int x, int y, int z) {
        BlockPos.MutableBlockPos blockpos$mutableblockpos = new BlockPos.MutableBlockPos();
        for (int i = x; i < x + this.entitySizeX; i++) {
            for (int j = y; j < y + this.entitySizeY; j++) {
                for (int k = z; k < z + this.entitySizeZ; k++) {
                    Block block = this.blockaccess.getBlockState((BlockPos)blockpos$mutableblockpos.func_181079_c(i, j, k)).getBlock();
                    if (block.getMaterial() != Material.water)
                        return 0; 
                } 
            } 
        } 
        return -1;
    }
}
