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

package net.minecraft.client.resources.data;

import java.util.Collections;
import java.util.List;

public class TextureMetadataSection implements IMetadataSection {
    private final boolean textureBlur;
    
    private final boolean textureClamp;
    
    private final List<Integer> listMipmaps;
    
    public TextureMetadataSection(boolean p_i45102_1_, boolean p_i45102_2_, List<Integer> p_i45102_3_) {
        this.textureBlur = p_i45102_1_;
        this.textureClamp = p_i45102_2_;
        this.listMipmaps = p_i45102_3_;
    }
    
    public boolean getTextureBlur() {
        return this.textureBlur;
    }
    
    public boolean getTextureClamp() {
        return this.textureClamp;
    }
    
    public List<Integer> getListMipmaps() {
        return Collections.unmodifiableList(this.listMipmaps);
    }
}
