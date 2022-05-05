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

import java.awt.image.BufferedImage;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.renderer.ImageBufferDownload;
import net.minecraft.util.ResourceLocation;

public class CapeImageBuffer extends ImageBufferDownload {
    private AbstractClientPlayer player;
    
    private ResourceLocation resourceLocation;
    
    public CapeImageBuffer(AbstractClientPlayer p_i28_1_, ResourceLocation p_i28_2_) {
        this.player = p_i28_1_;
        this.resourceLocation = p_i28_2_;
    }
    
    public BufferedImage parseUserSkin(BufferedImage image) {
        return CapeUtils.parseCape(image);
    }
    
    public void skinAvailable() {
        if (this.player != null)
            this.player.setLocationOfCape(this.resourceLocation); 
    }
    
    public void cleanup() {
        this.player = null;
    }
}
