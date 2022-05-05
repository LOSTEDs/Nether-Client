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
import net.minecraft.client.model.ModelSpider;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.client.renderer.entity.layers.LayerSpiderEyes;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntitySpider;
import net.minecraft.util.ResourceLocation;

public class RenderSpider<T extends EntitySpider> extends RenderLiving<T> {
    private static final ResourceLocation spiderTextures = new ResourceLocation("textures/entity/spider/spider.png");
    
    public RenderSpider(RenderManager renderManagerIn) {
        super(renderManagerIn, (ModelBase)new ModelSpider(), 1.0F);
        addLayer((LayerRenderer)new LayerSpiderEyes(this));
    }
    
    protected float getDeathMaxRotation(T entityLivingBaseIn) {
        return 180.0F;
    }
    
    protected ResourceLocation getEntityTexture(T entity) {
        return spiderTextures;
    }
}
