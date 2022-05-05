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
import net.minecraft.client.model.ModelZombie;
import net.minecraft.client.renderer.entity.layers.LayerBipedArmor;
import net.minecraft.client.renderer.entity.layers.LayerHeldItem;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.monster.EntityPigZombie;
import net.minecraft.util.ResourceLocation;

public class RenderPigZombie extends RenderBiped<EntityPigZombie> {
    private static final ResourceLocation ZOMBIE_PIGMAN_TEXTURE = new ResourceLocation("textures/entity/zombie_pigman.png");
    
    public RenderPigZombie(RenderManager renderManagerIn) {
        super(renderManagerIn, (ModelBiped)new ModelZombie(), 0.5F, 1.0F);
        addLayer((LayerRenderer)new LayerHeldItem(this));
        addLayer((LayerRenderer)new LayerBipedArmor(this) {
                    protected void initArmor() {
                        this.field_177189_c = (ModelBase)new ModelZombie(0.5F, true);
                        this.field_177186_d = (ModelBase)new ModelZombie(1.0F, true);
                    }
                });
    }
    
    protected ResourceLocation getEntityTexture(EntityPigZombie entity) {
        return ZOMBIE_PIGMAN_TEXTURE;
    }
}
