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

package client.gui.clickgui;

import client.gui.ClassicButton;
import client.gui.GuiServerSwitch;
import client.gui.clickgui.comp.ModButton;
import client.hud.HUDConfigScreen;
import client.hud.impl.HudManager;
import client.hud.impl.HudMod;
import java.awt.Color;
import java.io.IOException;
import java.util.ArrayList;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.server.MinecraftServer;
import org.lwjgl.opengl.GL11;

public class ClickGUI extends GuiScreen {
    ArrayList<ModButton> modButtons = new ArrayList<>();
    
    public void initGui() {
        super.initGui();
        this.modButtons.add(new ModButton(this.width / 2 + 25, this.height / 2 - 125, 58, this.fontRendererObj.FONT_HEIGHT + 4, (HudMod)HudManager.clientName));
        this.modButtons.add(new ModButton(this.width / 2 + 105, this.height / 2 - 125, 62, this.fontRendererObj.FONT_HEIGHT + 4, (HudMod)HudManager.timeMod));
        this.modButtons.add(new ModButton(this.width / 2 + 25, this.height / 2 - 100, 60, this.fontRendererObj.FONT_HEIGHT + 4, (HudMod)HudManager.fpsMod));
        this.modButtons.add(new ModButton(this.width / 2 + 105, this.height / 2 - 100, 60, this.fontRendererObj.FONT_HEIGHT + 4, (HudMod)HudManager.cpsMod));
        this.modButtons.add(new ModButton(this.width / 2 + 25, this.height / 2 - 75, 60, this.fontRendererObj.FONT_HEIGHT + 4, (HudMod)HudManager.xyzMod));
        this.modButtons.add(new ModButton(this.width / 2 + 105, this.height / 2 - 75, 67, this.fontRendererObj.FONT_HEIGHT + 4, (HudMod)HudManager.biomeMod));
        this.modButtons.add(new ModButton(this.width / 2 + 25, this.height / 2 - 50, 60, this.fontRendererObj.FONT_HEIGHT + 4, (HudMod)HudManager.targetHUD));
        this.modButtons.add(new ModButton(this.width / 2 + 105, this.height / 2 - 50, 68, this.fontRendererObj.FONT_HEIGHT + 4, (HudMod)HudManager.directionMod));
        this.modButtons.add(new ModButton(this.width / 2 + 25, this.height / 2 - 25, 65, this.fontRendererObj.FONT_HEIGHT + 4, (HudMod)HudManager.armorStatus));
        this.modButtons.add(new ModButton(this.width / 2 + 105, this.height / 2 - 25, 70, this.fontRendererObj.FONT_HEIGHT + 4, (HudMod)HudManager.potionsStatus));
        this.modButtons.add(new ModButton(this.width / 2 + 25, this.height / 2, 60, this.fontRendererObj.FONT_HEIGHT + 4, (HudMod)HudManager.keystrokesMod));
        this.modButtons.add(new ModButton(this.width / 2 + 105, this.height / 2, 62, this.fontRendererObj.FONT_HEIGHT + 4, (HudMod)HudManager.blockOutline));
        this.modButtons.add(new ModButton(this.width / 2 + 25, this.height / 2 + 25, 66, this.fontRendererObj.FONT_HEIGHT + 4, (HudMod)HudManager.toggleSprint));
        this.modButtons.add(new ModButton(this.width / 2 + 105, this.height / 2 + 25, 60, this.fontRendererObj.FONT_HEIGHT + 4, (HudMod)HudManager.buttonStyle));
        this.modButtons.add(new ModButton(this.width / 2 + 25, this.height / 2 + 50, 63, this.fontRendererObj.FONT_HEIGHT + 4, (HudMod)HudManager.perspectiveMod));
        this.modButtons.add(new ModButton(this.width / 2 + 105, this.height / 2 + 50, 60, this.fontRendererObj.FONT_HEIGHT + 4, (HudMod)HudManager.scrollZoom));
        this.modButtons.add(new ModButton(this.width / 2 + 25, this.height / 2 + 75, 68, this.fontRendererObj.FONT_HEIGHT + 4, (HudMod)HudManager.memoryUsage));
        this.modButtons.add(new ModButton(this.width / 2 + 105, this.height / 2 + 75, 70, this.fontRendererObj.FONT_HEIGHT + 4, (HudMod)HudManager.serverIPMod));
        this.buttonList.add(new ClassicButton(10, this.width / 2 + 120, this.height / 2 + 100, 20, 20, I18n.format(">>", new Object[0])));
        this.buttonList.add(new ClassicButton(11, this.width / 2 + 60, this.height / 2 + 100, 20, 20, I18n.format("<<", new Object[0])));
        this.buttonList.add(new ClassicButton(1, this.width / 2 - 170, this.height / 2 + 70, 150, 20, I18n.format("Cosmetics Menu", new Object[0])));
        this.buttonList.add(new ClassicButton(2, this.width / 2 - 170, this.height / 2 + 100, 72, 20, I18n.format("Server List", new Object[0])));
        this.buttonList.add(new ClassicButton(3, this.width / 2 - 92, this.height / 2 + 100, 72, 20, I18n.format("Mods Config", new Object[0])));
    }
    
