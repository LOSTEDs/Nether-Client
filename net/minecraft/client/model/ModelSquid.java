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

public class ModelSquid extends ModelBase {
    ModelRenderer squidBody;
    
    ModelRenderer[] squidTentacles = new ModelRenderer[8];
    
    public ModelSquid() {
        int i = -16;
        this.squidBody = new ModelRenderer(this, 0, 0);
        this.squidBody.addBox(-6.0F, -8.0F, -6.0F, 12, 16, 12);
        this.squidBody.rotationPointY += (24 + i);
        for (int j = 0; j < this.squidTentacles.length; j++) {
            this.squidTentacles[j] = new ModelRenderer(this, 48, 0);
            double d0 = j * Math.PI * 2.0D / this.squidTentacles.length;
            float f = (float)Math.cos(d0) * 5.0F;
            float f1 = (float)Math.sin(d0) * 5.0F;
            this.squidTentacles[j].addBox(-1.0F, 0.0F, -1.0F, 2, 18, 2);
            (this.squidTentacles[j]).rotationPointX = f;
            (this.squidTentacles[j]).rotationPointZ = f1;
            (this.squidTentacles[j]).rotationPointY = (31 + i);
            d0 = j * Math.PI * -2.0D / this.squidTentacles.length + 1.5707963267948966D;
            (this.squidTentacles[j]).rotateAngleY = (float)d0;
        } 
    }
    
    public void setRotationAngles(float p_78087_1_, float p_78087_2_, float p_78087_3_, float p_78087_4_, float p_78087_5_, float p_78087_6_, Entity entityIn) {
        byte b;
        int i;
        ModelRenderer[] arrayOfModelRenderer;
        for (i = (arrayOfModelRenderer = this.squidTentacles).length, b = 0; b < i; ) {
            ModelRenderer modelrenderer = arrayOfModelRenderer[b];
            modelrenderer.rotateAngleX = p_78087_3_;
            b++;
        } 
    }
    
    public void render(Entity entityIn, float p_78088_2_, float p_78088_3_, float p_78088_4_, float p_78088_5_, float p_78088_6_, float scale) {
        setRotationAngles(p_78088_2_, p_78088_3_, p_78088_4_, p_78088_5_, p_78088_6_, scale, entityIn);
        this.squidBody.render(scale);
        for (int i = 0; i < this.squidTentacles.length; i++)
            this.squidTentacles[i].render(scale); 
    }
}