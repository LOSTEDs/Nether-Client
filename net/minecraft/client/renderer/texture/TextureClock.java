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

package net.minecraft.client.renderer.texture;

import net.minecraft.client.Minecraft;
import net.minecraft.src.Config;
import net.minecraft.util.MathHelper;
import shadersmod.client.ShadersTex;

public class TextureClock extends TextureAtlasSprite {
    private double field_94239_h;
    
    private double field_94240_i;
    
    private static final String __OBFID = "CL_00001070";
    
    public TextureClock(String iconName) {
        super(iconName);
    }
    
    public void updateAnimation() {
        if (!this.framesTextureData.isEmpty()) {
            Minecraft minecraft = Minecraft.getMinecraft();
            double d0 = 0.0D;
            if (minecraft.theWorld != null && Minecraft.thePlayer != null) {
                d0 = minecraft.theWorld.getCelestialAngle(1.0F);
                if (!minecraft.theWorld.provider.isSurfaceWorld())
                    d0 = Math.random(); 
            } 
            double d1;
            for (d1 = d0 - this.field_94239_h; d1 < -0.5D; d1++);
            while (d1 >= 0.5D)
                d1--; 
            d1 = MathHelper.clamp_double(d1, -1.0D, 1.0D);
            this.field_94240_i += d1 * 0.1D;
            this.field_94240_i *= 0.8D;
            this.field_94239_h += this.field_94240_i;
            int i;
            for (i = (int)((this.field_94239_h + 1.0D) * this.framesTextureData.size()) % this.framesTextureData.size(); i < 0; i = (i + this.framesTextureData.size()) % this.framesTextureData.size());
            if (i != this.frameCounter) {
                this.frameCounter = i;
                if (Config.isShaders()) {
                    ShadersTex.uploadTexSub(this.framesTextureData.get(this.frameCounter), this.width, this.height, this.originX, this.originY, false, false);
                } else {
                    TextureUtil.uploadTextureMipmap(this.framesTextureData.get(this.frameCounter), this.width, this.height, this.originX, this.originY, false, false);
                } 
            } 
        } 
    }
}
