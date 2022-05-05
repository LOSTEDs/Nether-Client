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

package net.minecraft.client.renderer.entity;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelGhast;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityGhast;
import net.minecraft.util.ResourceLocation;

public class RenderGhast extends RenderLiving<EntityGhast> {
    private static final ResourceLocation ghastTextures = new ResourceLocation("textures/entity/ghast/ghast.png");
    
    private static final ResourceLocation ghastShootingTextures = new ResourceLocation("textures/entity/ghast/ghast_shooting.png");
    
    public RenderGhast(RenderManager renderManagerIn) {
        super(renderManagerIn, (ModelBase)new ModelGhast(), 0.5F);
    }
    
    protected ResourceLocation getEntityTexture(EntityGhast entity) {
        return entity.isAttacking() ? ghastShootingTextures : ghastTextures;
    }
    
    protected void preRenderCallback(EntityGhast entitylivingbaseIn, float partialTickTime) {
        float f = 1.0F;
        float f1 = (8.0F + f) / 2.0F;
        float f2 = (8.0F + 1.0F / f) / 2.0F;
        GlStateManager.scale(f2, f1, f2);
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
    }
}
