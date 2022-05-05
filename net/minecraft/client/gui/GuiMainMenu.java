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

package net.minecraft.client.gui;

import client.Client;
import client.gui.ClassicButton;
import client.gui.DiscordButton;
import client.utils.Particles;
import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;

public class GuiMainMenu extends GuiScreen implements GuiYesNoCallback {
    private Particles particles;
    
    public void initGui() {
        this.particles = new Particles(this.width, this.height);
        this.buttonList.add(new ClassicButton(1, this.width / 2 - 100, this.height / 2 - 20, 200, 20, I18n.format("SinglePlayer", new Object[0])));
        this.buttonList.add(new ClassicButton(2, this.width / 2 - 100, this.height / 2 + 5, 200, 20, I18n.format("MultiPlayer", new Object[0])));
        this.buttonList.add(new ClassicButton(3, this.width / 2 - 100, this.height / 2 + 35, 98, 20, I18n.format("Options...", new Object[0])));
        this.buttonList.add(new ClassicButton(4, this.width / 2 + 2, this.height / 2 + 35, 98, 20, I18n.format("Resource Packs", new Object[0])));
        this.buttonList.add(new ClassicButton(5, this.width - 23, 6, 20, 20, I18n.format("X", new Object[0])));
        this.buttonList.add(new DiscordButton(6, this.width / 2 - 125, this.height / 2 + 35, 20, 20, I18n.format("", new Object[0])));
    }
    
    protected void actionPerformed(GuiButton button) throws IOException {
        switch (button.id) {
            case 1:
                this.mc.displayGuiScreen(new GuiSelectWorld(this));
                break;
            case 2:
                this.mc.displayGuiScreen(new GuiMultiplayer(this));
                break;
            case 3:
                this.mc.displayGuiScreen(new GuiOptions(this, this.mc.gameSettings));
                break;
            case 4:
                this.mc.gameSettings.saveOptions();
                this.mc.displayGuiScreen(new GuiScreenResourcePacks(this));
                break;
            case 5:
                this.mc.shutdown();
                break;
            case 6:
                try {
                    Desktop desktop = Desktop.getDesktop();
                    URI oURL = new URI("https://discord.com/invite/SuchSpeed");
                    desktop.browse(oURL);
                } catch (Exception e) {
                    e.printStackTrace();
                } 
                break;
        } 
        super.actionPerformed(button);
    }
    
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        GlStateManager.disableAlpha();
        GlStateManager.enableAlpha();
        Minecraft mc = Minecraft.getMinecraft();
        ScaledResolution sr = new ScaledResolution(mc);
        this.mc.getTextureManager().bindTexture(new ResourceLocation(Client.Background));
        Gui.drawModalRectWithCustomSizedTexture(-21 + Mouse.getX() / 90, Mouse.getY() * -1 / 90, 0.0F, 0.0F, this.width + 20, this.height + 20, (this.width + 21), (this.height + 20));
        this.particles.drawParticles();
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.getTextureManager().bindTexture(new ResourceLocation("client/title.png"));
        Gui.drawScaledCustomSizeModalRect(0, 0, 0.0F, 0.0F, sr.getScaledWidth(), sr.getScaledHeight(), sr.getScaledWidth(), sr.getScaledHeight(), sr.getScaledWidth(), sr.getScaledHeight());
        GlStateManager.pushMatrix();
        GlStateManager.scale(4.0F, 4.0F, 0.0F);
        GlStateManager.popMatrix();
        super.drawScreen(mouseX, mouseY, partialTicks);
    }
}
