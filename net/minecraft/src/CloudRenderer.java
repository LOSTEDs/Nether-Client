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

package net.minecraft.src;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GLAllocation;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraft.util.Vec3;
import org.lwjgl.opengl.GL11;

public class CloudRenderer {
    private Minecraft mc;
    
    private boolean updated = false;
    
    private boolean renderFancy = false;
    
    int cloudTickCounter;
    
    private Vec3 cloudColor;
    
    float partialTicks;
    
    private boolean updateRenderFancy = false;
    
    private int updateCloudTickCounter = 0;
    
    private Vec3 updateCloudColor = new Vec3(-1.0D, -1.0D, -1.0D);
    
    private double updatePlayerX = 0.0D;
    
    private double updatePlayerY = 0.0D;
    
    private double updatePlayerZ = 0.0D;
    
    private int glListClouds = -1;
    
    public CloudRenderer(Minecraft p_i29_1_) {
        this.mc = p_i29_1_;
        this.glListClouds = GLAllocation.generateDisplayLists(1);
    }
    
    public void prepareToRender(boolean p_prepareToRender_1_, int p_prepareToRender_2_, float p_prepareToRender_3_, Vec3 p_prepareToRender_4_) {
        this.renderFancy = p_prepareToRender_1_;
        this.cloudTickCounter = p_prepareToRender_2_;
        this.partialTicks = p_prepareToRender_3_;
        this.cloudColor = p_prepareToRender_4_;
    }
    
    public boolean shouldUpdateGlList() {
        if (!this.updated)
            return true; 
        if (this.renderFancy != this.updateRenderFancy)
            return true; 
        if (this.cloudTickCounter >= this.updateCloudTickCounter + 20)
            return true; 
        if (Math.abs(this.cloudColor.xCoord - this.updateCloudColor.xCoord) > 0.003D)
            return true; 
        if (Math.abs(this.cloudColor.yCoord - this.updateCloudColor.yCoord) > 0.003D)
            return true; 
        if (Math.abs(this.cloudColor.zCoord - this.updateCloudColor.zCoord) > 0.003D)
            return true; 
        Entity entity = this.mc.getRenderViewEntity();
        boolean flag = (this.updatePlayerY + entity.getEyeHeight() < 128.0D + (this.mc.gameSettings.ofCloudsHeight * 128.0F));
        boolean flag1 = (entity.prevPosY + entity.getEyeHeight() < 128.0D + (this.mc.gameSettings.ofCloudsHeight * 128.0F));
        return flag1 ^ flag;
    }
    
    public void startUpdateGlList() {
        GL11.glNewList(this.glListClouds, 4864);
    }
    
    public void endUpdateGlList() {
        GL11.glEndList();
        this.updateRenderFancy = this.renderFancy;
        this.updateCloudTickCounter = this.cloudTickCounter;
        this.updateCloudColor = this.cloudColor;
        this.updatePlayerX = (this.mc.getRenderViewEntity()).prevPosX;
        this.updatePlayerY = (this.mc.getRenderViewEntity()).prevPosY;
        this.updatePlayerZ = (this.mc.getRenderViewEntity()).prevPosZ;
        this.updated = true;
        GlStateManager.resetColor();
    }
    
    public void renderGlList() {
        Entity entity = this.mc.getRenderViewEntity();
        double d0 = entity.prevPosX + (entity.posX - entity.prevPosX) * this.partialTicks;
        double d1 = entity.prevPosY + (entity.posY - entity.prevPosY) * this.partialTicks;
        double d2 = entity.prevPosZ + (entity.posZ - entity.prevPosZ) * this.partialTicks;
        double d3 = ((this.cloudTickCounter - this.updateCloudTickCounter) + this.partialTicks);
        float f = (float)(d0 - this.updatePlayerX + d3 * 0.03D);
        float f1 = (float)(d1 - this.updatePlayerY);
        float f2 = (float)(d2 - this.updatePlayerZ);
        GlStateManager.pushMatrix();
        if (this.renderFancy) {
            GlStateManager.translate(-f / 12.0F, -f1, -f2 / 12.0F);
        } else {
            GlStateManager.translate(-f, -f1, -f2);
        } 
        GlStateManager.callList(this.glListClouds);
        GlStateManager.popMatrix();
        GlStateManager.resetColor();
    }
    
    public void reset() {
        this.updated = false;
    }
}
