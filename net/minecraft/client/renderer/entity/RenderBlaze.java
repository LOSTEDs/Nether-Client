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
import net.minecraft.client.model.ModelBlaze;
import net.minecraft.entity.Entity;
import net.minecraft.entity.monster.EntityBlaze;
import net.minecraft.util.ResourceLocation;

public class RenderBlaze extends RenderLiving<EntityBlaze> {
    private static final ResourceLocation blazeTextures = new ResourceLocation("textures/entity/blaze.png");
    
    public RenderBlaze(RenderManager renderManagerIn) {
        super(renderManagerIn, (ModelBase)new ModelBlaze(), 0.5F);
    }
    
    protected ResourceLocation getEntityTexture(EntityBlaze entity) {
        return blazeTextures;
    }
}
