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

import java.awt.Rectangle;
import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;

public class TooltipManager {
    private GuiScreen guiScreen;
    
    private TooltipProvider tooltipProvider;
    
    private int lastMouseX = 0;
    
    private int lastMouseY = 0;
    
    private long mouseStillTime = 0L;
    
    public TooltipManager(GuiScreen p_i112_1_, TooltipProvider p_i112_2_) {
        this.guiScreen = p_i112_1_;
        this.tooltipProvider = p_i112_2_;
    }
    
    public void drawTooltips(int p_drawTooltips_1_, int p_drawTooltips_2_, List<GuiButton> p_drawTooltips_3_) {
        if (Math.abs(p_drawTooltips_1_ - this.lastMouseX) <= 5 && Math.abs(p_drawTooltips_2_ - this.lastMouseY) <= 5) {
            int i = 700;
            if (System.currentTimeMillis() >= this.mouseStillTime + i) {
                GuiButton guibutton = GuiScreenOF.getSelectedButton(p_drawTooltips_1_, p_drawTooltips_2_, p_drawTooltips_3_);
                if (guibutton != null) {
                    Rectangle rectangle = this.tooltipProvider.getTooltipBounds(this.guiScreen, p_drawTooltips_1_, p_drawTooltips_2_);
                    String[] astring = this.tooltipProvider.getTooltipLines(guibutton, rectangle.width);
                    if (astring != null) {
                        if (this.tooltipProvider.isRenderBorder()) {
                            int j = -528449408;
                            drawRectBorder(rectangle.x, rectangle.y, rectangle.x + rectangle.width, rectangle.y + rectangle.height, j);
                        } 
                        Gui.drawRect(rectangle.x, rectangle.y, rectangle.x + rectangle.width, rectangle.y + rectangle.height, -536870912);
                        for (int l = 0; l < astring.length; l++) {
                            String s = astring[l];
                            int k = 14540253;
                            if (s.endsWith("!"))
                                k = 16719904; 
                            FontRenderer fontrenderer = (Minecraft.getMinecraft()).fontRendererObj;
                            fontrenderer.drawStringWithShadow(s, (rectangle.x + 5), (rectangle.y + 5 + l * 11), k);
                        } 
                    } 
                } 
            } 
        } else {
            this.lastMouseX = p_drawTooltips_1_;
            this.lastMouseY = p_drawTooltips_2_;
            this.mouseStillTime = System.currentTimeMillis();
        } 
    }
    
    private void drawRectBorder(int p_drawRectBorder_1_, int p_drawRectBorder_2_, int p_drawRectBorder_3_, int p_drawRectBorder_4_, int p_drawRectBorder_5_) {
        Gui.drawRect(p_drawRectBorder_1_, p_drawRectBorder_2_ - 1, p_drawRectBorder_3_, p_drawRectBorder_2_, p_drawRectBorder_5_);
        Gui.drawRect(p_drawRectBorder_1_, p_drawRectBorder_4_, p_drawRectBorder_3_, p_drawRectBorder_4_ + 1, p_drawRectBorder_5_);
        Gui.drawRect(p_drawRectBorder_1_ - 1, p_drawRectBorder_2_, p_drawRectBorder_1_, p_drawRectBorder_4_, p_drawRectBorder_5_);
        Gui.drawRect(p_drawRectBorder_3_, p_drawRectBorder_2_, p_drawRectBorder_3_ + 1, p_drawRectBorder_4_, p_drawRectBorder_5_);
    }
}
