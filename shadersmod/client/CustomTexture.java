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

package shadersmod.client;

import net.minecraft.client.renderer.texture.ITextureObject;
import net.minecraft.client.renderer.texture.TextureUtil;

public class CustomTexture implements ICustomTexture {
    private int textureUnit = -1;
    
    private String path = null;
    
    private ITextureObject texture = null;
    
    public CustomTexture(int textureUnit, String path, ITextureObject texture) {
        this.textureUnit = textureUnit;
        this.path = path;
        this.texture = texture;
    }
    
    public int getTextureUnit() {
        return this.textureUnit;
    }
    
    public String getPath() {
        return this.path;
    }
    
    public ITextureObject getTexture() {
        return this.texture;
    }
    
    public int getTextureId() {
        return this.texture.getGlTextureId();
    }
    
    public void deleteTexture() {
        TextureUtil.deleteTexture(this.texture.getGlTextureId());
    }
    
    public String toString() {
        return "textureUnit: " + this.textureUnit + ", path: " + this.path + ", glTextureId: " + this.texture.getGlTextureId();
    }
}
