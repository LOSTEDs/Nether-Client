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

package net.minecraft.client.particle;

import net.minecraft.world.World;

public class EntityAuraFX extends EntityFX {
    protected EntityAuraFX(World worldIn, double xCoordIn, double yCoordIn, double zCoordIn, double xSpeedIn, double ySpeedIn, double speedIn) {
        super(worldIn, xCoordIn, yCoordIn, zCoordIn, xSpeedIn, ySpeedIn, speedIn);
        float f = this.rand.nextFloat() * 0.1F + 0.2F;
        this.particleRed = f;
        this.particleGreen = f;
        this.particleBlue = f;
        setParticleTextureIndex(0);
        setSize(0.02F, 0.02F);
        this.particleScale *= this.rand.nextFloat() * 0.6F + 0.5F;
        this.motionX *= 0.019999999552965164D;
        this.motionY *= 0.019999999552965164D;
        this.motionZ *= 0.019999999552965164D;
        this.particleMaxAge = (int)(20.0D / (Math.random() * 0.8D + 0.2D));
        this.noClip = true;
    }
    
    public void onUpdate() {
        this.prevPosX = this.posX;
        this.prevPosY = this.posY;
        this.prevPosZ = this.posZ;
        moveEntity(this.motionX, this.motionY, this.motionZ);
        this.motionX *= 0.99D;
        this.motionY *= 0.99D;
        this.motionZ *= 0.99D;
        if (this.particleMaxAge-- <= 0)
            setDead(); 
    }
    
    public static class Factory implements IParticleFactory {
        public EntityFX getEntityFX(int particleID, World worldIn, double xCoordIn, double yCoordIn, double zCoordIn, double xSpeedIn, double ySpeedIn, double zSpeedIn, int... p_178902_15_) {
            return new EntityAuraFX(worldIn, xCoordIn, yCoordIn, zCoordIn, xSpeedIn, ySpeedIn, zSpeedIn);
        }
    }
    
    public static class HappyVillagerFactory implements IParticleFactory {
        public EntityFX getEntityFX(int particleID, World worldIn, double xCoordIn, double yCoordIn, double zCoordIn, double xSpeedIn, double ySpeedIn, double zSpeedIn, int... p_178902_15_) {
            EntityFX entityfx = new EntityAuraFX(worldIn, xCoordIn, yCoordIn, zCoordIn, xSpeedIn, ySpeedIn, zSpeedIn);
            entityfx.setParticleTextureIndex(82);
            entityfx.setRBGColorF(1.0F, 1.0F, 1.0F);
            return entityfx;
        }
    }
}
