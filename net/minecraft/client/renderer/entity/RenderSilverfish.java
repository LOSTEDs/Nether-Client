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
import net.minecraft.client.model.ModelSilverfish;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntitySilverfish;
import net.minecraft.util.ResourceLocation;

public class RenderSilverfish extends RenderLiving<EntitySilverfish> {
    private static final ResourceLocation silverfishTextures = new ResourceLocation("textures/entity/silverfish.png");
    
    public RenderSilverfish(RenderManager renderManagerIn) {
        super(renderManagerIn, (ModelBase)new ModelSilverfish(), 0.3F);
    }
    
    protected float getDeathMaxRotation(EntitySilverfish entityLivingBaseIn) {
        return 180.0F;
    }
    
    protected ResourceLocation getEntityTexture(EntitySilverfish entity) {
        return silverfishTextures;
    }
}
