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

package net.minecraft.client.model;

import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;

public class ModelWitch extends ModelVillager {
    public boolean field_82900_g;
    
    private ModelRenderer field_82901_h = (new ModelRenderer(this)).setTextureSize(64, 128);
    
    private ModelRenderer witchHat;
    
    public ModelWitch(float p_i46361_1_) {
        super(p_i46361_1_, 0.0F, 64, 128);
        this.field_82901_h.setRotationPoint(0.0F, -2.0F, 0.0F);
        this.field_82901_h.setTextureOffset(0, 0).addBox(0.0F, 3.0F, -6.75F, 1, 1, 1, -0.25F);
        this.villagerNose.addChild(this.field_82901_h);
        this.witchHat = (new ModelRenderer(this)).setTextureSize(64, 128);
        this.witchHat.setRotationPoint(-5.0F, -10.03125F, -5.0F);
        this.witchHat.setTextureOffset(0, 64).addBox(0.0F, 0.0F, 0.0F, 10, 2, 10);
        this.villagerHead.addChild(this.witchHat);
        ModelRenderer modelrenderer = (new ModelRenderer(this)).setTextureSize(64, 128);
        modelrenderer.setRotationPoint(1.75F, -4.0F, 2.0F);
        modelrenderer.setTextureOffset(0, 76).addBox(0.0F, 0.0F, 0.0F, 7, 4, 7);
        modelrenderer.rotateAngleX = -0.05235988F;
        modelrenderer.rotateAngleZ = 0.02617994F;
        this.witchHat.addChild(modelrenderer);
        ModelRenderer modelrenderer1 = (new ModelRenderer(this)).setTextureSize(64, 128);
        modelrenderer1.setRotationPoint(1.75F, -4.0F, 2.0F);
        modelrenderer1.setTextureOffset(0, 87).addBox(0.0F, 0.0F, 0.0F, 4, 4, 4);
        modelrenderer1.rotateAngleX = -0.10471976F;
        modelrenderer1.rotateAngleZ = 0.05235988F;
        modelrenderer.addChild(modelrenderer1);
        ModelRenderer modelrenderer2 = (new ModelRenderer(this)).setTextureSize(64, 128);
        modelrenderer2.setRotationPoint(1.75F, -2.0F, 2.0F);
        modelrenderer2.setTextureOffset(0, 95).addBox(0.0F, 0.0F, 0.0F, 1, 2, 1, 0.25F);
        modelrenderer2.rotateAngleX = -0.20943952F;
        modelrenderer2.rotateAngleZ = 0.10471976F;
        modelrenderer1.addChild(modelrenderer2);
    }
    
    public void setRotationAngles(float p_78087_1_, float p_78087_2_, float p_78087_3_, float p_78087_4_, float p_78087_5_, float p_78087_6_, Entity entityIn) {
        super.setRotationAngles(p_78087_1_, p_78087_2_, p_78087_3_, p_78087_4_, p_78087_5_, p_78087_6_, entityIn);
        this.villagerNose.offsetX = this.villagerNose.offsetY = this.villagerNose.offsetZ = 0.0F;
        float f = 0.01F * (entityIn.getEntityId() % 10);
        this.villagerNose.rotateAngleX = MathHelper.sin(entityIn.ticksExisted * f) * 4.5F * 3.1415927F / 180.0F;
        this.villagerNose.rotateAngleY = 0.0F;
        this.villagerNose.rotateAngleZ = MathHelper.cos(entityIn.ticksExisted * f) * 2.5F * 3.1415927F / 180.0F;
        if (this.field_82900_g) {
            this.villagerNose.rotateAngleX = -0.9F;
            this.villagerNose.offsetZ = -0.09375F;
            this.villagerNose.offsetY = 0.1875F;
        } 
    }
}