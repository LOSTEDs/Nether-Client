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

package client.gui;

import client.Client;
import java.awt.Color;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class DiscordButton extends GuiButton {
    private int j6;
    
    private int j10;
    
    int fade;
    
    int fade2;
    
    public DiscordButton(int i, int j, int k, String s) {
        this(i, j, k, 21, 21, s);
    }
    
    public DiscordButton(int i, int j, int k, int l, int i1, String s) {
        super(i, j, k, l, i1, s);
        this.enabled = true;
        this.visible = true;
    }
    
    protected int getHoverState(boolean flag) {
        byte byte0 = 1;
        if (!this.enabled) {
            byte0 = 0;
        } else if (flag) {
            byte0 = 2;
        } 
        return byte0;
    }
    
    public void drawButton(Minecraft mc, int mouseX, int mouseY) {
        this.hovered = (mouseX >= this.xPosition && mouseY >= this.yPosition && mouseX < this.xPosition + this.width && mouseY < this.yPosition + this.height);
        int j = -1;
        if (this.hovered)
            j = Client.ButtonString; 
        if (!this.hovered) {
            this.fade = 230;
        } else {
            if (this.fade <= 50)
                return; 
            if (this.fade != 160)
                this.fade -= 10; 
        } 
        if (!this.hovered) {
            this.fade2 = 200;
        } else {
            if (this.fade2 <= 30)
                return; 
            if (this.fade2 != 200)
                this.fade2 += 10; 
        } 
        float b = (this.hovered ? (new Color(30, 0, 0, 100)).getRGB() : (new Color(30, 0, 0, 30)).getRGB());
        Color a = new Color(10, 10, 10, this.fade);
        Color a2 = Client.ButtonOutline;
        Color a3 = new Color(10, 10, 10, this.fade);
        if (this.xPosition >= this.xPosition && this.yPosition >= this.yPosition && this.xPosition < this.xPosition + this.width && this.yPosition < this.yPosition + this.height) {
            int n = 5;
            FontRenderer fr = mc.fontRendererObj;
            this.hovered = (mouseX >= this.xPosition && mouseY >= this.yPosition && mouseX < this.xPosition + this.width && mouseY < this.yPosition + this.height);
            drawRoundedRect(this.xPosition - 1, this.yPosition - 1, this.width + 2, this.height + 2, 3, a2);
            drawRoundedRect(this.xPosition, this.yPosition, this.width, this.height, 3, a3);
            drawRoundedRect(this.xPosition, this.yPosition, this.width, this.height, 3, a);
            if (this.hovered) {
                GlStateManager.color(1.0F, 0.0F, 0.0F);
            } else {
                GlStateManager.color(1.0F, 1.0F, 1.0F);
            } 
            Minecraft.getMinecraft().getTextureManager().bindTexture(new ResourceLocation("client/discord.png"));
            Gui.drawModalRectWithCustomSizedTexture(this.xPosition + 3, this.yPosition + 3, 0.0F, 0.0F, 15, 15, 15.0F, 15.0F);
            drawCenteredString(fr, this.displayString, this.xPosition + this.width / 2, this.yPosition + (this.height - 8) / 2, j);
        } 
    }
    
    private void drawRoundedRect(int x, int y, int width, int height, int cornerRadius, Color color) {
        Gui.drawRect(x, y + cornerRadius, x + cornerRadius, y + height - cornerRadius, color.getRGB());
        Gui.drawRect(x + cornerRadius, y, x + width - cornerRadius, y + height, color.getRGB());
        Gui.drawRect(x + width - cornerRadius, y + cornerRadius, x + width, y + height - cornerRadius, color.getRGB());
        drawArc(x + cornerRadius, y + cornerRadius, cornerRadius, 0, 90, color);
        drawArc(x + width - cornerRadius, y + cornerRadius, cornerRadius, 270, 360, color);
        drawArc(x + width - cornerRadius, y + height - cornerRadius, cornerRadius, 180, 270, color);
        drawArc(x + cornerRadius, y + height - cornerRadius, cornerRadius, 90, 180, color);
    }
    
    private void drawArc(int x, int y, int radius, int startAngle, int endAngle, Color color) {
        GL11.glPushMatrix();
        GL11.glEnable(3042);
        GL11.glDisable(3553);
        GL11.glBlendFunc(770, 771);
        GL11.glColor4f(color.getRed() / 255.0F, color.getGreen() / 255.0F, color.getBlue() / 255.0F, color.getAlpha() / 255.0F);
        WorldRenderer worldRenderer = Tessellator.getInstance().getWorldRenderer();
        worldRenderer.begin(6, DefaultVertexFormats.POSITION);
        worldRenderer.pos(x, y, 0.0D).endVertex();
        for (int i = (int)(startAngle / 360.0D * 100.0D); i <= (int)(endAngle / 360.0D * 100.0D); i++) {
            double angle = 6.283185307179586D * i / 100.0D + Math.toRadians(180.0D);
            worldRenderer.pos(x + Math.sin(angle) * radius, y + Math.cos(angle) * radius, 0.0D).endVertex();
        } 
        Tessellator.getInstance().draw();
        GL11.glEnable(3553);
        GL11.glDisable(3042);
        GL11.glPopMatrix();
    }
    
    private void drawCircle(int x, int y, int width, int height, Color color) {
        drawArc(x + width, y + height / 2, width / 2, 0, 360, color);
    }
}
