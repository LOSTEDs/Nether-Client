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
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.shader.Framebuffer;
import net.minecraft.util.ResourceLocation;

public class SplashProgress {
    private static final int MAX = 9;
    
    private static int PROGRESS = 0;
    
    private static String CURRENT = "";
    
    private static ResourceLocation splash;
    
    private static UnicodeFontRenderer ufr;
    
    public static void update() {
        if (Minecraft.getMinecraft() == null || Minecraft.getMinecraft().getLanguageManager() == null)
            return; 
        drawSplash(Minecraft.getMinecraft().getTextureManager());
    }
    
    public static void setProgress(int givenProgress, String givenTEXT) {
        PROGRESS = givenProgress;
        CURRENT = givenTEXT;
        update();
    }
    
    public static void drawSplash(TextureManager tm) {
        ScaledResolution scaledResolution = new ScaledResolution(Minecraft.getMinecraft());
        int scaleFactor = scaledResolution.getScaleFactor();
        Framebuffer framebuffer = new Framebuffer(scaledResolution.getScaledWidth() * scaleFactor, scaledResolution.getScaledHeight() * scaleFactor, true);
        framebuffer.bindFramebuffer(false);
        GlStateManager.matrixMode(5889);
        GlStateManager.loadIdentity();
        GlStateManager.ortho(0.0D, scaledResolution.getScaledWidth(), scaledResolution.getScaledHeight(), 0.0D, 1000.0D, 3000.0D);
        GlStateManager.matrixMode(5888);
        GlStateManager.loadIdentity();
        GlStateManager.translate(0.0F, 0.0F, -2000.0F);
        GlStateManager.disableLighting();
        GlStateManager.disableFog();
        GlStateManager.disableDepth();
        GlStateManager.enableTexture2D();
        if (splash == null)
            splash = new ResourceLocation(Client.SplashScreen); 
        tm.bindTexture(splash);
        GlStateManager.resetColor();
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        Gui.drawScaledCustomSizeModalRect(0, 0, 0.0F, 0.0F, 1920, 1080, scaledResolution.getScaledWidth(), scaledResolution.getScaledHeight(), 1920.0F, 1080.0F);
        drawProgress();
        framebuffer.unbindFramebuffer();
        framebuffer.framebufferRender(scaledResolution.getScaledWidth() * scaleFactor, scaledResolution.getScaledHeight() * scaleFactor);
        GlStateManager.enableAlpha();
        GlStateManager.alphaFunc(516, 0.1F);
        Minecraft.getMinecraft().updateDisplay();
    }
    
    private static void drawProgress() {
        if ((Minecraft.getMinecraft()).gameSettings == null || Minecraft.getMinecraft().getTextureManager() == null)
            return; 
        if (ufr == null)
            ufr = UnicodeFontRenderer.getFontOnPC("Arial", 20); 
        ScaledResolution sr = new ScaledResolution(Minecraft.getMinecraft());
        double nProgress = PROGRESS;
        double calc = nProgress / 9.0D * sr.getScaledWidth();
        Gui.drawRect(0, sr.getScaledHeight() - 35, sr.getScaledWidth(), sr.getScaledHeight(), (new Color(0, 0, 0, 120)).getRGB());
        GlStateManager.resetColor();
        resetTexturestate();
        ufr.drawString(CURRENT, 20.0F, (sr.getScaledHeight() - 25), -1);
        String step = String.valueOf(String.valueOf(PROGRESS)) + "/" + '\t';
        ufr.drawString(step, (sr.getScaledWidth() - 20 - ufr.getStringWidth(step)), (sr.getScaledHeight() - 25), -505290241);
        GlStateManager.resetColor();
        resetTexturestate();
        Gui.drawRect(0, sr.getScaledHeight() - 3, (int)calc, sr.getScaledHeight(), Color.red.getRGB());
        Gui.drawRect(0, sr.getScaledHeight() - 3, sr.getScaledWidth(), sr.getScaledHeight(), (new Color(0, 0, 0, 10)).getRGB());
    }
    
    private static void resetTexturestate() {
        (GlStateManager.textureState[GlStateManager.activeTextureUnit]).textureName = -1;
    }
}