    protected void actionPerformed(GuiButton button) throws IOException {
        switch (button.id) {
            case 1:
                this.mc.displayGuiScreen(new CosmeticsMenu());
                break;
            case 2:
                this.mc.displayGuiScreen((GuiScreen)new GuiServerSwitch(this));
                break;
            case 3:
                this.mc.displayGuiScreen((GuiScreen)new HUDConfigScreen());
                break;
            case 10:
                this.mc.displayGuiScreen(new ClickGUI_2());
                break;
            case 11:
                this.mc.displayGuiScreen(new ClickGUI());
                break;
        } 
        super.actionPerformed(button);
    }
    
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        GlStateManager.enableAlpha();
        GlStateManager.enableBlend();
        Playerdraw(this.width / 2 - 100, this.height / 2 + 30, 25, 50.0F, 0.0F, (EntityLivingBase)Minecraft.thePlayer);
        Gui.drawRect(this.width / 2 + 200, this.height / 2 + 150, this.width / 2 - 200, this.height / 2 - 150, (new Color(25, 25, 25, 255)).getRGB());
        Gui.drawRect(this.width / 2 + 195, this.height / 2 + 145, this.width / 2 - 195, this.height / 2 - 145, (new Color(50, 50, 50, 255)).getRGB());
        Gui.drawRect(this.width / 2 + 190, this.height / 2 + 140, this.width / 2, this.height / 2 - 140, (new Color(35, 35, 35, 255)).getRGB());
        drawCenteredString(this.fontRendererObj, I18n.format("Mods Settings", new Object[0]), this.width / 2 - 100, this.height / 2 - 135, -1);
        super.drawScreen(mouseX, mouseY, partialTicks);
        for (ModButton m : this.modButtons)
            m.draw(); 
    }
    
    public static void Playerdraw(int posX, int posY, int scale, float mouseX, float mouseY, EntityLivingBase ent) {
        if (MinecraftServer.getServer().isSinglePlayer()) {
            GlStateManager.enableColorMaterial();
            GlStateManager.pushMatrix();
            GlStateManager.translate(posX, posY, 50.0F);
            GlStateManager.scale(-65.0F, 65.0F, 65.0F);
            GlStateManager.rotate(180.0F, 0.0F, 0.0F, 1.0F);
            float f = ent.renderYawOffset;
            float f2 = ent.rotationYaw;
            float f3 = ent.rotationPitch;
            float f4 = ent.prevRotationYawHead;
            float f5 = ent.rotationYawHead;
            GlStateManager.rotate(155.0F, 0.0F, 1.0F, 0.0F);
            RenderHelper.enableStandardItemLighting();
            GlStateManager.rotate(-135.0F, 0.0F, 1.0F, 0.0F);
            GlStateManager.rotate(-((float)Math.atan((mouseY / 40.0F))) * 20.0F, 1.0F, 0.0F, 0.0F);
            ent.renderYawOffset = (float)Math.atan((mouseX / 40.0F)) * 20.0F;
            ent.rotationYaw = (float)Math.atan((mouseX / 40.0F)) * 40.0F;
            ent.rotationPitch = -((float)Math.atan((mouseY / 40.0F))) * 20.0F;
            ent.rotationYawHead = ent.rotationYaw;
            ent.prevRotationYawHead = ent.rotationYaw;
            GlStateManager.translate(0.0F, 0.0F, 0.0F);
            RenderManager rendermanager = Minecraft.getMinecraft().getRenderManager();
            rendermanager.setPlayerViewY(180.0F);
            rendermanager.setRenderShadow(false);
            rendermanager.renderEntityWithPosYaw((Entity)ent, 0.0D, 0.0D, 0.0D, 0.0F, 1.0F);
            rendermanager.setRenderShadow(true);
            ent.renderYawOffset = f;
            ent.rotationYaw = f2;
            ent.rotationPitch = f3;
            ent.prevRotationYawHead = f4;
            ent.rotationYawHead = f5;
            GlStateManager.popMatrix();
            RenderHelper.disableStandardItemLighting();
            GlStateManager.disableRescaleNormal();
            GlStateManager.setActiveTexture(OpenGlHelper.lightmapTexUnit);
            GlStateManager.disableTexture2D();
            GlStateManager.setActiveTexture(OpenGlHelper.defaultTexUnit);
        } else if (Minecraft.getMinecraft().getCurrentServerData() != null) {
            GlStateManager.enableColorMaterial();
            GlStateManager.pushMatrix();
            GlStateManager.translate(posX, posY, 50.0F);
            GlStateManager.scale(-65.0F, 65.0F, 65.0F);
            GlStateManager.rotate(180.0F, 0.0F, 0.0F, 1.0F);
            float f = ent.renderYawOffset;
            float f2 = ent.rotationYaw;
            float f3 = ent.rotationPitch;
            float f4 = ent.prevRotationYawHead;
            float f5 = ent.rotationYawHead;
            GlStateManager.rotate(155.0F, 0.0F, 1.0F, 0.0F);
            RenderHelper.enableStandardItemLighting();
            GlStateManager.rotate(-135.0F, 0.0F, 1.0F, 0.0F);
            GlStateManager.rotate(-((float)Math.atan((mouseY / 40.0F))) * 20.0F, 1.0F, 0.0F, 0.0F);
            ent.renderYawOffset = (float)Math.atan((mouseX / 40.0F)) * 20.0F;
            ent.rotationYaw = (float)Math.atan((mouseX / 40.0F)) * 40.0F;
            ent.rotationPitch = -((float)Math.atan((mouseY / 40.0F))) * 20.0F;
            ent.rotationYawHead = ent.rotationYaw;
            ent.prevRotationYawHead = ent.rotationYaw;
            GlStateManager.translate(0.0F, 0.0F, 0.0F);
            RenderManager rendermanager = Minecraft.getMinecraft().getRenderManager();
            rendermanager.setPlayerViewY(180.0F);
            rendermanager.setRenderShadow(false);
            rendermanager.renderEntityWithPosYaw((Entity)ent, 0.0D, 0.0D, 0.0D, 0.0F, 1.0F);
            rendermanager.setRenderShadow(true);
            ent.renderYawOffset = f;
            ent.rotationYaw = f2;
            ent.rotationPitch = f3;
            ent.prevRotationYawHead = f4;
            ent.rotationYawHead = f5;
            GlStateManager.popMatrix();
            RenderHelper.disableStandardItemLighting();
            GlStateManager.disableRescaleNormal();
            GlStateManager.setActiveTexture(OpenGlHelper.lightmapTexUnit);
            GlStateManager.disableTexture2D();
            GlStateManager.setActiveTexture(OpenGlHelper.defaultTexUnit);
        } 
    }
    
    protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
        super.mouseClicked(mouseX, mouseY, mouseButton);
        for (ModButton m : this.modButtons)
            m.onClieck(mouseX, mouseY, mouseButton); 
    }
}
