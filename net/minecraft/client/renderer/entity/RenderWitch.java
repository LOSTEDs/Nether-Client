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
import net.minecraft.client.model.ModelWitch;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.layers.LayerHeldItemWitch;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityWitch;
import net.minecraft.util.ResourceLocation;

public class RenderWitch extends RenderLiving<EntityWitch> {
    private static final ResourceLocation witchTextures = new ResourceLocation("textures/entity/witch.png");
    
    public RenderWitch(RenderManager renderManagerIn) {
        super(renderManagerIn, (ModelBase)new ModelWitch(0.0F), 0.5F);
        addLayer((LayerRenderer)new LayerHeldItemWitch(this));
    }
    
    public void doRender(EntityWitch entity, double x, double y, double z, float entityYaw, float partialTicks) {
        ((ModelWitch)this.mainModel).field_82900_g = (entity.getHeldItem() != null);
        super.doRender(entity, x, y, z, entityYaw, partialTicks);
    }
    
    protected ResourceLocation getEntityTexture(EntityWitch entity) {
        return witchTextures;
    }
    
    public void transformHeldFull3DItemLayer() {
        GlStateManager.translate(0.0F, 0.1875F, 0.0F);
    }
    
    protected void preRenderCallback(EntityWitch entitylivingbaseIn, float partialTickTime) {
        float f = 0.9375F;
        GlStateManager.scale(f, f, f);
    }
}
