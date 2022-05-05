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

import java.util.Random;
import net.minecraft.block.material.Material;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelEnderman;
import net.minecraft.client.renderer.entity.layers.LayerEndermanEyes;
import net.minecraft.client.renderer.entity.layers.LayerHeldBlock;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.util.ResourceLocation;

public class RenderEnderman extends RenderLiving<EntityEnderman> {
    private static final ResourceLocation endermanTextures = new ResourceLocation("textures/entity/enderman/enderman.png");
    
    private ModelEnderman endermanModel;
    
    private Random rnd = new Random();
    
    public RenderEnderman(RenderManager renderManagerIn) {
        super(renderManagerIn, (ModelBase)new ModelEnderman(0.0F), 0.5F);
        this.endermanModel = (ModelEnderman)this.mainModel;
        addLayer((LayerRenderer)new LayerEndermanEyes(this));
        addLayer((LayerRenderer)new LayerHeldBlock(this));
    }
    
    public void doRender(EntityEnderman entity, double x, double y, double z, float entityYaw, float partialTicks) {
        this.endermanModel.isCarrying = (entity.getHeldBlockState().getBlock().getMaterial() != Material.air);
        this.endermanModel.isAttacking = entity.isScreaming();
        if (entity.isScreaming()) {
            double d0 = 0.02D;
            x += this.rnd.nextGaussian() * d0;
            z += this.rnd.nextGaussian() * d0;
        } 
        super.doRender(entity, x, y, z, entityYaw, partialTicks);
    }
    
    protected ResourceLocation getEntityTexture(EntityEnderman entity) {
        return endermanTextures;
    }
}
