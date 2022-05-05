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
import net.minecraft.client.model.ModelMagmaCube;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityMagmaCube;
import net.minecraft.util.ResourceLocation;

public class RenderMagmaCube extends RenderLiving<EntityMagmaCube> {
    private static final ResourceLocation magmaCubeTextures = new ResourceLocation("textures/entity/slime/magmacube.png");
    
    public RenderMagmaCube(RenderManager renderManagerIn) {
        super(renderManagerIn, (ModelBase)new ModelMagmaCube(), 0.25F);
    }
    
    protected ResourceLocation getEntityTexture(EntityMagmaCube entity) {
        return magmaCubeTextures;
    }
    
    protected void preRenderCallback(EntityMagmaCube entitylivingbaseIn, float partialTickTime) {
        int i = entitylivingbaseIn.getSlimeSize();
        float f = (entitylivingbaseIn.prevSquishFactor + (entitylivingbaseIn.squishFactor - entitylivingbaseIn.prevSquishFactor) * partialTickTime) / (i * 0.5F + 1.0F);
        float f1 = 1.0F / (f + 1.0F);
        float f2 = i;
        GlStateManager.scale(f1 * f2, 1.0F / f1 * f2, f1 * f2);
    }
}
