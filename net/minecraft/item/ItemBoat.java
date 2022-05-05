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

package net.minecraft.item;

import java.util.List;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityBoat;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.stats.StatList;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

public class ItemBoat extends Item {
    public ItemBoat() {
        this.maxStackSize = 1;
        setCreativeTab(CreativeTabs.tabTransport);
    }
    
    public ItemStack onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer playerIn) {
        float f = 1.0F;
        float f1 = playerIn.prevRotationPitch + (playerIn.rotationPitch - playerIn.prevRotationPitch) * f;
        float f2 = playerIn.prevRotationYaw + (playerIn.rotationYaw - playerIn.prevRotationYaw) * f;
        double d0 = playerIn.prevPosX + (playerIn.posX - playerIn.prevPosX) * f;
        double d1 = playerIn.prevPosY + (playerIn.posY - playerIn.prevPosY) * f + playerIn.getEyeHeight();
        double d2 = playerIn.prevPosZ + (playerIn.posZ - playerIn.prevPosZ) * f;
        Vec3 vec3 = new Vec3(d0, d1, d2);
        float f3 = MathHelper.cos(-f2 * 0.017453292F - 3.1415927F);
        float f4 = MathHelper.sin(-f2 * 0.017453292F - 3.1415927F);
        float f5 = -MathHelper.cos(-f1 * 0.017453292F);
        float f6 = MathHelper.sin(-f1 * 0.017453292F);
        float f7 = f4 * f5;
        float f8 = f3 * f5;
        double d3 = 5.0D;
        Vec3 vec31 = vec3.addVector(f7 * d3, f6 * d3, f8 * d3);
        MovingObjectPosition movingobjectposition = worldIn.rayTraceBlocks(vec3, vec31, true);
        if (movingobjectposition == null)
            return itemStackIn; 
        Vec3 vec32 = playerIn.getLook(f);
        boolean flag = false;
        float f9 = 1.0F;
        List<Entity> list = worldIn.getEntitiesWithinAABBExcludingEntity((Entity)playerIn, playerIn.getEntityBoundingBox().addCoord(vec32.xCoord * d3, vec32.yCoord * d3, vec32.zCoord * d3).expand(f9, f9, f9));
        for (int i = 0; i < list.size(); i++) {
            Entity entity = list.get(i);
            if (entity.canBeCollidedWith()) {
                float f10 = entity.getCollisionBorderSize();
                AxisAlignedBB axisalignedbb = entity.getEntityBoundingBox().expand(f10, f10, f10);
                if (axisalignedbb.isVecInside(vec3))
                    flag = true; 
            } 
        } 
        if (flag)
            return itemStackIn; 
        if (movingobjectposition.typeOfHit == MovingObjectPosition.MovingObjectType.BLOCK) {
            BlockPos blockpos = movingobjectposition.getBlockPos();
            if (worldIn.getBlockState(blockpos).getBlock() == Blocks.snow_layer)
                blockpos = blockpos.down(); 
            EntityBoat entityboat = new EntityBoat(worldIn, (blockpos.getX() + 0.5F), (blockpos.getY() + 1.0F), (blockpos.getZ() + 0.5F));
            entityboat.rotationYaw = (((MathHelper.floor_double((playerIn.rotationYaw * 4.0F / 360.0F) + 0.5D) & 0x3) - 1) * 90);
            if (!worldIn.getCollidingBoundingBoxes((Entity)entityboat, entityboat.getEntityBoundingBox().expand(-0.1D, -0.1D, -0.1D)).isEmpty())
                return itemStackIn; 
            if (!worldIn.isRemote)
                worldIn.spawnEntityInWorld((Entity)entityboat); 
            if (!playerIn.capabilities.isCreativeMode)
                itemStackIn.stackSize--; 
            playerIn.triggerAchievement(StatList.objectUseStats[Item.getIdFromItem(this)]);
        } 
        return itemStackIn;
    }
}
