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

package net.minecraft.src;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.multiplayer.PlayerControllerMP;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.client.network.NetHandlerPlayClient;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

public class PlayerControllerOF extends PlayerControllerMP {
    private boolean acting = false;
    
    private BlockPos lastClickBlockPos = null;
    
    private Entity lastClickEntity = null;
    
    public PlayerControllerOF(Minecraft p_i82_1_, NetHandlerPlayClient p_i82_2_) {
        super(p_i82_1_, p_i82_2_);
    }
    
    public boolean clickBlock(BlockPos loc, EnumFacing face) {
        this.acting = true;
        this.lastClickBlockPos = loc;
        boolean flag = super.clickBlock(loc, face);
        this.acting = false;
        return flag;
    }
    
    public boolean onPlayerDamageBlock(BlockPos posBlock, EnumFacing directionFacing) {
        this.acting = true;
        this.lastClickBlockPos = posBlock;
        boolean flag = super.onPlayerDamageBlock(posBlock, directionFacing);
        this.acting = false;
        return flag;
    }
    
    public boolean sendUseItem(EntityPlayer playerIn, World worldIn, ItemStack itemStackIn) {
        this.acting = true;
        boolean flag = super.sendUseItem(playerIn, worldIn, itemStackIn);
        this.acting = false;
        return flag;
    }
    
    public boolean onPlayerRightClick(EntityPlayerSP player, WorldClient worldIn, ItemStack heldStack, BlockPos hitPos, EnumFacing side, Vec3 hitVec) {
        this.acting = true;
        this.lastClickBlockPos = hitPos;
        boolean flag = super.onPlayerRightClick(player, worldIn, heldStack, hitPos, side, hitVec);
        this.acting = false;
        return flag;
    }
    
    public boolean interactWithEntitySendPacket(EntityPlayer playerIn, Entity targetEntity) {
        this.lastClickEntity = targetEntity;
        return super.interactWithEntitySendPacket(playerIn, targetEntity);
    }
    
    public boolean func_178894_a(EntityPlayer p_178894_1_, Entity p_178894_2_, MovingObjectPosition p_178894_3_) {
        this.lastClickEntity = p_178894_2_;
        return super.func_178894_a(p_178894_1_, p_178894_2_, p_178894_3_);
    }
    
    public boolean isActing() {
        return this.acting;
    }
    
    public BlockPos getLastClickBlockPos() {
        return this.lastClickBlockPos;
    }
    
    public Entity getLastClickEntity() {
        return this.lastClickEntity;
    }
}
