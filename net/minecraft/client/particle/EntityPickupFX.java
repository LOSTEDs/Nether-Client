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

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.src.Config;
import net.minecraft.world.World;
import shadersmod.client.Shaders;

public class EntityPickupFX extends EntityFX {
    private Entity field_174840_a;
    
    private Entity field_174843_ax;
    
    private int age;
    
    private int maxAge;
    
    private float field_174841_aA;
    
    private RenderManager field_174842_aB = Minecraft.getMinecraft().getRenderManager();
    
    private static final String __OBFID = "CL_00000930";
    
    public EntityPickupFX(World worldIn, Entity p_i1233_2_, Entity p_i1233_3_, float p_i1233_4_) {
        super(worldIn, p_i1233_2_.posX, p_i1233_2_.posY, p_i1233_2_.posZ, p_i1233_2_.motionX, p_i1233_2_.motionY, p_i1233_2_.motionZ);
        this.field_174840_a = p_i1233_2_;
        this.field_174843_ax = p_i1233_3_;
        this.maxAge = 3;
        this.field_174841_aA = p_i1233_4_;
    }
    
    public void renderParticle(WorldRenderer worldRendererIn, Entity entityIn, float partialTicks, float p_180434_4_, float p_180434_5_, float p_180434_6_, float p_180434_7_, float p_180434_8_) {
        int i = 0;
        if (Config.isShaders()) {
            i = Shaders.activeProgram;
            Shaders.nextEntity(this.field_174840_a);
        } 
        float f = (this.age + partialTicks) / this.maxAge;
        f *= f;
        double d0 = this.field_174840_a.posX;
        double d1 = this.field_174840_a.posY;
        double d2 = this.field_174840_a.posZ;
        double d3 = this.field_174843_ax.lastTickPosX + (this.field_174843_ax.posX - this.field_174843_ax.lastTickPosX) * partialTicks;
        double d4 = this.field_174843_ax.lastTickPosY + (this.field_174843_ax.posY - this.field_174843_ax.lastTickPosY) * partialTicks + this.field_174841_aA;
        double d5 = this.field_174843_ax.lastTickPosZ + (this.field_174843_ax.posZ - this.field_174843_ax.lastTickPosZ) * partialTicks;
        double d6 = d0 + (d3 - d0) * f;
        double d7 = d1 + (d4 - d1) * f;
        double d8 = d2 + (d5 - d2) * f;
        int j = getBrightnessForRender(partialTicks);
        int k = j % 65536;
        int l = j / 65536;
        OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, k / 1.0F, l / 1.0F);
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        d6 -= interpPosX;
        d7 -= interpPosY;
        d8 -= interpPosZ;
        this.field_174842_aB.renderEntityWithPosYaw(this.field_174840_a, (float)d6, (float)d7, (float)d8, this.field_174840_a.rotationYaw, partialTicks);
        if (Config.isShaders())
            Shaders.useProgram(i); 
    }
    
    public void onUpdate() {
        this.age++;
        if (this.age == this.maxAge)
            setDead(); 
    }
    
    public int getFXLayer() {
        return 3;
    }
}
