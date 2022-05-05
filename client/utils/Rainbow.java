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

package client.utils;

import java.awt.Color;
import net.minecraft.client.Minecraft;

public class Rainbow {
    public static Color rainbowEffect(long offset, float fade) {
        float hue = (float)(System.nanoTime() + offset) / 1.0E10F % 1.0F;
        long color = Long.parseLong(Integer.toHexString(Integer.valueOf(Color.HSBtoRGB(hue, 1.0F, 1.0F)).intValue()), 16);
        Color c = new Color((int)color);
        return new Color(c.getRed() / 255.0F * fade, c.getGreen() / 255.0F * fade, c.getBlue() / 255.0F * fade, c.getAlpha() / 255.0F);
    }
    
    public static Color rainbowEffectFast(long offset, float fade) {
        float hue = (float)(System.nanoTime() + offset) / 1.0E10F % 1.0F;
        long color = Long.parseLong(Integer.toHexString(Integer.valueOf(Color.HSBtoRGB(hue, 1.0F, 1.0F)).intValue()), 19);
        Color c = new Color((int)color);
        return new Color(c.getRed() / 255.0F * fade, c.getGreen() / 255.0F * fade, c.getBlue() / 255.0F * fade, c.getAlpha() / 255.0F);
    }
    
    public static class RainbowColor {
        public static int getColor() {
            long l = System.currentTimeMillis();
            return Color.HSBtoRGB((float)(l % 2000L) / 2000.0F, 0.8F, 0.8F);
        }
    }
    
    public static Color colorLerpv2(Color start, Color end, float ratio) {
        int red = (int)Math.abs(ratio * start.getRed() + (1.0F - ratio) * end.getRed());
        int green = (int)Math.abs(ratio * start.getGreen() + (1.0F - ratio) * end.getGreen());
        int blue = (int)Math.abs(ratio * start.getBlue() + (1.0F - ratio) * end.getBlue());
        return new Color(red, green, blue);
    }
    
    public static void drawChromaString(String string, int x, int y, boolean shadow) {
        Minecraft mc = Minecraft.getMinecraft();
        int xTmp = x;
        byte b;
        int i;
        char[] arrayOfChar;
        for (i = (arrayOfChar = string.toCharArray()).length, b = 0; b < i; ) {
            char textChar = arrayOfChar[b];
            long l = System.currentTimeMillis() - (xTmp * 10 - y * 10);
            int j = Color.HSBtoRGB((float)(l % 2000L) / 2000.0F, 0.8F, 0.8F);
            String tmp = String.valueOf(textChar);
            mc.fontRendererObj.drawString(tmp, xTmp, y, j, shadow);
            xTmp += mc.fontRendererObj.getCharWidth(textChar);
            b++;
        } 
    }
    
    public static int getChromaColor() {
        Minecraft mc = Minecraft.getMinecraft();
        int xTmp = 1;
        long l = System.currentTimeMillis() - (xTmp * 10 - 10);
        int i = Color.HSBtoRGB((float)(l % 2000L) / 2000.0F, 0.8F, 0.8F);
        return i;
    }
}
