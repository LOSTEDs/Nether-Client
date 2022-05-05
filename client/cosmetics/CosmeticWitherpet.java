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

package client.cosmetics;

import client.cosmetics.util.CosmeticBase;
import client.cosmetics.util.CosmeticModelBase;
import client.hud.impl.HudManager;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class CosmeticWitherpet extends CosmeticBase {
    private final CosmeticVilligerNose2 wingsModel;
    
    public CosmeticWitherpet(RenderPlayer renderPlayer) {
        super(renderPlayer);
        this.wingsModel = new CosmeticVilligerNose2(renderPlayer, 0.0F);
    }
    
    public void render(AbstractClientPlayer player, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
        GL11.glPushMatrix();
        if (player.isSneaking())
            GlStateManager.translate(0.0D, 0.2D, -0.05D); 
        Minecraft.getMinecraft().getTextureManager().bindTexture(new ResourceLocation("textures/entity/wither/wither.png"));
        GlStateManager.scale(0.4D, 0.4D, 0.4D);
        GlStateManager.translate(1.7D, -1.0D, 0.0D);
        float f1 = ageInTicks / 60.0F;
        float f2 = f1 * 3.1415927F * 1.0F;
        GlStateManager.translate(0.0F, -((float)(Math.sin((f2 + 2.0F)) + 0.5D)) * 0.08F, 0.0F);
        Minecraft.getMinecraft();
        if (player == Minecraft.thePlayer && HudManager.cosmeticWither.isEnabled()) {
            GlStateManager.color(255.0F, 255.0F, 255.0F);
            this.wingsModel.render((Entity)player, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
        } 
        GL11.glPopMatrix();
    }
    
    public class CosmeticVilligerNose2 extends CosmeticModelBase {
        private ModelRenderer[] field_82905_a;
        
        private ModelRenderer[] field_82904_b;
        
        public CosmeticVilligerNose2(RenderPlayer player, float p_i46302_1_) {
            super(player);
            this.textureWidth = 64;
            this.textureHeight = 64;
            this.field_82905_a = new ModelRenderer[3];
            this.field_82905_a[0] = new ModelRenderer((ModelBase)this, 0, 16);
            (new ModelRenderer((ModelBase)this, 0, 16)).addBox(-10.0F, 3.9F, -0.5F, 20, 3, 3, p_i46302_1_);
            this.field_82905_a[1] = (new ModelRenderer((ModelBase)this)).setTextureSize(this.textureWidth, this.textureHeight);
            (new ModelRenderer((ModelBase)this)).setTextureSize(this.textureWidth, this.textureHeight).setRotationPoint(-2.0F, 6.9F, -0.5F);
            this.field_82905_a[1].setTextureOffset(0, 22).addBox(0.0F, 0.0F, 0.0F, 3, 10, 3, p_i46302_1_);
            this.field_82905_a[1].setTextureOffset(24, 22).addBox(-4.0F, 1.5F, 0.5F, 11, 2, 2, p_i46302_1_);
            this.field_82905_a[1].setTextureOffset(24, 22).addBox(-4.0F, 4.0F, 0.5F, 11, 2, 2, p_i46302_1_);
            this.field_82905_a[1].setTextureOffset(24, 22).addBox(-4.0F, 6.5F, 0.5F, 11, 2, 2, p_i46302_1_);
            this.field_82905_a[2] = new ModelRenderer((ModelBase)this, 12, 22);
            (new ModelRenderer((ModelBase)this, 12, 22)).addBox(0.0F, 0.0F, 0.0F, 3, 6, 3, p_i46302_1_);
            this.field_82904_b = new ModelRenderer[3];
            this.field_82904_b[0] = new ModelRenderer((ModelBase)this, 0, 0);
            (new ModelRenderer((ModelBase)this, 0, 0)).addBox(-4.0F, -4.0F, -4.0F, 8, 8, 8, p_i46302_1_);
            this.field_82904_b[1] = new ModelRenderer((ModelBase)this, 32, 0);
            (new ModelRenderer((ModelBase)this, 32, 0)).addBox(-4.0F, -4.0F, -4.0F, 6, 6, 6, p_i46302_1_);
            (this.field_82904_b[1]).rotationPointX = -8.0F;
            (this.field_82904_b[1]).rotationPointY = 4.0F;
            this.field_82904_b[2] = new ModelRenderer((ModelBase)this, 32, 0);
            (new ModelRenderer((ModelBase)this, 32, 0)).addBox(-4.0F, -4.0F, -4.0F, 6, 6, 6, p_i46302_1_);
            (this.field_82904_b[2]).rotationPointX = 10.0F;
            (this.field_82904_b[2]).rotationPointY = 4.0F;
        }
        
        public void render(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float headYaw, float headPitch, float scale) {
            GlStateManager.pushMatrix();
            ModelRenderer[] field_82904_b;
            for (int length = (field_82904_b = this.field_82904_b).length, i = 0; i < length; i++) {
                ModelRenderer modelrenderer = field_82904_b[i];
                modelrenderer.render(scale);
            } 
            ModelRenderer[] field_82905_a;
            for (int length2 = (field_82905_a = this.field_82905_a).length, j = 0; j < length2; j++) {
                ModelRenderer modelrenderer2 = field_82905_a[j];
                modelrenderer2.render(scale);
            } 
            GlStateManager.popMatrix();
        }
    }
}
