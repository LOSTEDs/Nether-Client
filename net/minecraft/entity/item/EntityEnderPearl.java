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

package net.minecraft.entity.item;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityEndermite;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class EntityEnderPearl extends EntityThrowable {
    private EntityLivingBase field_181555_c;
    
    public EntityEnderPearl(World p_i46455_1_) {
        super(p_i46455_1_);
    }
    
    public EntityEnderPearl(World worldIn, EntityLivingBase p_i1783_2_) {
        super(worldIn, p_i1783_2_);
        this.field_181555_c = p_i1783_2_;
    }
    
    public EntityEnderPearl(World worldIn, double p_i1784_2_, double p_i1784_4_, double p_i1784_6_) {
        super(worldIn, p_i1784_2_, p_i1784_4_, p_i1784_6_);
    }
    
    protected void onImpact(MovingObjectPosition p_70184_1_) {
        EntityLivingBase entitylivingbase = getThrower();
        if (p_70184_1_.entityHit != null) {
            if (p_70184_1_.entityHit == this.field_181555_c)
                return; 
            p_70184_1_.entityHit.attackEntityFrom(DamageSource.causeThrownDamage((Entity)this, (Entity)entitylivingbase), 0.0F);
        } 
        for (int i = 0; i < 32; i++)
            this.worldObj.spawnParticle(EnumParticleTypes.PORTAL, this.posX, this.posY + this.rand.nextDouble() * 2.0D, this.posZ, this.rand.nextGaussian(), 0.0D, this.rand.nextGaussian(), new int[0]); 
        if (!this.worldObj.isRemote) {
            if (entitylivingbase instanceof EntityPlayerMP) {
                EntityPlayerMP entityplayermp = (EntityPlayerMP)entitylivingbase;
                if (entityplayermp.playerNetServerHandler.getNetworkManager().isChannelOpen() && entityplayermp.worldObj == this.worldObj && !entityplayermp.isPlayerSleeping()) {
                    if (this.rand.nextFloat() < 0.05F && this.worldObj.getGameRules().getBoolean("doMobSpawning")) {
                        EntityEndermite entityendermite = new EntityEndermite(this.worldObj);
                        entityendermite.setSpawnedByPlayer(true);
                        entityendermite.setLocationAndAngles(entitylivingbase.posX, entitylivingbase.posY, entitylivingbase.posZ, entitylivingbase.rotationYaw, entitylivingbase.rotationPitch);
                        this.worldObj.spawnEntityInWorld((Entity)entityendermite);
                    } 
                    if (entitylivingbase.isRiding())
                        entitylivingbase.mountEntity(null); 
                    entitylivingbase.setPositionAndUpdate(this.posX, this.posY, this.posZ);
                    entitylivingbase.fallDistance = 0.0F;
                    entitylivingbase.attackEntityFrom(DamageSource.fall, 5.0F);
                } 
            } else if (entitylivingbase != null) {
                entitylivingbase.setPositionAndUpdate(this.posX, this.posY, this.posZ);
                entitylivingbase.fallDistance = 0.0F;
            } 
            setDead();
        } 
    }
    
    public void onUpdate() {
        EntityLivingBase entitylivingbase = getThrower();
        if (entitylivingbase != null && entitylivingbase instanceof net.minecraft.entity.player.EntityPlayer && !entitylivingbase.isEntityAlive()) {
            setDead();
        } else {
            super.onUpdate();
        } 
    }
}
