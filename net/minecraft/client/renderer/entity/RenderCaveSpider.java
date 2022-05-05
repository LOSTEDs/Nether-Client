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

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityCaveSpider;
import net.minecraft.entity.monster.EntitySpider;
import net.minecraft.util.ResourceLocation;

public class RenderCaveSpider extends RenderSpider<EntityCaveSpider> {
    private static final ResourceLocation caveSpiderTextures = new ResourceLocation("textures/entity/spider/cave_spider.png");
    
    public RenderCaveSpider(RenderManager renderManagerIn) {
        super(renderManagerIn);
        this.shadowSize *= 0.7F;
    }
    
    protected void preRenderCallback(EntityCaveSpider entitylivingbaseIn, float partialTickTime) {
        GlStateManager.scale(0.7F, 0.7F, 0.7F);
    }
    
    protected ResourceLocation getEntityTexture(EntityCaveSpider entity) {
        return caveSpiderTextures;
    }
}
