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

import client.hud.impl.HudManager;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EnumPlayerModelParts;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;

public class CosmeticCape implements LayerRenderer<AbstractClientPlayer> {
    private final RenderPlayer playerRenderer;
    
    private static final String __08FID = "CL_00002425";
    
    public CosmeticCape(RenderPlayer playerRendererIn) {
        this.playerRenderer = playerRendererIn;
    }
    
    public void doRenderLayer(AbstractClientPlayer entitylivingbaseIn, float p_177141_2_, float p_177141_3_, float partialTicks, float p_177141_5_, float p_177141_6_, float p_177141_7_, float scale) {
        if (HudManager.cosmeticCape.isEnabled() && entitylivingbaseIn.hasPlayerInfo() && !entitylivingbaseIn.isInvisible() && entitylivingbaseIn.isWearing(EnumPlayerModelParts.CAPE) && entitylivingbaseIn.getName().equals(Minecraft.getMinecraft().getSession().getUsername())) {
            GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
            this.playerRenderer.bindTexture(new ResourceLocation("client/cape.png"));
            GlStateManager.pushMatrix();
            GlStateManager.translate(0.0F, 0.0F, 0.125F);
            double d0 = entitylivingbaseIn.prevChasingPosX + (entitylivingbaseIn.chasingPosX - entitylivingbaseIn.prevChasingPosX) * partialTicks - entitylivingbaseIn.prevPosX + (entitylivingbaseIn.posX - entitylivingbaseIn.prevPosX) * partialTicks;
            double d2 = entitylivingbaseIn.prevChasingPosY + (entitylivingbaseIn.chasingPosY - entitylivingbaseIn.prevChasingPosY) * partialTicks - entitylivingbaseIn.prevPosY + (entitylivingbaseIn.posY - entitylivingbaseIn.prevPosY) * partialTicks;
            double d3 = entitylivingbaseIn.prevChasingPosZ + (entitylivingbaseIn.chasingPosZ - entitylivingbaseIn.prevChasingPosZ) * partialTicks - entitylivingbaseIn.prevPosZ + (entitylivingbaseIn.posZ - entitylivingbaseIn.prevPosZ) * partialTicks;
            float f = entitylivingbaseIn.prevRenderYawOffset + (entitylivingbaseIn.renderYawOffset - entitylivingbaseIn.prevRenderYawOffset) * partialTicks;
            double d4 = MathHelper.sin(f * 3.1415927F / 180.0F);
            double d5 = -MathHelper.cos(f * 3.1415927F / 180.0F);
            float f2 = (float)d2 * 10.0F;
            f2 = MathHelper.clamp_float(f2, -6.0F, 32.0F);
            float f3 = (float)(d0 * d4 + d3 * d5) * 100.0F;
            float f4 = (float)(d0 * d5 - d3 * d4) * 100.0F;
            if (f3 < 0.0F)
                f3 = 0.0F; 
            float f5 = entitylivingbaseIn.prevCameraYaw + (entitylivingbaseIn.cameraYaw - entitylivingbaseIn.prevCameraYaw) * partialTicks;
            f2 += MathHelper.sin((entitylivingbaseIn.prevDistanceWalkedModified + (entitylivingbaseIn.distanceWalkedModified - entitylivingbaseIn.prevDistanceWalkedModified) * partialTicks) * 6.0F) * 32.0F * f5;
            if (entitylivingbaseIn.isSneaking()) {
                f2 += 25.0F;
                GlStateManager.translate(0.0F, 0.142F, -0.0178F);
            } 
            GlStateManager.rotate(6.0F + f3 / 2.0F + f2, 1.0F, 0.0F, 0.0F);
            GlStateManager.rotate(f4 / 2.0F, 0.0F, 0.0F, 1.0F);
            GlStateManager.rotate(-f4 / 2.0F, 0.0F, 1.0F, 0.0F);
            GlStateManager.rotate(180.0F, 0.0F, 1.0F, 0.0F);
            this.playerRenderer.getMainModel().renderCape(0.0625F);
            GlStateManager.popMatrix();
        } 
    }
    
    public boolean shouldCombineTextures() {
        return false;
    }
}
