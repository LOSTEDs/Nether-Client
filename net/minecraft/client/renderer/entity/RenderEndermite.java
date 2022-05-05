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
import net.minecraft.client.model.ModelEnderMite;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityEndermite;
import net.minecraft.util.ResourceLocation;

public class RenderEndermite extends RenderLiving<EntityEndermite> {
    private static final ResourceLocation ENDERMITE_TEXTURES = new ResourceLocation("textures/entity/endermite.png");
    
    public RenderEndermite(RenderManager renderManagerIn) {
        super(renderManagerIn, (ModelBase)new ModelEnderMite(), 0.3F);
    }
    
    protected float getDeathMaxRotation(EntityEndermite entityLivingBaseIn) {
        return 180.0F;
    }
    
    protected ResourceLocation getEntityTexture(EntityEndermite entity) {
        return ENDERMITE_TEXTURES;
    }
}
