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

import com.google.common.collect.Lists;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import net.minecraft.client.resources.IResourceManager;
import net.minecraft.util.ResourceLocation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LayeredTexture extends AbstractTexture {
    private static final Logger logger = LogManager.getLogger();
    
    public final List<String> layeredTextureNames;
    
    public LayeredTexture(String... textureNames) {
        this.layeredTextureNames = Lists.newArrayList((Object[])textureNames);
    }
    
    public void loadTexture(IResourceManager resourceManager) throws IOException {
        deleteGlTexture();
        BufferedImage bufferedimage = null;
        try {
            for (String s : this.layeredTextureNames) {
                if (s != null) {
                    InputStream inputstream = resourceManager.getResource(new ResourceLocation(s)).getInputStream();
                    BufferedImage bufferedimage1 = TextureUtil.readBufferedImage(inputstream);
                    if (bufferedimage == null)
                        bufferedimage = new BufferedImage(bufferedimage1.getWidth(), bufferedimage1.getHeight(), 2); 
                    bufferedimage.getGraphics().drawImage(bufferedimage1, 0, 0, null);
                } 
            } 
        } catch (IOException ioexception) {
            logger.error("Couldn't load layered image", ioexception);
            return;
        } 
        TextureUtil.uploadTextureImage(getGlTextureId(), bufferedimage);
    }
}
