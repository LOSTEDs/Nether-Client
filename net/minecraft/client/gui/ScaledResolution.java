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

import net.minecraft.client.Minecraft;
import net.minecraft.util.MathHelper;

public class ScaledResolution {
    private final double scaledWidthD;
    
    private final double scaledHeightD;
    
    private int scaledWidth;
    
    private int scaledHeight;
    
    private int scaleFactor;
    
    public ScaledResolution(Minecraft p_i46445_1_) {
        this.scaledWidth = p_i46445_1_.displayWidth;
        this.scaledHeight = p_i46445_1_.displayHeight;
        this.scaleFactor = 1;
        boolean flag = p_i46445_1_.isUnicode();
        int i = p_i46445_1_.gameSettings.guiScale;
        if (i == 0)
            i = 1000; 
        while (this.scaleFactor < i && this.scaledWidth / (this.scaleFactor + 1) >= 320 && this.scaledHeight / (this.scaleFactor + 1) >= 240)
            this.scaleFactor++; 
        if (flag && this.scaleFactor % 2 != 0 && this.scaleFactor != 1)
            this.scaleFactor--; 
        this.scaledWidthD = this.scaledWidth / this.scaleFactor;
        this.scaledHeightD = this.scaledHeight / this.scaleFactor;
        this.scaledWidth = MathHelper.ceiling_double_int(this.scaledWidthD);
        this.scaledHeight = MathHelper.ceiling_double_int(this.scaledHeightD);
    }
    
    public int getScaledWidth() {
        return this.scaledWidth;
    }
    
    public int getScaledHeight() {
        return this.scaledHeight;
    }
    
    public double getScaledWidth_double() {
        return this.scaledWidthD;
    }
    
    public double getScaledHeight_double() {
        return this.scaledHeightD;
    }
    
    public int getScaleFactor() {
        return this.scaleFactor;
    }
}
