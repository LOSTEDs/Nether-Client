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
import net.minecraft.client.model.ModelSnowMan;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.client.renderer.entity.layers.LayerSnowmanHead;
import net.minecraft.entity.Entity;
import net.minecraft.entity.monster.EntitySnowman;
import net.minecraft.util.ResourceLocation;

public class RenderSnowMan extends RenderLiving<EntitySnowman> {
    private static final ResourceLocation snowManTextures = new ResourceLocation("textures/entity/snowman.png");
    
    public RenderSnowMan(RenderManager renderManagerIn) {
        super(renderManagerIn, (ModelBase)new ModelSnowMan(), 0.5F);
        addLayer((LayerRenderer)new LayerSnowmanHead(this));
    }
    
    protected ResourceLocation getEntityTexture(EntitySnowman entity) {
        return snowManTextures;
    }
    
    public ModelSnowMan getMainModel() {
        return (ModelSnowMan)super.getMainModel();
    }
}
