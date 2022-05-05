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
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelSkeleton;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.layers.LayerBipedArmor;
import net.minecraft.client.renderer.entity.layers.LayerHeldItem;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.util.ResourceLocation;

public class RenderSkeleton extends RenderBiped<EntitySkeleton> {
    private static final ResourceLocation skeletonTextures = new ResourceLocation("textures/entity/skeleton/skeleton.png");
    
    private static final ResourceLocation witherSkeletonTextures = new ResourceLocation("textures/entity/skeleton/wither_skeleton.png");
    
    public RenderSkeleton(RenderManager renderManagerIn) {
        super(renderManagerIn, (ModelBiped)new ModelSkeleton(), 0.5F);
        addLayer((LayerRenderer)new LayerHeldItem(this));
        addLayer((LayerRenderer)new LayerBipedArmor(this) {
                    protected void initArmor() {
                        this.field_177189_c = (ModelBase)new ModelSkeleton(0.5F, true);
                        this.field_177186_d = (ModelBase)new ModelSkeleton(1.0F, true);
                    }
                });
    }
    
    protected void preRenderCallback(EntitySkeleton entitylivingbaseIn, float partialTickTime) {
        if (entitylivingbaseIn.getSkeletonType() == 1)
            GlStateManager.scale(1.2F, 1.2F, 1.2F); 
    }
    
    public void transformHeldFull3DItemLayer() {
        GlStateManager.translate(0.09375F, 0.1875F, 0.0F);
    }
    
    protected ResourceLocation getEntityTexture(EntitySkeleton entity) {
        return (entity.getSkeletonType() == 1) ? witherSkeletonTextures : skeletonTextures;
    }
}
