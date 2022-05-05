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

package client.gui.clickgui.comp;

import client.hud.impl.HudMod;
import java.awt.Color;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;

public class ModButton {
    public int x;
    
    public int y;
    
    public int w;
    
    public int h;
    
    public HudMod m;
    
    public ModButton(int x, int y, int w, int h, HudMod m) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.m = m;
    }
    
    public void draw() {
        Gui.drawRect(this.x, this.y, this.x + this.w, this.y + this.h, (new Color(0, 0, 0, 100)).getRGB());
        drawHollowRect(this.x, this.y, this.w, this.h, (new Color(0, 0, 0)).getRGB());
        (Minecraft.getMinecraft()).fontRendererObj.drawStringWithShadow(this.m.name, (this.x + 2), (this.y + 2), getColor());
    }
    
    private int getColor() {
        if (this.m.isEnabled())
            return (new Color(85, 255, 85)).getRGB(); 
        return -1;
    }
    
    public void onClieck(int mouseX, int mouseY, int button) {
        if (mouseX >= this.x && mouseX <= this.x + this.w && mouseY >= this.y && mouseY <= this.y + this.h)
            if (this.m.isEnabled()) {
                this.m.setEnabled(false);
                System.out.println(this.m.name);
            } else {
                this.m.setEnabled(true);
                System.out.println(this.m.name);
            }  
    }
    
    public void drawHollowRect(int x, int y, int w, int h, int color) {
        drawHorizontalLine(x, x + w, y, color);
        drawHorizontalLine(x, x + w, y + h, color);
        drawVerticalLine(x, y + h, y, color);
        drawVerticalLine(x + w, y + h, y, color);
    }
    
    protected void drawHorizontalLine(int startX, int endX, int y, int color) {
        if (endX < startX) {
            int i = startX;
            startX = endX;
            endX = i;
        } 
        drawRect(startX, y, endX + 1, y + 1, color);
    }
    
    protected void drawVerticalLine(int x, int startY, int endY, int color) {
        if (endY < startY) {
            int i = startY;
            startY = endY;
            endY = i;
        } 
        drawRect(x, startY + 1, x + 1, endY, color);
    }
    
    public static void drawRect(int left, int top, int right, int bottom, int color) {
        if (left < right) {
            int i = left;
            left = right;
            right = i;
        } 
        if (top < bottom) {
            int j = top;
            top = bottom;
            bottom = j;
        } 
        float f3 = (color >> 24 & 0xFF) / 255.0F;
        float f = (color >> 16 & 0xFF) / 255.0F;
        float f1 = (color >> 8 & 0xFF) / 255.0F;
        float f2 = (color & 0xFF) / 255.0F;
        Tessellator tessellator = Tessellator.getInstance();
        WorldRenderer worldrenderer = tessellator.getWorldRenderer();
        GlStateManager.enableBlend();
        GlStateManager.disableTexture2D();
        GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
        GlStateManager.color(f, f1, f2, f3);
        worldrenderer.begin(7, DefaultVertexFormats.POSITION);
        worldrenderer.pos(left, bottom, 0.0D).endVertex();
        worldrenderer.pos(right, bottom, 0.0D).endVertex();
        worldrenderer.pos(right, top, 0.0D).endVertex();
        worldrenderer.pos(left, top, 0.0D).endVertex();
        tessellator.draw();
        GlStateManager.enableTexture2D();
        GlStateManager.disableBlend();
    }
}
